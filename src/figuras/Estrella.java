package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * Representa una forma de estrella.
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 * autor carol
 */
public class Estrella extends Figura {
    private Point centroGeometrico; // El centro geométrico de la estrella.
    private Point verticeReferencia; // Un punto de referencia para determinar el tamaño y la orientación.
    private int puntas = 5; // Número de puntas de la estrella.

    /**
     * Constructor de una Estrella con un punto central dado.
     * @param centro El punto central inicial de la estrella.
     */
    public Estrella(Point centro) {
        this.centroGeometrico = centro;
        this.verticeReferencia = new Point(centro); // Inicializar el vértice de referencia.
    }

    /**
     * Actualiza el punto de referencia que determina el tamaño y la orientación de la estrella.
     * @param nuevoPunto El nuevo punto de referencia.
     */
    @Override
    public void actualizar(Point nuevoPunto) {
        this.verticeReferencia = nuevoPunto;
    }

    /**
     * Dibuja la estrella en el contexto gráfico dado.
     * Calcula los vértices de la estrella basado en el centro y el punto de referencia.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        // Establecer el color del borde
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        // Calcular el radio
        int radioExterior = (int) centroGeometrico.distance(verticeReferencia); // Calcular el radio exterior.
        int radioInterior = (int) (radioExterior * 0.5); // Ajustar el radio interior.
        int[] puntosX = new int[puntas * 2]; // Arreglo para almacenar las coordenadas x de los vértices.
        int[] puntosY = new int[puntas * 2]; // Arreglo para almacenar las coordenadas y de los vértices.

        // Calcular coordenadas de los vértices
        for (int i = 0; i < puntas * 2; i++) {
            double angulo = Math.toRadians(-90 + i * (360.0 / (puntas * 2))); // Calcular el ángulo para cada vértice. Comienza a -90 grados (hacia arriba).
            int radio = (i % 2 == 0) ? radioExterior : radioInterior; // Alternar entre radio exterior e interior.
            puntosX[i] = centroGeometrico.x + (int) (radio * Math.cos(angulo)); // Calcular coordenada x.
            puntosY[i] = centroGeometrico.y + (int) (radio * Math.sin(angulo)); // Calcular coordenada y.
        }

        Polygon estrellaForma = new Polygon(puntosX, puntosY, puntas * 2); // Crear un objeto Polygon para la estrella.

        // Manejar el relleno
        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno.
                g.fillPolygon(estrellaForma); // Dibujar la estrella rellena.
            }

            // Dibujar el borde solo si es diferente del color de relleno.
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawPolygon(estrellaForma); // Dibujar el contorno.
            }
        } else {
            // Si no hay relleno, solo dibujar el contorno.
            g.drawPolygon(estrellaForma);
        }
    }
}