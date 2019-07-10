import javax.swing.*;
import java.awt.*;
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
    public In(Socket client, JTextArea textIn) {

        Chat frame = new Chat(smm);
      //  frame.add(scroll);
       // frame.pack();
      //  frame.setLocationRelativeTo(null);
      //  frame.setVisible(true);
        //frame.main(args);
        try {
            while (true) {


                BufferedReader sms = new BufferedReader(new InputStreamReader(client.getInputStream()));

                message.add(sms.readLine());
                //smm1 = sms.readLine();

                for (int i = 0; i < y; i++) {
                    // smm = "<html>"+smm + "p"+ "a</html>";


                    //  System.out.println(smm);

                    //  textIn = new JLabel(smm);


                    //smm1 = smm1 + smm + "<html></p></html>";
                    smm1 = message.get(i);

                }

                y++;

                if (y == 100) {
                    //     i = 99;
                    y--;
                    message.remove(0);

                }



           //     smm1 = smm1+smm+ "<html><br>-"+"</html>";
                textIn.setText(textIn.getText() + smm1+"\n");


System.out.println(textIn.getText());
textIn.repaint();
             //  Chat.vivod(Client.smm);


                 //  textIn.setText(Client.smm);






            }
        } catch (IOException e) {

        }

    }

}