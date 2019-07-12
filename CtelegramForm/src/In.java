import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class In {
    static String smm="ППЦ";
    String smm1 = "";
    String nikname ="";
    ArrayList<String> message = new ArrayList<String>();
    int y = 1;
    public In(Socket client, JTextArea textIn) {

        try {
            while (true) {
                BufferedReader sms = new BufferedReader(new InputStreamReader(client.getInputStream(), "Cp1251"));
                message.add(sms.readLine());

                for (int i = 0; i < y; i++) {
                    smm1 = message.get(i);
                }
                y++;
                if (y == 100) {
                    y--;
                    message.remove(0);
                }

                textIn.setText(textIn.getText() + smm1+"\n");

System.out.println(textIn.getText());
textIn.repaint();

            }
        } catch (IOException e) {

        }

    }

}