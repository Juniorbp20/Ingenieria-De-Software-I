package paint202510;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JToolBar.Separator;
import javax.swing.AbstractButton;

/**
 * Clase BarraDeHerramientas - Crea y gestiona la barra de herramientas con botones de figuras y acciones.
 * Ahora incluye botones para Deshacer, Rehacer y Limpiar.
 */
public class BarraDeHerramientas extends JToolBar{

    protected JToggleButton btnBorrador;
    protected JToggleButton btnLapiz;
    protected JToggleButton btnLinea;
    protected JToggleButton btnRectangulo;
    protected JToggleButton btnOvalo;
    protected JToggleButton btnCirculo;
    protected JToggleButton btnCuadrado;
    protected JToggleButton btnTriangulo;
    protected JToggleButton btnPentagono;
    protected JToggleButton btnRombo;
    protected JToggleButton btnHeptagono;
    protected JToggleButton btnOctagono;
    protected JToggleButton btnEstrella;
    protected JToggleButton btnFlecha;

    protected JButton btnGuardar;
    protected JButton btnDeshacer; // Botón para Deshacer
    protected JButton btnRehacer; // Botón para Rehacer
    protected JButton btnLimpiar; // Botón para Limpiar Lienzo

    public BarraDeHerramientas() {
        setOrientation(JToolBar.VERTICAL);

        btnLapiz = new JToggleButton("Lapiz");
        btnLinea = new JToggleButton("Linea");
        btnRectangulo = new JToggleButton("Rectangulo");
        btnOvalo = new JToggleButton("Óvalo");
        btnCirculo = new JToggleButton("Círculo");
        btnBorrador = new JToggleButton("Borrador");
        btnCuadrado = new JToggleButton("Cuadrado");
        btnTriangulo = new JToggleButton("Triángulo");
        btnPentagono = new JToggleButton("Pentágono");
        btnRombo = new JToggleButton("Rombo");
        btnHeptagono = new JToggleButton("Heptagono");
        btnOctagono = new JToggleButton("Octagono");
        btnEstrella = new JToggleButton("Estrella");
        btnFlecha = new JToggleButton("Flecha");
        btnGuardar = new JButton("Guardar");

        // Inicializar botones Deshacer, Rehacer y Limpiar
        btnDeshacer = new JButton("Deshacer");
        btnRehacer = new JButton("Rehacer");
        btnLimpiar = new JButton("Limpiar");

        // Añadir botones Deshacer, Rehacer y Limpiar
        formatearYAgregar(btnDeshacer, "deshacer.png", "Deshacer última acción"); // Asumiendo que tienes iconos como deshacer.png
        formatearYAgregar(btnRehacer, "rehacer.png", "Rehacer última acción deshecha"); // Asumiendo que tienes iconos como rehacer.png
        formatearYAgregar(btnLimpiar, "limpiar.png", "Limpiar todo el lienzo"); // Asumiendo que tienes iconos como limpiar.png
        // Añadir un separador visual
        add(new Separator());

        formatearYAgregar(btnLapiz, "lapiz.png", "Dibujo Libre");
        formatearYAgregar(btnLinea, "linea.png", "Línea");
        formatearYAgregar(btnRectangulo, "rectangulo.png", "Rectángulo");
        formatearYAgregar(btnCuadrado, "cuadrado.png", "Cuadrado");
        formatearYAgregar(btnOvalo, "ovalo.png", "Óvalo");
        formatearYAgregar(btnCirculo, "circulo.png", "Círculo");
        formatearYAgregar(btnTriangulo, "triangulo.png", "Triángulo");
        formatearYAgregar(btnRombo, "rombo.png", "Rombo");
        formatearYAgregar(btnPentagono, "pentagono.png", "Pentágono");
        formatearYAgregar(btnHeptagono, "heptagono.png", "Heptagono");
        formatearYAgregar(btnOctagono, "octagono.png", "Octagono");
        formatearYAgregar(btnEstrella, "estrella.png", "Estrella");
        formatearYAgregar(btnFlecha, "flecha.png", "Flecha");
        formatearYAgregar(btnBorrador, "borrador.png", "Borrador");

        // Añadir otro separador visual antes del botón Guardar
        add(new Separator());
        formatearYAgregar(btnGuardar, "guardar.png", "Guardar Imagen");


        // Configurar el ButtonGroup
        ButtonGroup grupoBotones = new ButtonGroup();
        for(Component boton : this.getComponents()) {
            if (boton instanceof JToggleButton) {
                grupoBotones.add((JToggleButton) boton);
            }
        }
    }

    private void formatearYAgregar(AbstractButton boton, String nombreIcono, String tooltip) {
        boton.setFocusable(false);
        boton.setToolTipText("Seleccione: " + tooltip);

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


    public String getHerramientaSeleccionada() {
        if (btnLapiz.isSelected()) {
            return "Dibujo Libre";
        } else if (btnLinea.isSelected()) {
            return "Línea";
        } else if (btnRectangulo.isSelected()) {
            return "Rectángulo";
        } else if (btnOvalo.isSelected()) {
            return "Óvalo";
        } else if (btnCirculo.isSelected()) {
            return "Círculo";
        } else if (btnBorrador.isSelected()) {
            return "Borrador";
        } else if (btnCuadrado.isSelected()) {
            return "Cuadrado";
        } else if (btnTriangulo.isSelected()) {
            return "Triángulo";
        } else if (btnPentagono.isSelected()) {
            return "Pentágono";
        } else if (btnRombo.isSelected()) {
            return "Rombo";
        } else if (btnHeptagono.isSelected()) {
            return "Heptagono";
        } else if (btnOctagono.isSelected()) {
            return "Octagono";
        } else if (btnEstrella.isSelected()) {
            return "Estrella";
        } else if (btnFlecha.isSelected()) {
            return "Flecha";
        }
        else {
            return "Dibujo Libre";
        }
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * Obtiene el botón de Deshacer.
     * @return El JButton de Deshacer.
     */
    public JButton getBtnDeshacer() {
        return btnDeshacer;
    }

    /**
     * Obtiene el botón de Rehacer.
     * @return El JButton de Rehacer.
     */
    public JButton getBtnRehacer() {
        return btnRehacer;
    }

    /**
     * Obtiene el botón de Limpiar.
     * @return El JButton de Limpiar.
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
}