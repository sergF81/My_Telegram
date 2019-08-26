import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ChatInterface extends JFrame {
    public static JTextArea textIn = new JTextArea();

    public ChatInterface(String output_message, String nikname) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel chatarea = new JPanel();
        chatarea.setLayout((LayoutManager) null);

        JTextArea textOut = new JTextArea();
        textOut.setLineWrap(true);
        textOut.setBounds(0, 220, 400, 100);
        chatarea.add(textOut);

        textIn.setLineWrap(true);
        textIn.setWrapStyleWord(true);
        textIn.setBackground(null);
        textIn.setBorder(null);
        textIn.setFocusable(false);
        textIn.setEditable(false);
        textIn.setText(output_message);
        textIn.setBounds(0, 0, 400, 200);
        textIn.setWrapStyleWord(true);
        textIn.setLineWrap(true);

        chatarea.add(textIn);

        JScrollPane okno_vivoda = new JScrollPane(textIn);
        okno_vivoda.setBounds(0, 0, 400, 200);
        chatarea.add(okno_vivoda);

        JButton vvod = new JButton("Ввод");
        vvod.setOpaque(false);
        vvod.setBounds(140, 325, 100, 23);
        vvod.setBackground(Color.LIGHT_GRAY);
        chatarea.add(vvod);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 400, 427);
        chatarea.add(background);
        setContentPane(chatarea);
        setPreferredSize(new Dimension(400, 427));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

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
      //  repaint();
    }

}