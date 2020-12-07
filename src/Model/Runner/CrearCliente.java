package Model.Runner;

import java.util.Observable;

public class CrearCliente extends Observable implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setChanged();
            this.notifyObservers();
        }

    }
}
