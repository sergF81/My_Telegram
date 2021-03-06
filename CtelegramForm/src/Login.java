import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
 * @author fortis@tochka.com
 * @created 27.08.19
 */

/*
 Данный класс реализует форму ввода логина и ввода адреса сервера чата
*/


public class Login extends JFrame {
    //Переменная nickname - содержит значение ника пользователя
    public String nikname;
    //Переменная client - содержит значение сокета пользователя
    public static Socket client;

    public Login() {

        //формироване общей панели для входа пользователя
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel login = new JPanel();
        login.setBounds(100, 320, 250, 80);
        login.setLayout((LayoutManager) null);

        //создание метки с надписью Ник:
        JLabel nikLabel = new JLabel("Ник: ");
        nikLabel.setBounds(55, 20, 150, 17);
        login.add(nikLabel);

        //создание текстового поля для ввода ника(логина) пользователя
        JTextField nik = new JTextField();
        nik.setBounds(82, 20, 150, 17);
        login.add(nik);

        //создание метки с надписью Имя сервера:
        JLabel server_adressLabel = new JLabel("Имя сервера: ");
        server_adressLabel.setBounds(2, 50, 150, 17);
        login.add(server_adressLabel);

        //создание текстового поля для ввода имени сервера чата
        JTextField serverAdress = new JTextField();
        serverAdress.setBounds(82, 50, 150, 17);
        login.add(serverAdress);

        //создание кнопки ввода Принять
        JButton vvod = new JButton("Принять");
        vvod.setOpaque(false);
        vvod.setBounds(80, 90, 150, 23);
        vvod.setBackground(Color.LIGHT_GRAY);
        login.add(vvod);
        setContentPane(login);

        setPreferredSize(new Dimension(270, 160));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //метод обработки нажания на кнопку ввода
        vvod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client = new Socket(serverAdress.getText(), 8000);

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Login.client.getOutputStream(), "Cp1251"));
                    nikname = nik.getText();
                    System.out.println(nikname);
                    writer.write(nikname);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                new ChatInterface("", nikname);
                setVisible(false);
            }
        });
    }
}