Socket chatSocket = new Socket("localhost", 5000);
InputStreamReader stream = new InputStreamReader(chatSocket.getInputStream());
BufferedReader reader = new BufferedReader(stream);
String message = reader.readLine();


Socket chatSocket = new Socket("localhost", 5000); 
PrintWriter writer= new PrintWriter(chatSocket.getOutputStream());
writer.println("Message to be sent.");


ServerSocket serversock =  new ServerSocket(5000);
Socket sock = new serversock.accept();
