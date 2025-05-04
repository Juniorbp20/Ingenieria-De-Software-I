package figuras;

import java.awt.*;

public class Flecha extends Figura {
    private Point inicio;
    private Point fin;
    Color colorDeRelleno = null;

    public Flecha(Point inicio) {
        this.inicio = inicio;
        this.fin = inicio;
    }

    @Override
    public void actualizar(Point nuevoFin) {
        this.fin = nuevoFin;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano);

        // Cálculo de coordenadas...

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno);
            }
            int dx = fin.x - inicio.x;
            int dy = fin.y - inicio.y;
            double angulo = Math.atan2(dy, dx);

            int longitud = (int) inicio.distance(fin);

            // Puntos para la línea del cuerpo de la flecha
            int x1 = inicio.x;
            int y1 = inicio.y;
            int x2 = fin.x;
            int y2 = fin.y;

            g.drawLine(x1, y1, x2, y2);

            // Tamaño de la cabeza de flecha
            int size = 10;

            // Cálculo de los dos puntos de la cabeza de la flecha
            int x3 = x2 - (int) (size * Math.cos(angulo - Math.PI / 6));
            int y3 = y2 - (int) (size * Math.sin(angulo - Math.PI / 6));

            int x4 = x2 - (int) (size * Math.cos(angulo + Math.PI / 6));
            int y4 = y2 - (int) (size * Math.sin(angulo + Math.PI / 6));

            Polygon cabeza = new Polygon();
            cabeza.addPoint(x2, y2);
            cabeza.addPoint(x3, y3);
            cabeza.addPoint(x4, y4);
            boolean relleno = false;

            if (relleno) {
                g.fillPolygon(cabeza);
            } else {
                g.drawPolygon(cabeza);
            }
        }
    }
}