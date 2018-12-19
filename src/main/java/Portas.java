import java.util.concurrent.Semaphore;

public class Portas implements Runnable {

    /*
     * true: aberto; false: fechado
     */

    Semaphore sem;
    String nome;
    Thread t;
    boolean estado;

    public Portas(boolean estado){
        this.estado = estado;
    }

    public Portas(String thread, Semaphore sem) {
        this.nome = thread;
        this.sem = sem;
        t = new Thread(this, nome);
        System.out.println("Nova Thread " + t);
        t.start();
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
            try {
                System.out.println("Porta está a abrir");
                Thread.sleep(5000);
                estado = true;
                System.out.println("Porta aberta. Estado:" + estado);
                Thread.sleep(5000);
                System.out.println("Porta está a fechar");
                Thread.sleep(5000);
                estado = false;
                System.out.println("Porta fechada. Estado:" + estado);


            }catch (InterruptedException e) {
                System.out.println(nome + "Interrupted");
            }
            System.out.println(nome + " exiting.");
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
