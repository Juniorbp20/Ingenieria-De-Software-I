/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;
   import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
/**
 *
 * @author carol
 */

public class Octagono extends Figura {
    private Point centroGeometrico;
    private Point verticeReferencia;
    private int numeroLados = 8; // Constante para el número de lados del heptágono


    public Octagono(Point centroGeometrico) {
        this.centroGeometrico = centroGeometrico;
        this.verticeReferencia= centroGeometrico;
    }

    @Override
    public void actualizar(Point verticeReferencia) {
        this.verticeReferencia = verticeReferencia;
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

        Polygon octagonoForma = new Polygon(puntosX, puntosY, numeroLados);
        boolean relleno = false;

        if (relleno) {
            grafico.fillPolygon(octagonoForma);
        } else {
            grafico.drawPolygon(octagonoForma);
        }
    }
}

