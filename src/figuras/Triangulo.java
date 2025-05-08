package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Representa una forma de triángulo.
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 * Nota: La implementación actual dibuja tipos específicos de triángulos rectángulos.
 */
public class Triangulo extends Figura {
    private Point puntoInicial; // El punto de inicio.
    private Point puntoFinal; // El punto final que determina la forma del triángulo.

    // Eliminar esta variable local que oculta la heredada
    // boolean relleno = false; // Esta variable local oculta el campo heredado.

    /**
     * Constructor de un Triángulo con un punto inicial dado.
     * @param puntoInicial El punto inicial del triángulo.
     */
    public Triangulo(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
    }

    /**
     * Actualiza el punto final que determina la forma del triángulo.
     * @param puntoActual El punto actual.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoFinal = puntoActual;
    }

    /**
     * Dibuja el triángulo en el contexto gráfico dado.
     * Calcula los vértices basado en los puntos inicial y final.
     * Dibuja un polígono relleno si 'relleno' es true, y un contorno de polígono.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        int[] xPoints;
        int[] yPoints;

        // Determinar el tipo de triángulo basado en la posición del puntoFinal
        // Esta lógica crea triángulos rectángulos específicos.
        if (puntoFinal.x > puntoInicial.x) {
            xPoints = new int[]{puntoInicial.x, puntoFinal.x, puntoInicial.x};
            yPoints = new int[]{puntoInicial.y, puntoFinal.y, puntoFinal.y};
        } else {
            xPoints = new int[]{puntoInicial.x, puntoFinal.x, puntoInicial.x};
            yPoints = new int[]{puntoInicial.y, puntoFinal.y, puntoInicial.y};
        }

        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno.
            }
            g.fillPolygon(xPoints, yPoints, 3); // Dibujar el triángulo relleno.

            // Dibujar el borde si el color de relleno es diferente al color de borde.
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawPolygon(xPoints, yPoints, 3); // Dibujar el contorno del triángulo.
            }
        } else {
            g.drawPolygon(xPoints, yPoints, 3); // Si no hay relleno, solo dibujar el contorno del triángulo.
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Triangulo");
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