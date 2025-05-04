package paint202510;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PanelDeColores extends JPanel {
    private final JButton botonColorBorde;
    private Color colorBordeActual = Color.BLACK;
    private JButton botonColorRelleno; // Este botón no está inicializado
    private JButton botonInformacion;
    private Color colorRellenoActual = Color.WHITE;
    private final JCheckBox checkRellenar;
    protected JToggleButton botonCargar;

    public PanelDeColores() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Opcional: alinear componentes a la izquierda

        // Inicializar los botones de color
        botonColorBorde = new JButton("Color Borde");
        botonColorRelleno = new JButton("Color Relleno"); // AÑADIDO: Inicializar el botón de relleno
        botonInformacion = new JButton("Informacion");
        botonCargar = new JToggleButton("Cargar Imagen");


        // Inicializar el checkbox de rellenar
        checkRellenar = new JCheckBox("Rellenar");
        checkRellenar.setSelected(false);

        // Añadir Listener al botón de color de Borde
        botonColorBorde.addActionListener((ActionEvent e) -> {
            Color nuevoColor = JColorChooser.showDialog(
                    PanelDeColores.this,
                    "Seleccionar Color del Borde",
                    colorBordeActual
            );

            if (nuevoColor != null) {
                colorBordeActual = nuevoColor;
                // Opcional: Cambiar el color de fondo del botón para mostrar el color seleccionado
                // botonColorBorde.setBackground(colorBordeActual);
            }
        });

        // Añadir Listener al botón de color de Relleno
        botonColorRelleno.addActionListener((ActionEvent e) -> {
            Color nuevoColor = JColorChooser.showDialog(
                    PanelDeColores.this,
                    "Seleccionar Color de Relleno",
                    colorRellenoActual
            );

            if (nuevoColor != null) {
                colorRellenoActual = nuevoColor;
                // Opcional: Cambiar el color de fondo del botón para mostrar el color seleccionado
                // botonColorRelleno.setBackground(colorRellenoActual);
            }
        });

        // Añadir listener al botón de ayuda
        botonInformacion.addActionListener((ActionEvent e) -> {
            mostrarInformacionProyecto();
        });

        // Añadir los controles al panel
        add(botonColorBorde);
        add(botonColorRelleno);
        add(checkRellenar);
        add(botonInformacion);
    }

    private void mostrarInformacionProyecto() {
        // Cambiar el tamaño de la ventana
        setSize(800, 600); // Ajustar el tamaño de la ventana

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

    // Métodos públicos para obtener el color del borde seleccionado
    public Color getColorBordeActual() {
        return colorBordeActual;
    }

    // Métodos públicos para obtener el color de relleno seleccionado
    public Color getColorRellenoActual() {
        return colorRellenoActual;
    }

    // Métodos públicos para saber si el relleno está activado
    public boolean isRellenar() {
        return checkRellenar.isSelected();
    }
}