import java.net.*;
import java.io.*;

public class part2_a_client_18209022
{  private Socket s               = null;
   private DataInputStream  dis   = null;
   private DataOutputStream dos   = null;

   public part2_a_client_18209022(String serverName, int serverPort)
   {  System.out.println("Establishing connection. Please wait ...");
      try
      {  s = new Socket(serverName, serverPort);
         System.out.println("Connected: " + s);
         start();
      }
      catch(UnknownHostException uhe)
      {  System.out.println("Unknown Host: " + uhe.getMessage());
      }
      catch(IOException ioe)
      {  System.out.println("Unexpected exception: " + ioe.getMessage());
      }
      String line = "";
      while (!line.equals("exit"))
      {  try
         {  
    	  System.out.print("Client_1: "); 
    	  line = dis.readLine();
            dos.writeUTF("Client_1: " + line);
            dos.flush();
         }
         catch(IOException ioe)
         {  System.out.println("Error while sending: " + ioe.getMessage());
         }
      }
   }
   public void start() throws IOException
   {  dis   = new DataInputStream(System.in);
      dos = new DataOutputStream(s.getOutputStream());
   }
   public void stop()
   {  try
      {  if (dis   != null)  dis.close();
         if (dos   != null)  dos.close();
         if (s     != null)  s.close();
      }
      catch(IOException ioe)
      {  System.out.println("Closing error ...");
      }
   }
   public static void main(String args[])
   {  part2_a_client_18209022 client = null;
      if (args.length != 2)
         System.out.println("Usage: java part2_a_client_18209022 host port");
      else
         client = new part2_a_client_18209022(args[0], Integer.parseInt(args[1]));
   }
}