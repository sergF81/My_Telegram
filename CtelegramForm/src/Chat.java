import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Chat extends JFrame {
     public static JTextArea textIn = new JTextArea();
    public static JPanel chatarea;
  // public static JScrollPane jsp = new JScrollPane(textIn, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private String nikname;

    public Chat(String smm, String nikname) {



        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel chatarea = new JPanel();
        chatarea.setLayout((LayoutManager) null);

        JTextArea textOut = new JTextArea();
        textOut.setLineWrap(true);
        textOut.setBounds(0, 0, 400, 100);
        chatarea.add(textOut);

        textIn.setLineWrap(true);
        textIn.setWrapStyleWord(true);
        textIn.setBackground(null);
        textIn.setBorder(null);
        textIn.setFocusable(false);
        textIn.setEditable(false);

        textIn.setText(smm);
        textIn.setBounds(0, 120, 400, 200);
        textIn.setWrapStyleWord(true);
        textIn.setLineWrap(true);
        chatarea.add(textIn);

        JScrollPane jsp = new JScrollPane(textIn);
        jsp.setBounds(0, 120, 400, 200);
        chatarea.add(jsp);



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



        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        vvod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String Sreader = textOut.getText();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Client.client.getOutputStream()));
                    writer.write(nikname + ": " + Sreader);
                    writer.newLine();
                    writer.flush();

                    textOut.setText("");

                } catch (IOException r) {
                    r.printStackTrace();
                }
            }
        });
        repaint();
    }



   // }
}