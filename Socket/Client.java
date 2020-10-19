import java.io.DataOutputStream;
import java.util.Scanner;
import java.net.Socket;

public class Client {
  public static void main(String[] args) {
    try{
      Socket s = new Socket("localhost", 6666);
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());

      System.out.println("Enter your message:");
      Scanner messageScanner = new Scanner(System.in);

      dout.writeUTF(messageScanner.nextLine());
      dout.flush();

      messageScanner.close();
      dout.close();
      s.close();
    } catch(Exception e) { System.out.println(e); }
  }
}
