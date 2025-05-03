/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Figura {
    protected Color colorDePrimerPlano = Color.BLACK;
    protected boolean relleno = false;
    public Color colorDeRelleno;

    public abstract void dibujar(Graphics g);
    public abstract void actualizar(Point puntoActual);

    // Métodos para establecer propiedades
    public void setColorDePrimerPlano(Color color) {
        this.colorDePrimerPlano = color;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    // Métodos getter
    public Color getColorDePrimerPlano() {
        return colorDePrimerPlano;
    }

    public boolean isRelleno() {
        return relleno;
    }

    public void setColorDeRelleno(Color colorDeRelleno) {
        this.colorDeRelleno = colorDeRelleno;
    }

    public Color getColorDeRelleno() {
        return colorDeRelleno;
    }
}