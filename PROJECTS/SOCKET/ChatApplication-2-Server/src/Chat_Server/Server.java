package Chat_Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hirwa
 */
public class Server extends javax.swing.JFrame {

    ArrayList clientOutPutStreams;
    ArrayList<String> users;
    
    /**
     * Creates new form Server
     */
    
    public Server() {
        initComponents();
    }
    
    /*This is another class created inside the main SERVER.JAVA class.*/
    public class ClientHandler implements Runnable {
        
        BufferedReader reader;
        Socket sock;
        PrintWriter client;
        
        public ClientHandler (Socket clientSocket, PrintWriter user){
            client = user;
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);   
            } catch (Exception e) {
                serverTextArea.append("Unexpected error...\n");
            }    
        }   
        
        @Override
        public void run(){
            /*Bellow are variable that we shall be using for specific activities like Connecting, Disconnecting, Chat,....*/
            String message;
            String connect = "Connect"; 
            String disconnect = "Disconnect";
            String chat = "Chat";
            String privatemsg = "private";
            
            String[] data;
            /*This one is an array of different activities that can be called.*/

            try {
                while ((message = reader.readLine())!= null) {
                    serverTextArea.append("Received: "+message+"\n");
                    data = message.split(":");
                    
                    for (String token: data){
                        serverTextArea.append(token+ "\n");
                    }
                    
                    if(data[2].equals(connect)){
                        tellEveryone((data[0]+ ":"+data[1]+":"+chat));
                        userAdd(data[0]);
                    }else if (data[2].equals(disconnect)){
                        tellEveryone((data[0]+ ":has disconnected."+":"+chat));
                        userRemove(data[0]);
                    }else if (data[2].equals(chat)){
                        tellEveryone(message);
                    }else if (data[2].equals(privatemsg)){
                        
                        int recievedID = getid(data[3]);
                    
                        if (recievedID != -1) {
                            tellthispersononly(message, recievedID, data[3]);
                        }else {
                            tellthispersononly (message, recievedID, data[0]);
                        }
                            
                    }else if (data[2].equals("request")){
                        int recievedID;
                        StringBuilder stringBuilder = new StringBuilder();
                        for(String current_user: users){
                            recievedID = getid(current_user);
                            System.out.println(recievedID);
                            stringBuilder.append(current_user).append(", With ID = ").append(recievedID);
                            stringBuilder.append(".     ");
                        }
                        recievedID = getid(data[0]);
                        String finalString = stringBuilder.toString();
                        finalString = data[0]+ ":"+finalString+":"+"request";
                        tellthispersononly(finalString, recievedID, data[0]);
                        /*data[0] here is the reciever person who is the in this case the sender himself.*/
                    }else {
                        serverTextArea.append("No Conditions were met. \n");
                    }
                }
            } catch (Exception e) {
                serverTextArea.append("Lost a connection. \n");
                e.printStackTrace();
                clientOutPutStreams.remove(client);
            }
        }
    }
    
    public class ServerStart implements Runnable {
        
        @Override
        public void run(){
            clientOutPutStreams = new ArrayList();
            users = new ArrayList();
            
            try {
                ServerSocket serverSock = new ServerSocket(2222);
                 
                while (true){
                    Socket clientSock =  serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutPutStreams.add(writer);
                    
                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                    serverTextArea.append("Got a connection. \n");
                }
            } catch (Exception e) {
                serverTextArea.append("Error making a connection. \n");
            }
        }
    }
    
    
    public void userAdd(String data){
        String message; 
        String add=": :Connect";
        String done = "Server: :Done";
        String name = data;
        
        serverTextArea.append("Before "+name+" added. \n");
        users.add(name);
        serverTextArea.append("After "+ name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        
        for (String token: tempList){
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data){
        String message;
        String add = ": :Connect";
        String done = "Server: :Done";
        String name = data;
        
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        
        for(String token: tempList){
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) {
        Iterator it = clientOutPutStreams.iterator();
        
        while (it.hasNext()){
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                serverTextArea.append("Sending: "+message+"\n");
                writer.flush();
                serverTextArea.setCaretPosition(serverTextArea.getDocument().getLength());
            } catch (Exception e) {
                serverTextArea.append("Error telling everyone. \n");
            }
        }
    }
    
    public int getid (String data){
        int userid = users.indexOf(data);
        return userid;
    }
    
    public void tellthispersononly(String msg, int personid, String recievername){
        
        if (personid == -1){//If there is not user id. This is the first case.
            msg = "The server ... : User not found your message was delivered :private";
            personid = getid(recievername);
            
            try {
                PrintWriter writer = (PrintWriter) clientOutPutStreams.get(personid);
                writer.println(msg);
                writer.flush();
                serverTextArea.append("Sending to {"+recievername+"} only this msg : Message not sent because the User was not found in online users\n");
                serverTextArea.setCaretPosition(serverTextArea.getDocument().getLength());
                
            } catch (Exception e) {
                serverTextArea.append("Error in telling this to "+recievername+ "."+"\n");
            }
        } else {//This is the second case. If the person id not null.
            if (clientOutPutStreams.get(personid)!=null){
                try {
                    PrintWriter writer = (PrintWriter) clientOutPutStreams.get(personid);
                    writer.println(msg);
                    writer.flush();
                    serverTextArea.append("Sending to {" +recievername+ "} only this msg: "+msg+ "\n");
                    serverTextArea.setCaretPosition(serverTextArea.getDocument().getLength());
                } catch (Exception e) {
                    serverTextArea.append("Error in telling this "+ recievername + "." + "\n");
                }
            }else {
                    serverTextArea.append("Error in telling this ... his ID is not found OR His outputstream is null "+ recievername+"."+"\n");
            }        
        }
    }
    
    public void writeUsers(){
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token : tempList){
            //users.append(token + "\n")
        }
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
        StartServerButton = new javax.swing.JButton();
        ServerOnlineUsers = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        StopServerButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server View");
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(44, 122, 208));

        StartServerButton.setBackground(new java.awt.Color(254, 254, 254));
        StartServerButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        StartServerButton.setText("Start");
        StartServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartServerButtonActionPerformed(evt);
            }
        });

        ServerOnlineUsers.setBackground(new java.awt.Color(254, 254, 254));
        ServerOnlineUsers.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ServerOnlineUsers.setText("Users");
        ServerOnlineUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerOnlineUsersActionPerformed(evt);
            }
        });

        ClearButton.setBackground(new java.awt.Color(254, 254, 254));
        ClearButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        StopServerButton.setBackground(new java.awt.Color(254, 254, 254));
        StopServerButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        StopServerButton.setText("Stop");
        StopServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopServerButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(253, 205, 96));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("SERVER");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(151, 151, 151))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        serverTextArea.setColumns(20);
        serverTextArea.setRows(5);
        jScrollPane1.setViewportView(serverTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(StartServerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ServerOnlineUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(ClearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StopServerButton)
                        .addGap(13, 13, 13))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartServerButton)
                    .addComponent(ServerOnlineUsers)
                    .addComponent(ClearButton)
                    .addComponent(StopServerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartServerButtonActionPerformed
        // TODO add your handling code here:
        Thread starter = new Thread(new ServerStart());
        starter.start();
        serverTextArea.append("Server started ...\n");
    }//GEN-LAST:event_StartServerButtonActionPerformed

    private void ServerOnlineUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerOnlineUsersActionPerformed
        // TODO add your handling code here:
        serverTextArea.append("\nOnline users: \n");
        for (String current_user : users) {
            serverTextArea.append(current_user);
            serverTextArea.append("\n");
        }
    }//GEN-LAST:event_ServerOnlineUsersActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        // TODO add your handling code here:
        serverTextArea.setText("");
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void StopServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopServerButtonActionPerformed
        // TODO add your handling code here:
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }//GEN-LAST:event_StopServerButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton ServerOnlineUsers;
    private javax.swing.JButton StartServerButton;
    private javax.swing.JButton StopServerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea serverTextArea;
    // End of variables declaration//GEN-END:variables
}
