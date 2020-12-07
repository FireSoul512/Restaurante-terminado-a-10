package Model.Runner;

import Model.Config;
import Model.Hilos.Cliente;
import Model.Monitor;

import java.util.ArrayList;
import java.util.Observable;

public class CorrerRecepcion extends Observable implements Runnable {
    private Monitor monitor;

    public CorrerRecepcion(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        String estado;
        try {
            while (true){
                monitor.compruebaClientes();
                estado = "Entro";
                //System.out.println("aver recepion 1");
                this.setChanged();
                this.notifyObservers(estado);
                //System.out.println("aver recepion 2");
                monitor.repEsp();
                //System.out.println("aver recepion 3");
                Thread.sleep(1000);
                //System.out.println("aver recepion 4");
                int pos = monitor.mesaDesocupada();
                Cliente cliente = monitor.remueveCliente();
                cliente.setMesa(Config.mesas[pos], pos);
                estado = "Salio";
                this.setChanged();
                this.notifyObservers(estado);
                //System.out.println("aver recepion 5");
                //System.out.println("Posible Error");
                cliente.correr();
                // --------------------------------------------------------------> cliente.corer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
