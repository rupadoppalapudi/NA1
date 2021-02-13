import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_file {
	 // public Socket s;
	
	public void receive_file(Socket socket) throws Exception //**main(String[] args) throws Exception
	{
		// ServerSocket ss = new ServerSocket(1024); 
		
			
			//...
			//fos.close();
			//is.close();
			
			
		  
	     // connect server socket to client socket 
	    //** Socket s = ss.accept(); 
		
		
		
      

     // byte []b=new byte[4714763 ]; //2002];
     
     InputStream is = socket.getInputStream();
  //   FileOutputStream fos=new FileOutputStrea
     FileOutputStream fos = new FileOutputStream("C:/Users/RupaSri/Desktop/largeFile_upd.txt");
     //' is.read(b,0,b.length);
     //' fos.write(b,0,b.length);
     
     int b;
     while  ((b=is.read()) != -1) {	
	            fos.write(b); 
	            // System.out.println("File saved successfully");
	            System.out.print((char)b);
     }
     fos.close();
     is.close();
     
			//}
			
              // Server_file.appendTextToFile();
              
             // Server_file.sendFile();
	}   
	
	 // append a new line to the file received
     public void appendTextToFile() throws IOException
     {
         String textToAppend = "Appended line at server side while sending the file to client: RupaSri";

         BufferedWriter writer = new BufferedWriter(
                 new FileWriter("C:/Users/RupaSri/Desktop/largeFile_upd.txt", true)  //Set true for append mode
         );
         writer.newLine();   //Add new line
         writer.write(textToAppend);
         writer.close();
         // System.out.println("Test11111111");

     }
         public void sendFile(Socket socket) throws IOException {
        	// System.out.println("Test11111111");
             File largefile_upd = new File("C:/Users/RupaSri/Desktop/largeFile_upd.txt");
             FileInputStream fis = new FileInputStream(largefile_upd); //(args[0]); 
    	        // byte[] blength = new byte[(int) fis.length()];
    	        
    	       byte[] flength = new byte[(int) largefile_upd.length()];
    	       
    	       fis.read(flength, 0,flength.length);
    	       OutputStream os=socket.getOutputStream();
    	       os.write(flength,0,flength.length);
    	       
    	       fis.close();
         }
     
     public static void main(String[] args) throws Exception{
    	 try (ServerSocket ss = new ServerSocket(1024)) {
 			// connect server socket to client socket 
 			Socket socket = ss.accept(); 
 			System.out.println("Connection established");
 			
    	 Server_file sf = new Server_file();
    	  sf.receive_file(socket);
    	  System.out.println("apppend");
    	  sf.appendTextToFile();
    	  sf.sendFile(socket);
 			
    	 }
         //s.start();
     //s.close();
     // ss.close();

     }
}
