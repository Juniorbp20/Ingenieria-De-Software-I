package paint202510;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
public class PanelDeColores extends JPanel {
    private final JButton botonColorBorde;
    private Color colorBordeActual = Color.BLACK;
    private JButton botonColorRelleno; // Este botón no está inicializado
    private Color colorRellenoActual = Color.WHITE;
    private final JCheckBox checkRellenar;
    public PanelDeColores() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Opcional: alinear componentes a la izquierda
        
        // Inicializar los botones de color
        botonColorBorde = new JButton("Color Borde");
        botonColorRelleno = new JButton("Color Relleno"); // AÑADIDO: Inicializar el botón de relleno
        
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
        
        // Añadir los controles al panel
        add(botonColorBorde);
        add(botonColorRelleno);
        add(checkRellenar);
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