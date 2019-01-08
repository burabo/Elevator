import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Botoneira implements Runnable{
    java.awt.Frame frame = new java.awt.Frame("Botoneira");
    Button open = new Button("<>");
    Button close = new Button("><");
    Cabin cabin;
    Portas porta;
    Thread t = new Thread(this, "Buttons");

    Button[] buttons;


    public Botoneira(Cabin cabin, Portas porta) {
        this.cabin = cabin;
        this.porta = porta;

        buttons = new Button[cabin.FLOORS];
        t.start();
    }


    public void menu() {

            System.out.println("|***Informações***|\n");
            System.out.println("Encontra-se no piso " + cabin.getCurrentFloor());
            if (porta.isOpen())
                System.out.println("As portas encontram-se abertas\n");
            else
                System.out.println("As portas encontram-se fechadas\n");

    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
            for (int i = 0; i < cabin.FLOORS; i++) {
                buttons[i] = new Button("" + i);
                final int indx = i;

                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //ta1.append("Porta aberta!\n");
                        cabin.addFloor(indx);
                        try {
                            cabin.tryToOpenDoor();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                frame.add(buttons[i]);
            }
            frame.add(open);
            frame.add(close);

            frame.setSize(280, 150); //set the layout
            //specify the layout to have 2 rows and 2 columns
            frame.setLayout(new GridLayout(2, 2));
            frame.setVisible(true);

    }
}