package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Representa una forma de cuadrado.
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 */
public class Cuadrado extends Figura {
    private Point puntoInicial; // El punto de inicio (típicamente la ubicación donde se presionó el ratón).
    private Point puntoFinal; // El punto final (típicamente la ubicación donde se soltó el ratón).

    // Constructor
    /**
     * Constructor de un Cuadrado con un punto inicial dado.
     * @param puntoInicial El punto inicial del cuadrado.
     */
    public Cuadrado(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial; // Inicialmente, el punto final es el mismo que el punto inicial.
    }

    /**
     * Actualiza el punto final para determinar el tamaño y la posición del cuadrado.
     * @param puntoActual El punto actual (típicamente la ubicación mientras se arrastra el ratón).
     */
    @Override
    public void actualizar(Point puntoActual) {
        // Actualiza el punto final para determinar el tamaño del cuadrado
        this.puntoFinal = puntoActual;
    }

    /**
     * Dibuja el cuadrado en el contexto gráfico dado.
     * Dibuja un rectángulo relleno si 'relleno' es true, y un contorno de rectángulo.
     * Asegura que la forma dibujada sea un cuadrado.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Usa el color de la clase Figura para el contorno.

        // Calcular las coordenadas de la esquina superior izquierda, el ancho y la altura.
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        // Calcular la longitud del lado, asegurando que sea un cuadrado.
        int lado = Math.max(Math.abs(puntoFinal.x - puntoInicial.x), Math.abs(puntoFinal.y - puntoInicial.y));

        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno si se especifica.
            }
            g.fillRect(x, y, lado, lado); // Dibujar el cuadrado relleno.

            // Dibujar el borde si el color de relleno es diferente al color de borde.
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawRect(x, y, lado, lado); // Dibujar el contorno del cuadrado.
            }
        } else {
            // Si no hay relleno, solo dibujar el contorno del cuadrado.
            g.drawRect(x, y, lado, lado);
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Cuadrado");
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