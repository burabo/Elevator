import java.util.concurrent.Semaphore;


public class Cabin {

    final int FLOORS = 5; // Default number of floors
    Semaphore moveSem; //New Semaphore Object
    Semaphore doorSem; //New Semaphore Object
    Portas porta; //New Portas Object
    int currentFloor;

    public Cabin(){
        moveSem = new Semaphore(1);
        doorSem = new Semaphore(1);
        porta = new Portas(false);
        currentFloor = 0;
    }


    public void changeFloor(int option){

        new Motor("Motor thread", doorSem, moveSem, option);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");

    }

    public void openDoor(){

        new Portas("Porta thread ", doorSem, false); //Returns moveSem and ???
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");

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
    public int getNextFloor(){
        return currentFloor + 1;
    }

    /*
     * Returns the previous floor
     *
     */
    public int getPreviousFloor(){
        return currentFloor - 1;
    }

    /*
     * Define current floor(?)
     *
     */
    public void setCurrentFloor(int floor){ // throws Exception {
        /*
        if(floor<0){
            throw new Exception();
        }
        */
        currentFloor = floor;
    }
}
