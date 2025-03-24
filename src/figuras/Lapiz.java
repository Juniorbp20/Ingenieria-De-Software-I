/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author junior
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Lapiz extends Figura{
    private List<Point> puntos; // Lista de puntos
    private Color color;
    private int grosor;
    
    public Lapiz(Color color, int grosor) {
        this.puntos = new ArrayList<>(); // Inicializa la lista de puntos
        this.color = color;
        this.grosor = grosor; 
    }
    
    public void actualizar(Point punto) {
        puntos.add(punto); // Agrega un nuevo punto al trazo
    }
    
    public void dibujar(Graphics g) {
        g.setColor(color);
        for (int i = 1; i < puntos.size(); i++) {
            Point p1 = puntos.get(i - 1); // Punto anterior
            Point p2 = puntos.get(i); // Punto actual
            g.drawLine(p1.x, p1.y, p2.x, p2.y); // Dibuja una línea entre los dos puntos
        }
    }
}
