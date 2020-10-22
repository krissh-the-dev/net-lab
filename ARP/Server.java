import java.io.*;
import java.net.*;
import java.util.*;

class Server {
  public static void main(String args[]) {
    try {
      ServerSocket socket = new ServerSocket(5604);
      System.out.println("ARP Server is up.");
      Socket client = socket.accept();

      while(true) {
        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());

        String str = din.readLine();
        String ip[] = {"165.165.80.80", "165.165.79.1"};
        String mac[] = {"6A:08:AA:C2", "8A:BC:E3:FA"};

        for(int i = 0; i < ip.length; i++) {
          if(str.equals(ip[i])) {
            dout.writeBytes(mac[i] + '\n');
            break;
          }
        }
        socket.close();
      }
    } catch(NullPointerException npe) {
      System.out.println("Shutting down server...");
    } catch(Exception e) {
      System.err.println(e);
    }
  }
}
