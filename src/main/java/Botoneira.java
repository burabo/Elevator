import java.util.*;
import java.util.concurrent.Semaphore;

public class Botoneira {

    final int FLOORS = 5; // Default number of floors
    Scanner scanner = new Scanner(System.in);
    Semaphore moveSem; //moveSem was already created in Cabin
    Portas porta = new Portas(false); //New Portas Object
    SortedSet<Integer> pressedFloors; // ???
    int option, i;

    public Botoneira(Semaphore moveSem) {
        pressedFloors = new TreeSet<Integer>(); //???
        this.moveSem = moveSem; //this.moveSem = moveSem from Cabin
        menu(); //Runs menu()
    }

    public void menu() {

        do {
            do {
                System.out.println("|***Informações***|\n");
                System.out.println("Encontra-se no piso ");
                if (porta.isEstado())
                    System.out.println("As portas encontram-se abertas\n");
                else
                    System.out.println("As portas encontram-se fechadas\n");
                System.out.println("|***Botoneira***|\n");
                for (i = 1; i <= FLOORS; i++)
                    System.out.println(i + ". Piso " + i);
                System.out.println((i++) + ". Abrir Porta");
                System.out.println((i++) + ". Fechar Porta\n");
                System.out.println("Introduza a opção pretendida: ");
                option = scanner.nextInt();
            } while (option > 0 && option < 5);

            pressedFloors.add(option); //???

            System.out.println("PREMIU " + option);

            switch (option) {
                case 1:
                    break;
                case 6:
                    new Portas("Porta thread ", moveSem, false); //Returns moveSem and ???
                    try {
                        Thread.sleep(16000);
                    } catch (InterruptedException e) {
                        System.out.println("Main thread Interrupted");
                    }
                    System.out.println("Main thread exiting.");

                    break;
            }

        } while (option != 0);
    }
}
