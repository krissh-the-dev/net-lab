import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;

public class SocketHTTPClient {
  public static void main(String[] args) {
    String hostName = "www.google.com";
    int portNumber = 80;
    Boolean startDownload = false;

    try {
      Socket socket = new Socket(hostName, portNumber);
      PrintWriter socketOutStream = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedWriter writer = new BufferedWriter(new FileWriter("download.html"));

      socketOutStream.println("GET / HTTP/1.1\nHost: www.google.com\n\n");
      String line;
      while ((line = in.readLine()) != null) {
        if (line.contains("<!")) {
          System.out.println("Starting download...");
          startDownload = true;
        }
        if (startDownload) {
          writer.write(line);
        } else {
          System.out.println(line);
        }
      }

      writer.close();
      socket.close();
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
      System.exit(-1);
    }
  }
}
