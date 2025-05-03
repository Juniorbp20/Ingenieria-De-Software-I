package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class DibujoLibre extends Figura {
    ArrayList<Point> puntos = new ArrayList<>();

    public DibujoLibre(Point puntoInicial) {
        actualizar(puntoInicial);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano en lugar de color local
        for(int i = 1; i < puntos.size(); i++) {
            g.drawLine(puntos.get(i-1).x, puntos.get(i-1).y, puntos.get(i).x, puntos.get(i).y);
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntos.add(puntoActual);
    }
}