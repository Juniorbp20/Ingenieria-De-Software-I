/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author marco
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Pentagono extends Figura {
    private Point centro;
    private Point puntoActual;

    public Pentagono(Point centro) {
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

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno);
            }
            int radio = (int) centro.distance(puntoActual);
            int[] xPoints = new int[5];
            int[] yPoints = new int[5];

            for (int i = 0; i < 5; i++) {
                double angle = Math.toRadians(-90 + i * 72); // Empieza hacia arriba
                xPoints[i] = centro.x + (int) (radio * Math.cos(angle));
                yPoints[i] = centro.y + (int) (radio * Math.sin(angle));
            }

            Polygon pentagono = new Polygon(xPoints, yPoints, 5);
            boolean relleno = false;

            if (relleno) {
                g.fillPolygon(pentagono);
            } else {
                g.drawPolygon(pentagono);
            }
        }
    }
}