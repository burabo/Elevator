import java.util.concurrent.Semaphore;
import java.util.*;

public class Motor extends Cabin implements Runnable {

    Semaphore doorSem;
    Semaphore moveSem;
    String nome;
    Thread motor;
    //boolean goingUp;
    protected int selectedFloor;

    /**
     *
     */
    @Override
    public void run() {
        try {
            moveSem.acquire();
            try {
            if (Integer.compare(selectedFloor, getCurrentFloor()) == 0){
                System.out.println("Já está no piso seleccionado");
            }else if (Integer.compare(selectedFloor, getCurrentFloor()) < 0){
                System.out.println("Elevador está a descer");
                while(getCurrentFloor() > selectedFloor) {
                    Thread.sleep(5000);
                    currentFloor--;
                    System.out.println("Piso " + getCurrentFloor());
                }
                System.out.println("Já chegamos!");
            }else{
                System.out.println("Elevador está a subir");
                while(getCurrentFloor() < selectedFloor) {
                    Thread.sleep(5000);
                    currentFloor++;
                    System.out.println("Piso " + getCurrentFloor());

                }
                System.out.println("Já chegamos!");
            }

            }catch (InterruptedException e) {
                System.out.println(nome + "Interrupted");
            }
            System.out.println(nome + " exiting.");
            //releasing the lock
            moveSem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Motor(){}

    public Motor(String thread, Semaphore doorSem, Semaphore moveSem, int option){
        this.nome = thread;
        this.moveSem = moveSem;
        this.doorSem = doorSem;
        this.selectedFloor = option;
        motor = new Thread(this, nome);
        System.out.println("Nova Thread " + motor);
        motor.start();
    }




    /**
     *
     * @param name the name of the new thread
     * @param doorSem semaforo das portas
     * @param moveSem semaforo de que é sinal para mover
     *
     */
    /*
    public Motor(String name, Semaphore doorSem, Semaphore moveSem, Cabin cabin) {
        super(name);
        this.doorSem = doorSem;
        this.moveSem = moveSem;
        this.cabin = cabin;
    }
    */


}