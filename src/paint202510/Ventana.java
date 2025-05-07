/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paint202510;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Gustavo j. Bonifacio
 */
public class Ventana extends javax.swing.JFrame {

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
    public Ventana() {
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
                    JOptionPane.showMessageDialog(Ventana.this,
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

                int userSelection = fileChooser.showSaveDialog(Ventana.this);

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
                            JOptionPane.showMessageDialog(Ventana.this,
                                    "Imagen guardada correctamente como ." + fileExtension.toUpperCase(),
                                    "Guardado Exitoso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(Ventana.this,
                                    "Formato de archivo no soportado para guardar: ." + fileExtension + "\nIntente .png o .jpg",
                                    "Error de Formato al Guardar",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Ventana.this,
                                "Error al guardar la imagen:\n" + ex.getMessage(),
                                "Error de E/S al Guardar",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Ventana.this,
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Copiar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Pegar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // Comando para copiar
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // Comando para pegar
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
