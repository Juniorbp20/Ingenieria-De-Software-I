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

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Linea");
        data.setPuntoInicial(this.puntoInicial);
        data.setPuntoFinal(this.puntoFinal); // Para rectángulos, puntoInicial y puntoFinal definen el tamaño/posición
        data.setColorDePrimerPlano(this.colorDePrimerPlano);
        data.setColorDeRelleno(this.colorDeRelleno);
        data.setEstaRelleno(this.relleno);
        // No tiene sentido para Rectangulo setear centro, puntosTrazo o tamanoBorrador
        return data;
    }

    // Implementación de contains para Rectángulo (más precisa)
    @Override
    public boolean contains(Point p) {
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int width = Math.abs(puntoFinal.x - puntoInicial.x);
        int height = Math.abs(puntoFinal.y - puntoInicial.y);
        // Crear un rectángulo Java y verificar si contiene el punto
        return new java.awt.Rectangle(x, y, width, height).contains(p);
    }
}