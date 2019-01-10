import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Cabin {

    //Semaphore Objects
    Semaphore moveSem; //Motor Lock
    Semaphore doorSem; //Door Lock

    //Objects from Elevator
    Botoneira buttons; //Botoneira object

    //Objects related to floors
    SortedSet<Integer> pressedFloors; //Set that provides a total ordering on the floors
    Integer nextFloor; // Integer object that stores the next floor

    //Related to logger file
    Logger logger = Logger.getLogger("MyLog"); // Logger object used to log messages for the Elevator activity
    FileHandler fh; //FileHandler that write's to a specified file

    //Related to config file
    String fileName = "about.config"; // Configuration File
    InputStream is = null; //ImputStream object that read data from the config file
    Properties prop = new Properties(); //Properties Object

    //Primitive Data Types
    public final int FLOORS; // Value is  stored in the config file
    int direction = 1; //Direction of the Elevator. Can either be 1 or -1;
    int currentFloor = 0; //number of the current floor

    /*
     *Cabin constructor
     *
     */
    public Cabin(Semaphore doorSem){

        /*
         * Obtains the inputs in the config file and loads it
         *
         */
        try {
            is = new FileInputStream(fileName); //obtains input bytes from a file in a file system
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("MyLogFile.log"); //Gets the file
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter(); //Print log in a human readable format
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FLOORS = Integer.valueOf(prop.getProperty("FLOORS")); //Gets the floor in the config file
        pressedFloors = new TreeSet<>(); //Implementation of the SortedSet.
        moveSem = new Semaphore(0, true); //Semaphore with 0 permits and fair = true
        this.doorSem = doorSem;
        currentFloor = 0; //Sets the current floor to 0


    }

    /*
     * Sets Botoneira object from this class to equals Botoneira object
     * @param {Botoneira} some object
     */
    public void setButtons(Botoneira buttons){
        this.buttons = buttons;
    }

    /*
     * Opens Elevator Door
     *
     */
    public void tryToOpenDoor() throws InterruptedException {
        logger(FLOORS);
        doorSem.release();
        Thread.currentThread().sleep(30);
        buttons.menu();

    }

    public void goToNextSelectedFloor() throws InterruptedException {
        moveSem.release();
        Thread.currentThread().sleep(30);
        System.out.println("WENT TO NEXT FLOOR");


        pressedFloors.remove(currentFloor);
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
            logger(floor);
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

    /*
     * Logs a message on the logger
     * @param {option} some object
     */
    public void logger(int option){
        if(option<FLOORS){
            logger.info("O elevador foi para o piso  " + option);
        }
        else if(option==FLOORS){
            logger.info("As portas foram abertas");
        }
    }



    public static void main(String[] args) {

        Semaphore doorSem = new Semaphore(0,true);
        Portas porta = new Portas("Portas", doorSem, true);
        Cabin cabin = new Cabin( doorSem);
        Motor motor = new Motor("Motor XPTO 500", cabin, 1000);
        Botoneira botoneira = new Botoneira(cabin, porta);
        cabin.setButtons(botoneira);
        botoneira.menu();

    }
}
