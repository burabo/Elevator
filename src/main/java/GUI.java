import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    Cabin cabin;

    /*************************************************
     *Creates and displays a window of the class ButtonDemo.
     *************************************************/
    public static void main(String[] args)
    {
        //GUI buttonGui = new GUI();
        //buttonGui.setVisible(true);
    }
    final JTextArea textArea;

    public GUI(Cabin cabin)
    {
        this.cabin = cabin;
        setSize(WIDTH, HEIGHT);

        setTitle("Button Demo");
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.magenta);

        contentPane.setLayout(new FlowLayout());

        JButton[] buttons = new JButton[10];
        textArea= new JTextArea(10, 40);
        textArea.setEditable(false);
        getContentPane().add(BorderLayout.CENTER, textArea);

        for (int i = 0; i<Cabin.FLOORS; i++){
            buttons[i] = new JButton(""+i);
            buttons[i].addActionListener(this);
            contentPane.add(buttons[i]);
        }
    }

    public void actionPerformed(ActionEvent e){
        Container contentPane = getContentPane();
        String key =""+e.getActionCommand().charAt(0);
        textArea.append(key);
        System.out.println();
        cabin.addFloor(e.getActionCommand().charAt(0));

    }
}
