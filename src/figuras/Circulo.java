package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Representa una forma de círculo.
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 */
public class Circulo extends Figura {
    private Point centro; // El punto central del círculo.
    private Point puntoActual; // El punto actual que determina el radio.

    /**
     * Constructor de un Círculo con un punto central dado.
     * @param centro El punto central inicial del círculo.
     */
    public Circulo(Point centro) {
        this.centro = centro;
        this.puntoActual = centro; // Inicialmente, el punto actual es el mismo que el centro.
    }

    /**
     * Actualiza el punto que determina el radio del círculo mientras se está dibujando.
     * @param puntoActual El punto actual.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    /**
     * Dibuja el círculo en el contexto gráfico dado.
     * Dibuja un óvalo relleno si 'relleno' es true, y un contorno de óvalo.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        int radio = (int) centro.distance(puntoActual); // Calcular el radio basado en la distancia desde el centro hasta el punto actual.

        int x = centro.x - radio; // Calcular la coordenada 'x' superior izquierda del cuadro delimitador.
        int y = centro.y - radio; // Calcular la coordenada y superior izquierda del cuadro delimitador.
        int diametro = radio * 2; // Calcular el diámetro.

        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno si se especifica.
            }
            g.fillOval(x, y, diametro, diametro); // Dibujar el óvalo relleno.

            // Dibujar el borde si el color de relleno es diferente al color de borde.
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawOval(x, y, diametro, diametro); // Dibujar el contorno del óvalo.
            }
        } else {
            g.drawOval(x, y, diametro, diametro); // Si no hay relleno, solo dibujar el contorno del óvalo.
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Circulo");
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