package paint202510;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser; // Aunque no se usa directamente en esta clase, está en los imports originales.
import javax.swing.JScrollPane;
import javax.swing.JFileChooser; // Aunque no se usa directamente para cargar/guardar en esta clase, está en los imports originales.
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage; // Aunque no se usa directamente para cargar/guardar en esta clase, está en los imports originales.
import java.io.File; // Aunque no se usa directamente para cargar/guardar en esta clase, está en los imports originales.
import java.io.IOException; // Aunque no se usa directamente para cargar/guardar en esta clase, está en los imports originales.
import javax.imageio.ImageIO; // Aunque no se usa directamente para cargar/guardar en esta clase, está en los imports originales.


/**
 * La ventana principal de la aplicación Paint.
 * Esta clase configura el JFrame y añade el panel de dibujo,
 * la barra de herramientas y el panel de colores. También maneja
 * los listeners de acciones para los botones de acción (Guardar, Deshacer, Rehacer, Limpiar) en la barra de herramientas.
 */
public class VentanaPrincipal extends JFrame {

    /**
     * El panel de dibujo principal donde se dibujan las figuras.
     */
    private PanelDeDibujo lienzo;
    /**
     * La barra de herramientas que contiene botones de formas y acciones.
     */
    private BarraDeHerramientas barraDeHerramientas;
    /**
     * El panel para seleccionar colores y opciones de relleno.
     */
    private PanelDeColores panelDeColores;
    /**
     * Panel con scroll para la barra de herramientas, permitiendo desplazamiento vertical si es necesario.
     */
    private JScrollPane scrollPaneBarra;

    /**
     * Constructor de la ventana principal de la aplicación.
     * Inicializa y organiza los componentes principales: barra de herramientas, panel de colores y panel de dibujo.
     * Configura los listeners de acciones para los botones de la barra de herramientas.
     */
    public VentanaPrincipal() {
        initComponents(); // Inicializa componentes generados por el diseñador de formularios (si hay alguno).

        // Configurar el título de la ventana
        setTitle("Mi Paint 202510");

        // Crear instancias de los paneles principales en el orden correcto
        barraDeHerramientas = new BarraDeHerramientas();
        panelDeColores = new PanelDeColores();
        lienzo = new PanelDeDibujo(barraDeHerramientas, panelDeColores);

        // Ahora qué lienzo está creado, pasárselo a panelDeColores
        panelDeColores.setPanelDeDibujo(lienzo);

        // Añadir listeners de acciones para los botones de acción
        JButton btnGuardar = barraDeHerramientas.getBtnGuardar();
        btnGuardar.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de clic en el botón Guardar.
             * Abre un selector de archivos y guarda el contenido del lienzo como una imagen PNG.
             * @param e El ActionEvent.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Funcionalidad de guardar (ya presente y documentada)
                if (lienzo.getWidth() <= 0 || lienzo.getHeight() <= 0) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this,
                            "El área de dibujo no tiene tamaño para guardar.",
                            "Error al Guardar",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BufferedImage imagen = new BufferedImage(lienzo.getWidth(), lienzo.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = imagen.createGraphics();
                lienzo.printAll(g2d);
                g2d.dispose();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar imagen del lienzo");

                int userSelection = fileChooser.showSaveDialog(VentanaPrincipal.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                    String filePath = fileToSave.getAbsolutePath();
                    String lowerCaseFilePath = filePath.toLowerCase();

                    if (!lowerCaseFilePath.endsWith(".png") &&
                            !lowerCaseFilePath.endsWith(".jpg") &&
                            !lowerCaseFilePath.endsWith(".jpeg") &&
                            !lowerCaseFilePath.endsWith(".gif") &&
                            !lowerCaseFilePath.endsWith(".bmp")) {
                        fileToSave = new File(filePath + ".png");
                    }

                    try {
                        String fileExtension = "png";
                        int dotIndex = fileToSave.getName().lastIndexOf('.');
                        if (dotIndex > 0 && dotIndex < fileToSave.getName().length() - 1) {
                            fileExtension = fileToSave.getName().substring(dotIndex + 1).toLowerCase();
                        }

                        boolean success = ImageIO.write(imagen, fileExtension, fileToSave);

                        if (success) {
                            System.out.println("Imagen guardada correctamente en: " + fileToSave.getAbsolutePath());
                            JOptionPane.showMessageDialog(VentanaPrincipal.this,
                                    "Imagen guardada correctamente como ." + fileExtension.toUpperCase(),
                                    "Guardado Exitoso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(VentanaPrincipal.this,
                                    "Formato de archivo no soportado para guardar: ." + fileExtension + "\nIntente .png o .jpg",
                                    "Error de Formato al Guardar",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(VentanaPrincipal.this,
                                "Error al guardar la imagen:\n" + ex.getMessage(),
                                "Error de E/S al Guardar",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(VentanaPrincipal.this,
                                "Ocurrió un error inesperado:\n" + ex.getMessage(),
                                "Error General al Guardar",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.out.println("Guardado cancelado por el usuario.");
                }
            }
        });

        // Añadir ActionListener para el botón Deshacer
        JButton btnDeshacer = barraDeHerramientas.getBtnDeshacer();
        btnDeshacer.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de clic en el botón Deshacer.
             * Llama al método undo() del panel de dibujo para deshacer la última acción.
             * @param e El ActionEvent.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lienzo.undo(); // Llamar al método undo en PanelDeDibujo
            }
        });

        // Añadir ActionListener para el botón Rehacer
        JButton btnRehacer = barraDeHerramientas.getBtnRehacer();
        btnRehacer.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de clic en el botón Rehacer.
             * Llama al método redo() del panel de dibujo para rehacer la última acción deshecha.
             * @param e El ActionEvent.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lienzo.redo(); // Llamar al método redo en PanelDeDibujo
            }
        });

        // Añadir ActionListener para el botón Limpiar
        JButton btnLimpiar = barraDeHerramientas.getBtnLimpiar();
        btnLimpiar.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de clic en el botón Limpiar.
             * Llama al método clearCanvas() del panel de dibujo para borrar todo el contenido.
             * @param e El ActionEvent.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lienzo.clearCanvas(); // Llamar al método clearCanvas en PanelDeDibujo
            }
        });


        scrollPaneBarra = new JScrollPane(barraDeHerramientas);
        scrollPaneBarra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneBarra.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneBarra.setPreferredSize(new Dimension(80, (int) barraDeHerramientas.getPreferredSize().getHeight()));

        getContentPane().add(panelDeColores, BorderLayout.NORTH);
        getContentPane().add(scrollPaneBarra, BorderLayout.EAST);
        getContentPane().add(lienzo, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    /**
     * Inicializa los componentes del frame.
     * Este método es típicamente generado por un constructor de GUI.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * El punto de entrada principal de la aplicación.
     * Crea y muestra la ventana principal.
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal vp = new VentanaPrincipal();
                vp.setVisible(true);
            }
        });
    }
}
