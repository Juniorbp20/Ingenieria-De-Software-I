/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Representa una forma de semicírculo que puede ser dibujada y rellenada.
 * Extiende la clase abstracta Figura.
 * @author ebenezer
 */
public class Semicirculo extends Figura {
    private Point puntoInicial; // Punto de inicio (una esquina del bounding box)
    private Point puntoActual; // Punto actual (esquina opuesta del bounding box)

    /**
     * Constructor de un Semicirculo con un punto inicial.
     * @param puntoInicial El punto donde se inicia el dibujo del semicírculo.
     */
    public Semicirculo(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoActual = puntoInicial; // Inicialmente el punto actual es el mismo que el inicial
        // Los colores y el estado de relleno se heredarán de Figura.
    }

    /**
     * Actualiza el punto actual que determina el tamaño y la forma del semicírculo.
     * @param puntoActual El punto actual mientras se arrastra el ratón.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    /**
     * Dibuja la forma del semicírculo en el contexto gráfico dado.
     * Utiliza Arc2D para definir la forma.
     * La dibuja o rellena según el estado 'relleno'.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(colorDePrimerPlano); // Usa el color heredado de Figura

        // Calcular la posición y tamaño del semicírculo basado en puntoInicial y puntoActual
        // Usamos Math.min y Math.abs para manejar el arrastre en cualquier dirección
        int x = Math.min(puntoInicial.x, puntoActual.x);
        int y = Math.min(puntoInicial.y, puntoActual.y);
        int width = Math.abs(puntoActual.x - puntoInicial.x);
        int height = Math.abs(puntoActual.y - puntoInicial.y);

        // Asegurarse de que haya un tamaño mínimo
        if (width <= 0 || height <= 0) {
            return; // No dibujar si el tamaño es cero o negativo
        }

        // Determinar la dirección del semicírculo basado en la dirección del arrastre vertical
        // Si puntoActual.y es menor que puntoInicial.y, dibujamos la mitad superior
        // Si puntoActual.y es mayor que puntoInicial.y, dibujamos la mitad inferior
        int startAngle = 0; // Ángulo de inicio en grados
        int extentAngle = 180; // Extensión del arco en grados (180 para medio círculo)
        int arcType = Arc2D.PIE; // Tipo de arco (PIE para incluir los radios al centro)

        if (puntoActual.y < puntoInicial.y) {
            // Dibujar la mitad superior
            startAngle = 180;
        } else {
            // Dibujar la mitad inferior
            startAngle = 0;
        }


        // Crear la forma del arco. El constructor Arc2D.Double(x, y, w, h, start, extent, type)
        // define un arco dentro de un rectángulo delimitador (x, y, w, h).
        Arc2D.Double semicirculoForma = new Arc2D.Double(x, y, width, height, startAngle, extentAngle, arcType);


        if (relleno) { // Si relleno es true
            g2.fill(semicirculoForma); // Rellenar la forma con colorDePrimerPlano
        } else { // Si relleno es false
            g2.draw(semicirculoForma); // Dibujar solo el contorno con colorDePrimerPlano
        }
    }

    /**
     * Obtiene un objeto FiguraData que represente los datos de este rectángulo.
     * @return Un objeto FiguraData con los datos del rectángulo.
     */
    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Semicirculo");
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
