import java.util.concurrent.Semaphore;

public class Motor extends Thread{

    Semaphore sem;



    /**
     *
     */
    @Override
    public void run() {
        try {
            // acquiring the lock
            sem.acquire();



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}