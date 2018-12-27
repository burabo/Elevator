import java.util.concurrent.Semaphore;

public class Cabin {

    //Portas doors; //New Portas Object
    //Motor motor; //New Motor object
    Semaphore moveSem = new Semaphore(1); //New Semaphore Object
    Botoneira buttons = new Botoneira(moveSem); //New Botoneira Object
    int currentFloor;

    /*
    public Cabin(Botoneira buttons, Portas doors, Motor motor, Semaphore moveSem) {
        this.buttons = buttons;
        this.doors = doors;
        this.motor = motor;
        this.moveSem = moveSem;
    }
    */



    /*
     * Returns the current floor
     *
     */
    public int getCurrentFloor(){
        return currentFloor;
    }

    /*
     * Returns the next floor(?)
     *
     */
    public int getNextFloor(){
        return 0;

    }

    /*
     * Define current floor(?)
     *
     */
    public void setCurrentFloor(int floor) throws Exception {
        if(floor<0){
            throw new Exception();
        }
        currentFloor = floor;
    }
}
