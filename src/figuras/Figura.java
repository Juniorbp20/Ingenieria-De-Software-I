/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Clase base abstracta para todas las figuras dibujables.
 * Define propiedades comunes como color, estado de relleno y métodos abstractos para dibujar y actualizar.
 */
public abstract class Figura {
    protected Color colorDePrimerPlano = Color.BLACK; // El color del contorno o trazo de la figura.
    protected boolean relleno = false; // Indica si la figura debe ser rellenada.
    public Color colorDeRelleno; // El color usado para rellenar la figura.

    /**
     * Método abstracto para dibujar la figura en el contexto gráfico dado.
     * Este método debe ser implementado por todas las subclases de figura concretas.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    public abstract void dibujar(Graphics g);

    /**
     * Método abstracto para actualizar la forma o posición de la figura basado en un nuevo punto.
     * Esto se usa típicamente durante el dibujo mientras se arrastra el ratón.
     * Este método debe ser implementado por todas las subclases de figura concretas.
     * @param puntoActual El punto actual para actualizar la figura.
     */
    public abstract void actualizar(Point puntoActual);

    // Métodos para establecer propiedades

    /**
     * Establece el color de primer plano (color del contorno o trazo) de la figura.
     * @param color El Color a establecer como color de primer plano.
     */
    public void setColorDePrimerPlano(Color color) {
        this.colorDePrimerPlano = color;
    }

    /**
     * Establece si la figura debe ser rellenada.
     * @param relleno true para habilitar el relleno, false para deshabilitar.
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * Establece el color de relleno de la figura.
     * @param colorDeRelleno El Color a establecer como color de relleno.
     */
    public void setColorDeRelleno(Color colorDeRelleno) {
        this.colorDeRelleno = colorDeRelleno;
    }

    // Métodos getter

    /**
     * Obtiene el color de primer plano actual de la figura.
     * @return El Color de primer plano actual.
     */
    public Color getColorDePrimerPlano() {
        return colorDePrimerPlano;
    }

    /**
     * Verifica si la figura está configurada para ser rellenada.
     * @return true si el relleno está habilitado, false en caso contrario.
     */
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * Obtiene el color de relleno actual de la figura.
     * @return El Color de relleno actual.
     */
    public Color getColorDeRelleno() {
        return colorDeRelleno;
    }
}