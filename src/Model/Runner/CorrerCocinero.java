package Model.Runner;

import Model.Monitor;

import java.util.Observable;

public class CorrerCocinero extends Observable implements Runnable  {
    private Monitor monitor;

    public CorrerCocinero(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true){
            this.setChanged();
            this.notifyObservers("Esperando");
            int pos = monitor.cocineroCocina();
            this.setChanged();
            this.notifyObservers("Cocinando");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitor.cocineroEntrega(pos);
        }
    }
}
