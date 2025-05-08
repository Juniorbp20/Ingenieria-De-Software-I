package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point; // Importar Point
import java.awt.Polygon; // Importar Polygon

/**
 * Representa una forma de trapecio que puede ser dibujada y rellenada.
 * Extiende la clase abstracta Figura.
 * @author Bryan
 */
public class Trapecio extends Figura { // Extender de Figura
    private final Point puntoInicial; // Punto de inicio (esquina superior izquierda del bounding box inicial)
    private Point puntoActual; // Punto actual (determina tamaño y forma del trapecio)

    /**
     * Constructor de un Trapecio con un punto inicial.
     * @param puntoInicial El punto donde se inicia el dibujo del trapecio.
     */
    public Trapecio(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoActual = puntoInicial; // Inicialmente el punto actual es el mismo que el inicial
        // Los colores y el estado de relleno se heredarán de Figura y se establecerán
        // en PanelDeDibujo antes de añadir la figura a la lista.
    }

    /**
     * Actualiza el punto actual que determina el tamaño y la forma del trapecio.
     * @param puntoActual El punto actual mientras se arrastra el ratón.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    /**
     * Dibuja la forma del trapecio en el contexto gráfico dado.
     * Calcula los vértices del trapecio basado en puntoInicial y puntoActual
     * y lo dibuja o rellena según el estado 'relleno'.
     * @param g El objeto Graphics sobre el que dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Calcular las coordenadas del cuadro delimitador
        int x = Math.min(puntoInicial.x, puntoActual.x);
        int y = Math.min(puntoInicial.y, puntoActual.y);
        int width = Math.abs(puntoActual.x - puntoInicial.x);
        int height = Math.abs(puntoActual.y - puntoInicial.y);

        // Calcular los puntos del trapecio.
        // Este es un ejemplo simple de un trapecio isósceles.
        // Puedes ajustar esta lógica para crear diferentes tipos de trapecios.
        int baseSuperiorAncho = (int) (width * 0.6); // La base superior es el 60% de la inferior
        int diferenciaAncho = (width - baseSuperiorAncho) / 2;

        int[] xPoints = {
                x + diferenciaAncho,           // Esquina superior izquierda
                x + width - diferenciaAncho,   // Esquina superior derecha
                x + width,                     // Esquina inferior derecha
                x                              // Esquina inferior izquierda
        };

        int[] yPoints = {
                y,         // Esquina superior izquierda
                y,         // Esquina superior derecha
                y + height,// Esquina inferior derecha
                y + height // Esquina inferior izquierda
        };

        Polygon trapecioForma = new Polygon(xPoints, yPoints, 4); // Crear un objeto Polygon para el trapecio.

        if (relleno) { // Verificar si el relleno está habilitado (propiedad heredada)
            if (colorDeRelleno != null) { // Usar colorDeRelleno (propiedad heredada)
                g2.setColor(colorDeRelleno);
                g2.fillPolygon(trapecioForma); // Rellenar la forma del trapecio
            }

            // Dibujar el borde si el color de relleno es diferente al color de borde
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g2.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano (propiedad heredada)
                g2.drawPolygon(trapecioForma); // Dibujar el contorno del trapecio
            }  // Si no hay color de relleno especificado pero relleno es true,
            // puedes decidir si quieres dibujar el borde o no.
            // Siguiendo la lógica de tus otras figuras, si colorDeRelleno es null
            // y relleno es true, solo se rellena (con el color actual, que ya se estableció).
            // Si quieres el borde, descomenta la siguiente línea:
            // g2.setColor(colorDePrimerPlano);
            // g2.drawPolygon(trapecioForma);


        } else { // Si no hay relleno, solo dibujar el contorno
            g2.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano (propiedad heredada)
            g2.drawPolygon(trapecioForma); // Dibujar el contorno del trapecio
        }
    }

    // Los setters de color, posicion y tamaño ya no son necesarios aquí
    // porque la clase Figura ya tiene setColorDePrimerPlano, setColorDeRelleno y setRelleno,
    // y la posición y tamaño se manejan a través de puntoInicial y puntoActual
    // y el método actualizar. Si necesitas acceder a estos valores, puedes añadir getters.

    @Override
    public FiguraData getFiguraData() {
        FiguraData data = new FiguraData("Trapecio");
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
