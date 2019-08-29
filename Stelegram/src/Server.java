import java.io.*;
import java.net.*;
import java.util.ArrayList;

/*
 * @author fortis@tochka.com
 * @created 27.08.19

 Данный класс реализует создание соединения сервера чата
*/

public class Server implements Runnable {

    ServerSocket serverSocket;
    //Массив, в которые заносятся значения clientSocket
    ArrayList<Socket> listSocked = new ArrayList<>();
    //Массив, в которые заносятся значения nickname
    ArrayList<String> listNickname = new ArrayList<>();

    {
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Сервер запущен");
        } catch (IOException e) {

        }
    }

    //метод формирует поток с созданием клиентского соединения
    @Override
    public void run() {
        while (true) {

            try {
                Client client = new Client(serverSocket.accept());
                Thread clientThread = new Thread(client);
                clientThread.start();
            } catch (IOException e) {
                System.out.println("Обрыв соединения с клиентом");
            }
        }
    }

    //Класс реализует обмен сообщенями между сервером и клиентом
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
                BufferedWriter writerHiMessage = new BufferedWriter(new OutputStreamWriter(listSocked.get(i).getOutputStream(), "Cp1251"));
                writerHiMessage.write("Клиент: " + nickname + " присоединился к чату");
                writerHiMessage.newLine();
                writerHiMessage.flush();
            }
        }

        //метод реализует обмен сообщенями между сервером и клиентом
        @Override
        public void run() {
            try {
                while (true) {
                    BufferedReader readerMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "Cp1251"));
                    String Sreader1 = readerMessage.readLine();
                    System.out.println(Sreader1);

                    for (int i = 0; i < listSocked.size(); i++) {
                        BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(listSocked.get(i).getOutputStream(), "Cp1251"));
                        writerMessage.write(Sreader1);
                        writerMessage.newLine();
                        writerMessage.flush();
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
                            BufferedWriter writerExitMessage = new BufferedWriter(new OutputStreamWriter(listSocked.get(i).getOutputStream(), "Cp1251"));
                            writerExitMessage.write("Клиент: " + nickname + " вышел из чата.");
                            writerExitMessage.newLine();
                            writerExitMessage.flush();
                        }
                    }

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








