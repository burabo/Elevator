import java.io.IOException;
import java.util.SortedSet;
import java.util.concurrent.Semaphore;


public class Cabin {

    protected final int FLOORS = 5; // Default number of floors
    Semaphore moveSem; //Lock do movimento do motor
    Semaphore doorSem; //Lock da abertura das portas
    protected Portas porta; //Referência para portas
    int currentFloor;
    int direction; //1 or -1;
    SortedSet<Integer> pressedFloors;
    int nextFloor;

    public Cabin(Portas porta, Semaphore doorSem){
        moveSem = new Semaphore(1);
        this.porta = porta;
        currentFloor = 0;
    }

    public void tryToOpenDoor() throws InterruptedException {
        if(moveSem.tryAcquire())
        moveSem.acquire();
        doorSem.release();
        Thread.sleep(3000);
        doorSem.acquire();
        moveSem.acquire();
    }

    public void goToNextFloor() throws InterruptedException {

        moveSem.release();
        Thread.sleep(10);
        moveSem.acquire();
    }

    /*
     * Returns the current floor
     *
     */
    public int getCurrentFloor(){
        return currentFloor;
    }

    /*
     * Returns the next floor
     *
     */
    public void determineNextFloor(){
        if(direction==1){
            nextFloor = pressedFloors.subSet(currentFloor+1, FLOORS).first();
        }
        else if(direction==-1) {
            nextFloor = pressedFloors.headSet(currentFloor).last();
        }
    }

    public static void main(String[] args) {

        //Problema: Elevador começa sempre do piso 0
        Boolean doorOpenButton = true;
        Semaphore doorSem = new Semaphore(1);
        Portas porta = new Portas("Portas", doorSem, doorOpenButton);
        Cabin cabin = new Cabin(porta, doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 5000);
        Botoneira botoneira = new Botoneira(cabin);
        try {
            botoneira.menu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
