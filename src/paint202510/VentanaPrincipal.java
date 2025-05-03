package paint202510;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaPrincipal extends javax.swing.JFrame {
    private PanelDeDibujo lienzo;
    private BarraDeHerramientas barraDeHerramientas;
    private PanelDeColores panelDeColores;

    public VentanaPrincipal() {
        initComponents();

        setSize(1000, 800);
        setLocationRelativeTo(null);

        barraDeHerramientas = new BarraDeHerramientas();
        panelDeColores = new PanelDeColores();
        lienzo = new PanelDeDibujo(barraDeHerramientas, panelDeColores);

        getContentPane().add(panelDeColores, BorderLayout.NORTH);
        getContentPane().add(barraDeHerramientas, BorderLayout.EAST);
        getContentPane().add(lienzo, BorderLayout.CENTER);

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}