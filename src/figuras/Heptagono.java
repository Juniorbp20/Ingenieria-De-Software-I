package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * autor carol
 */

public class Heptagono extends Figura {
    private Point centroGeometrico;
    private Point verticeReferencia;
    private int numeroLados = 7; // Constante para el número de lados del heptágono
    
    public Heptagono(Point centro) {
        this.centroGeometrico = centro;
        this.verticeReferencia = new Point(centro); // Inicializamos el vértice de referencia
    }
    
    @Override
    public void actualizar(Point nuevoPunto) {
        this.verticeReferencia = nuevoPunto;
    }
    
    @Override
    public void dibujar(Graphics grafico) {
        grafico.setColor(colorDePrimerPlano);

        int radio = (int) centroGeometrico.distance(verticeReferencia);
        int[] puntosX = new int[numeroLados];
        int[] puntosY = new int[numeroLados];

        for (int i = 0; i < numeroLados; i++) {
            double angulo = Math.toRadians(-90 + i * (360.0 / numeroLados));
            puntosX[i] = centroGeometrico.x + (int) (radio * Math.cos(angulo));
            puntosY[i] = centroGeometrico.y + (int) (radio * Math.sin(angulo));
        }

        Polygon heptagonoForma = new Polygon(puntosX, puntosY, numeroLados);
        boolean relleno = false;

        if (relleno) {
            grafico.fillPolygon(heptagonoForma);
        } else {
            grafico.drawPolygon(heptagonoForma);
        }
    }
}