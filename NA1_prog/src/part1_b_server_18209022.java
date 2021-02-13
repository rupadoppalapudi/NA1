import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class part1_b_server_18209022 extends Thread {

    public ServerSocket ss;

    public part1_b_server_18209022(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) { e.printStackTrace(); } 
    }

    public void run() {
        while (true) {
            try {
                Socket s = ss.accept();
                System.out.println("Connection established");
                saveFile(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket s) throws IOException {
        DataInputStream dis = new DataInputStream(s.getInputStream());
        FileOutputStream fos = new FileOutputStream("C:/Users/RupaSri/Desktop/largeFileReceivedAtServer.txt");
        byte[] buffer = new byte[1335369];

        int filesize =1335369; 
        int rf = 0;
        int remaining = filesize;
        while((rf = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            remaining -= rf;
            fos.write(buffer, 0, rf);
            
            final BufferedReader reader = new BufferedReader(new FileReader("C:/Users/RupaSri/Desktop/largeFileReceivedAtServer.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
        }
        sendFile(s);

        fos.close();
        dis.close();
    }

    public static void appendTextToFile() throws IOException
    {
        String textToAppend = "Demo_spring2020";

        BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:/Users/RupaSri/Desktop/largeFileReceivedAtServer.txt", true)  //Set true for append mode
        );
        writer.newLine();   //Add new line
        writer.write(textToAppend);
        writer.close();
    }

    private void sendFile(Socket sock) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        appendTextToFile();

        try {
            // send file
            File myFile = new File("C:/Users/RupaSri/Desktop/largeFileReceivedAtServer.txt");
            byte[] mybytearray = new byte[(int) myFile.length()];
            fis = new FileInputStream(myFile);
            bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            os = sock.getOutputStream();
            System.out.println("Sending " + "largeFileReceivedAtServer.txt" + " to client");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            System.out.println("File with appended line sent successfully");
        } finally {
            if (bis != null) bis.close();
            if (os != null) os.close();
            if (sock != null) sock.close();
        }
    }
    public static void main(String[] args) {
    	part1_b_server_18209022 fs = new part1_b_server_18209022(1024);
        fs.start();
    }

}