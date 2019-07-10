import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client {
    public static Socket client;
 //   public static String smm="ППЦ";
    public static JTextArea textIn;
    public static JScrollPane jsp;

    public static void main(String[] args) throws IOException {
      //    smm ="ППЦ";
        client = new Socket("127.0.0.1", 8000);



        new In(client, Chat.textIn, Chat.jsp);




    }


}
