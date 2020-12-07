package Model.Runner;

import Model.Config;
import Model.Mesas;
import Model.Monitor;

import java.util.Observable;

public class CorrerMesero extends Observable implements Runnable {
    private Monitor monitor;

    public CorrerMesero(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true){
            Mesas mesas = new Mesas(0,0,"Descanso");
            this.setChanged();
            this.notifyObservers(mesas);
            String resp = monitor.meseroTomaSirveOrden();
            if (resp.equals("orden")){
                int pos = monitor.meseroTomaOrden();
                System.out.println("retorna pos: "+pos);
                this.setChanged();
                this.notifyObservers(Config.mesas[pos]);
                System.out.println("aver 1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aver 2");
                Mesas mesass = new Mesas(0,0,"Cocina");
                this.setChanged();
                this.notifyObservers(mesass);
                System.out.println("aver 3");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aver 4");
                monitor.meseroEntregaOrden(pos);
            } else {
                int pos = monitor.meseroTomaComida();
                Mesas mesass = new Mesas(0,0,"Cocina");
                this.setChanged();
                this.notifyObservers(mesass);
                System.out.println("retorna pos: "+pos);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aver 1");
                this.setChanged();
                this.notifyObservers(Config.mesas[pos]);
                System.out.println("aver 2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aver 3");
                monitor.meseroEntregaComida(pos);
            }
        }
    }
}
