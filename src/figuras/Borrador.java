package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Representa una herramienta de borrador.
 * Dibuja círculos blancos para borrar partes del dibujo en el lienzo.
 */
public class Borrador extends Figura {
    private int tamano = 20; // El tamaño del pincel del borrador.
    private Point centro; // El punto central del trazo del borrador.

    /**
     * Constructor de un Borrador en un punto dado.
     * Establece el color de primer plano a blanco para simular el borrado.
     * @param p El punto inicial del borrador.
     */
    public Borrador(Point p) {
        this.centro = p;
        this.colorDePrimerPlano = Color.WHITE; // El borrador dibuja en blanco para coincidir con el fondo.
    }

    /**
     * Dibuja el trazo del borrador como un óvalo (o rectángulo) blanco relleno.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE); // Establecer el color de dibujo a blanco.
//        g.fillRect(centro.x - tamano / 2, centro.y - tamano / 2, tamano, tamano); // Opción para dibujar un borrador rectangular.
        g.fillOval(centro.x - tamano / 2, centro.y - tamano / 2, tamano, tamano); // Dibujar un óvalo blanco relleno.
    }

    /**
     * Actualiza la posición del borrador.
     * @param p El nuevo punto central del borrador.
     */
    @Override
    public void actualizar(Point p) {
        this.centro = p;
    }

    /**
     * Establece el tamaño del pincel del borrador.
     * @param tamano El nuevo tamaño del borrador.
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}