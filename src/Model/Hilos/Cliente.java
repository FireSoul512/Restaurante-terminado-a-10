package Model.Hilos;

import Model.Mesas;
import Model.Monitor;
import Model.Runner.CorrerCliente;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;

public class Cliente extends Circle implements Observer {
    private Mesas mesa;
    private Monitor monitor;
    private int pos;

    public Cliente(Monitor monitor){
        this.setFill(Color.GREEN);
        this.setRadius(15);
        this.setStroke(Color.BLACK);
        this.monitor = monitor;
        this.setLayoutY(-500);
        this.setLayoutX(-500);
    }

    public void setMesa(Mesas mesa, int pos){;
        //System.out.println("Numero de recervaciones: "+ Config.noRecervacionesInt);
        this.mesa = mesa;
        this.pos = pos;
    }

    public void correr(){
        CorrerCliente correr = new CorrerCliente(monitor, pos);
        correr.addObserver(this);
        new Thread(correr).start();
    }


    @Override
    public void update(Observable o, Object arg) {
        String respuesta = (String) arg;
        if (respuesta.equals("Entrar")){
            this.setLayoutX(mesa.getPosX());
            this.setLayoutY(mesa.getPosY());
        } else if(respuesta.equals("Esparando orden"))
            setFill(Color.ORANGE);
        else if (respuesta.equals("Comiendo"))
            setFill(Color.RED);
        else if (respuesta.equals("Saliendo")){
            this.setLayoutX(-500);
        }
    }
}
