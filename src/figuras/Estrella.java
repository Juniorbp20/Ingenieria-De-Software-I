package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * autor carol
 */

public class Estrella extends Figura {
    private Point centroGeometrico;
    private Point verticeReferencia;
    private int puntas = 5; // Número de puntas de la estrella

    public Estrella(Point centro) {
        this.centroGeometrico = centro;
        this.verticeReferencia = new Point(centro); // Inicializamos el vértice de referencia
    }

    @Override
    public void actualizar(Point nuevoPunto) {
        this.verticeReferencia = nuevoPunto;
    }

    @Override
    public void dibujar(Graphics g) {
        // Establecer el color del borde
        g.setColor(colorDePrimerPlano);

        // Calcular el radio
        int radioExterior = (int) centroGeometrico.distance(verticeReferencia);
        int radioInterior = (int) (radioExterior * 0.5); // Ajustar el radio interior
        int[] puntosX = new int[puntas * 2];
        int[] puntosY = new int[puntas * 2];

        // Calcular coordenadas de los vértices
        for (int i = 0; i < puntas * 2; i++) {
            double angulo = Math.toRadians(-90 + i * (360.0 / (puntas * 2)));
            int radio = (i % 2 == 0) ? radioExterior : radioInterior; // Alternar entre radio exterior e interior
            puntosX[i] = centroGeometrico.x + (int) (radio * Math.cos(angulo));
            puntosY[i] = centroGeometrico.y + (int) (radio * Math.sin(angulo));
        }

        Polygon estrellaForma = new Polygon(puntosX, puntosY, puntas * 2);

        // Manejar el relleno
        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno
                g.fillPolygon(estrellaForma); // Dibujar el relleno
            }

            // Dibujar el borde solo si es diferente del color de relleno
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano);
                g.drawPolygon(estrellaForma); // Dibujar el borde
            }
        } else {
            // Si no hay relleno, solo dibujar el borde
            g.drawPolygon(estrellaForma);
        }
    }
}