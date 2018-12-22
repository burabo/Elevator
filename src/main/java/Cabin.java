import java.util.concurrent.Semaphore;

public class Cabin {
    Botoneira buttons;
    Portas doors;
    int currentFloor;
    Motor motor;
    Semaphore moveSem;

    public Cabin(Botoneira buttons, Portas doors, Motor motor, Semaphore moveSem) {
        this.buttons = buttons;
        this.doors = doors;
        this.motor = motor;
        this.moveSem = moveSem;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }


    public int getNextFloor(){
        return 0;

    }

    public void setCurrentFloor(int floor) throws Exception {
        if(floor<0){
            throw new Exception();
        }
        currentFloor = floor;
    }
}
