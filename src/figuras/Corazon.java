package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point; // Importar Point
import java.awt.geom.Path2D;
// No necesitamos Arc2D para esta forma, podemos usar solo Path2D y curveTo/lineTo
// import java.awt.geom.Arc2D; // Importar Arc2D para los semicírculos


/**
 * Representa una forma de corazón que puede ser dibujada y rellenada.
 * Extiende la clase abstracta Figura.
 * @author Bryan
 */
public class Corazon extends Figura { // Extender de Figura
    private Point puntoInicial; // Punto de inicio (esquina superior izquierda del bounding box)
    private Point puntoActual; // Punto actual (determina tamaño del bounding box)

    /**
     * Constructor de un Corazon con un punto inicial.
     * @param puntoInicial El punto donde se inicia el dibujo del corazón.
     */
    public Corazon(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoActual = puntoInicial; // Inicialmente el punto actual es el mismo que el inicial
        // Los colores y el estado de relleno se heredarán de Figura y se establecerán
        // en PanelDeDibujo antes de añadir la figura a la lista.
    }

    /**
     * Actualiza el punto actual que determina el tamaño y la forma del corazón.
     * @param puntoActual El punto actual mientras se arrastra el ratón.
     */
    @Override
    public void actualizar(Point puntoActual) {
        this.puntoActual = puntoActual;
    }

    /**
     * Dibuja la forma del corazón en el contexto gráfico dado.
     * Utiliza Path2D para definir la forma (dos lóbulos superiores curvos y una punta en V).
     * La dibuja o rellena según el estado 'relleno'.
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

        // Asegurar que el tamaño sea positivo para evitar errores de dibujo
        if (width <= 0 || height <= 0) {
            return;
        }

        // Calcular puntos clave basados en el cuadro delimitador
        // La hendidura superior del corazón estará en el centro superior del bounding box
        int hendiduraX = x + width / 2;
        int hendiduraY = y;

        // La punta inferior del corazón estará en el centro inferior del bounding box
        int puntaX = x + width / 2;
        int puntaY = y + height;

        // Puntos de control para los lóbulos superiores
        // Estos puntos determinan la curvatura de los lóbulos.
        // Ajusta estos valores para refinar la forma.
        int controlLobeXOffset = width / 2; // Distancia horizontal desde la hendidura/punta
        int controlLobeYOffset = height / 3; // Distancia vertical desde la hendidura

        // Puntos donde los lóbulos se encuentran con las líneas de la punta
        // Estos puntos forman la base de la "V" invertida.
        int vBaseY = y + height * 2 / 3; // Ajustar esta fracción para controlar la altura de la V
        int leftVBaseX = x + width / 6; // Ajustar esta fracción para controlar el ancho de la V
        int rightVBaseX = x + width * 5 / 6; // Ajustar esta fracción para controlar el ancho de la V


        Path2D corazon = new Path2D.Double();

        // Mover al punto superior central (la hendidura)
        corazon.moveTo(hendiduraX, hendiduraY);

        // Dibujar el lóbulo izquierdo
        // Curva desde la hendidura hasta la base izquierda de la V
        // Puntos de control: uno hacia arriba y a la izquierda, otro hacia abajo y a la izquierda
        corazon.curveTo(hendiduraX - controlLobeXOffset * 0.8, hendiduraY - controlLobeYOffset * 0.5, // Control 1 (ajustado)
                x, y + height / 3, // Control 2 (ajustado)
                leftVBaseX, vBaseY); // Destino (base izquierda de la V)


        // Línea desde la base izquierda de la V hasta la punta inferior
        corazon.lineTo(puntaX, puntaY);

        // Línea desde la punta inferior hasta la base derecha de la V
        corazon.lineTo(rightVBaseX, vBaseY);

        // Dibujar el lóbulo derecho
        // Curva desde la base derecha de la V hasta la hendidura
        // Puntos de control: uno hacia abajo y a la derecha, otro hacia arriba y a la derecha
        corazon.curveTo(x + width, y + height / 3, // Control 1 (ajustado)
                hendiduraX + controlLobeXOffset * 0.8, hendiduraY - controlLobeYOffset * 0.5, // Control 2 (ajustado)
                hendiduraX, hendiduraY); // Destino (la hendidura)


        // Cerrar la ruta (opcional, pero asegura un polígono cerrado para el relleno)
        corazon.closePath();


        if (relleno) { // Verificar si el relleno está habilitado (propiedad heredada)
            if (colorDeRelleno != null) { // Usar colorDeRelleno (propiedad heredada)
                g2.setColor(colorDeRelleno);
                g2.fill(corazon); // Rellenar la forma del corazón
            }

            // Dibujar el borde si el color de relleno es diferente al color de borde
            if (colorDeRelleno != colorDePrimerPlano && colorDeRelleno != null) {
                g2.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano (propiedad heredada)
                g2.draw(corazon); // Dibujar el contorno del corazón
            } else if (colorDeRelleno == null) {
                // Si no hay color de relleno especificado pero relleno es true,
                // puedes decidir si quieres dibujar el borde o no.
                // Siguiendo la lógica de tus otras figuras, si colorDeRelleno es null
                // y relleno es true, solo se rellenaría (con el color actual, que ya se estableció).
                // Si quieres el borde, descomenta la siguiente línea:
                // g2.setColor(colorDePrimerPlano);
                // g2.draw(corazon);
            }

        } else { // Si no hay relleno, solo dibujar el contorno
            g2.setColor(colorDePrimerPlano); // Usar colorDePrimerPlano (propiedad heredada)
            g2.draw(corazon); // Dibujar el contorno del corazón
        }
    }

    // Ejemplo de getters si los necesitas:
    /*
    public Point getPuntoInicial() {
        return puntoInicial;
    }

    public Point getPuntoActual() {
        return puntoActual;
    }
    */
}
