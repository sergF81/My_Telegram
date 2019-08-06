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

        JTextField nik = new JTextField();
        nik.setBounds(20, 20, 150, 15);
        login.add(nik);

        JButton vvod_nika = new JButton("Ввод ника");
        vvod_nika.setOpaque(false);
        vvod_nika.setBounds(40, 40, 100, 23);
        vvod_nika.setBackground(Color.LIGHT_GRAY);
        login.add(vvod_nika);
        setContentPane(login);

        setPreferredSize(new Dimension(210, 120));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        vvod_nika.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                     client = new Socket("fortis", 8000);
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