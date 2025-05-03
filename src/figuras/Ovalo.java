/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author robin
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ovalo extends Figura {
    private Point puntoInicial;
    private Point puntoFinal;

    public Ovalo(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial; // Inicialmente igual al inicial
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoFinal = puntoActual;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano);

        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int width = Math.abs(puntoFinal.x - puntoInicial.x);
        int height = Math.abs(puntoFinal.y - puntoInicial.y);
        boolean relleno = false;
        
        if (relleno) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }
}