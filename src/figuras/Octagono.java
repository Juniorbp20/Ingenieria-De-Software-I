package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * Representa una forma de octágono (polígono de 8 lados).
 * Puede ser dibujado con un contorno y rellenado con un color separado.
 * autor carol
 */
public class Octagono extends Figura {
    private Point centroGeometrico; // El centro geométrico del octágono.
    private Point verticeReferencia; // Un punto de referencia para determinar el tamaño y la orientación.
    private int numeroLados = 8; // Constante para el número de lados del octágono.

    /**
     * Constructor de un Octágono con un punto central dado.
     * @param centro El punto central inicial del octágono.
     */
    public Octagono(Point centro) {
        this.centroGeometrico = centro;
        this.verticeReferencia = new Point(centro); // Inicializar el vértice de referencia.
    }

    /**
     * Actualiza el punto de referencia que determina el tamaño y la orientación del octágono.
     * @param nuevoPunto El nuevo punto de referencia.
     */
    @Override
    public void actualizar(Point nuevoPunto) {
        this.verticeReferencia = nuevoPunto;
    }

    /**
     * Dibuja el octágono en el contexto gráfico dado.
     * Calcula los vértices del octágono basado en el centro y el punto de referencia.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        // Establecer el color del borde
        g.setColor(colorDePrimerPlano); // Establecer el color para el contorno.

        // Calcular el radio
        int radio = (int) centroGeometrico.distance(verticeReferencia); // Calcular el radio.
        int[] puntosX = new int[numeroLados]; // Arreglo para almacenar las coordenadas x de los vértices.
        int[] puntosY = new int[numeroLados]; // Arreglo para almacenar las coordenadas y de los vértices.

        // Calcular coordenadas de los vértices
        for (int i = 0; i < numeroLados; i++) {
            double angulo = Math.toRadians(-90 + i * (360.0 / numeroLados)); // Calcular el ángulo para cada vértice. Comienza a -90 grados (hacia arriba).
            puntosX[i] = centroGeometrico.x + (int) (radio * Math.cos(angulo)); // Calcular coordenada x.
            puntosY[i] = centroGeometrico.y + (int) (radio * Math.sin(angulo)); // Calcular coordenada y.
        }

        Polygon octagonoForma = new Polygon(puntosX, puntosY, numeroLados); // Crear un objeto Polygon para el octágono.

        // Manejar el relleno
        if (relleno) { // Verificar si el relleno está habilitado.
            if (colorDeRelleno != null) {
                g.setColor(colorDeRelleno); // Establecer el color de relleno.
                g.fillPolygon(octagonoForma); // Dibujar el octágono relleno.
            }

            // Dibujar el borde solo si es diferente del color de relleno.
            if (colorDeRelleno != colorDePrimerPlano) {
                g.setColor(colorDePrimerPlano); // Restablecer el color al color de borde.
                g.drawPolygon(octagonoForma); // Dibujar el contorno.
            }
        } else {
            // Si no hay relleno, solo dibujar el contorno.
            g.drawPolygon(octagonoForma);
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Octagono");
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