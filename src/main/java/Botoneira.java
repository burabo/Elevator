import java.util.*;
import java.util.concurrent.Semaphore;

public class Botoneira extends Cabin{

    Scanner scanner = new Scanner(System.in);
    SortedSet<Integer> pressedFloors; // ???
    protected int option, i;


    public Botoneira() {
    }


    public void menu() {

        do {
            do {
                System.out.println("|***Informações***|\n");
                System.out.println("Encontra-se no piso " + getCurrentFloor());
                if (porta.isEstado())
                    System.out.println("As portas encontram-se abertas\n");
                else
                    System.out.println("As portas encontram-se fechadas\n");
                System.out.println("|***Botoneira***|\n");
                for (i = 0; i < FLOORS; i++)
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
                    changeFloor(option);
                        //Não há break visto que quando chega ao piso pretendido abrem-se as Portas
                case 5:
                        openDoor();
                        break;
            }

        } while (option != 9);
    }
}
