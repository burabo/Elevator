import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Botoneira implements Runnable{

    Scanner scanner = new Scanner(System.in);
    protected int option, i;
    Thread buttons;
    Cabin cabin;

    public Botoneira(Cabin cabin) {
        this.cabin = cabin;
        buttons = new Thread(this, "Botoneira");

        buttons.start();
    }
/*

    public void menu() throws InterruptedException {

        do {
            do {
                System.out.println("|***Informações***|\n");
                System.out.println("Encontra-se no piso " + cabin.getCurrentFloor());
                if (cabin.porta.isOpen())
                    System.out.println("As portas encontram-se abertas\n");
                else
                    System.out.println("As portas encontram-se fechadas\n");

                System.out.println("|***Botoneira***|\n");
                for (i = 0; i < cabin.FLOORS; i++)
                    System.out.println(i + ". Piso " + i);
                System.out.println((i++) + ". Abrir Porta");
                System.out.println((i++) + ". Fechar Porta\n");
                System.out.println("Introduza a opção pretendida: ");
                option = scanner.nextInt();
            } while (option < 0 && option > Cabin.FLOORS);


            System.out.println("PREMIU " + option);
            cabin.addFloor(option);
            //cabin.tryToOpenDoor();

        } while (option != Cabin.FLOORS+2);
    }

*/
    @Override
    public void run() {

        do {
            do {
                System.out.println("|***Informações***|\n");
                System.out.println("Encontra-se no piso " + cabin.getCurrentFloor());
                /*if (cabin.porta.isOpen())
                    System.out.println("As portas encontram-se abertas\n");
                else
                    System.out.println("As portas encontram-se fechadas\n");
                    */

                System.out.println("|***Botoneira***|\n");
                for (i = 0; i < cabin.FLOORS; i++)
                    System.out.println(i + ". Piso " + i);
                System.out.println((i++) + ". Abrir Porta");
                //System.out.println((i++) + ". Fechar Porta\n");
                System.out.println("Introduza a opção pretendida: ");
                option = scanner.nextInt();
            } while (option < 0 && option > Cabin.FLOORS+1);
            System.out.println("PREMIU " + option);
            if(option==cabin.FLOORS){
                try {
                    cabin.tryToOpenDoor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            cabin.addFloor(option);
        } while (option != Cabin.FLOORS+2);
    }
}
