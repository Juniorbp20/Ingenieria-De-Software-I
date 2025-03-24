/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint202510;

import figuras.Lapiz;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author josearielpereyra
 */
public class PanelDeDibujo extends JPanel {
    //Linea lineaActual;


    /*public PanelDeDibujo() {
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                lineaActual = new Linea(e.getPoint());
                repaint();
                System.out.println("Se presionó el mouse en " + e.getPoint());
            }
        });
        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                lineaActual.actualizar(e.getPoint());
                repaint();
                System.out.println("Se arrastró el mouse a " + e.getPoint());
            }
        });
    }
    */
    
    //////////////////////////////////////////////////////////////////////////////////////////
    
    private List<Lapiz> trazos; // Lista de trazos dibujados en el panel
    private Lapiz lapizActual;
    
    public PanelDeDibujo() {
        trazos = new ArrayList<>(); // Inicializa la lista de trazos array
        
        // detectar cuando se presiona el mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lapizActual = new Lapiz(Color.BLACK, 2); // Crea un nuevo trazo con color negro y un grosor 2
                lapizActual.actualizar(e.getPoint()); // Agrega el punto inicial
                trazos.add(lapizActual); // Añade el trazo a la lista
                repaint(); // Redibuja el panel
            }
        });
        
        // detectar cuando se arrastra el mouse
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lapizActual != null) {
                    lapizActual.actualizar(e.getPoint()); // Agrega nuevos puntos al arrastrar el mouse
                    repaint(); // Redibuja el panel
                    System.out.println("Se arrastró el mouse a " + e.getPoint());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE); // fondo en blanco
        
        // Dibuja todos los trazos
        for (Lapiz lapiz : trazos) {
            lapiz.dibujar(g);
        }
    }
}


   /* @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        
        if(lineaActual != null) {
            lineaActual.dibujar(g);
        }
    }
    
}*/
