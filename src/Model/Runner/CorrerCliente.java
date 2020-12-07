package Model.Runner;
import Model.Monitor;

import java.util.Observable;

public class CorrerCliente extends Observable implements Runnable {
    private Monitor monitor;
    private int pos;

    public CorrerCliente(Monitor monitor, int pos){
        this.pos = pos;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        this.setChanged();
        this.notifyObservers("Entrar");
        monitor.clienteOrdena(pos);
        this.setChanged();
        this.notifyObservers("Esparando orden");
        monitor.clienteEspera(pos);
        this.setChanged();
        this.notifyObservers("Comiendo");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setChanged();
        this.notifyObservers("Saliendo");
        monitor.clienteSeVa(pos);
    }
}
