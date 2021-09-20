package Client;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient {

	public String user;

	JLabel nameoftheuser;
	JTextField username;
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}	
	
	public void go(){
		
		JFrame frame = new JFrame("Chat Application - Group II");
		JPanel mainPanel = new JPanel();
		
		nameoftheuser = new JLabel("Username:    ");
		username = new JTextField(12);
		JButton joinButton = new JButton("Join Chat");
		joinButton.addActionListener(new JoinButtonListener());
		JButton leaveButton = new JButton("Leave");
		leaveButton.addActionListener(new LeaveButtonListener());
		incoming = new JTextArea(15,40);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(nameoftheuser);
		mainPanel.add(username);
		mainPanel.add(joinButton);
		mainPanel.add(leaveButton);
		outgoing = new JTextField(30);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		setUpNetworking();
		
		/*We are starting a new thread using a new inner class as the runnable(job) 
		for the thread. The thread's job is to read from the server's socket stream, 
		displaying any incoming message in the scrolling text area.*/
		Thread readerThread = new Thread(new IncomingReader());	
		readerThread.start();

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 350);
		frame.setVisible(true);		
				
	}
	
	/*The method to set up the network between the client and the server.*/
	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Network established");
			
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	/*What Happens when I click on the join button*/
	public class JoinButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			
			nameoftheuser.setText("Username: "+username.getText());
			user = username.getText();
			username.setText("");
			incoming.setText("************************* Welcome "+user+"! *************************\n");
			
			try {
				writer.println(user+" is connected.");
				writer.flush();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		} 
	}
	
	public void notifyingDisconnection() {
		String leaving = (user+ " left the chat!");
		try {
			writer.println(leaving);
			writer.flush();
			
		} catch (Exception ex){
			incoming.append("Could not send the disconnection message.");
		} 
	}
	
	public void Disconnect() {
		try {
			incoming.append("You are now disconnected.");
                       
                       Thread.sleep(5000);
			sock.close();
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			streamReader.close();
			
			Frame frame = new JFrame("Exit");
                        if(JOptionPane.showConfirmDialog(frame, "Do you want to leave the chat?","Chat Application.",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
                            System.exit(0);
                        }
			
		} catch (Exception ex) {
			incoming.append("Failed to disconnect.");
		}
	}
	
	/*What happens when I click on the leave button. */
	public class LeaveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			
			notifyingDisconnection();
			Disconnect();		
		}
	}
	
	
	/*What happens when I click on the send button*/
	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev){
			try {
				/*Nothing new here. When the user clicks the send button, 
				this method sends the contents of the textfield to the server.*/
				writer.println(user+" : "+outgoing.getText());
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
	
	/*This is what the thread does!! 
	In the run() method. it stays in a loop (as long as what it gets from 
	the server is not null), reading a line at a time and adding each line 
	to the scrolling text area (along with a new line character).
	*/	
	public class IncomingReader implements Runnable {
		public void run(){
			String message;
			try {
				while((message = reader.readLine()) != null) {
					System.out.println("read " +message);
					incoming.append(message+ "\n\n");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
