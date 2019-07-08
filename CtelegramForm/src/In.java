import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class In {
    String smm="ППЦ";
    String smm1 = "";
    ArrayList<String> message = new ArrayList<String>();
    int y = 1;
    public In(Socket client, JLabel textIn) {

        Chat frame = new Chat(smm);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.main(args);
        try {
            while (true) {


                BufferedReader sms = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //message.add(sms.readLine());
                smm = sms.readLine();
                textIn.setText(smm);


System.out.println(textIn.getText());
textIn.repaint();
             //  Chat.vivod(Client.smm);


                 //  textIn.setText(Client.smm);






            }
        } catch (IOException e) {

        }

    }

}