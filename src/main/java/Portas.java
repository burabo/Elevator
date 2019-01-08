import java.util.concurrent.Semaphore;

public class Portas implements Runnable {

    /*
     * true: aberto; false: fechado
     */
    Semaphore doorSem;
    String nome;
    Thread t;
    protected boolean estado;
    Boolean doorOpenButton;

    public Portas(boolean estado) {
        this.estado = estado;
    }

    public Portas(String thread, Semaphore doorSem, Boolean doorOpenButton) {
        this.nome = thread;
        this.doorSem = doorSem;
        t = new Thread(this, nome);
        t.start();
        this.doorOpenButton = doorOpenButton; //Estado do butao para abir a porta. Deve ter o comportamento esperado...
    }

    public boolean isOpen() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        while (!t.isInterrupted()) {
            try {
                doorSem.acquire();
                System.out.println("Porta está a abrir");
                Thread.sleep(3000);
                estado = true;
                System.out.println("Porta aberta. Estado:" + estado);
                Thread.sleep(5000);
                System.out.println("Porta está a fechar");
                Thread.sleep(3000);
                estado = false;
                System.out.println("Porta fechada. Estado:" + estado);


            } catch (InterruptedException e) {
                System.out.println(nome + "Interrupted");
            }System.out.println(nome + " exiting.");
        }
    }
}