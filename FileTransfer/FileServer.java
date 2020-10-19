import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.IOException;

public class FileServer {
	public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(6666);
    Socket socket = serverSocket.accept();

    System.out.println("Connection established. \nClient: \n\t" + socket);

    File transferFile = new File("document.txt");
    byte[] bytearray  = new byte[(int)transferFile.length()];
    FileInputStream fin = new FileInputStream(transferFile);
    BufferedInputStream bin = new BufferedInputStream(fin);
    bin.read(bytearray,0,bytearray.length);
    OutputStream os = socket.getOutputStream();
    System.out.println("Sending Files...");

    os.write(bytearray,0,bytearray.length);
    os.flush();
    socket.close();
    System.out.println("File transfer complete.");
  }
}
