package paint202510;

import figuras.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack; // Importar Stack para la funcionalidad de rehacer.

import javax.imageio.ImageIO; // Aunque se usa en saveImagen, está relacionado con el contenido de este panel.
import java.awt.image.BufferedImage; // Aunque se usa en setImagenDeFondo y saveImagen.
import java.io.File; // Aunque se usa en saveImagen.
import javax.swing.JFileChooser; // Aunque se usa en saveImagen.
import javax.swing.JOptionPane; // Aunque se usa en saveImagen y setImagenDeFondo.

/**
 * Clase PanelDeDibujo - Representa el lienzo de dibujo donde se dibujan las figuras.
 * Maneja los eventos del ratón para crear y modificar figuras y gestiona la lista de figuras.
 * Incluye funcionalidad para Undo, Redo, y Clear. Ahora soporta las figuras Corazon y Trapecio.
 */
public class PanelDeDibujo extends JPanel {
    private Figura figuraActual; // La figura que se está dibujando o modificando actualmente.
    private List<Figura> figuras = new ArrayList<>(); // Lista para almacenar todas las figuras dibujadas.
    private Stack<Figura> figurasDeshechas = new Stack<>(); // Pila para almacenar figuras deshechas para la funcionalidad de rehacer.
    PanelDeColores panelDeColores; // Referencia al panel de colores para obtener la configuración actual de color/relleno.
    BarraDeHerramientas barraDeHerramientas; // Referencia a la barra de herramientas para obtener la herramienta seleccionada.
    private BufferedImage imagenDeFondo; // Imagen de fondo para el lienzo.


    /**
     * Constructor del panel de dibujo.
     * @param barraDeHerramientas La instancia de BarraDeHerramientas.
     * @param panelDeColores La instancia de PanelDeColores.
     */
    public PanelDeDibujo(BarraDeHerramientas barraDeHerramientas, PanelDeColores panelDeColores) {
        this.barraDeHerramientas = barraDeHerramientas;
        this.panelDeColores = panelDeColores;
        configurarEventosRaton(); // Configurar los listeners de eventos del ratón.
        setBackground(Color.WHITE); // Establecer el color de fondo del panel.
    }

