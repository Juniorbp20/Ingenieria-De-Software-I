package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Representa una forma de rectángulo.
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 */
public class Rectangulo extends Figura {
    private Point puntoInicial; // El punto de inicio (típicamente la ubicación donde se presionó el ratón).
    private Point puntoFinal; // El punto final (típicamente la ubicación donde se soltó el ratón).

    /**
     * Constructor de un Rectángulo con un punto inicial dado.
     * @param puntoInicial El punto inicial del rectángulo.
     */
    public Rectangulo(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial; // Inicialmente igual al punto inicial.
    }

    /**
     * Actualiza el punto final para determinar el tamaño y la posición del rectángulo.
     * @param puntoFinal El punto actual (típicamente la ubicación mientras se arrastra el ratón).
     */
    @Override
    public void actualizar(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    /**
     * Dibuja el rectángulo en el contexto gráfico dado.
     * Dibuja un rectángulo relleno si 'relleno' es true, y un contorno de rectángulo.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        // Calcular las coordenadas de la esquina superior izquierda, el ancho y la altura del rectángulo.
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int width = Math.abs(puntoFinal.x - puntoInicial.x);
        int height = Math.abs(puntoFinal.y - puntoInicial.y);

        if (relleno) { // Verificar si el relleno está habilitado.
            g.setColor(colorDeRelleno); // Establecer el color de relleno.
            g.fillRect(x, y, width, height); // Dibujar el rectángulo relleno.

            // Dibujar el borde si hay un color diferente.
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawRect(x, y, width, height); // Dibujar el contorno del rectángulo.
            }
        } else {
            g.drawRect(x, y, width, height); // Si no hay relleno, solo dibujar el contorno del rectángulo.
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Rectangulo");
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