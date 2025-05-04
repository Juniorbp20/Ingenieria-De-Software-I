package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Representa un segmento de línea recta.
 * Puede ser dibujado con un color especificado.
 */
public class Linea extends Figura {
    private Point puntoInicial; // El punto de inicio de la línea.
    private Point puntoFinal; // El punto final de la línea.

    /**
     * Constructor de una Línea con puntos inicial y final dados.
     * @param puntoInicial El punto de inicio de la línea.
     * @param puntoFinal El punto final de la línea.
     */
    public Linea(Point puntoInicial, Point puntoFinal) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    /**
     * Constructor de una Línea que comienza y termina en el mismo punto.
     * Esto se usa típicamente cuando se presiona el ratón por primera vez.
     * @param puntoInicial El punto inicial de la línea.
     */
    public Linea(Point puntoInicial) {
        this(puntoInicial, puntoInicial);
    }

    /**
     * Dibuja el segmento de línea en el contexto gráfico dado.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano de la clase base.
        g.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y); // Dibujar la línea.
    }

    /**
     * Actualiza el punto final de la línea mientras se está dibujando.
     * @param puntoActual El punto final actual.
     */
    @Override
    public void actualizar(Point puntoActual) {
        puntoFinal = puntoActual;
    }
}