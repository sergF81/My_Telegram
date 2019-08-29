import java.io.IOException;

/*
 * @author fortis@tochka.com
 * @created 27.08.19

 Данный класс реализует запуск сервера чата
*/

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
}
