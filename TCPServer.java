package tcpchat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class TCPServer {
	private static final int PORT = 3000;

	private static String intSort(String msg) {
		String returnString = "";
		String[] inputs = msg.split(" ");
		ArrayList numbers = new ArrayList();
		for (int i =1; i< inputs.length; i++) {
			try {
				numbers.add(Integer.parseInt(inputs[i]));
			} catch (NumberFormatException ex) {
				return "Invalid sort:  values must be numeric";
			} // use try catch to throw error when input isn't numeric
		} //end for loop to confirm sort values are numeric
		Collections.sort(numbers);
		returnString = numbers.toString();
		return returnString;
	}
	
	public static void main(String args[]) {
		System.out.println("server running...");

		try {
			ServerSocket serverSocket = new ServerSocket(TCPServer.PORT);
			serverSocket.setSoTimeout(10000);
			while (true) {
				// accept client connection, add to list of clients
				Socket messageSocket = serverSocket.accept();
				//clients.add(messageSocket);
				
				// read incoming line
				BufferedReader msgReader = new BufferedReader(new InputStreamReader(messageSocket.getInputStream()));
				String msgText = msgReader.readLine();
				String responseText = new String();
				String command = msgText.substring(0, 4);

				if (command.equalsIgnoreCase("sort")) {
					responseText = TCPServer.intSort(msgText);
				} else {
					responseText = msgText.toLowerCase();
				}

				//local output to confirm
				System.out.println("Blast from the past: " + msgText);
				System.out.println(responseText);

				// send back to client(s)
				DataOutputStream dos = new DataOutputStream(messageSocket.getOutputStream());
				dos.writeBytes(responseText + "\n");

			} // end while  
		} catch (InterruptedIOException iioe) {
			System.out.println("I am aweary");
		} catch (IOException e) {
			System.out.println(e);
		} // end try
	} // end main
} // end class