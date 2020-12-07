package Controller;

import Model.Hilos.*;
import Model.Mesas;
import Model.Monitor;
import Model.Config;
import Model.Runner.CrearCliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Controller implements Observer {
    @FXML private AnchorPane canvas;
    @FXML private Button crear;
    @FXML private Button btnIniciar;
    private Mesas[] mesas;
    private Monitor monitor;
    Random random = new Random(System.currentTimeMillis());

    @FXML
    void cerrarClic(){
        System.exit(0);
    }

    @FXML
    void iniciarClic() {
        CrearCliente crearCliente = new CrearCliente();
        crearCliente.addObserver(this);
        new Thread(crearCliente).start();
        Recepcion recepcion = new Recepcion(monitor);
        RecepcionVip recepcionVip = new RecepcionVip(monitor);
        canvas.getChildren().addAll(recepcion, recepcionVip);
        btnIniciar.setDisable(true);
    }

    @FXML
    void crearClic() {
        crear(10);
        crear.setDisable(true);
        btnIniciar.setDisable(false);
    }

    private void crear(int num){
        mesas = new Mesas[num];
        int mese_cos = (num*10)/100;
        int vip = (num*20)/100;
        Config.maxVip = vip;
        int res = num - vip;
        int cont = 0;
        Config.maxClientes = res;
        for(int i = 0; i<5; i++) // y
            for(int j = 0; j<2; j++){ // x
                cont++;
                Circle cir = new Circle();
                cir.setRadius(20);
                if (cont > res) {
                    cir.setFill(Color.YELLOW);
                    mesas[cont-1] = new Mesas(130+(j*270), 90+(i*110), "Vip");
                } else {
                    cir.setFill(Color.WHITE);
                    mesas[cont-1] = new Mesas(130+(j*270), 90+(i*110), "Normal");
                }
                cir.setStroke(Color.BLACK);
                cir.setLayoutX(130+(j*270));
                cir.setLayoutY(90+(i*110));
                canvas.getChildren().add(cir);
                if (cont == num){
                    i=9; j=10;
                }
            }
        Config.mesas = mesas;
        cont = 0;
        monitor = new Monitor();
        for(int i = 0; i<3; i++) // y
            for (int j = 0; j<3; j++){ // x
                cont++;
                Mesero mesero = new Mesero((j*85)+580, (i*50)+260, monitor);
                Cocinero cocinero = new Cocinero((j*70)+580, (i*40)+60, monitor);
                canvas.getChildren().addAll(mesero, cocinero);
                if (cont == mese_cos){
                    j = 3; i = 3;
                }
            }
    }


    @Override
    public void update(Observable o, Object arg) {
        Cliente cliente = new Cliente(monitor);
        int ran = random.nextInt(10)+1;
        if (ran < 3 && Config.noRecervacionesInt < Config.maxVip){
            monitor.agregarClienteVip(cliente);
        }else {
            monitor.agregarCliente(cliente);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().add(cliente);
            }
        });

    }
}
