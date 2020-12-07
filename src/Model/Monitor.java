package Model;

import Model.Hilos.Cliente;

public class Monitor {

    //-------------------------------------> Recepcion <----------------------------------
    public synchronized void repEsp(){
        while (Config.clientes == Config.maxClientes) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Config.clientes++;
    }

    public synchronized void repEspVip(){
        while (Config.vip == Config.maxVip) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Config.vip++;
    }

    // ----------------------------------------------------> Cliente <---------------------------------------------------------
    public synchronized void clienteOrdena(int pos){
        //System.out.println("Cliente ordena: orden es "+orden);
        Config.mesas[pos].setEstado("orden");
        Config.pedidos.add(pos);
        Config.orden++;
        notifyAll();
        while (Config.mesas[pos].getEstado().equals("orden")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void clienteEspera(int pos){
        while (Config.mesas[pos].getEstado().equals("espera")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void clienteSeVa(int pos){
        Config.mesas[pos].setEstado("vacio");
        if(Config.mesas[pos].getTipo().equals("Vip")){
            Config.vip--;
        } else {
            Config.clientes--;
        }
    }

    // --------------------------------------------------> Cosinero <-------------------------------------------
    public synchronized int cocineroCocina(){
        while (Config.cocinar == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Config.cocinar--;
        int pos = Config.ordendes.remove(0);
        return pos;
    }

    public synchronized void cocineroEntrega(int pos){
        Config.comidas.add(pos);
        Config.comida++;
        notifyAll();
    }

    //--------------------------------------------------> Mesero <-------------------------------------------------
    public synchronized String meseroTomaSirveOrden(){
        System.out.println("Decide que hacer");
        while (Config.orden==0 && Config.comida==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(Config.comida != 0){
            Config.comida--;
            System.out.println("Toma o sirve orden: comida es "+Config.comida);
            return "comida";
        } else if (Config.orden != 0) {
            Config.orden--;
            System.out.println("Toma o sirve orden: orden es "+Config.orden);
            return "orden";
        }
        return "nada";
    }

    public synchronized int meseroTomaOrden(){
        System.out.println("Toma orden");
        int pos = Config.pedidos.remove(0);
        Config.mesas[pos].setEstado("espera");
        notifyAll();
        return pos;
    }

    public synchronized void meseroEntregaOrden(int pos){
        System.out.println("Entrega orden");
        Config.cocinar++;
        //System.out.println("Mesero Entrega Orden: cocinar es" + cocinar);
        Config.ordendes.add(pos);
        notifyAll();
    }

    public synchronized int meseroTomaComida(){
        System.out.println("Toma comida");
        int pos = Config.comidas.remove(0);
        return pos;
    }

    public synchronized void meseroEntregaComida (int pos){
        System.out.println("Entrega comida");
        Config.mesas[pos].setEstado("comiendo");
        notifyAll();
    }

    // ---------------------------------------> Recepcion part 2 <------------------------------------------

    public synchronized void agregarCliente(Cliente cliente){
        Config.clientess.add(cliente);
        Config.noEnColaInt++;
        notifyAll();
    }

    public synchronized void agregarClienteVip(Cliente cliente){
        Config.clientesVip.add(cliente);
        Config.noRecervacionesInt++;
        notifyAll();
    }

    public synchronized void compruebaClientes(){
        while (Config.noEnColaInt == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Cliente remueveCliente(){
        Cliente cliente = Config.clientess.remove(0);
        Config.noEnColaInt--;
        return cliente;
    }

    public synchronized void compruebaClientesVip(){
        while (Config.noRecervacionesInt == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Cliente remueveClienteVip(){
        Cliente cliente = Config.clientesVip.remove(0);
        Config.noRecervacionesInt--;
        return cliente;
    }

    public synchronized int mesaDesocupadaVip(){
        for(int i = Config.maxClientes; i < Config.mesas.length; i++){
            if(Config.mesas[i].getEstado().equals("vacio")){
                Config.mesas[i].setEstado("Ocupado");
                return i;
            }
        }
        return -50;
    }

    public synchronized int mesaDesocupada(){
        for(int i = 0; i< Config.maxClientes; i++){
            if(Config.mesas[i].getEstado().equals("vacio")){
                Config.mesas[i].setEstado("Ocupado");
                return i;
            }
        }
        return -50;
    }
}
