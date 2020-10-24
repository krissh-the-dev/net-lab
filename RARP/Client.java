import java.io.*;
import java.net.*;
import java.util.*;

class Client {
  public static void main(String args[]) {
    try {
      DatagramSocket client = new DatagramSocket();
      InetAddress addr = InetAddress.getByName("127.0.0.1");
      byte[] sendbyte = new byte[1024];
      byte[] receivebyte = new byte[1024];

      Scanner in = new Scanner(System.in);
      System.out.print("Enter the Physical address (MAC): ");
      String str = in.nextLine();

      sendbyte = str.getBytes();
      DatagramPacket senderPacket = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
      client.send(senderPacket);

      DatagramPacket receiverPacket = new DatagramPacket(receivebyte, receivebyte.length);
      client.receive(receiverPacket);
      String s = new String(receiverPacket.getData());
      System.out.println("The Logical Address is(IP): " + s.trim());

      in.close();
      client.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
