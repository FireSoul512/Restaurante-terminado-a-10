package Model.Hilos;

import Model.Monitor;
import Model.Runner.CorrerCocinero;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;

public class Cocinero extends Rectangle implements Observer {

    public Cocinero(int x, int y, Monitor monitor){
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setWidth(30);
        this.setHeight(30);
        this.setFill(Color.rgb(38,255,31));
        this.setStroke(Color.BLACK);

        CorrerCocinero correr = new CorrerCocinero(monitor);
        correr.addObserver(this);
        new Thread(correr).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        String respuesta = (String) arg;
        if (respuesta.equals("Esperando"))
            this.setFill(Color.rgb(38,255,31));
        else if (respuesta.equals("Cocinando"))
            this.setFill(Color.ORANGE);
    }
}
