import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server implements Runnable {

    ServerSocket serverSocket;
    ArrayList<Socket> user = new ArrayList<Socket>();

    {
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void run() {

        System.out.println("Hi 1");
        try {
            while (true) {
                Client client = new Client(serverSocket.accept());
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Client implements Runnable {
        public Socket clientSocket;

        public Client(Socket clientSocket) throws IOException {

            System.out.println(clientSocket);
            this.clientSocket = clientSocket;
            user.add(this.clientSocket);

            for (int i = 0; i < user.size(); i++) {

                System.out.println(user.get(i) + ": " + user.size());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(user.get(i).getOutputStream(), "Cp1251"));
                writer.write("Клиент: " + clientSocket + "присоединился к чату");
                writer.newLine();
                writer.flush();
            }
        }

        @Override
        public void run() {
            try {

                while (true) {

                    BufferedReader readerS = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "Cp1251"));
                    String Sreader1 = readerS.readLine();
                    System.out.println(Sreader1);

                    for (int i = 0; i < user.size(); i++) {

                        System.out.println(user.get(i) + ": " + user.size());
                        // System.out.println(user.size());
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(user.get(i).getOutputStream(), "Cp1251"));
                        writer.write(Sreader1);
                        writer.newLine();
                        writer.flush();
                        System.out.println(Sreader1);

                    }

                }
            } catch (IOException e) {
                try {
                    for (int i = 0; i < user.size(); i++) {
                        if (user.get(i) == clientSocket) user.remove(i);
                        System.out.println("Клиент: " + clientSocket + "вышел из чата.");
                    }
                    clientSocket.close();

                } catch (IOException e1) {

                }
            }
        }

    }


}