    /**
     * Configura los listeners de eventos del ratón para el dibujo.
     */
    private void configurarEventosRaton() {
        addMouseListener(new MouseAdapter() {
            /**
             * Maneja los eventos de presión del ratón.
             * Inicia el dibujo de una nueva figura o inicia una acción basada en la herramienta seleccionada.
             * @param e El MouseEvent.
             */
            @Override
            public void mousePressed(MouseEvent e) {
                if (barraDeHerramientas.btnGuardar.isSelected()) {
                    // Guardar se maneja en el ActionListener de VentanaPrincipal
                    return;
                }

                // Obtener el tipo de figura según la herramienta seleccionada y crear una nueva instancia.
                figuraActual = obtenerFiguraADibujar(e.getPoint());

                // Si la figura actual no es nula (es decir, no es una acción como guardar), configurar sus propiedades
                if (figuraActual != null) {
                    // Usamos los colores y estado de relleno DEL PANEL DE COLORES
                    figuraActual.setColorDePrimerPlano(panelDeColores.getColorBordeActual()); // Establecer color de borde de la figura
                    figuraActual.setColorDeRelleno(panelDeColores.getColorRellenoActual()); // Establecer color de relleno de la figura
                    figuraActual.setRelleno(panelDeColores.isRellenar()); // Establecer estado de relleno de la figura

                    figuras.add(figuraActual); // Añadir la nueva figura a la lista.
                    figurasDeshechas.clear(); // Limpiar la pila de rehacer cuando se dibuja una nueva figura.
                    repaint(); // Repintar el panel para mostrar la nueva figura.
                }
            }

            /**
             * Maneja los eventos de liberación del ratón.
             * Finaliza la figura actual.
             * @param e El MouseEvent.
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                figuraActual = null; // Restablecer la figura actual después de completar el dibujo.
            }

        });


        addMouseMotionListener(new MouseAdapter() {
            /**
             * Maneja los eventos de arrastre del ratón.
             * Actualiza la figura actual mientras se arrastra el ratón.
             * @param e El MouseEvent.
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                String herramienta = barraDeHerramientas.getHerramientaSeleccionada();

                if ("Borrador".equals(herramienta)) {
                    figuraActual = new Borrador(e.getPoint());
                    figuras.add(figuraActual); // Añadir cada pequeña marca de borrador como una nueva figura
                    figurasDeshechas.clear(); // Limpiar la pila de rehacer al usar el borrador.
                } else if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                }
                repaint(); // Repintar el panel para mostrar la figura actualizada.
            }
        });
    }

    /**
     * Determina y crea un nuevo objeto Figura basado en la herramienta seleccionada actualmente.
     * @param punto El punto de inicio para la figura.
     * @return Un nuevo objeto Figura del tipo seleccionado.
     */
    private Figura obtenerFiguraADibujar(Point punto) {
        String herramienta = barraDeHerramientas.getHerramientaSeleccionada(); // Obtener el nombre de la herramienta seleccionada.

        // Crear una nueva instancia de figura basada en la herramienta seleccionada.
        switch (herramienta) {
            case "Línea":
                return new Linea(punto);
            case "Rectángulo":
                return new Rectangulo(punto);
            case "Borrador":
                return new Borrador(punto);
            case "Óvalo":
                return new Ovalo(punto);
            case "Círculo":
                return new Circulo(punto);
            case "Cuadrado":
                return new Cuadrado(punto);
            case "Triángulo":
                return new Triangulo(punto);
            case "Pentágono":
                return new Pentagono(punto);
            case "Rombo":
                return new Rombo(punto);
            case "Heptagono":
                return new Heptagono(punto);
            case "Octagono":
                return new Octagono(punto);
            case "Estrella":
                return new Estrella(punto);
            case "Flecha":
                return new Flecha(punto);
            case "Corazón":
                return new Corazon(punto);
            case "Trapecio": // Añadir caso para la figura Trapecio
                return new Trapecio(punto); // Crear una nueva instancia de Trapecio
            case "Dibujo Libre":
            default:
                return new DibujoLibre(punto); // Por defecto, dibujo libre (Lapiz)
        }
    }

    /**
     * Guarda el contenido actual del panel como una imagen PNG.
     * Este método es triggered por el ActionListener del botón Guardar en VentanaPrincipal.
     */
    private void guardarImagen() {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = imagen.getGraphics();
        paint(g);
        g.dispose();

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar Imagen");

        int seleccion = selector.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                String rutaArchivo = archivo.getAbsolutePath();
                if (!rutaArchivo.toLowerCase().endsWith(".png")) {
                    rutaArchivo += ".png";
                }
                ImageIO.write(imagen, "png", new File(rutaArchivo));
                JOptionPane.showMessageDialog(this, "Imagen guardada exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        }
    }

    /**
     * Pinta el componente. Este método es llamado por el framework Swing
     * cuando el componente necesita ser dibujado.
     * @param g El objeto Graphics a usar para pintar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagenDeFondo != null) {
            g.drawImage(imagenDeFondo, 0, 0, this);
        }

        for (Figura figura : figuras) {
            figura.dibujar(g);
        }
    }

    /**
     * Establece una imagen de fondo para el panel de dibujo.
     * @param imagen El BufferedImage a establecer como fondo.
     */
    public void setImagenDeFondo(BufferedImage imagen) {
        this.imagenDeFondo = imagen;
        repaint();
    }

    /**
     * Deshace la última acción de dibujo eliminando la última figura de la lista.
     * Mueve la figura deshecha a la pila de rehacer.
     */
    public void undo() {
        if (!figuras.isEmpty()) {
            Figura figuraDeshecha = figuras.remove(figuras.size() - 1); // Eliminar la última figura.
            figurasDeshechas.push(figuraDeshecha); // Empujar la figura deshecha a la pila de rehacer.
            repaint(); // Repintar el panel.
        }
    }

    /**
     * Rehace la última acción de dibujo deshecha moviendo una figura de la pila de rehacer
     * de vuelta a la lista principal de figuras.
     */
    public void redo() {
        if (!figurasDeshechas.isEmpty()) {
            Figura figuraRehecha = figurasDeshechas.pop(); // Sacar la figura de la pila de rehacer.
            figuras.add(figuraRehecha); // Añadir la figura de vuelta a la lista principal.
            repaint(); // Repintar el panel.
        }
        // Nota: Si se añade una nueva figura después de rehacer, la pila figurasDeshechas se limpia
        // en mousePressed o mouseDragged (para el borrador).
    }

    /**
     * Limpia completamente el lienzo de dibujo eliminando todas las figuras.
     * También limpia la pila de rehacer.
     */
    public void clearCanvas() {
        figuras.clear(); // Limpiar la lista principal de figuras.
        figurasDeshechas.clear(); // Limpiar la pila de rehacer.
        imagenDeFondo = null; // También limpiar la imagen de fondo si hay alguna.
        repaint(); // Repintar el panel vacío.
    }
}
