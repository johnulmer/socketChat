package tcpchat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
	
	private static final int PORT = 3000;
	
	public static void main(String args[]) throws Exception {
	    System.out.println("client running...");
	    String msg;
	    String modMsg;
	    while (true) {
		    BufferedReader userMsg = new BufferedReader(new InputStreamReader(System.in));
		    Socket clientSocket = new Socket("localhost", TCPClient.PORT);
		    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		    BufferedReader responseMsg = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    msg = userMsg.readLine();
		    outToServer.writeBytes(msg + '\n');
		    modMsg = responseMsg.readLine();
		    System.out.println("from server: " + modMsg);
		    clientSocket.close();
	    }
	}   
}