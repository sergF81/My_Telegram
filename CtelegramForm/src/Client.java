import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client {
    public static Socket client;

    public static void main(String[] args) throws IOException {

        client = new Socket("fortis", 8000);
        Login frame1 = new Login(client);
        new In(client, Chat.textIn);

    }

}
