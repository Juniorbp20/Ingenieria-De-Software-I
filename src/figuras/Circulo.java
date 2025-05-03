/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author robin
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circulo extends Figura {
    private Point centro;
    private Point puntoActual;

    public Circulo(Point centro) {
        this.centro = centro;
        this.puntoActual = centro;
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano);

        int radio = (int) centro.distance(puntoActual);

        int x = centro.x - radio;
        int y = centro.y - radio;
        int diametro = radio * 2;
        boolean relleno = false;

        if (relleno) {
            g.fillOval(x, y, diametro, diametro);
        } else {
            g.drawOval(x, y, diametro, diametro);
        }
    }
}
