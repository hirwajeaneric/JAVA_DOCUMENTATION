import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {
	
	ArrayList clientOutputStreams;
	/*This is a Class that will help us to connect to the clients and recieve messages
	from them.
	It contains two methods:
	1. The first one connects to the client sockets, recieves their messages using
		InputStreamReader, and the BufferedReader that converts the message 
		into plain text.
	2. The second method uses the read text from the InputStreams to print 
		some messages in the terminal and later calling the tellEveryone method 
		to send the message to everyone who is connected on the chat.
	*/
	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader =  new BufferedReader(isReader);
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		public void run(){
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("read "+message);
					tellEveryone(message);
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}	
	
	/*The main method.*/
	public static void main (String[] args) {
		new VerySimpleChatServer().go();
	}
	
	/*This method is the one that waits for a client socket to connect it.*/	
	public void go() {
		clientOutputStreams = new ArrayList();
		try{
			ServerSocket serverSock = new ServerSocket(5000);
				
			while(true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
					
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("Got a connection");
			}			
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
		
	/*This method helps us to send the message to all the clients.*/	
	public void tellEveryone(String message) {
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext()){
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
			
}
