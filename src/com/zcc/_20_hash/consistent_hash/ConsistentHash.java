package com.zcc._20_hash.consistent_hash;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Zcc
 * created on 22/10/27 20:19
 */
public class ConsistentHash {

    private final long max_hash_value = Double.valueOf(Math.pow(2, 32)).longValue();
    private final ServerNode headNode = new ServerNode(-1L, "头节点");
    private final ServerNode endNode = new ServerNode(max_hash_value, "尾节点");


    public ConsistentHash addServerNode(String serviceAddr, int virtualCount) {
        int minVirtualNodeCount = 4;
        if (virtualCount < minVirtualNodeCount) virtualCount = minVirtualNodeCount;
        for (int i = 0; i < virtualCount / 4; i++) {
            //每次md5生成4个副本位置，md5有128bit长,这里参考了dubbo
            //org.apache.dubbo.rpc.cluster.loadbalance.ConsistentHashLoadBalance
            byte[] digest = getMD5(serviceAddr + i);
            for (int h = 0; h < 4; h++) {
                long hash = hash(digest, h);
                ServerNode serverNode = new ServerNode(serviceAddr, serviceAddr + "#" + (i * 4 + h), hash);
                if (headNode.next == null) {
                    //add the first node
                    headNode.next = serverNode;
                    serverNode.pre = headNode;
                    serverNode.next = endNode;
                    endNode.pre = serverNode;

                } else {
                    ServerNode temp = headNode;
                    while (temp.next != null) {
                        if (temp.next.getHash() > serverNode.getHash()) {
                            break;
                        }
                        temp = temp.next;
                    }
                    serverNode.next = temp.next;
                    serverNode.pre = temp;
                    temp.next.pre = serverNode;
                    temp.next = serverNode;
                }
            }
        }
        return this;
    }


    public String getShowCallServer(String clientIp) {

        if (headNode.next == null || headNode.next.equals(endNode)) return "no server!";
        long clientIpHash = hash(getMD5(clientIp), 2);
        if (clientIpHash < max_hash_value / 2) {
            ServerNode temp = headNode;
            while (!endNode.equals(temp.next)) {
                if (temp.next.getHash() >= clientIpHash) {
                    break;
                }
                temp = temp.next;
            }
            ServerNode serverNode;
            if (endNode.equals(temp.next)) {
                serverNode = headNode.next;
            } else {
                serverNode = temp.next;
            }
            if (!serverNode.getClientIPSet().contains(clientIp)) {
                serverNode.addClientIP(clientIp);
            }
            return serverNode.getVirtualAddr();
        } else {
            ServerNode temp = endNode;
            while (!headNode.equals(temp.pre)) {
                if (temp.pre.getHash() < clientIpHash) {
                    break;
                }
                temp = temp.pre;
            }
            ServerNode serverNode;
            if(temp.equals(endNode)){
                serverNode=headNode.next;
            }else {
                serverNode=temp;
            }
            if (!serverNode.getClientIPSet().contains(clientIp)) {
                serverNode.addClientIP(clientIp);
            }
            return serverNode.getVirtualAddr();
        }
    }

    public ConsistentHash removeServerNode(String serverIp) {
        ServerNode temp = headNode;
        while (temp.next != null && !temp.next.equals(endNode)) {
            if (temp.next.getAddr().equals(serverIp)) {
                ServerNode node = temp.next;
                temp.next = node.next;
                node.next.pre = temp;
            } else {
                temp = temp.next;
            }
        }
        return this;
    }

    public void showServerNode() {
        ServerNode temp = headNode;
        int index = 0;
        while (temp.next != null && temp.next != endNode) {
            ServerNode node = temp.next;
            String sb = ++index + ":【" + "[" + node.getAddr() + "]" + "-" +
                    "[" + node.getVirtualAddr() + "]" + "-" +
                    "[" + node.getHash() + "]" + "-" +
                    "[" + node.getHexHash() + "]" + "】";
            System.out.println(sb);
            temp = temp.next;
        }
    }


    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }


    private byte[] getMD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return md.digest(str.getBytes());
    }
}
