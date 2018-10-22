import java.net.*;
import java.io.*;
 
/**
 * Simple server that waits for someone to connect on port 4242,
 * and then repeatedly asks for their name and greets them.
 * Connect either by "telnet localhost 4242" or by running HelloClient.java
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author Travis Peters, Dartmouth CS 10, Winter 2015 (updated to use MyIPAddressHelper)
 */

public class HelloServer {

    public static void main(String[] args) throws IOException {
        // Display your IP address so that you can tell others and they can connect to your server.
        System.out.println("HelloServer's IP Address INSIDE of the current network : " + MyIPAddressHelper.getMyLocalIP());
        System.out.println("HelloServer's IP Address OUTSIDE of the current network: " + MyIPAddressHelper.getMyGlobalIP());
        
	    // Listen on a server socket for a connection
		System.out.println("HelloServer: waiting for someone to connect...");
		ServerSocket listen = new ServerSocket(4242);
		
		// When someone connects, create a specific socket for them
		Socket sock = listen.accept();
		System.out.println("someone connected");

		// Now talk with them
		PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		out.println("who is it?");
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println("received:" + line);
			out.println("hi " + line + "!  anybody else there?");
		}
		System.out.println("client hung up");

		// Clean up shop
		out.close();
		in.close();
		sock.close();
		listen.close();
	}
}
