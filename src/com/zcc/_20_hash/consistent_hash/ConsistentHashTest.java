package com.zcc._20_hash.consistent_hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Zcc
 * created on 22/10/27 21:37
 */
public class ConsistentHashTest {
    public static void main(String[] args) {
        ConsistentHash hash = new ConsistentHash();
        String addr1 = generateServerAddr();
        String addr2 = generateServerAddr();
        String clientIp = generateIp();
        hash.addServerNode(addr1, 1)
                .addServerNode(addr2, 1)
                .showServerNode();
        String showCallServer = hash.getShowCallServer(clientIp);
        System.out.println("### show Call Server Addr: " + showCallServer);


        System.out.println("## remove");
        hash.removeServerNode(showCallServer.split("#")[0])
                .showServerNode();
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));


        System.out.println("## remove");
        hash.removeServerNode(addr2)
                .showServerNode();
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));


        System.out.println("## re add");
        hash.addServerNode(generateServerAddr(), 1)
                .showServerNode();
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));
        System.out.println("### show Call Server Addr: " + hash.getShowCallServer(clientIp));
    }

    public static String generateServerAddr() {
        return generateIp() + ":" + (new Random().nextInt(19000) + 1000);
    }

    public static String generateIp() {
        List<String> ip = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            ip.add(String.valueOf(random.nextInt(255) + 1));
        }
        return String.join(".", ip);
    }
}
