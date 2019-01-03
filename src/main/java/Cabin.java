import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

import static java.lang.Math.abs;

public class Cabin {

    static final int FLOORS = 5; // Default number of floors
    Semaphore moveSem; //Lock do movimento do motor
    Semaphore doorSem; //Lock da abertura das portas
    protected Portas porta; //Referência para portas
    int currentFloor = 0;
    int direction = 1; //1 or -1;
    SortedSet<Integer> pressedFloors; //Conjunto Ordenado dos floors
    Integer nextFloor;

    public Cabin(Portas porta, Semaphore doorSem){
        pressedFloors = new TreeSet<>();
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
        moveSem.release();
    }

    public void goToNextSelectedFloor() throws InterruptedException {
        moveSem.release();
        Thread.sleep(1000);
        moveSem.acquire();
        pressedFloors.remove(currentFloor);
    }

    public void determineNextFloor(){
        if(direction==1 && !pressedFloors.subSet(currentFloor+1, FLOORS).isEmpty()){
        }
        if(direction==1 && pressedFloors.subSet(currentFloor+1, FLOORS).isEmpty()){
            if(pressedFloors.headSet(currentFloor).isEmpty()){
                nextFloor=null;
                return;
            }
            else {
                direction=-1;
                nextFloor=pressedFloors.headSet(currentFloor).last();
            }
        }
    }

    public void addFloor(int floor){
        if(pressedFloors.isEmpty()){
            pressedFloors.add(floor);
            determineNextFloor();
        }
    }

    /*
     * Returns the current floor
     *
     */
    public int getCurrentFloor(){
        return currentFloor;
    }

    public static void main(String[] args) {

        Boolean doorOpenButton = true;
        Semaphore doorSem = new Semaphore(1);
        Portas porta = new Portas("Portas", doorSem, doorOpenButton);
        Cabin cabin = new Cabin(porta, doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 5000);
        Botoneira botoneira = new Botoneira(cabin);

    }
}
