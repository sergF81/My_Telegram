import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Message
{

    String smm1 = "";
    ArrayList<String> message = new ArrayList<String>();
    int y = 1;

    public Message()
    {
        try
        {

                    while (true)
                        if (Login.client == null)
                        {
                            ChatInterface.textIn.setText(ChatInterface.textIn.getText() + "\n");
                            ChatInterface.textIn.repaint();
                        } else
                            {smm1="";
                                BufferedReader sms = new BufferedReader(new InputStreamReader(Login.client.getInputStream(), "Cp1251"));
                                message.add(sms.readLine());

                                for (int i = 0; i < y; i++)
                                {
                                 //   smm1 = message.get(i);
                                    smm1 = smm1 + message.get(i)+"\n";
                                    System.out.println(smm1);
                                    ChatInterface.textIn.setText(smm1);
                                }
                                y++;
                                if (y == 7)
                                {
                                    y--;
                                    message.remove(0);


                                }

                           //     ChatInterface.textIn.setText(ChatInterface.textIn.getText() + smm1 + "\n");

                                ChatInterface.textIn.repaint();
                               // System.out.println(ChatInterface.textIn.getText() + smm1 + "\n");
                                //System.out.println(smm1);
                            }


        } catch (IOException e)
        {

        }
    }
}
