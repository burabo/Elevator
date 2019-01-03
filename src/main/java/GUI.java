import javax.swing.*;
import java.awt.*;

public class GUI {
    private JButton a1Button;
    private JTextField floor1TextField;
    private JButton a2Button;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(300,200);
    }
}
