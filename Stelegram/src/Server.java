import java.io.*;
import java.net.*;

public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket clientaccept = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientaccept.getInputStream()));
            String Sreader = reader.readLine();
            System.out.println(Sreader);

        }

    }

}
