package figuras;

import java.awt.Graphics;
import java.awt.Point;

public class Circulo extends Figura {
    private Point centro;
    private Point puntoActual;

    public Circulo(Point centro) {
        this.centro = centro;
        this.puntoActual = centro;
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano);

        int radio = (int) centro.distance(puntoActual);

        int x = centro.x - radio;
        int y = centro.y - radio;
        int diametro = radio * 2;

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno);
            }
            g.fillOval(x, y, diametro, diametro);

            // Dibuja el borde si los colores son diferentes
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g.setColor(colorDePrimerPlano);
                g.drawOval(x, y, diametro, diametro);
            }
        } else {
            g.drawOval(x, y, diametro, diametro);
        }
    }
}