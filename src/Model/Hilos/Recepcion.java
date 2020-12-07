package Model.Hilos;

import Model.Monitor;
import Model.Runner.CorrerRecepcion;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;

public class Recepcion extends Circle implements Observer {

    public Recepcion(Monitor monitor){
        this.setFill(Color.GREEN);
        this.setRadius(15);
        this.setStroke(Color.BLACK);
        this.setLayoutX(-500);
        this.setLayoutY(-500);

        CorrerRecepcion correr = new CorrerRecepcion(monitor);
        correr.addObserver(this);
        new Thread(correr).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        String respuesta = (String) arg;
        if(respuesta.equals("Entro")){
            this.setLayoutX(620);
            this.setLayoutY(523);
        } else if (respuesta.equals("Salio")) {
            this.setLayoutX(-500);
            this.setLayoutY(-500);
        }
    }
}
