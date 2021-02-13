import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.net.*;
import java.io.*;
import java.util.Scanner;


public class part2_d_server_18209022 {
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream dis = null;

    public part2_d_server_18209022(int port) {
        try {
            ss = new ServerSocket(port);
            s = ss.accept();
            System.out.println("Connection Established");

            dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));

            while (true) {
                String line = "";
                while (!line.equals("exit")) {
                    line = dis.readUTF();
                    if(line.equals("Client: weather") || line.equals("Client: Weather")) {
                        
                        System.out.print("Server: Next consecutive three days temperature : ");
                        getWeather();
                    }
                    else{
                        System.out.println(line);
                    }
                }
                close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void close() throws IOException {
        if (s != null) s.close();
        if (dis != null) dis.close();
    }

    public void getWeather() throws IOException {
        String weatherUrl = "http://api.openweathermap.org/data/2.5/forecast?q=kansas%20city&appid=4e9649f88e0f7512bd61fd82800cb44a&units=metric";
            URL url= new URL(weatherUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            String data ="";
        if(responseCode != 200){
                throw new RuntimeException("HTTPResponseCode: " +responseCode);
            }else{
                Scanner sc= new Scanner(url.openStream());
                while(sc.hasNext()){
                     data = sc.nextLine();
                }
            }
        JSONParser parse = new JSONParser();
        JSONObject jsonObject =null;
		try {
			jsonObject = (JSONObject)parse.parse(data);
			// extract next 3 days weather details
			final JSONArray jsonObjectArray = (JSONArray) parse.parse(jsonObject.get("list").toString());
            final String day1Data = ((JSONObject) parse.parse(jsonObjectArray.get(0).toString())).get("main").toString();
            final String day2Data = ((JSONObject) parse.parse(jsonObjectArray.get(8).toString())).get("main").toString();
            final String day3Data = ((JSONObject) parse.parse(jsonObjectArray.get(16).toString())).get("main").toString();
            // extract the temperatures only
            final String day1Temp = ((JSONObject) parse.parse(day1Data)).get("temp").toString();
            final String day2Temp = ((JSONObject) parse.parse(day2Data)).get("temp").toString();
            final String day3Temp = ((JSONObject) parse.parse(day3Data)).get("temp").toString();
            System.out.println(day1Temp + "c/" + day2Temp + "c/" + day3Temp + "c");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        part2_d_server_18209022 server = null;
        if (args.length != 1)
            System.out.println("Usage: java part2_d_server port");
        else
            server = new part2_d_server_18209022(Integer.parseInt(args[0]));
    }
}