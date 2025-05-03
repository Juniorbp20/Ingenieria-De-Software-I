package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Estrella extends Figura {
    private Point centroGeometrico;
    private Point verticeReferencia;
    private final int numeroPuntas = 5;

    public Estrella(Point centroGeometrico) {
        this.centroGeometrico = centroGeometrico;
        this.verticeReferencia = centroGeometrico;
    }

    @Override
    public void actualizar(Point verticeReferencia) {
        this.verticeReferencia = verticeReferencia;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano);

        // Cálculo de coordenadas...

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno);
            }

            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano);
                int radioExterior = (int) centroGeometrico.distance(verticeReferencia);
                int radioInterior = radioExterior / 2;

                int[] puntosX = new int[numeroPuntas * 2];
                int[] puntosY = new int[numeroPuntas * 2];

                for (int i = 0; i < numeroPuntas * 2; i++) {
                    double angulo = Math.toRadians(-90 + i * 360.0 / (numeroPuntas * 2));
                    int radio = (i % 2 == 0) ? radioExterior : radioInterior;
                    puntosX[i] = centroGeometrico.x + (int) (radio * Math.cos(angulo));
                    puntosY[i] = centroGeometrico.y + (int) (radio * Math.sin(angulo));
                }

                Polygon formaEstrella = new Polygon(puntosX, puntosY, numeroPuntas * 2);
                boolean relleno = false;

                if (relleno) {
                    g.fillPolygon(formaEstrella);
                } else {
                    g.drawPolygon(formaEstrella);
                }
            }
        }
    }
}