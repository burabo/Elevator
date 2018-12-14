import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int x;
        Scanner scanner = new Scanner(System.in);
        Portas porta = new Portas(false);

        do {
            do {
                System.out.println(" Informações");
                System.out.println("Encontra-se no piso ");
                if (porta.isEstado())
                    System.out.println("As portas encontram-se abertas");
                else
                    System.out.println("As portas encontram-se fechadas");
                System.out.println("Menu");
                System.out.println("1. Abrir/ Fechar Portas");
                System.out.println("2. Seleccionar Piso");
                System.out.println("Introduza a opção pretendida: ");
                x = scanner.nextInt();
            } while (x > 0 && x < 5);
            switch (x){
                case 1:
                   break;
            }
        }while(x != 0);
        }

}
