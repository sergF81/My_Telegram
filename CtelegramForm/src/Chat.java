import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Chat extends JFrame {
     public static JLabel textIn = new JLabel();



    public Chat(String smm) {


        JPanel chatarea = new JPanel();
        chatarea.setLayout((LayoutManager) null);

        JTextField textOut = new JTextField();
        textOut.setBounds(0, 0, 400, 100);
        chatarea.add(textOut);

      //  JLabel textIn = new JLabel();
        textIn.setText(smm);
        textIn.setBounds(0, 120, 400, 200);
        chatarea.add(textIn);


        JButton vvod = new JButton("RUB");
        vvod.setOpaque(false);
        vvod.setBounds(140, 385, 100, 23);
        vvod.setBackground(Color.LIGHT_GRAY);
        chatarea.add(vvod);

        JLabel background = new JLabel(new ImageIcon("bankomat.jpg"));
        background.setBounds(0, 0, 690, 727);
        chatarea.add(background);
        setContentPane(chatarea);
        setPreferredSize(new Dimension(690, 727));

        vvod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                        String Sreader = textOut.getText();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Client.client.getOutputStream()));
                        writer.write(": " + Sreader);
                        writer.newLine();
                        writer.flush();

                        textOut.setText("");

                } catch (IOException r) {
                    r.printStackTrace();
                }
            }
        });





        }
   // }
}