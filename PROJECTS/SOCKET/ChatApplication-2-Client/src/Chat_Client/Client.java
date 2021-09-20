/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat_Client;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author hirwa
 */
public class Client extends javax.swing.JFrame {

    String username;
    String address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    /**
     * PrintWriter enables you to write formatted data to an underlying writer. 
     * For instance, writing int, long and other primitive data formatted as 
     * text,rather than as their byte values. 
     * It is also important when you have to mix text and numbers.
     */
    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
    }
    
    /*This method listens and waits for incoming calls*/
    public void ListenThread(){
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }
    
    /*This is the method that adds users*/
    public void userAdd(String data){
        users.add(data);
    }
    
    public void writeUsers(){
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token:tempList){
            //users.append(token+ "\n")
        }
    }
    
    /*This method will be notify us that a user named x has left.*/
    public void userRemove(String data){
        chatTextArea.append(data+ "has left.\n");
    }
    
    public void sendDisconnect(){
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();//This means clearing the stream of any element that might still be there.
        } catch (Exception e) {
            chatTextArea.append("Could not send Disconnect message. \n");
        }
    }
    
    /*This method will be writing the chat area that one is disconnected. And close the socket connection.*/
    public void Disconnect(){
        try {
            chatTextArea.append("Disconnected.\n");
            sock.close();
        } catch (Exception e) {
            chatTextArea.append("Failed to disconnect. \n");
        }
        isConnected = false;
        clientNameField.setEditable(true);
    }
    
    public class IncomingReader implements Runnable {
        @Override
        public void run(){
            String[] data;
            String stream; 
            String done = "Done";
            String connect = "Connect";
            String disconnect = "Disconnect"; 
            String chat = "Chat"; 
            String privatemsg = "private";
            
            try {
                while ((stream = reader.readLine()) != null){
                    data = stream.split(":");
                    
                    if (data[2].equals(chat)){
                        chatTextArea.append(data[0]+ ":"+data[1]+ "\n");
                        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
                    }else if (data[2].equals(connect)){
                        chatTextArea.removeAll();
                        userAdd(data[0]);
                    }else if (data[2].equals(disconnect)){
                        userRemove(data[0]);
                    }else if (data[2].equals(done)) {
                        writeUsers();
                        users.clear();
                    }else if (data[2].equals(privatemsg)){
                        chatTextArea.append("Private Message from "+data[0]+" : "+data[1]+"\n");
                        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
                    }else if (data[2].equals("request")){
                        chatTextArea.append("Server replied "+"\n"+data[1]+"\n");
                        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    
    public void send_it(){
        
        String nothing = "";
        String mydata = chatTextField.getText();
        Pattern pattern = Pattern.compile("(@).*\\1");
        Matcher matcher = pattern.matcher(mydata);
        
        if ((chatTextField.getText()).equals(nothing)){
            chatTextField.setText("");
            chatTextField.requestFocus();
        }else if (matcher.find()){
            String[] data = mydata.split("@");
            try {
                writer.println(username + ":"+data[2]+":"+"private"+":"+data[1]);
                /*data[2] is the txtmsg.. data[1] is the reciever of the private msg name*/
                writer.flush();
                chatTextArea.append("You have sent { "+data[2]+" } as a private message to : "+"'"+data[1]+"'"+"\n");
            } catch (Exception e) {
                chatTextArea.append("Message was not sent. \n"+"No online user fount by that name");
            }
        }else {
            try {
                writer.println(username + ":"+chatTextField.getText()+":"+"Chat");
                writer.flush();
            } catch (Exception e) {
                chatTextArea.append("Message was not sent. \n");
            }
            chatTextField.setText("");
            chatTextField.requestFocus();
        }
        chatTextField.setText("");
        chatTextField.requestFocus();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        chatTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        userConnectButton = new javax.swing.JButton();
        OnlineUsers = new javax.swing.JButton();
        InboxButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        clientNameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Group Chat");

        jPanel1.setBackground(new java.awt.Color(253, 205, 96));
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(163, 212, 89));

        chatTextArea.setColumns(20);
        chatTextArea.setRows(5);
        jScrollPane1.setViewportView(chatTextArea);

        chatTextField.setText("Type text here....");
        chatTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chatTextFieldKeyPressed(evt);
            }
        });

        sendButton.setBackground(new java.awt.Color(2, 34, 61));
        sendButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        sendButton.setForeground(new java.awt.Color(254, 254, 254));
        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        userConnectButton.setBackground(new java.awt.Color(41, 147, 239));
        userConnectButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        userConnectButton.setForeground(new java.awt.Color(254, 254, 254));
        userConnectButton.setText("Connect");
        userConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userConnectButtonActionPerformed(evt);
            }
        });

        OnlineUsers.setBackground(new java.awt.Color(41, 147, 239));
        OnlineUsers.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        OnlineUsers.setForeground(new java.awt.Color(254, 254, 254));
        OnlineUsers.setText("All Users");
        OnlineUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnlineUsersActionPerformed(evt);
            }
        });

        InboxButton.setBackground(new java.awt.Color(41, 147, 239));
        InboxButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        InboxButton.setForeground(new java.awt.Color(254, 254, 254));
        InboxButton.setText("Inbox Mode");
        InboxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InboxButtonActionPerformed(evt);
            }
        });

        disconnectButton.setBackground(new java.awt.Color(41, 147, 239));
        disconnectButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        disconnectButton.setForeground(new java.awt.Color(254, 254, 254));
        disconnectButton.setText("Leave");
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(userConnectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InboxButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OnlineUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(InboxButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OnlineUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userConnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(disconnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chatTextField)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Username");

        clientNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientNameFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(167, 167, 167))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(clientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientNameFieldActionPerformed

    private void userConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userConnectButtonActionPerformed
        // TODO add your handling code here:
        if(isConnected == false){
            
            username = clientNameField.getText();
            clientNameField.setEditable(false);
            
            try {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } catch (Exception e) {
                chatTextArea.append("Cannot Connect! Try Again. \n");
                clientNameField.setEditable(true);
            }
            ListenThread();
        }else if (isConnected == true){
            chatTextArea.append("You are already connected. \n");
        }
    }//GEN-LAST:event_userConnectButtonActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        // TODO add your handling code here:
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_disconnectButtonActionPerformed

    private void OnlineUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnlineUsersActionPerformed
        // TODO add your handling code here:
        chatTextArea.append("\n Online users: \n");
        try {
            writer.println(username + ":"+"Request to know who is online "+":"+"request"+":"+username);
            writer.flush();
        } catch (Exception e) {
            chatTextArea.append("Message was not sent. \n");
        }
    }//GEN-LAST:event_OnlineUsersActionPerformed

    private void InboxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InboxButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "To send a private message to a particular person, write his name between double @ eg. @Name@ in the beginning of the message", "How to send a private message", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_InboxButtonActionPerformed

    private void chatTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatTextFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            send_it();
            System.out.print(evt.getKeyChar());
        }
    }//GEN-LAST:event_chatTextFieldKeyPressed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
        send_it();
        String nothing = "";
        if ((chatTextField.getText()).equals(nothing)){
            chatTextField.setText("");
            chatTextField.requestFocus();
        }else {
            try {
                writer.println(username + ":" + chatTextField.getText() + ":"+"Chat");
                writer.flush();
            } catch (Exception e) {
                chatTextArea.append("Message was not sent. \n");
            }
            chatTextField.setText("");
            chatTextField.requestFocus();
        }
        chatTextField.setText("");
        chatTextField.requestFocus();
    }//GEN-LAST:event_sendButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InboxButton;
    private javax.swing.JButton OnlineUsers;
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JTextField chatTextField;
    private javax.swing.JTextField clientNameField;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton userConnectButton;
    // End of variables declaration//GEN-END:variables
}
