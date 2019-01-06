import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class Cabin {

    static final int FLOORS = 9; // Default number of floors
    Semaphore moveSem; //Lock do movimento do motor
    Semaphore doorSem; //Lock da abertura das portas
    //protected Portas porta; //Referência para portas
    int currentFloor = 0;
    int direction = 1; //1 or -1;
    SortedSet<Integer> pressedFloors; //Conjunto Ordenado dos floors
    Integer nextFloor;
    Botoneira buttons;
    Properties prop = new Properties();
    String fileName = "about.config";
    InputStream is = null;


    public Cabin(Semaphore doorSem){
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FLOORS = Integer.valueOf(prop.getProperty("FLOORS"));
        pressedFloors = new TreeSet<>();
        moveSem = new Semaphore(0, true);
        this.doorSem = doorSem;
        //this.porta = porta;
        currentFloor = 0;
    }
    public void setButtons(Botoneira buttons){
        this.buttons = buttons;
    }

    public void tryToOpenDoor() throws InterruptedException {
        doorSem.release();
        Thread.currentThread().sleep(3000);
        doorSem.acquire();
        buttons.menu();

    }

    public void goToNextSelectedFloor() throws InterruptedException {
        moveSem.release();
        Thread.currentThread().sleep(3000);
        System.out.println("WENT TO NEXT FLOOR");

        moveSem.acquire();
        pressedFloors.remove(currentFloor);
        tryToOpenDoor();
    }

    public void determineNextFloor(){
        if(!pressedFloors.subSet(currentFloor+1,FLOORS).isEmpty()){
            if(direction==1){
                nextFloor=pressedFloors.subSet(currentFloor+1,FLOORS).first();
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
        else if(!pressedFloors.headSet(currentFloor).isEmpty()){
            if(direction==-1){
                nextFloor=pressedFloors.headSet(currentFloor).last();
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
        else {
            nextFloor=null;
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


    public void menu() throws InterruptedException {

    }

    public static void main(String[] args) {


        //Boolean doorOpenButton = true;
        Semaphore doorSem = new Semaphore(0,true);
        Portas porta = new Portas("Portas", doorSem, true);
        Cabin cabin = new Cabin( doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 1000);
        Botoneira botoneira = new Botoneira(cabin, porta);
        cabin.setButtons(botoneira);

/*
        Semaphore doorSem = new Semaphore(0);
        Cabin cabin = new Cabin( doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 1000);

        cabin.addFloor(2);

        print("Current floor is \t"+cabin.currentFloor);
        print("Amount of pressed Floors is \t"+cabin.pressedFloors.size());
        print("Next floor is \t"+cabin.nextFloor);
        print("Direction is \t"+cabin.direction);
*/
    }
}
