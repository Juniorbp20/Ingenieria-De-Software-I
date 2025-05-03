package figuras;

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

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno);
            }
            g.fillOval(x, y, width, height);

            // Dibuja el borde si los colores son diferentes
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g.setColor(colorDePrimerPlano);
                g.drawOval(x, y, width, height);
            }
        } else {
            g.drawOval(x, y, width, height);
        }
    }
}