import java.util.concurrent.Semaphore;

public class Portas implements Runnable {

    /*
     * true: aberto; false: fechado
     */

    Semaphore sem;

    boolean estado;

    public Portas(){}

    public Portas(boolean estado, Semaphore sem) {
        this.estado = estado;
        this.sem = sem;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
