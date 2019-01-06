import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class Cabin {

    static final int FLOORS = 5; // Default number of floors
    Semaphore moveSem; //Lock do movimento do motor
    Semaphore doorSem; //Lock da abertura das portas
    //protected Portas porta; //Referência para portas
    int currentFloor = 0;
    int direction = 1; //1 or -1;
    SortedSet<Integer> pressedFloors; //Conjunto Ordenado dos floors
    Integer nextFloor;

    public Cabin(Semaphore doorSem){
        pressedFloors = new TreeSet<>();
        moveSem = new Semaphore(0);
        this.doorSem = doorSem;
        //this.porta = porta;
        currentFloor = 0;
    }

    public void tryToOpenDoor() throws InterruptedException {
        if(moveSem.tryAcquire()) {
            doorSem.release();
            Thread.sleep(3000);
            doorSem.acquire();
            moveSem.release();
        }
    }

    public void goToNextSelectedFloor() throws InterruptedException {
        moveSem.release();
        Thread.sleep(1000);
        moveSem.acquire();
        pressedFloors.remove(currentFloor);
    }

    public void determineNextFloor(){
        if(direction==1){
            if(!pressedFloors.subSet(currentFloor+1,FLOORS).isEmpty()){
                nextFloor=pressedFloors.subSet(currentFloor+1,FLOORS).first();
                try {
                    goToNextSelectedFloor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                direction=-1;
                determineNextFloor();
            }
        }
        if(direction==-1){
            if(!pressedFloors.headSet(currentFloor).isEmpty()){
                nextFloor=pressedFloors.headSet(currentFloor).last();
                try {
                    goToNextSelectedFloor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                direction=1;
                determineNextFloor();
            }
        }
    }

    public void addFloor(int floor){
        if(currentFloor!=floor && floor<FLOORS && floor>=0) {
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

    public static void print(String str){
        System.out.println(str);
    }

    public static void main(String[] args) {

        /*

        Boolean doorOpenButton = true;
        Semaphore doorSem = new Semaphore(1);
        //Portas porta = new Portas("Portas", doorSem, doorOpenButton);
        Cabin cabin = new Cabin( doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 1000);
        Botoneira botoneira = new Botoneira(cabin);
        GUI botons = new GUI(cabin);
        botons.setVisible(true);
        */



        Semaphore doorSem = new Semaphore(1);
        Cabin cabin = new Cabin( doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 1000);

        cabin.addFloor(2);

        print("Current floor is \t"+cabin.currentFloor);
        print("Amount of pressed Floors is \t"+cabin.pressedFloors.size());
        print("Next floor is \t"+cabin.nextFloor);
        print("Direction is \t"+cabin.direction);

    }
}
