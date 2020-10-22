import java.io.*;
import java.net.*;
import java.util.*;

class Client {
  public static void main(String args[]) {
    try {
      Socket socket = new Socket("127.0.0.1", 5604);

      Scanner in = new Scanner(System.in);
      DataInputStream din = new DataInputStream(socket.getInputStream());
      DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

      System.out.print("Enter the Logical address(IP): ");
      String str1 = in.nextLine();
      dout.writeBytes(str1 + '\n');
      String str = din.readLine();
      System.out.println("The Physical Address is: " + str);

      socket.close();
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}
