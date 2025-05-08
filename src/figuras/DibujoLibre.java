package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color; // Asumiendo que Color es usado por colorDePrimerPlano


/**
 * Representa una herramienta de dibujo a mano alzada.
 * Captura una serie de puntos mientras se arrastra el ratón y dibuja líneas entre ellos.
 */
public class DibujoLibre extends Figura {
    ArrayList<Point> puntos = new ArrayList<>(); // Lista para almacenar los puntos del dibujo a mano alzada.


    /**
     * Constructor de un DibujoLibre (Dibujo Libre) que comienza en un punto dado.
     * @param puntoInicial El punto inicial del dibujo a mano alzada.
     */
    public DibujoLibre(Point puntoInicial) {
        actualizar(puntoInicial); // Añadir el punto inicial a la lista.
    }

    /**
     * Dibuja el trazo a mano alzada conectando los puntos en la lista con líneas.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        if (puntos.size() < 1) {
            return; // No hacer nada si no hay puntos para dibujar.
        }

        g.setColor(colorDePrimerPlano); // Establecer el color para el dibujo.

        // Dibujar líneas entre puntos consecutivos.
        for(int i = 1; i < puntos.size(); i++) {
            Point p1 = puntos.get(i-1); // Obtener el punto anterior.
            Point p2 = puntos.get(i); // Obtener el punto actual.
            g.drawLine(p1.x, p1.y, p2.x, p2.y); // Dibujar un segmento de línea entre los dos puntos.
        }
        // Si solo hay un punto, dibujar un solo píxel.
        if (puntos.size() == 1) {
            Point p = puntos.get(0);
            g.fillRect(p.x, p.y, 1, 1);
        }
    }

    /**
     * Añade un nuevo punto al dibujo a mano alzada.
     * @param puntoActual El punto actual a añadir.
     */
    @Override
    public void actualizar(Point puntoActual) {
        puntos.add(puntoActual); // Añadir el punto actual a la lista de puntos.
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("DibujoLibre");
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