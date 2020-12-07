package Model.Hilos;

import Model.Monitor;

import Model.Runner.CorrerRecepcionVip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RecepcionVip extends Circle implements Observer {
    public RecepcionVip(Monitor monitor){
        this.setFill(Color.GREEN);
        this.setRadius(15);
        this.setStroke(Color.BLACK);
        this.setLayoutX(-500);
        this.setLayoutY(-500);

        CorrerRecepcionVip correr = new CorrerRecepcionVip(monitor);
        correr.addObserver(this);
        new Thread(correr).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        String respuesta = (String) arg;
        if(respuesta.equals("Entro")){
            this.setLayoutX(730);
            this.setLayoutY(520);
        } else if (respuesta.equals("Salio")) {
            this.setLayoutX(-500);
            this.setLayoutY(-500);
        }
    }
}
