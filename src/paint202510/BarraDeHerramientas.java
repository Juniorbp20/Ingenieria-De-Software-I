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
 * Ahora incluye el botón de selección de figura.
 */
public class BarraDeHerramientas extends JToolBar{

    // JToggleButtons para las herramientas de dibujo y selección
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
    protected JToggleButton btnCorazon;
    protected JToggleButton btnTrapecio;
    protected JToggleButton btnSemicirculo;
    protected JToggleButton btnSeleccionar; // Botón para la herramienta de selección

    // JButtons para acciones
    protected JButton btnGuardar;
    protected JButton btnDeshacer;
    protected JButton btnRehacer;
    protected JButton btnLimpiar;

    private ButtonGroup grupoBotones;

    private PanelDeColores panelDeColores; // Referencia a PanelDeColores

    public BarraDeHerramientas() {
        setOrientation(JToolBar.VERTICAL);

        // Inicialización de los botones de figura (JToggleButtons)
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
        btnCorazon = new JToggleButton("Corazon");
        btnTrapecio = new JToggleButton("Trapecio");
        btnSemicirculo = new JToggleButton("Semicirculo");

        // Inicialización del botón de selección (JToggleButton)
        btnSeleccionar = new JToggleButton("Seleccionar Figura");


        // Inicialización de los botones de acción (JButtons)
        btnGuardar = new JButton("Guardar");
        btnDeshacer = new JButton("Deshacer");
        btnRehacer = new JButton("Rehacer");
        btnLimpiar = new JButton("Limpiar");

        // Formatear y agregar botones de acción primero
        formatearYAgregar(btnDeshacer, "deshacer.png", "Deshacer última acción", false);
        formatearYAgregar(btnRehacer, "rehacer.png", "Rehacer última acción deshecha", false);
        formatearYAgregar(btnLimpiar, "limpiar.png", "Limpiar todo el lienzo", false);
        formatearYAgregar(btnSeleccionar, "seleccion.png", "Seleccionar Figura", true);

        // Añadir un separador visual
        add(new Separator());

        // Formatear y agregar botones de figuras y herramientas de dibujo (son herramientas)
        formatearYAgregar(btnLapiz, "lapiz.png", "Dibujo Libre", true);
        formatearYAgregar(btnLinea, "linea.png", "Línea", true);
        formatearYAgregar(btnRectangulo, "rectangulo.png", "Rectángulo", true);
        formatearYAgregar(btnCuadrado, "cuadrado.png", "Cuadrado", true);
        formatearYAgregar(btnOvalo, "ovalo.png", "Óvalo", true);
        formatearYAgregar(btnCirculo, "circulo.png", "Círculo", true);
        formatearYAgregar(btnTriangulo, "triangulo.png", "Triángulo", true);
        formatearYAgregar(btnRombo, "rombo.png", "Rombo", true);
        formatearYAgregar(btnPentagono, "pentagono.png", "Pentágono", true);
        formatearYAgregar(btnHeptagono, "heptagono.png", "Heptagono", true);
        formatearYAgregar(btnOctagono, "octagono.png", "Octagono", true);
        formatearYAgregar(btnEstrella, "estrella.png", "Estrella", true);
        formatearYAgregar(btnFlecha, "flecha.png", "Flecha", true);
        formatearYAgregar(btnCorazon, "corazon.png", "Corazón", true);
        formatearYAgregar(btnTrapecio, "trapecio.png", "Trapecio", true);
        formatearYAgregar(btnBorrador, "borrador.png", "Borrador", true);
        formatearYAgregar(btnSemicirculo, "semicirculo.png", "Semicirculo", true);


        // Añadir otro separador visual antes del botón Guardar
        add(new Separator());
        formatearYAgregar(btnGuardar, "guardar.png", "Guardar Imagen", false);


        // Configurar el ButtonGroup para los JToggleButtons (todas las herramientas)
        grupoBotones = new ButtonGroup();
        grupoBotones.add(btnSeleccionar); // Añadir el botón de selección al grupo
        grupoBotones.add(btnLapiz);
        grupoBotones.add(btnLinea);
        grupoBotones.add(btnRectangulo);
        grupoBotones.add(btnCuadrado);
        grupoBotones.add(btnOvalo);
        grupoBotones.add(btnCirculo);
        grupoBotones.add(btnTriangulo);
        grupoBotones.add(btnPentagono);
        grupoBotones.add(btnRombo);
        grupoBotones.add(btnHeptagono);
        grupoBotones.add(btnOctagono);
        grupoBotones.add(btnEstrella);
        grupoBotones.add(btnFlecha);
        grupoBotones.add(btnCorazon);
        grupoBotones.add(btnTrapecio);
        grupoBotones.add(btnSemicirculo);
        grupoBotones.add(btnBorrador);


        // --- Listeners para los botones de herramienta (JToggleButtons) ---
        ActionListener herramientaButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando un botón de herramienta (incluido selección) es seleccionado
                JToggleButton sourceButton = (JToggleButton) e.getSource();
                if (sourceButton.isSelected()) {
                    // Deseleccionar la figura en el panel de dibujo al cambiar a cualquier herramienta
                    if (panelDeColores != null && panelDeColores.getPanelDeDibujo() != null) {
                        panelDeColores.getPanelDeDibujo().deseleccionarFigura();
                        panelDeColores.getPanelDeDibujo().repaint(); // Repintar para ocultar el borde de selección
                    }
                    // La coordinación con PanelDeColores (deseleccionar su botón de selección si existiera)
                    // ya no es necesaria porque el botón de selección está aquí.
                }
            }
        };

        // Añadir el mismo listener a todos los JToggleButtons
        btnSeleccionar.addActionListener(herramientaButtonListener);
        btnLapiz.addActionListener(herramientaButtonListener);
        btnLinea.addActionListener(herramientaButtonListener);
        btnRectangulo.addActionListener(herramientaButtonListener);
        btnOvalo.addActionListener(herramientaButtonListener);
        btnCirculo.addActionListener(herramientaButtonListener);
        btnCuadrado.addActionListener(herramientaButtonListener);
        btnTriangulo.addActionListener(herramientaButtonListener);
        btnPentagono.addActionListener(herramientaButtonListener);
        btnRombo.addActionListener(herramientaButtonListener);
        btnHeptagono.addActionListener(herramientaButtonListener);
        btnOctagono.addActionListener(herramientaButtonListener);
        btnEstrella.addActionListener(herramientaButtonListener);
        btnFlecha.addActionListener(herramientaButtonListener);
        btnCorazon.addActionListener(herramientaButtonListener);
        btnTrapecio.addActionListener(herramientaButtonListener);
        btnSemicirculo.addActionListener(herramientaButtonListener);
        btnBorrador.addActionListener(herramientaButtonListener);


        // --- Listeners para los botones de acción (JButtons) ---
        // Al usar un botón de acción, deseleccionar cualquier herramienta seleccionada
        ActionListener accionButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deseleccionarBotonesHerramienta(); // Deseleccionar cualquier botón de herramienta
                // Deseleccionar la figura en el panel de dibujo al usar una herramienta de acción
                if (panelDeColores != null && panelDeColores.getPanelDeDibujo() != null) {
                    panelDeColores.getPanelDeDibujo().deseleccionarFigura();
                    panelDeColores.getPanelDeDibujo().repaint(); // Repintar para ocultar el borde de selección
                }
            }
        };

        btnDeshacer.addActionListener(accionButtonListener);
        btnRehacer.addActionListener(accionButtonListener);
        btnLimpiar.addActionListener(accionButtonListener);


    }

    // Método formatearYAgregar actualizado
    private void formatearYAgregar(AbstractButton boton, String nombreIcono, String tooltip, boolean esHerramienta) {
        boton.setFocusable(false);
        boton.setToolTipText("Seleccione: " + tooltip); // Mantener "Seleccione: " para getHerramientaSeleccionada()

        // Usar un ClientProperty para marcar si es un botón de herramienta (JToggleButton)
        boton.putClientProperty("esHerramienta", esHerramienta);


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

    // Método de ayuda para verificar si un botón es una herramienta (JToggleButton en el ButtonGroup)
    private boolean esHerramienta(AbstractButton boton) {
        Object prop = boton.getClientProperty("esHerramienta");
        return prop != null && (boolean) prop;
    }


    public String getHerramientaSeleccionada() {
        // Recorrer los botones para encontrar cuál JToggleButton está seleccionado
        for (Component boton : this.getComponents()) {
            if (boton instanceof JToggleButton) {
                JToggleButton toggleButton = (JToggleButton) boton;
                if (toggleButton.isSelected()) {
                    // Devolver el nombre de la herramienta (obtenido del tooltip sin el prefijo)
                    return toggleButton.getToolTipText().replace("Seleccione: ", "");
                }
            }
        }
        // Si ningún JToggleButton está seleccionado, devolver un valor por defecto o indicar "Ninguna"
        // Podrías considerar que el Lápiz es la herramienta por defecto si no hay ninguna seleccionada.
        // Aquí devolvemos "Ninguna" para claridad.
        return "Ninguna";
    }


    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * Gets the Undo button.
     * @return The Undo JButton.
     */
    public JButton getBtnDeshacer() {
        return btnDeshacer;
    }

    /**
     * Gets the Redo button.
     * @return The Redo JButton.
     */
    public JButton getBtnRehacer() {
        return btnRehacer;
    }

    /**
     * Gets the Clear button.
     * @return The Clear JButton.
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /**
     * Deselecciona todos los JToggleButtons (herramientas) en esta barra de herramientas.
     */
    public void deseleccionarBotonesHerramienta() {
        if (grupoBotones != null) {
            grupoBotones.clearSelection(); // Esto deselecciona todos los botones en el grupo
        }
    }

    /**
     * Establece la referencia al PanelDeColores.
     * @param panelDeColores La instancia de PanelDeColores.
     */
    public void setPanelDeColores(PanelDeColores panelDeColores) {
        this.panelDeColores = panelDeColores;
    }

    // Este método ahora está en BarraDeHerramientas
    /**
     * Establece el estado seleccionado del botón de selección de figura.
     * @param selected El estado a establecer (true para seleccionado, false para deseleccionado).
     */
    public void setSeleccionarButtonState(boolean selected) {
        btnSeleccionar.setSelected(selected);
        // Deseleccionar la figura en el panel de dibujo si se desactiva la selección
        if (!selected && panelDeColores != null && panelDeColores.getPanelDeDibujo() != null) {
            panelDeColores.getPanelDeDibujo().deseleccionarFigura();
            panelDeColores.getPanelDeDibujo().repaint(); // Repintar para actualizar visualmente
        } else if (selected && panelDeColores != null && panelDeColores.getPanelDeDibujo() != null) {
            // Si se selecciona el botón de selección, deseleccionar cualquier figura actual antes de seleccionar una nueva
            panelDeColores.getPanelDeDibujo().deseleccionarFigura();
            panelDeColores.getPanelDeDibujo().repaint(); // Repintar para actualizar visualmente
        }

        // Cuando el botón de selección se activa desde PanelDeColores,
        // deseleccionar otros botones en esta barra de herramientas.
        if (selected) {
            deseleccionarOtrosBotonesHerramienta(btnSeleccionar);
        }
    }

    // Método auxiliar para deseleccionar todos los botones de herramienta excepto uno específico
    private void deseleccionarOtrosBotonesHerramienta(JToggleButton botonExcluido) {
        for (Component boton : this.getComponents()) {
            if (boton instanceof JToggleButton && boton != botonExcluido) {
                ((JToggleButton) boton).setSelected(false);
            }
        }
    }
}