package paint202510;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Clase PanelDeColores - Proporciona controles para seleccionar colores y opciones de relleno,
 * así como botones para mostrar información del proyecto y cargar una imagen.
 * El botón de selección ha sido movido a BarraDeHerramientas.
 */
public class PanelDeColores extends JPanel {
    /**
     * Botón para abrir el selector de color del borde.
     */
    private final JButton botonColorBorde;
    /**
     * El color de borde seleccionado actualmente.
     */
    private Color colorBordeActual = Color.BLACK;
    /**
     * Botón para abrir el selector de color de relleno.
     */
    private JButton botonColorRelleno;
    /**
     * Botón para mostrar información del proyecto.
     */
    private JButton botonInformacion;
    /**
     * El color de relleno seleccionado actualmente.
     */
    private Color colorRellenoActual = Color.WHITE;
    /**
     * Casilla de verificación para habilitar/deshabilitar el relleno de formas.
     */
    private final JCheckBox checkRellenar;
    /**
     * Botón para cargar una imagen en el panel de dibujo.
     */
    protected JToggleButton botonCargar; // Mantenido como JToggleButton si se usa como una herramienta

    /**
     * Referencia al panel de dibujo.
     */
    private PanelDeDibujo panelDeDibujo = null;

    /**
     * Referencia a la barra de herramientas para coordinar la selección de herramientas.
     */
    private BarraDeHerramientas barraDeHerramientas = null; // Referencia a BarraDeHerramientas

    /**
     * Constructor del panel de colores y sus componentes.
     */
    public PanelDeColores() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Usar FlowLayout para la disposición de componentes.
        botonColorBorde = new JButton("Color Borde"); // Inicializar botón de color de borde.
        botonColorRelleno = new JButton("Color Relleno"); // Inicializar botón de color de relleno.
        botonInformacion = new JButton("Informacion"); // Inicializar botón de información.
        botonCargar = new JToggleButton("Cargar Imagen"); // Inicializar botón de cargar imagen.

        // El botón de selección se ha movido a BarraDeHerramientas
        // botonSeleccionar = new JToggleButton("Seleccionar Figura");


        checkRellenar = new JCheckBox("Rellenar"); // Inicializar casilla de verificación de relleno.
        checkRellenar.setSelected(false); // Establecer el estado inicial de la casilla de relleno.

        // Añadir listener de acciones para el botón de color de borde.
        botonColorBorde.addActionListener((ActionEvent e) -> {
            Color nuevoColor = JColorChooser.showDialog(
                    PanelDeColores.this,
                    "Seleccionar Color del Borde",
                    colorBordeActual
            );

            if (nuevoColor != null) {
                colorBordeActual = nuevoColor; // Actualizar el color de borde actual si se selecciona un nuevo color.
                // Si hay una figura seleccionada, actualizar su color de borde
                if (panelDeDibujo != null && panelDeDibujo.getFiguraSeleccionada() != null) {
                    panelDeDibujo.getFiguraSeleccionada().setColorDePrimerPlano(colorBordeActual);
                    panelDeDibujo.repaint(); // Repintar para mostrar el cambio de color
                }
                // Al seleccionar un color, deseleccionar el modo de selección si está activo
                if (barraDeHerramientas != null) {
                    barraDeHerramientas.setSeleccionarButtonState(false);
                }
            }
        });

        // Añadir listener de acciones para el botón de color de relleno.
        botonColorRelleno.addActionListener((ActionEvent e) -> {
            Color nuevoColor = JColorChooser.showDialog(
                    PanelDeColores.this,
                    "Seleccionar Color de Relleno",
                    colorRellenoActual
            );

            if (nuevoColor != null) {
                colorRellenoActual = nuevoColor; // Actualizar el color de relleno actual si se selecciona un nuevo color.
                // Si hay una figura seleccionada y está marcada como rellena, actualizar su color de relleno
                if (panelDeDibujo != null && panelDeDibujo.getFiguraSeleccionada() != null && panelDeDibujo.getFiguraSeleccionada().isRelleno()) {
                    panelDeDibujo.getFiguraSeleccionada().setColorDeRelleno(colorRellenoActual);
                    panelDeDibujo.repaint(); // Repintar para mostrar el cambio de color
                }
                // Al seleccionar un color, deseleccionar el modo de selección si está activo
                if (barraDeHerramientas != null) {
                    barraDeHerramientas.setSeleccionarButtonState(false);
                }
            }
        });

        // Añadir listener de acciones para la casilla de verificación de relleno.
        checkRellenar.addActionListener((ActionEvent e) -> {
            // Si hay una figura seleccionada, actualizar su estado de relleno
            if (panelDeDibujo != null && panelDeDibujo.getFiguraSeleccionada() != null) {
                panelDeDibujo.getFiguraSeleccionada().setRelleno(checkRellenar.isSelected());
                panelDeDibujo.repaint(); // Repintar para mostrar el cambio de relleno
            }
            // Al cambiar el estado de relleno, deseleccionar el modo de selección si está activo
            if (barraDeHerramientas != null) {
                barraDeHerramientas.setSeleccionarButtonState(false);
            }
        });


        // Añadir listener de acciones para el botón de información.
        botonInformacion.addActionListener((ActionEvent e) -> {
            mostrarInformacionProyecto(); // Llamar al método para mostrar información del proyecto.
            // Al usar el botón de información, deseleccionar el modo de selección si está activo
            if (barraDeHerramientas != null) {
                barraDeHerramientas.setSeleccionarButtonState(false);
            }
        });

