package Model;

public class Mesas {
    private int posX;
    private int posY;
    private String estado;
    private String tipo;

    public Mesas(int posX, int posY, String tipo){
        this.posX = posX;
        this.posY = posY;
        this.tipo = tipo;
        estado = "vacio";
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
