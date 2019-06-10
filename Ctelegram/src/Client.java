import java.io.*;
import java.net.*;

public class Client {


    public static void main(String[] args) throws IOException {

        while (true) {
            Socket client = new Socket("127.0.0.1", 8000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String Sreader = reader.readLine();
            System.out.println();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            //String Swriter = reader.readLine();

            writer.write(Sreader);
            writer.newLine();
            writer.flush();
            System.out.println(Sreader);
        }

    }

}
