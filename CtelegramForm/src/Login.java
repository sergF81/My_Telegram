import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Login extends JFrame {
    public String nikname;
    public static Socket client;

    public Login() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel login = new JPanel();
        login.setBounds(100, 320, 250, 80);
        login.setLayout((LayoutManager) null);

        JLabel nikLabel = new JLabel("Ник: ");
        nikLabel.setBounds(55, 20, 150, 17);
        login.add(nikLabel);

        JTextField nik = new JTextField();
        nik.setBounds(82, 20, 150, 17);
        login.add(nik);

        JLabel server_adressLabel = new JLabel("Имя сервера: ");
        server_adressLabel.setBounds(2, 50, 150, 17);
        login.add(server_adressLabel);

        JTextField serverAdress = new JTextField();
        serverAdress.setBounds(82, 50, 150, 17);
        login.add(serverAdress);

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

        vvod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client = new Socket(serverAdress.getText(), 8000);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                nikname = nik.getText();

                System.out.println(nikname);
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(Login.client.getOutputStream(), "Cp1251"));
                    String Sreader = nikname;
                    writer.write(Sreader);
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