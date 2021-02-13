import java.net.*;
import java.io.*;

public class part2_b_server_18209022 implements Runnable
{  private Socket       s = null;
   private ServerSocket ss = null;
   private Thread       thread = null;
   private DataInputStream  dis  =  null;

   public part2_b_server_18209022(int port)
   {  try
      {  System.out.println("Binding to port " + port + ", please wait  ...");
         ss = new ServerSocket(port);  
         System.out.println("Server started: " + ss);
         start();
      }
      catch(IOException ioe)
      {  System.out.println(ioe); 
      }
   }
   public void run()
   {  while (thread != null)
      {   try
         {  System.out.println("Waiting for a client ..."); 
            s = ss.accept();
            System.out.println("Client accepted: " + s);
            open();
            boolean done = false;
            while (!done)
            {  try
               {  String line = dis.readUTF();
                  System.out.println(line);
                  //done = line.equals(".bye");
               }
               catch(IOException ioe)
               {  done = true;  }
            }
            close();
         }
         catch(IOException ie)
         {  System.out.println("Acceptance Error: " + ie);  }
      }
   }
   public void start()
   {  if (thread == null)
      {  thread = new Thread(this); 
         thread.start();
      }
   }
//   public void stop()
//   {  if (thread != null)
//      {  thread.stop(); 
//         thread = null;
//      }
//   }
   public void open() throws IOException
   {  dis = new DataInputStream(new 
                        BufferedInputStream(s.getInputStream()));
   }
   public void close() throws IOException
   {  if (s != null)    s.close();
      if (dis != null)  dis.close();
   }
   public static void main(String args[])
   {  part2_b_server_18209022 s = null;
      if (args.length != 1)
         System.out.println("Usage: java part2_b_server_18209022 port");
      else
         s = new part2_b_server_18209022(Integer.parseInt(args[0]));
   }
}
