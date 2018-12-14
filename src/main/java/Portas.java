public class Portas implements Runnable {

    /*
     * true: aberto; false: fechado
     */
    boolean estado;

    public Portas(){}

    public Portas(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void run() {

    }
}
