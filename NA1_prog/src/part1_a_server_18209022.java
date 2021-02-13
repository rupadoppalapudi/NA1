import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class part1_a_server_18209022 {

	public static void main(String[] args) throws Exception
	{
		// Create server socket 
        ServerSocket ss = new ServerSocket(1024); 
  
        // connect server socket to client socket 
        Socket s = ss.accept(); 
        System.out.println("Connection established"); 
  
        // send data to the client 
        PrintStream ps = new PrintStream(s.getOutputStream()); 
        
        // read data coming from the client 
        BufferedReader bfr = new BufferedReader(new InputStreamReader(s.getInputStream())); 
  
        // read data from the keyboard 
        BufferedReader bfr2 = new BufferedReader(new InputStreamReader(System.in)); 
        
        // server executes continuously 
        while (true) {                           
  
            String str1, str2; 
  
            // repeat as long as the client does not send a null string 
  
            // read from client 
            while ((str1 = bfr.readLine()) != null) { 
                System.out.println(str1);  
                System.out.print("Server: ");
                str2 = bfr2.readLine(); 
  
                // send to client 
                ps.println("Server: " +str2); 
      
            } 
  
            // close all the connections 
            ps.close(); 
            bfr.close(); 
            bfr2.close(); 
            ss.close(); 
            s.close(); 
  
            // terminate application 
            System.exit(0); 
  
        } // end of while 

	}

}
