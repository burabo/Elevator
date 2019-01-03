import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Botoneira{

    Scanner scanner = new Scanner(System.in);
    protected int option, i;
    Cabin cabin;

    public Botoneira(Cabin cabin) {
        this.cabin = cabin;
    }


    public void menu() throws IOException, InterruptedException {

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
            } while (option < 0 && option > 5);

//            pressedFloors.add(option); //???

            System.out.println("PREMIU " + option);

            switch (option) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    cabin.pressedFloors.add(option);
                    cabin.determineNextFloor();
                case 5:
                        cabin.tryToOpenDoor();
                        break;
            }
        } while (option != 9);
    }
}
