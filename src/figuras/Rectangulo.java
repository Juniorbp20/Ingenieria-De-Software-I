package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangulo extends Figura {
    private Point puntoInicial;
    private Point puntoFinal;
    private Color color;
    private boolean relleno;

    public Rectangulo(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
        this.color = Color.BLACK;
        this.relleno = false; // Por defecto sin relleno
    }

    @Override
    public void actualizar(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);

        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int width = Math.abs(puntoFinal.x - puntoInicial.x);
        int height = Math.abs(puntoFinal.y - puntoInicial.y);

        if (relleno) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }

    // Métodos para establecer propiedades
    public void setColor(Color color) {
        this.color = color;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
}