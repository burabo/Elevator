import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main{

    public static void main(String[] args) {

        JFrame f = new JFrame("Elevador");
        f.setSize(250, 250);
        f.setLocation(300,200);
        final JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        f.getContentPane().add(BorderLayout.CENTER, textArea);
        final JButton open = new JButton("<>");
        final JButton close = new JButton("><");
        f.getContentPane().add(BorderLayout.SOUTH, open);
        f.getContentPane().add(BorderLayout.SOUTH, close);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Porta aberta!\n");
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Porta fechada!\n");
            }
        });

        f.setVisible(true);

    }

}