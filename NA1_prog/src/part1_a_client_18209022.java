import java.io.*;
import java.net.Socket;

public class part1_a_client_18209022 {

	public static void main(String[] args) throws Exception
	{

		// Create client socket 
        Socket s = new Socket("localhost", 1024); //"localhost"
  
        // send data to the server 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
  
        System.out.print("Client: ");
        
        // read data coming from the server 
        BufferedReader bfr = new BufferedReader(new InputStreamReader(s.getInputStream())); 
  
        // read data from the keyboard 
        BufferedReader bfr2 = new BufferedReader(new InputStreamReader(System.in)); 
        
        // declare strings
        String str1, str2; 
  
        // repeat until exit is typed at client 
        while (!(str1 = bfr2.readLine()).equals("exit")) { 
  
        	
        	
            // send to the server 
            dos.writeBytes("Client: " +str1 + "\n"); //"Client: " +
  
            // receive from the server 
            str2 = bfr.readLine(); 
  
            System.out.println(str2);  //"Ser"
            System.out.print("Client: ");
        } 
  
        // close all the connections 
        dos.close(); 
        bfr.close(); 
        bfr2.close(); 
        s.close(); 

	}

}
