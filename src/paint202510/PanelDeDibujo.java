package paint202510;

import figuras.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PanelDeDibujo extends JPanel {
    private Figura figuraActual;
    private List<Figura> figuras = new ArrayList<>();
    PanelDeColores panelDeColores;
    BarraDeHerramientas barraDeHerramientas;


    public PanelDeDibujo(BarraDeHerramientas barraDeHerramientas, PanelDeColores panelDeColores) {
        this.barraDeHerramientas = barraDeHerramientas;
        this.panelDeColores = panelDeColores;
        configurarEventosRaton();
        setBackground(Color.WHITE);
    }

    private void configurarEventosRaton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (barraDeHerramientas.btnGuardar.isSelected()) {
                    guardarImagen();
                    barraDeHerramientas.btnGuardar.setSelected(false);
                    return;
                }

                figuraActual = obtenerFiguraADibujar(e.getPoint());


                // Usamos los colores y estado de relleno DEL PANEL DE COLORES
                figuraActual.setColorDePrimerPlano(panelDeColores.getColorBordeActual());
                figuraActual.setColorDeRelleno(panelDeColores.getColorRellenoActual());
                figuraActual.setRelleno(panelDeColores.isRellenar());

                figuras.add(figuraActual);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                figuraActual = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Usamos el método getHerramientaSeleccionada() de la barra
                String herramienta = barraDeHerramientas.getHerramientaSeleccionada();

                if ("Borrador".equals(herramienta)) {
                    // Lógica específica para el borrador en mouseDragged
                    figuraActual = new Borrador(e.getPoint());
                    figuras.add(figuraActual);
                    // Para el borrador, quizás quieras que use el color de fondo del panel
                    // figuraActual.setColorDePrimerPlano(getBackground());
                } else if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                }
                repaint();
            }
        });
    }

    // Modificamos este método para usar getHerramientaSeleccionada() de la barra
    private Figura obtenerFiguraADibujar(Point punto) {
        String herramienta = barraDeHerramientas.getHerramientaSeleccionada();

        switch (herramienta) {
            case "Línea":
                return new Linea(punto);
            case "Rectángulo":
                return new Rectangulo(punto);
            case "Borrador":
                return new Borrador(punto);
            case "Óvalo":
                return new Ovalo(punto);
            case "Círculo":
                return new Circulo(punto);
            case "Cuadrado":
                return new Cuadrado(punto);
            case "Triángulo":
                return new Triangulo(punto);
            case "Pentágono":
                return new Pentagono(punto);
            case "Rombo":
                return new Rombo(punto);
            case "Heptagono":
                return new Heptagono(punto);
            case "Octagono":
                return new Octagono(punto);
            case "Estrella":
                return new Estrella(punto);
            case "Flecha":
                return new Flecha(punto);
            case "Dibujo Libre":
            default:
                return new DibujoLibre(punto);
        }
    }


    private void guardarImagen() {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = imagen.getGraphics();
        paint(g);
        g.dispose();

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar Imagen");

        int seleccion = selector.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                // Asegurarse de que la extensión .png esté presente
                String rutaArchivo = archivo.getAbsolutePath();
                if (!rutaArchivo.toLowerCase().endsWith(".png")) {
                    rutaArchivo += ".png";
                }
                ImageIO.write(imagen, "png", new File(rutaArchivo));
                JOptionPane.showMessageDialog(this, "Imagen guardada exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Figura figura : figuras) {
            figura.dibujar(g);
        }
    }

    public void limpiarPanel() {
        figuras.clear();
        repaint();
    }

    public void deshacer() {
        if (!figuras.isEmpty()) {
            figuras.remove(figuras.size() - 1);
            repaint();
        }
    }
}