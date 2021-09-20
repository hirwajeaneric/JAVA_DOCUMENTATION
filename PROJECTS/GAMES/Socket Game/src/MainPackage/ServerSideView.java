
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
    private final String[] QUESTIONSLIST = {"1 + 2 = ","4 / 2 = ","50 x 2 = ","45 - 5 = ","14 x 2 = "};
    
    private final int[] ANSWERSLIST = {3, 2, 100, 40, 28};
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
            OUT.println("Welcome to Quick Answer Game!!");
            OUT.println("Waiting for other players......");
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
                        serverTextPane.setText(serverTextPane.getText() + this.username + " Is now in the Game!!\n");
                    }else{
                        serverTextPane.setText(serverTextPane.getText() + this.username + " IS giving the answer " + clientMessage + " to this question " + QUESTIONSLIST[incrementQuestion] + "\n");
                        if(clientMessage.trim().equalsIgnoreCase(String.valueOf(ANSWERSLIST[incrementQuestion]).trim())){
                            CLIENTSMARKS.put(this, CLIENTSMARKS.getOrDefault(this, 0)+2);  
                            incrementQuestion++;
                            for(ClientHandler cl : clients){
                                if(this==cl){
                                    this.OUT.println("\nBingo!! You won 2 marks\n");
                                }else{
                                    cl.OUT.println(this.username + " Answered before you!! \nYou lost!!\nNext Question!!\n");
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
                                    cl.OUT.println("\nSTART GAME! You are now " + clients.size() + " players \n");
                                    cl.OUT.println("Rule:");
                                    cl.OUT.println("The one who answers before wins 2 marks\n");
                                    cl.OUT.println("*******************************************************************\n");
                                    cl.OUT.println("START NOW!");
                                    cl.OUT.println("*******************************************************************\n");
                                    //cl.OUT.println("QUESTION 1:\n");
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
                            cl.OUT.println("GAME OVER!!\n Calculating marks\n");
                            for(ClientHandler key: CLIENTSMARKS.keySet()){
                                if(cl==key){
                                    cl.OUT.println("Congratulations!! You won " + CLIENTSMARKS.get(key) + "/" + QUESTIONSLIST.length *2 + "\n\n");
                                }
                            }                            
                        }
                        if(maxMarks>0){
                            if(winners.size()>1){
                                for(ClientHandler cl: clients){
                                    //cl.OUT.println("[SERVER] says, ARE YOU READY TO SEE THE WINNERS?\n");
                                    cl.OUT.println("Marks loading....\n");
                                }
                                Thread.sleep(7000);
                                for(ClientHandler cl: clients){
                                    cl.OUT.println("The Winners is:\n");
                                    for(ClientHandler winner: winners){
                                        cl.OUT.println(winner  +" and has got " + CLIENTSMARKS.get(winner) + "/" + QUESTIONSLIST.length *2 + "\n\n");
                                    }
                                }
                            }else{
                                for(ClientHandler cl: clients){
                                    //cl.OUT.println("[SERVER] says, ARE YOU READY TO SEE THE WIN?\n");
                                    cl.OUT.println("Marks loading....\n");
                                }
                                Thread.sleep(7000);
                                for(ClientHandler cl: clients){
                                    if(cl==singleWinner){
                                        cl.OUT.println("Congratulation, You won the game with " + maxMarks + "/" + QUESTIONSLIST.length *2 + "\n");
                                    }else{
                                        cl.OUT.println("The Winner For This Game is \n" + singleWinner  +" and has got " + maxMarks + "/" + QUESTIONSLIST.length *2 + "\n");
                                    }
                                }
                            }
                        }else{
                            for(ClientHandler cl: clients){
                                cl.OUT.println("No one won!!\n");
                                cl.OUT.println("You all have 0/10\n");
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
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startServer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        startServer.setForeground(new java.awt.Color(13, 242, 33));
        startServer.setText("Start Server");
        startServer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        startServer.setFocusable(false);
        startServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerActionPerformed(evt);
            }
        });

        clearServerTextPane.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearServerTextPane.setForeground(new java.awt.Color(180, 21, 136));
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
        serverTextPane.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(serverTextPane);

        currentUsersNumber.setEditable(false);
        currentUsersNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        currentUsersNumber.setText("0");
        currentUsersNumber.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Users:");

        stopServerBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stopServerBtn.setForeground(new java.awt.Color(231, 17, 17));
        stopServerBtn.setText("Stop Server");
        stopServerBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        stopServerBtn.setFocusable(false);
        stopServerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServerBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setText("SERVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentUsersNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(startServer, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopServerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearServerTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(229, 229, 229))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(startServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentUsersNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stopServerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearServerTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerActionPerformed
        // TODO add your handling code here:
        if(serverSocket==null){
            Thread listener = new Thread(new ServerSide());
            listener.start();
            serverTextPane.setText(serverTextPane.getText() + " Server started!\n\n");
            startServer.setEnabled(false);
        }else{
            serverTextPane.setText(serverTextPane.getText() + " Server not starting....\n\n");
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
            serverTextPane.setText(serverTextPane.getText() + " Server stopped....\n\n");
            stopServerBtn.setEnabled(false);
            startServer.setEnabled(true);
        }else{
            serverTextPane.setText(serverTextPane.getText() + " Server not stopping....\n\n");
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane serverTextPane;
    private javax.swing.JButton startServer;
    private javax.swing.JButton stopServerBtn;
    // End of variables declaration//GEN-END:variables
}
