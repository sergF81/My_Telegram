import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        Thread serverThread = new Thread(server);
      //  serverThread.setDaemon(true); // Теперь этот поток будет завершаться при закрытии главного окна
        serverThread.start();

    }
}