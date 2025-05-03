package figuras;

import java.awt.Graphics;
import java.awt.Point;

public class Linea extends Figura {
    private Point puntoInicial;
    private Point puntoFinal;

    public Linea(Point puntoInicial, Point puntoFinal) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public Linea(Point puntoInicial) {
        this(puntoInicial, puntoInicial);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano en lugar de color local
        g.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntoFinal = puntoActual;
    }
}