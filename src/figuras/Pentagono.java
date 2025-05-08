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
 * Representa una forma de pentágono (polígono de 5 lados).
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 */
public class Pentagono extends Figura {
    private Point centro; // El punto central del pentágono.
    private Point puntoActual; // El punto actual que determina el tamaño y la orientación.

    /**
     * Constructor de un Pentágono con un punto central dado.
     * @param centro El punto central inicial del pentágono.
     */
    public Pentagono(Point centro) {
        this.centro = centro;
        this.puntoActual = centro;
    }

    /**
     * Actualiza el punto que determina el tamaño y la orientación del pentágono.
     * @param puntoActual El punto actual.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    /**
     * Dibuja el pentágono en el contexto gráfico dado.
     * Calcula los vértices del pentágono basado en el centro y el punto actual.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno.
            }
            int radio = (int) centro.distance(puntoActual); // Calcular el radio.
            int[] xPoints = new int[5]; // Arreglo para almacenar las coordenadas x de los vértices.
            int[] yPoints = new int[5]; // Arreglo para almacenar las coordenadas y de los vértices.

            // Calcular coordenadas de los vértices.
            for (int i = 0; i < 5; i++) {
                double angle = Math.toRadians(-90 + i * 72); // Calcular el ángulo para cada vértice (72 grados por lado para un pentágono). Comienza apuntando hacia arriba (-90 grados).
                xPoints[i] = centro.x + (int) (radio * Math.cos(angle)); // Calcular coordenada x.
                yPoints[i] = centro.y + (int) (radio * Math.sin(angle)); // Calcular coordenada y.
            }

            Polygon pentagono = new Polygon(xPoints, yPoints, 5); // Crear un objeto Polygon para el pentágono.
            g.fillPolygon(pentagono); // Dibujar el pentágono relleno.

            // Dibujar el borde si es diferente del color de relleno.
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawPolygon(pentagono); // Dibujar el contorno.
            }
        } else {
            // Si no hay relleno, solo dibujar el contorno.
            int radio = (int) centro.distance(puntoActual);
            int[] xPoints = new int[5];
            int[] yPoints = new int[5];

            for (int i = 0; i < 5; i++) {
                double angle = Math.toRadians(-90 + i * 72);
                xPoints[i] = centro.x + (int) (radio * Math.cos(angle));
                yPoints[i] = centro.y + (int) (radio * Math.sin(angle));
            }

            Polygon pentagono = new Polygon(xPoints, yPoints, 5);
            g.drawPolygon(pentagono); // Solo dibujar el contorno.
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Pentagono");
        data.setPuntoInicial(this.puntoInicial);
        data.setPuntoFinal(this.puntoFinal); // Para rectángulos, puntoInicial y puntoFinal definen el tamaño/posición
        data.setColorDePrimerPlano(this.colorDePrimerPlano);
        data.setColorDeRelleno(this.colorDeRelleno);
        data.setEstaRelleno(this.relleno);
        // No tiene sentido para Rectangulo setear centro, puntosTrazo o tamanoBorrador
        return data;
    }

    // Implementación de contains para Rectángulo (más precisa)
    @Override
    public boolean contains(Point p) {
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int width = Math.abs(puntoFinal.x - puntoInicial.x);
        int height = Math.abs(puntoFinal.y - puntoInicial.y);
        // Crear un rectángulo Java y verificar si contiene el punto
        return new java.awt.Rectangle(x, y, width, height).contains(p);
    }
}