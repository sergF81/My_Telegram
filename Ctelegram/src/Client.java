import java.io.*;
import java.net.*;

public class Client {


    public static void main(String[] args) throws IOException {


        Socket client = new Socket("127.0.0.1", 8000);
        BufferedReader nikreader = new BufferedReader(new InputStreamReader(System.in));
        String Nikreader = nikreader.readLine();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String Sreader = reader.readLine();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write(Nikreader + ": " + Sreader);
            writer.newLine();
            writer.flush();

               BufferedReader sms = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String smm = sms.readLine();
               System.out.println(smm);
        }

    }
}
