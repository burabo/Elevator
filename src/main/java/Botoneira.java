import java.util.*;
import java.util.concurrent.Semaphore;

public class Botoneira {

    Semaphore moveSem = new Semaphore(1);
    int x, i;
    final int FLOORS = 5;
    Scanner scanner = new Scanner(System.in);
    Portas porta = new Portas(false);
    SortedSet<Integer> pressedFloors;

    public Botoneira(Semaphore moveSem) {
        pressedFloors = new TreeSet<Integer>();
        this.moveSem = moveSem;
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
                x = scanner.nextInt();
            } while (x > 0 && x < 5);

            pressedFloors.add(x);

            System.out.println("PREMIU " + x);
        } while (x != 0);
    }
}
