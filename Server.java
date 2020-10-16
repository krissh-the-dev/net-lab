import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args){
    try{
      ServerSocket ss = new ServerSocket(6666);
      Socket s = ss.accept();

      DataInputStream dis = new DataInputStream(s.getInputStream());

      String str = (String)dis.readUTF();
      System.out.println("Message: " + str);

      ss.close();
    } catch(Exception e) { System.err.println(e); }
  }
}
