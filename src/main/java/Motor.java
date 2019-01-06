import java.util.concurrent.Semaphore;
import java.util.*;

import static java.lang.Math.abs;

public class Motor implements Runnable {

    Semaphore moveSem;
    String nome;
    Thread motor;
    Cabin cabin;
    int timeBetweenFloors;
    //boolean goingUp;


    public Motor(String nome, Cabin cabin, int timeBetweenFloors){
        this.nome = nome;
        this.moveSem = cabin.moveSem;
        this.cabin = cabin;
        this.timeBetweenFloors = timeBetweenFloors;
        motor = new Thread(this, nome);
        System.out.println("Nova Thread " + nome);
        motor.start();
    }

    public Motor(){
        motor = new Thread(this, "Motor");
    }

    public void run() {
        while (!motor.isInterrupted()) { //Enquanto não for interrompido
            try {
                //acquiring lock
                moveSem.acquire();
                while (cabin.currentFloor!=cabin.nextFloor){
                    Thread.sleep(timeBetweenFloors);
                    cabin.currentFloor+=cabin.direction;
                    System.out.println("IN FLOOR"+cabin.currentFloor);
                }
                System.out.println("SIZE  "+cabin.pressedFloors.size());
                //releasing the lock
                moveSem.release();
            } catch (InterruptedException e) {
                System.err.println("MOTOR INTERROMPIDO");
            }
        }
    }
    /*
    public Motor(String name, Semaphore doorSem, Semaphore moveSem, Cabin cabin) {
        super(name);
        this.doorSem = doorSem;
        this.moveSem = moveSem;
        this.cabin = cabin;
    }
    */


}