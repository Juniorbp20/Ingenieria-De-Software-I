package figuras;

import java.awt.Graphics;
import java.awt.Point;

public class Rectangulo extends Figura {
    private Point puntoInicial;
    private Point puntoFinal;

    public Rectangulo(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
    }

    @Override
    public void actualizar(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
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
            g.fillRect(x, y, width, height);

            // Dibujar el borde si hay color diferente
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano);
                g.drawRect(x, y, width, height);
            }
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}