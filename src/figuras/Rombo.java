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

public class Rombo extends Figura {
    private Point centro;
    private Point puntoActual;

    public Rombo(Point centro) {
        this.centro = centro;
        this.puntoActual = centro;
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }
    
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color del borde

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno
            }
            int dx = puntoActual.x - centro.x;
            int dy = puntoActual.y - centro.y;

            int[] xPoints = {
                    centro.x,
                    centro.x + dx,
                    centro.x,
                    centro.x - dx
            };

            int[] yPoints = {
                    centro.y - dy,
                    centro.y,
                    centro.y + dy,
                    centro.y
            };

            Polygon rombo = new Polygon(xPoints, yPoints, 4);
            g.fillPolygon(rombo); // Dibujar el relleno

            // Dibujar el borde si es diferente del color de relleno
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano);
                g.drawPolygon(rombo); // Dibujar el borde
            }
        } else {
            // Si no hay relleno, solo dibujar el borde
            int dx = puntoActual.x - centro.x;
            int dy = puntoActual.y - centro.y;

            int[] xPoints = {
                    centro.x,
                    centro.x + dx,
                    centro.x,
                    centro.x - dx
            };

            int[] yPoints = {
                    centro.y - dy,
                    centro.y,
                    centro.y + dy,
                    centro.y
            };

            Polygon rombo = new Polygon(xPoints, yPoints, 4);
            g.drawPolygon(rombo); // Solo dibujar el borde
        }
    }
}