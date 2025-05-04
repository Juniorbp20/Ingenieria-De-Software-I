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

/**
 * Representa una forma de rombo.
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 */
public class Rombo extends Figura {
    private Point centro; // El punto central del rombo.
    private Point puntoActual; // Un punto que determina el tamaño y la forma del rombo.

    /**
     * Constructor de un Rombo con un punto central dado.
     * @param centro El punto central inicial del rombo.
     */
    public Rombo(Point centro) {
        this.centro = centro;
        this.puntoActual = centro;
    }

    /**
     * Actualiza el punto que determina el tamaño y la forma del rombo.
     * @param puntoActual El punto actual.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    /**
     * Dibuja el rombo en el contexto gráfico dado.
     * Calcula los vértices del rombo basado en el centro y el punto actual.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno.
            }
            int dx = puntoActual.x - centro.x; // Calcular la distancia horizontal desde el centro.
            int dy = puntoActual.y - centro.y; // Calcular la distancia vertical desde el centro.

            int[] xPoints = { // Coordenadas x de los vértices del rombo.
                    centro.x,
                    centro.x + dx,
                    centro.x,
                    centro.x - dx
            };

            int[] yPoints = { // Coordenadas y de los vértices del rombo.
                    centro.y - dy,
                    centro.y,
                    centro.y + dy,
                    centro.y
            };

            Polygon rombo = new Polygon(xPoints, yPoints, 4); // Crear un objeto Polygon para el rombo.
            g.fillPolygon(rombo); // Dibujar el rombo relleno.

            // Dibujar el borde si es diferente del color de relleno.
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawPolygon(rombo); // Dibujar el contorno.
            }
        } else {
            // Si no hay relleno, solo dibujar el contorno.
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
            g.drawPolygon(rombo); // Solo dibujar el contorno.
        }
    }
}