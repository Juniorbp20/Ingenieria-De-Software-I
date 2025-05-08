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
 * Ahora incluye botones para Deshacer, Rehacer, Limpiar, Corazón y Trapecio.
 * Se ha añadido funcionalidad para coordinar la selección de herramientas con PanelDeColores.
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
    protected JButton btnGuardar;
    protected JToggleButton btnPentagono;
    protected JToggleButton btnRombo;
    protected JToggleButton btnHeptagono;
    protected JToggleButton btnOctagono;
    protected JToggleButton btnEstrella;
    protected JToggleButton btnFlecha;
    protected JToggleButton btnCorazon; // Botón para la figura Corazón
    protected JToggleButton btnTrapecio; // Botón para la figura Trapecio
    protected JToggleButton btnSemicirculo;

    protected JButton btnDeshacer; // Button for Undo
    protected JButton btnRehacer; // Button for Redo
    protected JButton btnLimpiar; // Button for Clear Canvas

    private ButtonGroup grupoBotones; // Mantener una referencia al ButtonGroup

    private PanelDeColores panelDeColores; // Referencia a PanelDeColores

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
        btnCorazon = new JToggleButton("Corazon");
        btnTrapecio = new JToggleButton("Trapecio");
        btnSemicirculo = new JToggleButton("Semicirculo");


        btnGuardar = new JButton("Guardar");

        // Add Undo, Redo, and Clear buttons
        btnDeshacer = new JButton("Deshacer");
        btnRehacer = new JButton("Rehacer");
        btnLimpiar = new JButton("Limpiar");
        formatearYAgregar(btnDeshacer, "deshacer.png", "Deshacer última acción", false); // Último parámetro: esBotónFigura?
        formatearYAgregar(btnRehacer, "rehacer.png", "Rehacer última acción deshecha", false);
        formatearYAgregar(btnLimpiar, "limpiar.png", "Limpiar todo el lienzo", false);


        // Añadir un separador visual
        add(new Separator());

        // Formatear y agregar botones de figuras y herramientas, indicando que son botones de figura
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
        formatearYAgregar(btnSemicirculo, "semicirculo.png", "Circulo", true);

        // Añadir ActionListeners a los botones de figura para deseleccionar el botón de selección en PanelDeColores
        ActionListener figuraButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando un botón de figura es seleccionado
                if (((JToggleButton)e.getSource()).isSelected()) {
                    if (panelDeColores != null) {
                        // Deseleccionar el botón de selección en PanelDeColores
                        panelDeColores.setSeleccionarButtonState(false);
                        // Deseleccionar la figura en el panel de dibujo al cambiar a una herramienta de dibujo
                        if (panelDeColores.getPanelDeDibujo() != null) {
                            panelDeColores.getPanelDeDibujo().deseleccionarFigura();
                        }
                    }
                }
                // El caso 'else' (cuando un botón de figura se deselecciona porque otro se seleccionó)
                // no necesita hacer nada especial con el botón de selección en PanelDeColores
            }
        };

        btnLapiz.addActionListener(figuraButtonListener);
        btnLinea.addActionListener(figuraButtonListener);
        btnRectangulo.addActionListener(figuraButtonListener);
        btnOvalo.addActionListener(figuraButtonListener);
        btnCirculo.addActionListener(figuraButtonListener);
        btnCuadrado.addActionListener(figuraButtonListener);
        btnTriangulo.addActionListener(figuraButtonListener);
        btnPentagono.addActionListener(figuraButtonListener);
        btnRombo.addActionListener(figuraButtonListener);
        btnHeptagono.addActionListener(figuraButtonListener);
        btnOctagono.addActionListener(figuraButtonListener);
        btnEstrella.addActionListener(figuraButtonListener);
        btnFlecha.addActionListener(figuraButtonListener);
        btnCorazon.addActionListener(figuraButtonListener);
        btnTrapecio.addActionListener(figuraButtonListener);
        btnBorrador.addActionListener(figuraButtonListener);
        btnSemicirculo.addActionListener(figuraButtonListener);


        // Añadir otro separador visual antes del botón Guardar
        add(new Separator());
        formatearYAgregar(btnGuardar, "guardar.png", "Guardar Imagen", false);


        // Configurar el ButtonGroup para los JToggleButtons
        grupoBotones = new ButtonGroup();
        for(Component boton : this.getComponents()) {
            if (boton instanceof JToggleButton) {
                grupoBotones.add((JToggleButton) boton);
                // Añadir ActionListener a los botones de figura para deseleccionar el botón de selección en PanelDeColores
                if (esBotonFigura((JToggleButton)boton)) { // Usar la nueva lógica para identificar botones de figura
                    ((JToggleButton) boton).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (((JToggleButton)e.getSource()).isSelected()) {
                                // Si este botón de figura se selecciona, deseleccionar el botón de selección en PanelDeColores
                                if (panelDeColores != null) {
                                    panelDeColores.setSeleccionarButtonState(false);
                                    // Opcional: Deseleccionar la figura en el panel de dibujo al cambiar a una herramienta de dibujo
                                    if (panelDeColores.getPanelDeDibujo() != null) {
                                        panelDeColores.getPanelDeDibujo().deseleccionarFigura();
                                    }if (panelDeColores.getPanelDeDibujo() != null) {
                                        panelDeColores.getPanelDeDibujo().deseleccionarFigura();
                                    }
                                }
                            }
                        }
                    });
                }
            } else if (boton instanceof JButton && boton.equals(btnGuardar)) {
                // El botón Guardar no necesita deseleccionar el botón de selección
                // Su ActionListener se maneja en VentanaPrincipal
            } else if (boton instanceof JButton) {
                // Para los botones de acción (Deshacer, Rehacer, Limpiar), deseleccionar el botón de selección en PanelDeColores
                ((JButton) boton).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (panelDeColores != null) {
                            panelDeColores.setSeleccionarButtonState(false);
                            // Deseleccionar la figura en el panel de dibujo al usar una herramienta de acción
                            if (panelDeColores.getPanelDeDibujo() != null) {
                                panelDeColores.getPanelDeDibujo().deseleccionarFigura();
                            }
                        }
                        // También deseleccionar cualquier botón de figura en la BarraDeHerramientas
                        deseleccionarBotonesFiguras();
                    }
                });
            }
        }
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

    // Método de ayuda para verificar si un botón es un botón de figura/herramienta de dibujo
    private boolean esBotonFigura(AbstractButton boton) {
        Object prop = boton.getClientProperty("esBotonFigura");
        return prop != null && (boolean) prop;
    }


    public String getHerramientaSeleccionada() {
        // Recorrer los botones para encontrar cuál está seleccionado en el ButtonGroup
        for (Component boton : this.getComponents()) {
            if (boton instanceof JToggleButton) {
                JToggleButton toggleButton = (JToggleButton) boton;
                if (toggleButton.isSelected() && esBotonFigura(toggleButton)) { // Solo considerar botones de figura
                    return toggleButton.getToolTipText().replace("Seleccione: ", ""); // Obtener el nombre de la herramienta del tooltip
                }
            }
        }
        // Si ningún botón de figura está seleccionado, devolver un valor por defecto o indicar "Ninguna"
        return "Ninguna"; // O podrías devolver "Dibujo Libre" como antes si quieres un comportamiento por defecto
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
     * Deselecciona todos los JToggleButtons que son botones de figura en esta barra de herramientas.
     */
    public void deseleccionarBotonesFiguras() {
        if (grupoBotones != null) {
            grupoBotones.clearSelection(); // Esto deselecciona todos los botones en el grupo
        }
        // Si tienes botones de figura que no están en el ButtonGroup (no es el caso actual pero por si acaso),
        // necesitarías deseleccionarlos individualmente.
    }

    /**
     * Establece la referencia al PanelDeColores.
     * @param panelDeColores La instancia de PanelDeColores.
     */
    public void setPanelDeColores(PanelDeColores panelDeColores) {
        this.panelDeColores = panelDeColores;
    }

}