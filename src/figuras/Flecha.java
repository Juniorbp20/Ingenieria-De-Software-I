package figuras;

import java.awt.*;

/**
 * Representa una forma de flecha.
 * Puede ser dibujado con una línea y una punta de flecha. La opción de relleno para la punta de flecha no está completamente implementada.
 */
public class Flecha extends Figura {
    private Point inicio; // El punto de inicio de la flecha.
    private Point fin; // El punto final de la flecha (punta de la flecha).
    Color colorDeRelleno = null; // Color de relleno local (nota: esto oculta el campo heredado y no se usa correctamente).

    /**
     * Constructor de una Flecha que comienza en un punto dado.
     * @param inicio El punto de inicio inicial de la flecha.
     */
    public Flecha(Point inicio) {
        this.inicio = inicio;
        this.fin = inicio; // Inicialmente, el punto final es el mismo que el punto inicial.
    }

    /**
     * Actualiza el punto final de la flecha mientras se está dibujando.
     * @param nuevoFin El nuevo punto final.
     */
    @Override
    public void actualizar(Point nuevoFin) {
        this.fin = nuevoFin;
    }

    /**
     * Dibuja la flecha (línea y punta de flecha) en el contexto gráfico dado.
     * Nota: La lógica de relleno para la punta de flecha parece incompleta o incorrecta.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(colorDePrimerPlano); // Establecer el color para dibujar la flecha.

        // Cálculo de coordenadas...

        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) { // Este colorDeRelleno local no es establecido por el panel de colores.
                g.setColor(colorDeRelleno); // Esta línea usa el color local, probablemente siempre null.
            }
            int dx = fin.x - inicio.x; // Diferencia en coordenadas x.
            int dy = fin.y - inicio.y; // Diferencia en coordenadas y.
            double angulo = Math.atan2(dy, dx); // Calcular el ángulo de la flecha.

            int longitud = (int) inicio.distance(fin); // Calcular la longitud de la línea de la flecha.

            // Puntos para la línea del cuerpo de la flecha
            int x1 = inicio.x;
            int y1 = inicio.y;
            int x2 = fin.x;
            int y2 = fin.y;

            g.drawLine(x1, y1, x2, y2); // Dibujar la línea principal de la flecha.

            // Tamaño de la cabeza de flecha
            int size = 10; // Tamaño de la punta de la flecha.

            // Cálculo de los dos puntos de la cabeza de la flecha
            int x3 = x2 - (int) (size * Math.cos(angulo - Math.PI / 6)); // Calcular coordenada x para un punto de la punta de flecha.
            int y3 = y2 - (int) (size * Math.sin(angulo - Math.PI / 6)); // Calcular coordenada y para un punto de la punta de flecha.

            int x4 = x2 - (int) (size * Math.cos(angulo + Math.PI / 6)); // Calcular coordenada x para el otro punto de la punta de flecha.
            int y4 = y2 - (int) (size * Math.sin(angulo + Math.PI / 6)); // Calcular coordenada y para el otro punto de la punta de flecha.

            Polygon cabeza = new Polygon(); // Crear un objeto Polygon para la punta de flecha.
            cabeza.addPoint(x2, y2); // Añadir la punta de la flecha.
            cabeza.addPoint(x3, y3); // Añadir el segundo punto.
            cabeza.addPoint(x4, y4); // Añadir el tercer punto.
            boolean relleno = false; // Esta variable local también oculta el campo heredado.

            if (relleno) { // Esta condición siempre será false debido a la variable local.
                g.fillPolygon(cabeza); // Esto nunca se ejecutará.
            } else {
                g.drawPolygon(cabeza); // Dibujar el contorno de la punta de flecha.
            }
        } else {
            int dx = fin.x - inicio.x; // Diferencia en coordenadas x.
            int dy = fin.y - inicio.y; // Diferencia en coordenadas y.
            double angulo = Math.atan2(dy, dx); // Calcular el ángulo de la flecha.

            // Puntos para la línea del cuerpo de la flecha
            int x1 = inicio.x;
            int y1 = inicio.y;
            int x2 = fin.x;
            int y2 = fin.y;

            g.drawLine(x1, y1, x2, y2); // Dibujar la línea principal de la flecha.

            // Tamaño de la cabeza de flecha
            int size = 10; // Tamaño de la punta de la flecha.

            // Cálculo de los dos puntos de la cabeza de la flecha
            int x3 = x2 - (int) (size * Math.cos(angulo - Math.PI / 6)); // Calcular coordenada x para un punto de la punta de flecha.
            int y3 = y2 - (int) (size * Math.sin(angulo - Math.PI / 6)); // Calcular coordenada y para un punto de la punta de flecha.

            int x4 = x2 - (int) (size * Math.cos(angulo + Math.PI / 6)); // Calcular coordenada x para el otro punto de la punta de flecha.
            int y4 = y2 - (int) (size * Math.sin(angulo + Math.PI / 6)); // Calcular coordenada y para el otro punto de la punta de flecha.

            Polygon cabeza = new Polygon(); // Crear un objeto Polygon para la punta de flecha.
            cabeza.addPoint(x2, y2); // Añadir la punta de la flecha.
            cabeza.addPoint(x3, y3); // Añadir el segundo punto.
            cabeza.addPoint(x4, y4); // Añadir el tercer punto.
            g.drawPolygon(cabeza); // Dibujar el contorno de la punta de flecha.
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Flecha");
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