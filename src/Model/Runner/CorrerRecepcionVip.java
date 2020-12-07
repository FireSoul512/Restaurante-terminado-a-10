package Model.Runner;

import Model.Config;
import Model.Hilos.Cliente;
import Model.Monitor;

import java.util.ArrayList;
import java.util.Observable;

public class CorrerRecepcionVip extends Observable implements Runnable {
    private Monitor monitor;

    public CorrerRecepcionVip(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        String estado;
        try {
            while (true){
                monitor.compruebaClientesVip();
                estado = "Entro";
                Thread.sleep(2000);
                //System.out.println("aver vip 1");
                this.setChanged();
                this.notifyObservers(estado);
                //System.out.println("aver vip 2");
                monitor.repEspVip();
                //System.out.println("aver vip 3");
                Thread.sleep(1000);
                //System.out.println("aver vip 4");
                int pos = monitor.mesaDesocupadaVip();
                if(pos >= (Config.maxVip+Config.maxClientes))
                    System.out.println("Es mayor we");
                Cliente cliente = monitor.remueveClienteVip();
                cliente.setMesa(Config.mesas[pos], pos);
                estado = "Salio";
                this.setChanged();
                this.notifyObservers(estado);
                //System.out.println("aver vip 5");
                cliente.correr();
                // --------------------------------------------------------------> cliente.corer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
