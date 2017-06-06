package udpchat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private static final int PORT = 3000;

    public static void main(String args[]) throws Exception {
        System.out.println("server running...");
        DatagramSocket serverSocket = new DatagramSocket(UDPServer.PORT);
        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            
            byte[] sendData = sentence.toUpperCase().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, 
                    sendData.length, 
                    receivePacket.getAddress(),   // ip address 
                    receivePacket.getPort()       // port
            );
            serverSocket.send(sendPacket);
        }
    }
}