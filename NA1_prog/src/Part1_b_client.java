import java.io.*;
// import java.net.ServerSocket;
import java.net.Socket;
public class Part1_b_client {

	 // public Socket s;
	
	public void file_send(Socket socket) // main(String[] args) 
		throws FileNotFoundException,IOException 
	    { 
	        /* If file does not exist FileInputStream throws 
	           FileNotFoundException and read() write() throws 
	           IOException if I/O error occurs */
	        //FileInputStream fis = new FileInputStream(args[0]); 
	  
	        /* assuming that the file exists and need not to be 
	           checked */
	        //FileOutputStream fos = new FileOutputStream(args[1]); 
	  
	        //int b; 
	        //while  ((b=fis.read()) != -1) 
	          //  fos.write(b); 
	        
	       //System.out.println(b);
	       
		// Create client socket 
		// String File;
		
			File largefile = new File("C:/Users/RupaSri/Desktop/largeFile.txt");
			
		
	       //Socket s = new Socket("localhost", 1024); 
	       //File largefile = new File("C:/Users/RupaSri/Desktop/largeFile.txt");
	  
	       FileInputStream fis = new FileInputStream(largefile); //(args[0]); 
	        // byte[] blength = new byte[(int) fis.length()];
	        
	       byte[] flength = new byte[(int) largefile.length()];
	       
	       
	      // byte b[]=new byte[4714763]; //1270000];
	       fis.read(flength, 0,flength.length);
	       OutputStream os=socket.getOutputStream();
	       os.write(flength,0,flength.length);
	       
	                
	        //; byte b[]=new byte[1270000];
	         //;   fis.read(b, 0, b.length);
	         //;   OutputStream os=s.getOutputStream();
	         //;  os.write(b,0,b.length);
	        
	         //; int b;
	        // byte b[]=new byte[2002];
	         //;  while  ((b=fis.read()) != -1) {
	         //; 	OutputStream os=s.getOutputStream();
	         //; 	os.write(b);
	         //;  }
	        	
	       fis.close();
		
		// Part1_b_client.receiveFile();
		}
	    
	public void receiveFile(Socket socket) throws IOException {    //Socket sock
		 //Socket s = new Socket("localhost", 1024);
		 
	        int bytesRead;
	        int current = 0;
	        FileOutputStream fos = null;
	        BufferedOutputStream bos = null;
	        try {
	            // receive file
	            byte [] mybytearray  = new byte [4714844            ];
	            InputStream is = socket.getInputStream();
	            fos = new FileOutputStream("C:/Users/RupaSri/Desktop/largeFile_updated.txt");
	            bos = new BufferedOutputStream(fos);
	            bytesRead = is.read(mybytearray,0,mybytearray.length);
	            current = bytesRead;

	            do {
	                bytesRead =
	                        is.read(mybytearray, current, (mybytearray.length-current));
	                if(bytesRead >= 0) current += bytesRead;
	            } while(bytesRead > -1);

	            bos.write(mybytearray, 0 , current);
	            bos.flush();
	            System.out.println("File " + "FileToReceive.txt"
	                    + " downloaded (" + current + " bytes read)");
	        }
	        finally {
	            if (fos != null) fos.close();
	            if (bos != null) bos.close();
	            if (socket != null) socket.close();
	        }
		
	    }
	       //; os.close();
	      //********* s.close();
	        
	    //"    FileOutputStream fos = new FileOutputStream(args[1]); //getOutputStream()); 
	        
	     //'   int b;
	      //'  while  ((b=fis.read()) != -1) {	
	        //'    fos.write(b); 
	            //System.out.print((char)b);
	
	       
	            
	       // System.out.print((char)b);
	        
	      // System.out.println(fos.write());
	        // send data to the server 
	        //DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	  
	        // read data coming from the server 
	        //BufferedReader bfr = new BufferedReader(new InputStreamReader(s.getInputStream())); 
	  
	        // read data from the keyboard 
	        //BufferedReader bfr2 = new BufferedReader(new InputStreamReader(System.in)); 
	        
	        // declare strings
	        //String str1, str2; 
	  
	        // repeat until exit is typed at client 
	        //while (!(str1 = bfr2.readLine()).equals("exit")) { 
	  
	            // send to the server 
	          //  dos.writeBytes(str1 + "\n"); 
	  
	            // receive from the server 
	            //str2 = bfr.readLine(); 
	  
	            //System.out.println(str2); 
	  
	        /* read() will read only next int so we used while 
	           loop here in order to read upto end of file and 
	           keep writing the read int into dest file */
	        //fis.close(); 
	        //fos.close(); 
	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 1024)) {
		Part1_b_client cf = new Part1_b_client();
		 cf.file_send(socket);
		 cf.receiveFile(socket);
	}
	}
	    } 

	
