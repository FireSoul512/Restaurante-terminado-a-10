package Model.Hilos;

import Model.Mesas;
import Model.Monitor;
import Model.Runner.CorrerMesero;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;

public class Mesero extends Circle implements Observer {
    private int x;
    private int y;

    public Mesero(int posX, int posY, Monitor monitor){
        this.x = posX;
        this.y = posY;
        this.setRadius(15);
        this.setFill(Color.rgb(70,70,70));
        this.setStroke(Color.BLACK);
        this.setLayoutX(posX);
        this.setLayoutY(posY);

        CorrerMesero correr = new CorrerMesero(monitor);
        correr.addObserver(this);
        new Thread(correr).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Mesas mesa = (Mesas) arg;
        if (mesa.getTipo().equals("Cocina")){
            this.setLayoutY(100);
            this.setLayoutX(550);
        } else if (mesa.getTipo().equals("Descanso")){
            this.setLayoutX(x);
            this.setLayoutY(y);
        } else {
            this.setLayoutY(mesa.getPosY());
            this.setLayoutX(mesa.getPosX()+15);
        }

    }
}
