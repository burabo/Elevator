import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        //Problema: Elevador começa sempre do piso 0
        Cabin cabin = new Cabin();
        Motor motor = new Motor();
        Botoneira botoneira = new Botoneira();
        botoneira.menu();
    }

}
