import java.io.*;
import java.net.*;
import javax.swing;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClientA {
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;
	
	public void go (){
		//Make a gui and register a listener with the send button
		//Call the setUpNetworking() method
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel mainPanel = new JPanel();
		
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
		setUpNetworking();
		
		frame.setSize(400, 500);
		frame.setVisible(true);
	}
	
	private void setUpNetworking(){
		//make a Socket, then make a PrintWriter
		//assign the printWriter to writer instance variable
		try {
			sock = new Sock("localhost", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Network established");
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			//get the text from the textfield and 
			//send it to the server using the writer (a PrintWriter)
			try {
				writer.println(outgoing.getText());
				writer.flush();				
				
			}catch(IOException ex){
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
	public static void main (String[] args){
		new SimpleChatClientA().go();
	}
}
