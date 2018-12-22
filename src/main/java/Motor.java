import java.util.concurrent.Semaphore;

public class Motor extends Thread{

    Semaphore doorSem;
    Cabin cabin;
    boolean goingUp;
    Semaphore moveSem;


    /**
     *
     */
    @Override
    public void run() {
        try {
            // acquiring the lock
            doorSem.acquire();
            moveSem.acquire();

            if(goingUp){
                cabin.currentFloor++;
            }
            else {
                cabin.currentFloor--;
            }


            //releasing the lock
            moveSem.release();
            doorSem.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param name the name of the new thread
     * @param doorSem semaforo das portas
     * @param moveSem semaforo de que é sinal para mover
     *
     */
    public Motor(String name, Semaphore doorSem, Semaphore moveSem, Cabin cabin) {
        super(name);
        this.doorSem = doorSem;
        this.moveSem = moveSem;
        this.cabin = cabin;
    }


}