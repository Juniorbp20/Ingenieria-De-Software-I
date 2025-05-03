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


/**
 *
 * @author josearielpereyra
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
    protected JToggleButton btnGuardar;
    protected JToggleButton btnPentagono;
    protected JToggleButton btnRombo;
    protected JToggleButton btnHeptagono;
    protected JToggleButton btnOctagono;
    protected JToggleButton btnEstrella;
    protected JToggleButton btnFlecha;

    public BarraDeHerramientas() {
        setOrientation(JToolBar.VERTICAL);

        // Inicialización de los botones de forma/herramienta
        btnLapiz = new JToggleButton("Lapiz");
        btnLinea = new JToggleButton("Linea");
        btnRectangulo = new JToggleButton("Rectangulo");
        btnOvalo = new JToggleButton("Óvalo");
        btnCirculo = new JToggleButton("Círculo");
        btnBorrador = new JToggleButton("Borrador");
        btnCuadrado = new JToggleButton("Cuadrado");
        btnTriangulo = new JToggleButton("Triángulo");
        btnGuardar = new JToggleButton("Guardar");
        btnPentagono = new JToggleButton("Pentágono");
        btnRombo = new JToggleButton("Rombo");
        btnHeptagono = new JToggleButton("Heptagono");
        btnOctagono = new JToggleButton("Octagono");
        btnEstrella = new JToggleButton("Estrella");
        btnFlecha = new JToggleButton("Flecha");

        // Formatear y agregar los botones de forma a la barra de herramientas
        formatearYAgregar(btnLapiz, "lapiz.png", "Dibujo Libre");
        formatearYAgregar(btnLinea, "linea.png", "Línea");
        formatearYAgregar(btnRectangulo, "rectangulo.png", "Rectángulo");
        formatearYAgregar(btnOvalo, "ovalo.png", "Óvalo");
        formatearYAgregar(btnCirculo, "circulo.png", "Círculo");
        formatearYAgregar(btnBorrador, "borrador.png", "Borrador");
        formatearYAgregar(btnCuadrado, "cuadrado.png", "Cuadrado");
        formatearYAgregar(btnTriangulo, "triangulo.png", "Triángulo");
        formatearYAgregar(btnGuardar, "guardar.png", "Guardar Imagen");
        formatearYAgregar(btnPentagono, "pentagono.png", "Pentágono");
        formatearYAgregar(btnRombo, "rombo.png", "Rombo");
        formatearYAgregar(btnHeptagono, "heptagono.png", "Heptagono");
        formatearYAgregar(btnOctagono, "octagono.png", "Octagono");
        formatearYAgregar(btnEstrella, "estrella.png", "Estrella");
        formatearYAgregar(btnFlecha, "flecha.png", "Flecha");
        // Eliminamos formatearYAgregar(btnColor,"Color.png", "Color");


        // Añadir los controles de color y relleno a la barra de herramientas
        add(new JToolBar.Separator());

        // Configurar el ButtonGroup
        ButtonGroup grupoBotones = new ButtonGroup();
        for(Component boton : this.getComponents()) {
            if (boton instanceof JToggleButton && boton != btnGuardar) {
                grupoBotones.add((JToggleButton) boton);
            }
        }
    }

    private void formatearYAgregar(JToggleButton boton, String nombreIcono, String tooltip) {
        boton.setFocusable(false);
        boton.setToolTipText("Seleccione: " + tooltip);
        boton.setText(null);

        java.net.URL ruta = getClass().getResource("/iconos/" + nombreIcono);
        if (ruta != null) {
            boton.setIcon(new javax.swing.ImageIcon(ruta));
        } else {
            boton.setText(tooltip);
            boton.setFont(new Font("Arial", Font.BOLD, 10));
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        }

        add(boton);
    }

    // Método para obtener la herramienta seleccionada
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
        } else {
            return "Dibujo Libre";
        }
    }
}