import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * @author fortis@tochka.com
 * @created 27.08.19
 */

/*
 Данный класс реализует получение входящих сообщений с сервера
*/

public class Message {
    // переменная, которая содержит в себе сообшение дл явывода в окно чата
    String output_message = "";
    //массив для входящих сообщений
    ArrayList<String> message = new ArrayList<String>();
    //переменная для счетчика ограничения количества сообщений
    int y = 1;

    public Message() {
        try {

            while (true)
                if (Login.client == null) {
                    ChatInterface.textIn.repaint();
                } else {
                    output_message = "";
                    BufferedReader sms = new BufferedReader(new InputStreamReader(Login.client.getInputStream(), "Cp1251"));
                    message.add(sms.readLine());

                    // цикл для счетчика ограничения количесчтва сообщений
                    for (int i = 0; i < y; i++) {
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
