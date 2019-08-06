import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server implements Runnable {
    ServerSocket serverSocket;
    ArrayList<Socket> listSocked = new ArrayList<>();
    ArrayList<String> listNickname = new ArrayList<>();

    {
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Сервер запущен");
        } catch (IOException e) {

        }

    }

    @Override
    public void run() {
        while (true) {


            try {

                Client client = new Client(serverSocket.accept());

                Thread clientThread = new Thread(client);
                if (clientThread.isInterrupted()) {
                    System.out.println("Привет");


                }
                clientThread.start();



            } catch (IOException e) {


                System.out.println("Обрыв соединения с клиентом");

            }

        }


    }

    public class Client implements Runnable {
        public Socket clientSocket;
        private String nickname;

        public Client(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            listSocked.add(this.clientSocket);

            BufferedReader readerNickname = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "Cp1251"));
            nickname = readerNickname.readLine();
            listNickname.add(nickname);

            for (int i = 0; i < listSocked.size(); i++) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(listSocked.get(i).getOutputStream(), "Cp1251"));
                writer.write("Клиент: " + nickname + " присоединился к чату");
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

                    for (int i = 0; i < listSocked.size(); i++) {
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(listSocked.get(i).getOutputStream(), "Cp1251"));
                        writer.write(Sreader1);
                        writer.newLine();
                        writer.flush();
                        System.out.println(Sreader1);

                    }

                }
            } catch (IOException e) {
                try {
                    for (int i = 0; i < listSocked.size(); i++) {
                        if (listSocked.get(i) == clientSocket) {
                            listSocked.remove(i);
                            listNickname.remove(i);
                        }
                    }

                    if (listSocked.size() != 0) {
                        for (int i = 0; i < listSocked.size(); i++) {
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(listSocked.get(i).getOutputStream(), "Cp1251"));
                            writer.write("Клиент: " + nickname + " вышел из чата.");
                            writer.newLine();
                            writer.flush();
                        }
                    }
                   //

                } catch (IOException e1) {

                }

            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}








