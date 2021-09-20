
package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerSideView extends javax.swing.JFrame {    
    public ServerSocket serverSocket; 
    private final String[] QUESTIONSLIST = {
        "12 + 45 = ?",
        "58 X 78 - 12 = ?",
        "47 / 12 + 78 - 45 = ?",
        "120 + 145 /45 - 24 = ?",
        "1080 + 2 -14578 = ?"
    };
    
    private final int[] ANSWERSLIST = {57, 4512, 
        37, 
        219, 
        -13496
    };
    private final HashMap<ClientHandler, Integer> CLIENTSMARKS = new HashMap<>();
    private boolean firstTimeServerPub=false;
    private int incrementQuestion = 0;
    
    public ServerSideView() {
        initComponents();
        stopServerBtn.setEnabled(false);
    }

    public class ServerSide implements Runnable{
        private final ArrayList<ClientHandler> CLIENTS = new ArrayList<>();
        private Socket client;        
               
        @Override
        public void run() {            
            try {
                serverSocket = new ServerSocket(Config.PORT);            
                while(true){
                    try {
                        client = serverSocket.accept();
                        ClientHandler clientHandler = new ClientHandler(client, CLIENTS);
                        CLIENTS.add(clientHandler);
                        currentUsersNumber.setText(String.valueOf(CLIENTS.size()));
                        if(CLIENTS.size()>0){
                            stopServerBtn.setEnabled(true);
                        }
                        Thread listener = new Thread(clientHandler);
                        listener.start();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSideView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class ClientHandler implements Runnable{
        private PrintWriter OUT;
        private BufferedReader IN;
        private String username;
        private ArrayList<ClientHandler> clients;
        private Socket client;        
       
        
        public ClientHandler(Socket client, ArrayList<ClientHandler> clients){
            try { 
                this.client=client;
                this.clients=clients;
                OUT = new PrintWriter(client.getOutputStream(), true);
                IN = new BufferedReader(new InputStreamReader(client.getInputStream()));
                
            }catch (IOException ex) {
                Logger.getLogger(ServerSideView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {            
            OUT.println("[SERVER] says you're now connected......");
            OUT.println("Kindly wait for at least one or more users to start the game......");
            String clientMessage;
            try {
                while(true){
                    if(clients.size()>0){
                        stopServerBtn.setEnabled(true);
                    }
                    
                    clientMessage = IN.readLine();
                    if(clientMessage.startsWith("@1User")){
                        int firstSpaceIndex = clientMessage.indexOf(" ");
                        if(firstSpaceIndex>-1){
                            this.username=clientMessage.substring(6, firstSpaceIndex);
                        }else{
                          this.username=clientMessage.substring(6);  
                        }                       
                        serverTextPane.setText(serverTextPane.getText() + this.username + " IS CONNECTED....\n");
                    }else{
                        serverTextPane.setText(serverTextPane.getText() + this.username + " IS giving the answer " + clientMessage + " to this question " + QUESTIONSLIST[incrementQuestion] + "\n");
                        if(clientMessage.trim().equalsIgnoreCase(String.valueOf(ANSWERSLIST[incrementQuestion]).trim())){
                            CLIENTSMARKS.put(this, CLIENTSMARKS.getOrDefault(this, 0)+2);  
                            incrementQuestion++;
                            for(ClientHandler cl : clients){
                                if(this==cl){
                                    this.OUT.println("\nYOU GOT THE ANSWER\n");
                                }else{
                                    cl.OUT.println(this.username + "  HAS GIVEN THE ANSWER MOVE TO THE NEXT QUESTION\n");
                                }
                            }
                            publishQuestionToAll();
                        }
                    }
                    boolean allHaveUsername=true;
                    for(ClientHandler cl: clients){
                        if(cl.username==null){
                            allHaveUsername=false;
                        }
                    }
                    if(allHaveUsername){
                        if(clients.size()>1 && !firstTimeServerPub){
                            firstTimeServerPub=true;                        
                            Thread.sleep(5000);
                                for(ClientHandler cl : clients){
                                    cl.OUT.println("\n[GAME START] now it is time to start the game with " + clients.size() + " online users now\n");
                                    cl.OUT.println("Kindly reply to the answer quickly because the one who gets the answer before others is the winner to the question\n");
                                    cl.OUT.println("LET'S GOOOO....\n");
                                    cl.OUT.println("THE FIRST QUESTION IS LOADING....\n");
                                }
                                Thread.sleep(5000); 
                                publishQuestionToAll();  
                        }
                    }
                    
                    if(incrementQuestion==QUESTIONSLIST.length){
                        int maxMarks=0;
                        ArrayList<ClientHandler> winners= new ArrayList<>();
                        ClientHandler singleWinner =null;
                        for(ClientHandler cl: clients){
                            if(maxMarks< CLIENTSMARKS.get(cl)){
                                maxMarks=CLIENTSMARKS.get(cl);
                                singleWinner=cl;
                                if(winners.size()>0){
                                    winners.remove(winners.size()-1);
                                }
                                winners.add(cl);
                            }else if(maxMarks==CLIENTSMARKS.get(cl)){
                                winners.add(cl);
                            }
                            cl.OUT.println("[SERVER] says the gome is over....\nSEE THE LIST OF THE MARKS\n");
                            for(ClientHandler key: CLIENTSMARKS.keySet()){
                                if(cl==key){
                                    cl.OUT.println("You got " + CLIENTSMARKS.get(key) + "/" + QUESTIONSLIST.length *2 + "\n\n");
                                }
                            }                            
                        }
                        if(maxMarks>0){
                            if(winners.size()>1){
                                for(ClientHandler cl: clients){
                                    cl.OUT.println("[SERVER] says, ARE YOU READY TO SEE THE WINNERS?\n");
                                    cl.OUT.println("LOADING....\n");
                                }
                                Thread.sleep(7000);
                                for(ClientHandler cl: clients){
                                    cl.OUT.println("[SERVER] says, The Winners For This Game are:\n");
                                    for(ClientHandler winner: winners){
                                        cl.OUT.println(winner  +" and has got " + CLIENTSMARKS.get(winner) + "/" + QUESTIONSLIST.length *2 + "\n\n");
                                    }
                                }
                            }else{
                                for(ClientHandler cl: clients){
                                    cl.OUT.println("[SERVER] says, ARE YOU READY TO SEE THE WIN?\n");
                                    cl.OUT.println("LOADING....\n");
                                }
                                Thread.sleep(7000);
                                for(ClientHandler cl: clients){
                                    if(cl==singleWinner){
                                        cl.OUT.println("[SERVER] says, The Winner For This Game is You and You have got " + maxMarks + "/" + QUESTIONSLIST.length *2 + "\n");
                                    }else{
                                        cl.OUT.println("[SERVER] says, The Winner For This Game is \n" + singleWinner  +" and has got " + maxMarks + "/" + QUESTIONSLIST.length *2 + "\n");
                                    }
                                }
                            }
                        }else{
                            for(ClientHandler cl: clients){
                                cl.OUT.println("[SERVER] says, NO WINNER FOR THIS GAME\n");
                                cl.OUT.println("ALL HAVE GOT ZERO\n");
                            }
                        }
                    }
                }
            } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(ServerSideView.class.getName()).log(Level.SEVERE, null, ex);
            }                      
        }       
        private void publishQuestionToAll(){
            if(incrementQuestion<QUESTIONSLIST.length){
                for(ClientHandler cl: clients){
                    cl.OUT.println(QUESTIONSLIST[incrementQuestion]);                
                }
            }
            
        }
        
        @Override
        public String toString(){
           return this.username; 
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

        startServer = new javax.swing.JButton();
        clearServerTextPane = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverTextPane = new javax.swing.JTextPane();
        currentUsersNumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        stopServerBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startServer.setBackground(new java.awt.Color(0, 51, 51));
        startServer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        startServer.setForeground(new java.awt.Color(255, 255, 255));
        startServer.setText("Start Server");
        startServer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        startServer.setFocusable(false);
        startServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerActionPerformed(evt);
            }
        });

        clearServerTextPane.setBackground(new java.awt.Color(51, 51, 51));
        clearServerTextPane.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearServerTextPane.setForeground(new java.awt.Color(255, 255, 255));
        clearServerTextPane.setText("Clear pane");
        clearServerTextPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        clearServerTextPane.setFocusable(false);
        clearServerTextPane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearServerTextPaneActionPerformed(evt);
            }
        });

        serverTextPane.setEditable(false);
        serverTextPane.setBorder(null);
        serverTextPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(serverTextPane);

        currentUsersNumber.setEditable(false);
        currentUsersNumber.setBackground(new java.awt.Color(0, 51, 51));
        currentUsersNumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        currentUsersNumber.setForeground(new java.awt.Color(255, 255, 255));
        currentUsersNumber.setText("0");
        currentUsersNumber.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Current users:");

        stopServerBtn.setBackground(new java.awt.Color(102, 0, 0));
        stopServerBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stopServerBtn.setForeground(new java.awt.Color(255, 255, 255));
        stopServerBtn.setText("Stop Server");
        stopServerBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        stopServerBtn.setFocusable(false);
        stopServerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(currentUsersNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startServer, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)
                                .addComponent(stopServerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(clearServerTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentUsersNumber)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clearServerTextPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stopServerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(startServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerActionPerformed
        // TODO add your handling code here:
        if(serverSocket==null){
            Thread listener = new Thread(new ServerSide());
            listener.start();
            serverTextPane.setText(serverTextPane.getText() + " THE SERVER HAS STARTED....\n\n");
            startServer.setEnabled(false);
        }else{
            serverTextPane.setText(serverTextPane.getText() + " THE SERVER REFUSED TO START....\n\n");
        }
    }//GEN-LAST:event_startServerActionPerformed

    private void stopServerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopServerBtnActionPerformed
        // TODO add your handling code here:
        if(serverSocket!=null){
            try {
                serverSocket.close();
                serverSocket=null;
            } catch (IOException ex) {
                Logger.getLogger(ServerSideView.class.getName()).log(Level.SEVERE, null, ex);
            }
            serverTextPane.setText(serverTextPane.getText() + " THE SERVER HAS BEEN STOPPED....\n\n");
            stopServerBtn.setEnabled(false);
            startServer.setEnabled(true);
        }else{
            serverTextPane.setText(serverTextPane.getText() + " THE SERVER REFUSED TO STOP....\n\n");
        }
    }//GEN-LAST:event_stopServerBtnActionPerformed

    private void clearServerTextPaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearServerTextPaneActionPerformed
        // TODO add your handling code here:
        serverTextPane.setText("");
    }//GEN-LAST:event_clearServerTextPaneActionPerformed

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
            java.util.logging.Logger.getLogger(ServerSideView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerSideView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerSideView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerSideView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerSideView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearServerTextPane;
    private javax.swing.JTextField currentUsersNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane serverTextPane;
    private javax.swing.JButton startServer;
    private javax.swing.JButton stopServerBtn;
    // End of variables declaration//GEN-END:variables
}
