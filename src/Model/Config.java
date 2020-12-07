package Model;

import Model.Hilos.Cliente;
import Model.Mesas;

import java.util.ArrayList;

public class Config {
    public static Mesas[] mesas;
    public static int maxVip = 0;
    public static int maxClientes = 0;
    public static int clientes = 0;
    public static int vip = 0;
    public static int orden = 0;
    public static int cocinar = 0;
    public static int comida = 0;
    public static int noRecervacionesInt = 0;
    public static int noEnColaInt = 0;
    public static ArrayList<Integer> ordendes = new ArrayList<>();
    public static ArrayList<Integer> comidas = new ArrayList<>();
    public static ArrayList<Integer> pedidos = new ArrayList<>();
    public static ArrayList<Cliente> clientess = new ArrayList<>();
    public static ArrayList<Cliente> clientesVip = new ArrayList<>();

}
