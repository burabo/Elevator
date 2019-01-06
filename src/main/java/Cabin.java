import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Cabin {

    protected final int FLOORS = 5; // Default number of floors
    Semaphore moveSem; //New Semaphore Object for Motor
    Semaphore doorSem; //New Semaphore Object for Portas
    protected Portas porta; //New Portas Object
    protected static int currentFloor; //number of the current floor
    Logger logger = Logger.getLogger("MyLog"); //New Logger Object
    FileHandler fh; //new FileHandler Object

    //Constructor
    public Cabin(){
        moveSem = new Semaphore(1);
        doorSem = new Semaphore(1);
        porta = new Portas(false);
        currentFloor = 0;
        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeFloor(int option){

        new Motor("Motor thread", doorSem, moveSem, option);
        try {
            Thread.sleep(6000 * option);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
        logger(1);

    }

    public void openDoor(){

        new Portas("Porta thread ", doorSem, false); //Returns moveSem and ???
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
        logger(5);

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

    public void logger(int option){

        switch(option){
            case 1:
            case 2:
            case 3:
            case 4:
                logger.info("O elevador foi para o piso  " + option);
            case 5:
                logger.info("As portas foram abertas");
                break;
        }

    }

}