        // Añadir listener de acciones para el botón de cargar imagen.
        botonCargar.addActionListener((ActionEvent e) -> {
            // Al usar el botón de cargar, deseleccionar el modo de selección si está activo
            if (barraDeHerramientas != null) {
                barraDeHerramientas.setSeleccionarButtonState(false);
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Cargar Imagen");
            int userSelection = fileChooser.showOpenDialog(PanelDeColores.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                try {
                    BufferedImage loadedImage = ImageIO.read(fileToLoad);
                    if (loadedImage != null) {
                        if (panelDeDibujo != null) {
                            panelDeDibujo.setImagenDeFondo(loadedImage); // Establecer la imagen cargada como fondo en el panel de dibujo.
                            JOptionPane.showMessageDialog(PanelDeColores.this,
                                    "Imagen cargada correctamente.",
                                    "Carga Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(PanelDeColores.this,
                                    "Error interno: No se pudo obtener la referencia al Panel de Dibujo.",
                                    "Error de Carga",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(PanelDeColores.this,
                                "No se pudo leer la imagen. Asegúrate de que es un formato válido.",
                                "Error al Cargar",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PanelDeColores.this,
                            "Error al leer el archivo de imagen:\n" + ex.getMessage(),
                            "Error de E/S al Cargar",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PanelDeColores.this,
                            "Ocurrió un error inesperado al cargar la imagen:\n" + ex.getMessage(),
                            "Error General al Cargar",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // El listener para el botón de selección se ha movido a BarraDeHerramientas

        // Añadir componentes al panel.
        add(botonColorBorde);
        add(botonColorRelleno);
        add(checkRellenar);
        formatearYAgregar(botonInformacion, "informacion.png", "Infotmacion", false);
        formatearYAgregar(botonCargar, "subirImagen.png", "Cargar Imagen", false);


        // Nota: Necesitarás añadir los botones de Copiar y Pegar aquí también en un paso posterior.
    }
    // Método formatearYAgregar modificado para incluir si es un botón de figura
    private void formatearYAgregar(AbstractButton boton, String nombreIcono, String tooltip, boolean esBotonFigura) {
        boton.setFocusable(false);
        boton.setToolTipText("Seleccione: " + tooltip);

        // Usar un ClientProperty para marcar si es un botón de figura
        boton.putClientProperty("esBotonFigura", esBotonFigura);


        java.net.URL ruta = getClass().getResource("/iconos/" + nombreIcono);
        if (ruta != null) {
            boton.setIcon(new ImageIcon(ruta));
            boton.setText(null);
        } else {
            if (boton.getText() == null || boton.getText().isEmpty()) {
                boton.setText(tooltip);
            }

            boton.setFont(new Font("Arial", Font.BOLD, 10));
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            boton.setVerticalTextPosition(SwingConstants.BOTTOM);

            System.err.println("Icono no encontrado: /iconos/" + nombreIcono + " (Usando texto)");
        }

        add(boton);
    }


    /**
     * Establece la referencia al panel de dibujo.
     * @param panelDeDibujo La instancia de PanelDeDibujo.
     */
    public void setPanelDeDibujo(PanelDeDibujo panelDeDibujo) {
        this.panelDeDibujo = panelDeDibujo;
    }

    /**
     * Obtiene la referencia al panel de dibujo.
     * @return La instancia de PanelDeDibujo.
     */
    public PanelDeDibujo getPanelDeDibujo() {
        return panelDeDibujo;
    }


    /**
     * Establece la referencia a la barra de herramientas.
     * @param barraDeHerramientas La instancia de BarraDeHerramientas.
     */
    public void setBarraDeHerramientas(BarraDeHerramientas barraDeHerramientas) {
        this.barraDeHerramientas = barraDeHerramientas;
    }


    /**
     * Muestra información sobre el proyecto en un cuadro de diálogo de mensaje.
     */
    private void mostrarInformacionProyecto() {
        String mensaje = "Descripción del Proyecto:\n" +
                "Este proyecto es una aplicación de dibujo llamada \"Paint 2025-10\", que permite\n" +
                "a los usuarios crear y editar gráficos utilizando diversas herramientas.\n" +
                "Los usuarios pueden seleccionar diferentes formas, colores y opciones de relleno para\n" +
                "personalizar sus creaciones. La interfaz es intuitiva, con una barra de herramientas que facilita\n" +
                "el acceso a las funciones de dibujo, y un panel de colores para elegir los tonos deseados.\n" +
                "La aplicación también permite guardar las imágenes creadas en formato PNG.\n\n" +
                "Integrantes del Proyecto:\n" +
                "- José Ariel Pereyra Francisco (Profesor)\n" +
                "- Gustavo Junior Bonifacio Peña (Gerente del proyecto)\n" +
                "- Carolina De Jesús Reinoso\n" +
                "- Robinzon Michel Gabino Fernández\n" +
                "- Marcos Miguel Gómez Camilo\n" +
                "- Jon Luis Jones Esteban\n" +
                "- Frailyn José Martinez Santos\n" +
                "- Ebenezer Peña Hernandez\n" +
                "- Bryan José Ureña Castillo";

        JOptionPane.showMessageDialog(this, mensaje, "Acerca del Proyecto", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Obtiene el color de borde seleccionado actualmente.
     * @return El color de borde actual.
     */
    public Color getColorBordeActual() {
        return colorBordeActual;
    }

    /**
     * Obtiene el color de relleno seleccionado actualmente.
     * @return El color de relleno actual.
     */
    public Color getColorRellenoActual() {
        return colorRellenoActual;
    }

    /**
     * Verifica si la opción de relleno está seleccionada actualmente.
     * @return true si el relleno está habilitado, false en caso contrario.
     */
    public boolean isRellenar() {
        return checkRellenar.isSelected();
    }


}