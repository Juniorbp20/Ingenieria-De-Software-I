package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Borrador extends Figura {
    private int tamano = 20;
    private Point centro;

    public Borrador(Point p) {
        this.centro = p;
        this.colorDePrimerPlano = Color.WHITE;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
//        g.fillRect(centro.x - tamano / 2, centro.y - tamano / 2, tamano, tamano);
        g.fillOval(centro.x - tamano / 2, centro.y - tamano / 2, tamano, tamano);
    }

    @Override
    public void actualizar(Point p) {
        this.centro = p;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}