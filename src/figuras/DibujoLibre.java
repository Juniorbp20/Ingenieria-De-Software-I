/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author josearielpereyra
 */
public class DibujoLibre extends Figura{
    private Color color;

    ArrayList<Point> puntos = new ArrayList<>();

    public DibujoLibre(Point puntoInicial) {
        this.color = Color.BLACK;
        actualizar(puntoInicial);
    }
    
    

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        for(int i = 1; i < puntos.size(); i++) {
            g.drawLine(puntos.get(i-1).x, puntos.get(i-1).y, puntos.get(i).x, puntos.get(i).y);
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntos.add(puntoActual);
    }
    
    
}
