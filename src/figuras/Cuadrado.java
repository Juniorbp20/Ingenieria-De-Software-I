package figuras;

import java.awt.Graphics;
import java.awt.Point;

public class Cuadrado extends Figura {
    private Point puntoInicial;
    private Point puntoFinal;

    // Constructor
    public Cuadrado(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial; // Inicialmente, el punto final es igual al inicial
    }

    @Override
    public void actualizar(Point puntoActual) {
        // Actualiza el punto final para determinar el tamaño del cuadrado
        this.puntoFinal = puntoActual;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Usa el color de la clase Figura

        // Calcular las coordenadas de la esquina superior izquierda, el ancho y el alto
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int lado = Math.max(Math.abs(puntoFinal.x - puntoInicial.x), Math.abs(puntoFinal.y - puntoInicial.y));

        if (relleno) {
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno);
            }
            g.fillRect(x, y, lado, lado);

            // Dibuja el borde si los colores son diferentes
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g.setColor(colorDePrimerPlano);
                g.drawRect(x, y, lado, lado);
            }
        } else {
            g.drawRect(x, y, lado, lado);
        }
    }
}