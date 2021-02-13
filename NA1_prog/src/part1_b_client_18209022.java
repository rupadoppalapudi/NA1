import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class part1_b_client_18209022 {

   public Socket s; 

    public part1_b_client_18209022(String hst, int port, String file) {
        try {
            s = new Socket(hst, port);
            sendLargeFile(file,s);

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void sendLargeFile(String largeFile,Socket s) throws IOException {
    	BufferedInputStream bis = null;
    	FileInputStream fis = null;
        OutputStream os = null;

        try {
            // send largeFile from client to server
            File mylargeFile = new File("C:/Users/RupaSri/Desktop/largeFile.txt");
            byte[] b = new byte[(int) mylargeFile.length()];
            fis = new FileInputStream(mylargeFile);
            bis = new BufferedInputStream(fis);
            bis.read(b, 0, b.length);
            os = s.getOutputStream();
            System.out.println("Sending text file:" + " largeFile.txt" + " to server");
            os.write(b, 0, b.length);
            os.flush();
            System.out.println("Large file sent successfully");

            savelargeFileAtClient(s);
        } finally {
        	// close all the connections 
            if (bis != null) bis.close();
            if (os != null) os.close();
            if (s != null) s.close();
        }
    }

    private void savelargeFileAtClient(Socket clientSock) throws IOException {

        System.out.println("Starting to receive the appended file from server");
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream("C:/Users/RupaSri/Desktop/largeFile_Appended.txt");
        byte[] buffer = new byte[1335450];

        int largeFilesize = 1335450; 
        int rf = 0;
        int remaining = largeFilesize;
        while((rf = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            remaining -= rf;
            fos.write(buffer, 0, rf);
        }

        System.out.println("Large file " + "largeFileToClientFromServer_Appended_.txt"
                + " saved successfully");
        System.out.println("---------------------------------------------");
        System.out.println("The line appended at the server is as follows::");
        
        fos.close();
        dis.close();
        compareFiles();
    }

    private static void compareFiles() {
        try {
            BufferedReader clientSent = null;
            BufferedReader clientReceived = null;
            String sCurrentLine;
            final ArrayList<String> clientSentList = new ArrayList<String>();
            final ArrayList<String> clientReceivedList = new ArrayList<String>();
            clientSent = new BufferedReader(new FileReader("C:/Users/RupaSri/Desktop/largeFile.txt"));
            clientReceived = new BufferedReader(new FileReader("C:/Users/RupaSri/Desktop/largeFile_Appended.txt"));
            while ((sCurrentLine = clientSent.readLine()) != null) {
                clientSentList.add(sCurrentLine);
            }
            while ((sCurrentLine = clientReceived.readLine()) != null) {
                clientReceivedList.add(sCurrentLine);
            }
            clientReceivedList.removeAll(clientSentList);
            for (int i = 0; i < clientReceivedList.size(); i++) {
                System.out.println(clientReceivedList.get(i));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	part1_b_client_18209022 fc = new part1_b_client_18209022("localhost", 1024, "C:/Users/RupaSri/Desktop/largeFile.txt");
    }



}
