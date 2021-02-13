import java.net.*;
import java.io.*;

public class part2_a_server_18209022
{  private Socket          s   = null;
   private ServerSocket    ss   = null;
   private DataInputStream dis =  null;

   public part2_a_server_18209022(int port)
   {  try
      {  System.out.println("Binding to port " + port + ", please wait  ...");
         ss = new ServerSocket(port);  
         System.out.println("Server started: " + ss);
         System.out.println("Waiting for a client ..."); 
         s = ss.accept();
         System.out.println("Client accepted: " + s);
         open();
         boolean done = false;
         while (!done)
         {  try
            {  String line = dis.readUTF();
               System.out.println(line);
               done = line.equals("exit");
            }
            catch(IOException ioe)
            {  done = true;
            } 
         }
         close();
      }
      catch(IOException ioe)
      {  System.out.println(ioe); 
      }
   }
   public void open() throws IOException
   {  dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
   }
   public void close() throws IOException
   {  if (s != null)    s.close();
      if (dis != null)  dis.close();
   }
   public static void main(String args[])
   {  part2_a_server_18209022 s = null;
      if (args.length != 1)
         System.out.println("Usage: java part2_a_server_18209022 port");
      else
         s = new part2_a_server_18209022(Integer.parseInt(args[0]));
   }
}