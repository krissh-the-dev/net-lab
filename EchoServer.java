import java.io.*;
import java.net.*;

public class EchoServer {
	public EchoServer(int portnum) {
		try {
			server = new ServerSocket(portnum);
		}
		catch (Exception err)		{
			System.out.println(err);
		}
	}

	public void serve() {
		try {
			while (true) {
				Socket client = server.accept();
				BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter w = new PrintWriter(client.getOutputStream(), true);
				w.println("Welcome to the Java EchoServer. Type 'bye' to close.");
				String line;
				do {
					line = r.readLine();
					if (line != null){
            w.println("echo: "+ line);
            System.out.println("Forwarding: " + line);
          }
				}
				while ( !line.trim().equals("bye") );
				client.close();
			}
		}
		catch (Exception err) {
			System.err.println(err);
		}
	}

	public static void main(String[] args) {
		EchoServer s = new EchoServer(9999);
		s.serve();
	}

	private ServerSocket server;
}
