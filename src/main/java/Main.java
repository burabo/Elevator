import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1);
        int x, i;
        int PISOS = 5;
        Scanner scanner = new Scanner(System.in);
        Portas porta = new Portas(false);

        do {
            do {
                System.out.println("|***Informações***|\n");
                System.out.println("Encontra-se no piso ");
                if (porta.isEstado())
                    System.out.println("As portas encontram-se abertas\n");
                else
                    System.out.println("As portas encontram-se fechadas\n");
                System.out.println("|***Botoneira***|\n");
                for(i = 1; i <= PISOS; i++)
                    System.out.println(i + ". Piso " + i);
                System.out.println((i++) + ". Abrir Porta");
                System.out.println((i++) + ". Fechar Porta\n");
                System.out.println("Introduza a opção pretendida: ");
                x = scanner.nextInt();
            } while (x > 0 && x < 5);
            switch (x) {
                case 1:
                     break;
                case 6:
                    new Portas("Porta thread ", sem);
                    try {
                        Thread.sleep(16000);
                    } catch (InterruptedException e) {
                        System.out.println("Main thread Interrupted");
                    }
                    System.out.println("Main thread exiting.");

                    break;
            }
        }while(x != 0);

    }

}
