import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


/*
 * @author fortis@tochka.com
 * @created 27.08.19
 */

/*
 Данный класс реализует форму чата с полдями для воода и отображения сообщений
*/


public class ChatInterface extends JFrame {

    public static JTextArea textIn = new JTextArea();

    public ChatInterface(String output_message, String nikname) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //формироване общей панели  чата пользователя
        JPanel chatarea = new JPanel();
        chatarea.setBackground(new Color(225, 221, 255));
        chatarea.setLayout((LayoutManager) null);

        //создание метки с надписью Ник:
        JLabel textInLabel = new JLabel("Общий  чат:");
        textInLabel.setBounds(10, 5, 70, 10);
        chatarea.add(textInLabel);

        //создание текстового поля чата для вывода входящиъ сообщений
        textIn.setLineWrap(true);
        textIn.setWrapStyleWord(true);
        textIn.setBackground(null);
        textIn.setBorder(null);
        textIn.setFocusable(false);
        textIn.setEditable(false);
        textIn.setText(output_message);
        textIn.setBounds(10, 25, 400, 200);
        textIn.setWrapStyleWord(true);
        textIn.setLineWrap(true);
        textIn.setBackground(new Color(244, 250, 255));
        chatarea.add(textIn);

        //формирование окна прокрутки для текстового поля textIn
        JScrollPane okno_vivoda = new JScrollPane(textIn);
        okno_vivoda.setBounds(10, 25, 400, 200);
        chatarea.add(okno_vivoda);

        //создание метки с надписью Ваше сообщение:
        JLabel textOutLabel = new JLabel("Ваше сообщение:");
        textOutLabel.setBounds(10, 230, 170, 10);
        chatarea.add(textOutLabel);

        //создание текстового поля чата для ввода сообщения пользователя
        JTextArea textOut = new JTextArea();
        textOut.setLineWrap(true);
        textOut.setBounds(10, 250, 400, 100);
        textOut.setBackground(new Color(240, 248, 255));
        textOut.setForeground(Color.black);
        chatarea.add(textOut);

        //создание кнопки ввода Ввод
        JButton vvod = new JButton("Ввод");
        vvod.setOpaque(false);
        vvod.setBounds(140, 357, 100, 23);
        vvod.setBackground(Color.LIGHT_GRAY);
        chatarea.add(vvod);

        setContentPane(chatarea);
        setPreferredSize(new Dimension(435, 427));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //метод обработки нажания на кнопку ввода
        vvod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    BufferedWriter out_writer = new BufferedWriter(new OutputStreamWriter(Login.client.getOutputStream(), "Cp1251"));
                    String out_message = nikname + ": " + String.valueOf(textOut.getText());
                    out_writer.write(out_message);
                    out_writer.newLine();
                    out_writer.flush();
                    textOut.setText("");

                } catch (IOException r) {
                    r.printStackTrace();
                }
            }
        });
    }
}