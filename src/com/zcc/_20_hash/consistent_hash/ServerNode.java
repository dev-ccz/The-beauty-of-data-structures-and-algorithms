package com.zcc._20_hash.consistent_hash;

import java.util.HashSet;

/**
 * @author Zcc
 * created on 22/10/27 20:43
 */
public class ServerNode {
    private String addr;
    private String virtualAddr; // 198.192.3.1:7962#0,198.192.3.1:7962#1,198.192.3.1:7962#2....
    private HashSet<String> clientIPSet;
    private Long hash;
    private String hexHash;
    public ServerNode pre;
    public ServerNode next;
    private String desc;

    public ServerNode() {
    }

    public ServerNode(String addr, String virtualAddr, Long hash) {
        this.addr = addr;
        this.virtualAddr = virtualAddr;
        this.hash = hash;
        this.hexHash=Long.toHexString(hash);
        this.clientIPSet = new HashSet<>();

    }

    public ServerNode(Long hash, String desc) {
        this.hash = hash;
        this.hexHash=Long.toHexString(hash);
        this.desc = desc;
    }

    public ServerNode addClientIP(String clientIP) {
        this.clientIPSet.add(clientIP);
        return this;
    }


    public String getAddr() {
        return addr;
    }

    public String getVirtualAddr() {
        return virtualAddr;
    }

    public HashSet<String> getClientIPSet() {
        return clientIPSet;
    }

    public Long getHash() {
        return hash;
    }

    public String getHexHash() {
        return hexHash;
    }
}
