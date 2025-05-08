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

// Clase Lapiz - Similar a DibujoLibre, representa un trazo a mano alzada
// Nota: Esta clase tiene su propio campo 'color' y 'grosor' en lugar de usar
// colorDePrimerPlano de la clase base Figura. También no usa relleno.
// Podría ser redundante si DibujoLibre ya maneja trazos.
/**
 * Representa una herramienta de lápiz para dibujo a mano alzada.
 * Nota: Esta clase tiene sus propios campos 'color' y 'grosor' en lugar de usar
 * colorDePrimerPlano de la clase base Figura. Tampoco usa relleno.
 * Podría ser redundante si DibujoLibre ya maneja trazos.
 */
public class Lapiz extends Figura{
    private List<Point> puntos; // Lista de puntos que forman el trazo.
    private Color color; // Color del trazo del lápiz (local a esta clase).
    private int grosor; // Grosor del trazo (no implementado en dibujar con drawLine).

    // Constructor - Recibe el color y grosor (aunque grosor no se usa en dibujar)
    // Nota: Este constructor no recibe un punto inicial como otras figuras.
    // La lista de puntos se llena con llamadas a actualizar.
    /**
     * Constructor de un Lapiz (Lápiz) con un color y grosor especificados.
     * Nota: Este constructor no recibe un punto inicial como otras figuras.
     * La lista de puntos se llena con llamadas al método actualizar.
     * @param color El color del trazo del lápiz.
     * @param grosor El grosor del trazo (no usado actualmente en el dibujo).
     */
    public Lapiz(Color color, int grosor) {
        this.puntos = new ArrayList<>(); // Inicializar la lista de puntos.
        this.color = color; // Establecer el color local.
        this.grosor = grosor;
        // Nota: colorDePrimerPlano de la clase base Figura no se usa en esta clase.
    }

    // Método actualizar - Agrega un nuevo punto al trazo mientras se arrastra el ratón
    /**
     * Actualiza el trazo del lápiz añadiendo un nuevo punto mientras se arrastra el ratón.
     * @param punto El punto actual a añadir al trazo.
     */
    @Override
    public void actualizar(Point punto) {
        puntos.add(punto); // Añadir un nuevo punto al trazo.
    }

    // Método dibujar - Dibuja el trazo conectando los puntos
    /**
     * Dibuja el trazo del lápiz conectando los puntos con líneas.
     * Nota: La propiedad 'grosor' no se usa con g.drawLine().
     * Para usar grosor, necesitarías Graphics2D y Stroke.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        // Asegurarse de que hay puntos para dibujar
        if (puntos.size() < 1) {
            return;
        }

        // Establecer el color usando el campo 'color' local de Lapiz
        g.setColor(color);

        // Dibuja líneas entre puntos consecutivos
        // Nota: El grosor 'grosor' no se utiliza con g.drawLine().
        // Para usar grosor, necesitarías Graphics2D y Stroke.
        for (int i = 1; i < puntos.size(); i++) {
            Point p1 = puntos.get(i - 1); // Punto anterior.
            Point p2 = puntos.get(i); // Punto actual.
            g.drawLine(p1.x, p1.y, p2.x, p2.y); // Dibuja una línea entre los dos puntos.
        }
    }

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Lapiz");
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
        // Validación de entrada
        if (p == null) {
            return false;
        }

        // Validación si la figura no tiene puntos inicial o final definidos
        if (puntoInicial == null && puntoFinal == null) {
            return false;
        }

        // Usar una constante para la distancia umbral
        final int DISTANCIA_UMBRAL = 5;

        // Verificar si el punto está cerca de puntoInicial o puntoFinal
        if (puntoInicial != null && puntoInicial.distance(p) <= DISTANCIA_UMBRAL) {
            return true;
        }
        if (puntoFinal != null && puntoFinal.distance(p) <= DISTANCIA_UMBRAL) {
            return true;
        }

        // De lo contrario, retornar falso (no contiene)
        return false;
    }
}