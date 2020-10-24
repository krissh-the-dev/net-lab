import java.io.*;
import java.net.*;
import java.util.*;

class Server {
  public static void main(String args[]) {
    try {
      DatagramSocket server = new DatagramSocket(1309);
      while (true) {
        byte[] sendbyte = new byte[1024];
        byte[] receivebyte = new byte[1024];

        DatagramPacket receiverPacket = new DatagramPacket(receivebyte, receivebyte.length);
        server.receive(receiverPacket);
        String str = new String(receiverPacket.getData());
        String s = str.trim();

        InetAddress addr = receiverPacket.getAddress();
        int port = receiverPacket.getPort();
        String ip[] = { "165.165.80.80", "165.165.79.1" };
        String mac[] = { "6A:08:AA:C2", "8A:BC:E3:FA" };

        for (int i = 0; i < ip.length; i++) {
          if (s.equals(mac[i])) {
            sendbyte = ip[i].getBytes();
            DatagramPacket senderPacket = new DatagramPacket(sendbyte, sendbyte.length, addr, port);
            server.send(senderPacket);
            System.out.println("Shutting server gracefully...");
            break;
          }
        }
        break;
      }
      server.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
