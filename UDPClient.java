package udpchat;

import java.io.*;
import java.net.*;

public class UDPClient {

    private static final int PORT = 3000;

    public static void main(String args[]) throws Exception {
        System.out.println("client running...");
        
        // create socket
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        while (true) {
            // get user input from console
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in));
            String sentence = stdInput.readLine();
            
            if (sentence.equals("quit")) {
                break;
            }

            // send data
            byte[] sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, UDPClient.PORT);
            clientSocket.send(sendPacket);

            // receive data
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);            
        }
        clientSocket.close();
    }
}