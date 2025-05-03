package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * autor carol
 */

public class Octagono extends Figura {
    private Point centroGeometrico;
    private Point verticeReferencia;
    private int numeroLados = 8; // Constante para el número de lados del octágono

    public Octagono(Point centro) {
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
        int radio = (int) centroGeometrico.distance(verticeReferencia);
        int[] puntosX = new int[numeroLados];
        int[] puntosY = new int[numeroLados];

        // Calcular coordenadas de los vértices
        for (int i = 0; i < numeroLados; i++) {
            double angulo = Math.toRadians(-90 + i * (360.0 / numeroLados));
            puntosX[i] = centroGeometrico.x + (int) (radio * Math.cos(angulo));
            puntosY[i] = centroGeometrico.y + (int) (radio * Math.sin(angulo));
        }

        Polygon octagonoForma = new Polygon(puntosX, puntosY, numeroLados);

        // Manejar el relleno
        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno
                g.fillPolygon(octagonoForma); // Dibujar el relleno
            }

            // Dibujar el borde solo si es diferente del color de relleno
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano);
                g.drawPolygon(octagonoForma); // Dibujar el borde
            }
        } else {
            // Si no hay relleno, solo dibujar el borde
            g.drawPolygon(octagonoForma);
        }
    }
}