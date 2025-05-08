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