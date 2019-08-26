import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Message {
    String output_message = "";   // переменная, которая содержит в себе сообшение дл явывода в окно чата
    ArrayList<String> message = new ArrayList<String>();
    int y = 1;  //переменная для счетчика ограничения количесчтва сообщений

    public Message() {
        try {

            while (true)
                if (Login.client == null) {
                    ChatInterface.textIn.repaint();
                } else {
                    output_message = "";
                    BufferedReader sms = new BufferedReader(new InputStreamReader(Login.client.getInputStream(), "Cp1251"));
                    message.add(sms.readLine());

                    for (int i = 0; i < y; i++) {  // цыкл для счетчика ограничения количесчтва сообщений
                        output_message = output_message + message.get(i) + "\n";
                        ChatInterface.textIn.setText(output_message);
                        ChatInterface.textIn.setCaretPosition(ChatInterface.textIn.getText().length());
                    }
                    y++;
                    if (y == 25) {
                        y--;
                        message.remove(0);
                    }

                    ChatInterface.textIn.repaint();
                }
        } catch (IOException e) {

        }
    }
}
