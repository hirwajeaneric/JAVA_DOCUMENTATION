package Client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient {
               /**
                * 
                * @author hirwa
                */

	public String user;

	JLabel nameoftheuser;
              JLabel imageLabel;
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
		
                    JFrame frame = new JFrame("File Sharing Chat Program APP");
                    JPanel mainPanel = new JPanel();

                    nameoftheuser = new JLabel("Name:    ");
                    username = new JTextField(12);
                    JButton joinButton = new JButton("JOIN");
                    joinButton.addActionListener(new JoinButtonListener());
                    JButton leaveButton = new JButton("EXIT");
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
                    outgoing = new JTextField(20);
                    
                     JButton browseButton = new JButton("Browse");
                     browseButton.addActionListener(new BrowseButtonListener());
                     mainPanel.add(browseButton);

                     imageLabel = new JLabel();
                     mainPanel.add(imageLabel);
                     imageLabel.setSize(50, 50);

                     JButton sendButton = new JButton("Send");
                     sendButton.addActionListener(new SendButtonListener());
                     mainPanel.add(qScroller);
                     mainPanel.add(outgoing);
                     mainPanel.add(sendButton);

                     JButton sendPictureButton = new JButton("SendPicture");
                     sendButton.addActionListener(new SendButtonListener());
                     mainPanel.add(sendPictureButton);
                     
                     setUpNetworking();

                     Thread readerThread = new Thread(new IncomingReader());	
                     readerThread.start();

                     frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
                     frame.setSize(500, 500);
                     frame.setVisible(true);		
				
	}
	
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
	
	public class JoinButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			
                                   nameoftheuser.setText("Username: "+username.getText());
                                   user = username.getText();
                                   username.setText("");
                                   incoming.setText(" Welcome "+user+"! \n");
			
                                   try {
                                          writer.println(user+" IS CONNECTED.");
                                          writer.flush();
                                   } catch (Exception ex){
                                           ex.printStackTrace();
                                   }
		} 
	}
        
               public class SendPictureButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
                                    try {
                                                writer.println(user+" : "+outgoing.getText());
                                                writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
                                   
		} 
	}
        
              String path = null;
              File photo = null;
              public class BrowseButtonListener implements ActionListener {
                    public void actionPerformed(ActionEvent ev) {
                            JFileChooser file = new JFileChooser();
                            if(file.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
                            File photo = file.getSelectedFile();
                            //path = photo.getAbsolutePath();
                            
                            }
                    }
              }
	
	public void notifyingDisconnection() {
		String leaving = (user+ " LEFT THE CHAT!");
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
			
			Frame frame = new JFrame("Exit");
                        if(JOptionPane.showConfirmDialog(frame, "Do you want to leave the chat?","Chat Application.",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
                            System.exit(0);
                        }
			
		} catch (Exception ex) {
			incoming.append("Failed to disconnect.");
		}
	}
	
	public class LeaveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			
			notifyingDisconnection();
			Disconnect();		
		}
	}
	
	
	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev){
			try {
                                                writer.println(user+" : "+outgoing.getText());
                                                writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
	
	
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
