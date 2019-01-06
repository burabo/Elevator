import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Botoneira extends Cabin{

    Scanner scanner = new Scanner(System.in);
    protected int i;

    java.awt.Frame frame = new java.awt.Frame("Botoneira");
    Button open = new Button("<>");
    Button close = new Button("><");
    Button floor0 = new Button("0");
    Button floor1 = new Button("1");
    Button floor2 = new Button("2");
    Button floor3 = new Button("3");
    Button floor4 = new Button("4");


    public Botoneira() {
        frame.add(open);
        frame.add(close);
        frame.add(floor0);
        frame.add(floor1);
        frame.add(floor2);
        frame.add(floor3);
        frame.add(floor4);
        frame.setSize(280, 150); //set the layout
        //specify the layout to have 2 rows and 2 columns
        frame.setLayout(new GridLayout(2, 2));
        frame.setVisible(true);
        menu();
    }


    public void menu() {

            System.out.println("|***Informações***|\n");
            System.out.println("Encontra-se no piso " + getCurrentFloor());
            if (porta.isEstado())
                System.out.println("As portas encontram-se abertas\n");
            else
                System.out.println("As portas encontram-se fechadas\n");


            floor0.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //ta1.append("Porta aberta!\n");
                    changeFloor(0);
                    openDoor();
                }
            });

        floor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta1.append("Porta aberta!\n");
                changeFloor(1);
                openDoor();
            }
        });

        floor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta1.append("Porta aberta!\n");
                changeFloor(2);
                openDoor();
            }
        });

        floor3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta1.append("Porta aberta!\n");
                changeFloor(3);
                openDoor();
            }
        });

        floor4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta1.append("Porta aberta!\n");
                changeFloor(4);
                openDoor();
            }
        });

            //Não há break visto que quando chega ao piso pretendido abrem-se as Portas

        open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //ta1.append("Porta aberta!\n");
                    openDoor();
                }
            });

    }
}
