import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.IOException;

public class FileClient {
	public static void main (String [] args ) throws IOException {
    int filesize=1022386;
    int bytesRead;
    int currentTot = 0;

    Socket socket = new Socket("127.0.0.1", 6666);
    byte[] bytearray  = new byte[filesize];
    InputStream is = socket.getInputStream();
    FileOutputStream fos = new FileOutputStream("copy.txt");
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    bytesRead = is.read(bytearray,0,bytearray.length);
    currentTot = bytesRead;

    do {
      bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot));
      if(bytesRead >= 0) currentTot += bytesRead;
    } while(bytesRead > -1);
    System.out.println("File received.");

    bos.write(bytearray, 0 , currentTot);
    bos.flush();
    bos.close();
    socket.close();
  }
}
