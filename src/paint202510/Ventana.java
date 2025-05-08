package paint202510;

import figuras.*; // Asegúrate de importar todas las clases de figuras
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Importaciones necesarias para copiar y pegar
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Point; // Necesario para ajustar la posición al pegar


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

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

    // Variable para almacenar temporalmente la figura copiada (usando FiguraData y Transferible)
    private FiguraData figuraCopiadaData = null;


    /**
     * Constructor de la ventana principal de la aplicación.
     * Inicializa y organiza los componentes principales: barra de herramientas, panel de colores y panel de dibujo.
     * Configura los listeners de acciones para los botones de la barra de herramientas y los elementos del menú.
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

        // *** ESTABLECER LAS REFERENCIAS CRUZADAS (FRAGMENTO CLAVE) ***
        // Esto permite que PanelDeColores y BarraDeHerramientas se comuniquen
        panelDeColores.setBarraDeHerramientas(barraDeHerramientas);
        barraDeHerramientas.setPanelDeColores(panelDeColores);
        // ***********************************************************


        // Añadir listeners de acciones para los botones de acción en la barra de herramientas
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


        // --- IMPLEMENTACIÓN DE COPIAR Y PEGAR ---

        // ActionListener para el elemento de menú "Copiar"
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Figura figuraSeleccionada = lienzo.getFiguraSeleccionada(); // Obtener la figura seleccionada del panel de dibujo

                if (figuraSeleccionada != null) {
                    // Obtener los datos de la figura seleccionada
                    FiguraData dataToCopy = figuraSeleccionada.getFiguraData();

                    if (dataToCopy != null) {
                        // Crear un objeto Transferible con los datos de la figura
                        Transferable transferable = new TransferibleFigura(dataToCopy);

                        // Obtener el portapapeles del sistema
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                        // Colocar los datos en el portapapeles
                        clipboard.setContents(transferable, null); // El segundo argumento es un ClipboardOwner, null es aceptable para este caso

                        System.out.println("Figura copiada al portapapeles."); // Para depuración
                    } else {
                        System.out.println("No se pudieron obtener los datos de la figura para copiar.");
                    }
                } else {
                    System.out.println("Ninguna figura seleccionada para copiar."); // Para depuración
                }
            }
        });

        // ActionListener para el elemento de menú "Pegar"
        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el portapapeles del sistema
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                // Obtener el contenido del portapapeles
                Transferable content = clipboard.getContents(this);

                // Verificar si el contenido es de nuestro DataFlavor personalizado (FiguraData)
                if (content != null && content.isDataFlavorSupported(TransferibleFigura.FIGURA_FLAVOR)) {
                    try {
                        // Obtener los datos de la figura del portapapeles
                        FiguraData pastedData = (FiguraData) content.getTransferData(TransferibleFigura.FIGURA_FLAVOR);

                        // Crear una nueva instancia de figura a partir de los datos pegados
                        Figura nuevaFigura = crearFiguraDesdeData(pastedData); // Necesitas implementar este método

                        if (nuevaFigura != null) {
                            // Opcional: Ajustar la posición de la figura pegada (ejemplo: desplazarla un poco)
                            // Esto requiere modificar los puntos de la figura pegada.
                            // La forma de hacerlo depende de cómo cada figura usa sus puntos (puntoInicial, puntoFinal, centro, puntosTrazo).
                            // Aquí hay un ejemplo básico que asume puntoInicial y puntoFinal:
                            Point offset = new Point(20, 20); // Desplazamiento al pegar
                            ajustarPosicionFigura(nuevaFigura, offset); // Necesitas implementar este método

                            // Añadir la nueva figura al panel de dibujo
                            lienzo.addFigura(nuevaFigura); // addFigura ya limpia la pila de rehacer y repinta

                            System.out.println("Figura pegada en el lienzo."); // Para depuración

                        } else {
                            System.out.println("No se pudo crear la figura a partir de los datos pegados.");
                        }

                    } catch (UnsupportedFlavorException | IOException ex) {
                        ex.printStackTrace();
                        System.err.println("Error al pegar la figura: " + ex.getMessage());
                    }
                } else {
                    System.out.println("El portapapeles no contiene datos de figura válidos."); // Para depuración
                }
            }
        });

        // --- FIN IMPLEMENTACIÓN DE COPIAR Y PEGAR ---



        // --- INICIO Bloque de código añadido/reinsertado para el layout ---
        scrollPaneBarra = new JScrollPane(barraDeHerramientas);
        scrollPaneBarra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneBarra.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneBarra.setPreferredSize(new Dimension(80, (int) barraDeHerramientas.getPreferredSize().getHeight()));

        // Establecer el layout del content pane a BorderLayout antes de añadir los componentes
        getContentPane().setLayout(new BorderLayout()); // Añadir esta línea

        getContentPane().add(panelDeColores, BorderLayout.NORTH);
        getContentPane().add(scrollPaneBarra, BorderLayout.EAST);
        getContentPane().add(lienzo, BorderLayout.CENTER);

        // Configurar el tamaño y la ubicación de la ventana
        setSize(800, 600);
        setLocationRelativeTo(null);
        // --- FIN Bloque de código añadido/reinsertado ---

    }

    // --- MÉTODOS AUXILIARES PARA PEGAR ---

    /**
     * Crea una nueva instancia de Figura a partir de los datos contenidos en un objeto FiguraData.
     * Este método necesita lógica para instanciar la clase de figura correcta basada en figuraData.getTipoFigura().
     * DEBES IMPLEMENTAR LA CREACIÓN DE CADA TIPO DE FIGURA AQUÍ.
     * @param figuraData Los datos de la figura.
     * @return Una nueva instancia de Figura con los datos aplicados, o null si el tipo de figura es desconocido.
     */
    private Figura crearFiguraDesdeData(FiguraData figuraData) {
        if (figuraData == null || figuraData.getTipoFigura() == null) {
            return null;
        }

        Figura nuevaFigura = null;
        Point pInicial = figuraData.getPuntoInicial();
        Point pFinal = figuraData.getPuntoFinal();
        Point centro = figuraData.getCentro();
        java.util.ArrayList<Point> puntosTrazo = figuraData.getPuntosTrazo();


        // --- IMPLEMENTACIÓN NECESARIA: Crear la figura correcta según el tipo ---
        // Debes añadir un case para cada tipo de figura que soportes
        switch (figuraData.getTipoFigura()) {
            case "Linea":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Linea(pInicial, pFinal);
                }
                break;
            case "Rectangulo":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Rectangulo(pInicial);
                    nuevaFigura.actualizar(pFinal); // Rectangulo usa actualizar para definir el segundo punto
                }
                break;
            case "Ovalo":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Ovalo(pInicial);
                    nuevaFigura.actualizar(pFinal); // Ovalo usa actualizar para definir el segundo punto
                }
                break;
            case "Circulo":
                if (centro != null && pInicial != null) { // Circulo usa centro y punto inicial para radio
                    nuevaFigura = new Circulo(centro);
                    nuevaFigura.actualizar(pInicial); // El punto inicial aquí actúa como referencia para el radio
                }
                // Nota: La lógica de tu Circulo usa 'centro' y 'puntoActual' para el radio.
                // FiguraData tiene 'centro' y 'puntoFinal'. Necesitas decidir qué puntos de FiguraData
                // corresponden a los puntos necesarios para recrear un Circulo.
                // Si guardaste centro y un punto en la circunferencia en FiguraData, úsalos aquí.
                // Si guardaste centro y radio, necesitarás adaptar.
                break;
            case "Cuadrado":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Cuadrado(pInicial);
                    nuevaFigura.actualizar(pFinal); // Cuadrado usa actualizar para definir el segundo punto
                }
                break;
            case "Triángulo":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Triangulo(pInicial);
                    nuevaFigura.actualizar(pFinal); // Triangulo usa actualizar para definir el segundo punto
                }
                break;
            case "Pentágono":
                if (centro != null && pInicial != null) { // Asumiendo que guardaste centro y un punto en el borde
                    nuevaFigura = new Pentagono(centro);
                    nuevaFigura.actualizar(pInicial); // El punto inicial aquí actúa como referencia para tamaño/orientación
                }
                break;
            case "Rombo":
                if (centro != null && pInicial != null) { // Asumiendo que guardaste centro y un punto de referencia
                    nuevaFigura = new Rombo(centro);
                    nuevaFigura.actualizar(pInicial); // El punto inicial aquí actúa como referencia para tamaño/forma
                }
                break;
            case "Heptagono":
                if (centro != null && pInicial != null) { // Asumiendo que guardaste centro y un punto de referencia
                    nuevaFigura = new Heptagono(centro);
                    nuevaFigura.actualizar(pInicial); // El punto inicial aquí actúa como referencia para tamaño/orientación
                }
                break;
            case "Octagono":
                if (centro != null && pInicial != null) { // Asumiendo que guardaste centro y un punto de referencia
                    nuevaFigura = new Octagono(centro);
                    nuevaFigura.actualizar(pInicial); // El punto inicial aquí actúa como referencia para tamaño/orientación
                }
                break;
            case "Estrella":
                if (centro != null && pInicial != null) { // Asumiendo que guardaste centro y un punto de referencia
                    nuevaFigura = new Estrella(centro);
                    nuevaFigura.actualizar(pInicial); // El punto inicial aquí actúa como referencia para tamaño/orientación
                }
                break;
            case "Flecha":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Flecha(pInicial);
                    nuevaFigura.actualizar(pFinal); // Flecha usa actualizar para definir el punto final
                }
                break;
            case "Corazón":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Corazon(pInicial);
                    nuevaFigura.actualizar(pFinal); // Corazon usa actualizar para definir el segundo punto
                }
                break;
            case "Trapecio":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Trapecio(pInicial);
                    nuevaFigura.actualizar(pFinal); // Trapecio usa actualizar para definir el segundo punto
                }
                break;
            case "Semicirculo":
                if (pInicial != null && pFinal != null) {
                    nuevaFigura = new Semicirculo(pInicial);
                    nuevaFigura.actualizar(pFinal); // Semicirculo usa actualizar para definir el segundo punto
                }
                break;
            case "Dibujo Libre":
            case "Lapiz": // Asumiendo que Lapiz y DibujoLibre son similares o uno es el otro
                if (puntosTrazo != null && !puntosTrazo.isEmpty()) {
                    // Para DibujoLibre/Lapiz, necesitas un constructor que acepte la lista de puntos
                    // O añadir los puntos uno por uno después de crear la instancia.
                    // Si DibujoLibre tiene un constructor(Point), puedes crearlo y luego añadir los puntos.
                    // Si Lapiz tiene un constructor(Color, int), necesitas adaptar.
                    // Asumiremos que DibujoLibre puede ser recreado a partir de su lista de puntos.
                    DibujoLibre dl = new DibujoLibre(puntosTrazo.get(0)); // Crear con el primer punto
                    for (int i = 1; i < puntosTrazo.size(); i++) {
                        dl.actualizar(puntosTrazo.get(i)); // Añadir el resto de puntos
                    }
                    nuevaFigura = dl;
                }
                break;
            case "Borrador":
                // Copiar/pegar un borrador puede ser complicado, ya que son trazos individuales.
                // Si decides soportarlo, necesitarías recrear los trazos de borrador.
                // Por ahora, lo omitiremos o lo manejarás de forma diferente.
                System.out.println("Copiar/Pegar Borrador no implementado completamente.");
                break;
            default:
                System.err.println("Tipo de figura desconocido al pegar: " + figuraData.getTipoFigura());
                break;
        }
        // --- FIN IMPLEMENTACIÓN NECESARIA ---


        // Si se creó la figura, establecer sus propiedades comunes
        if (nuevaFigura != null) {
            nuevaFigura.setColorDePrimerPlano(figuraData.getColorDePrimerPlano());
            nuevaFigura.setColorDeRelleno(figuraData.getColorDeRelleno());
            nuevaFigura.setRelleno(figuraData.isEstaRelleno());
            // Si decides copiar el tamaño del borrador, necesitarías un setter en Borrador y manejarlo aquí
            // if (nuevaFigura instanceof Borrador) { ((Borrador) nuevaFigura).setTamano(figuraData.getTamanoBorrador()); }
        }

        return nuevaFigura;
    }

    /**
     * Ajusta la posición de una figura desplazando sus puntos por un offset dado.
     * La implementación exacta depende de cómo cada figura almacena su posición (puntoInicial, puntoFinal, centro, lista de puntos).
     * DEBES IMPLEMENTAR LA LÓGICA DE DESPLAZAMIENTO PARA CADA TIPO DE FIGURA AQUÍ.
     * @param figura La figura a ajustar.
     * @param offset El desplazamiento a aplicar.
     */
    private void ajustarPosicionFigura(Figura figura, Point offset) {
        if (figura == null || offset == null) {
            return;
        }

        // --- IMPLEMENTACIÓN NECESARIA: Desplazar los puntos de la figura ---
        // Debes adaptar esto según los campos de posición de cada clase Figura
        if (figura instanceof Linea) {
            Linea l = (Linea) figura;
            // Acceder y modificar los puntos inicial y final (si son accesibles o con setters)
            // l.puntoInicial.translate(offset.x, offset.y); // Si fueran públicos
            // l.puntoFinal.translate(offset.x, offset.y); // Si fueran públicos
            // Si no son públicos, necesitarías setters o un método mover en la clase Linea
            Point pInicial = getPuntoInicialDeFigura(l); // Usando el helper temporal
            Point pFinal = getPuntoFinalDeFigura(l); // Usando el helper temporal
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);

        } else if (figura instanceof Rectangulo) {
            Rectangulo r = (Rectangulo) figura;
            // Similar a Linea, ajustar puntoInicial y puntoFinal
            Point pInicial = getPuntoInicialDeFigura(r);
            Point pFinal = getPuntoFinalDeFigura(r);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);

        } else if (figura instanceof Ovalo) {
            Ovalo o = (Ovalo) figura;
            // Similar a Rectangulo, ajustar puntoInicial y puntoFinal
            Point pInicial = getPuntoInicialDeFigura(o);
            Point pFinal = getPuntoFinalDeFigura(o);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);

        } else if (figura instanceof Circulo) {
            Circulo c = (Circulo) figura;
            // Ajustar el centro y el punto de referencia (si existen getters/setters o son accesibles)
            // c.centro.translate(offset.x, offset.y); // Si fueran públicos
            // c.puntoActual.translate(offset.x, offset.y); // Si fueran públicos
            // Si no, necesitarías adaptar.
        } else if (figura instanceof Cuadrado) {
            Cuadrado cu = (Cuadrado) figura;
            // Similar a Rectangulo, ajustar puntoInicial y puntoFinal
            Point pInicial = getPuntoInicialDeFigura(cu);
            Point pFinal = getPuntoFinalDeFigura(cu);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);

        } else if (figura instanceof Triangulo) {
            Triangulo t = (Triangulo) figura;
            // Similar a Rectangulo, ajustar puntoInicial y puntoFinal
            Point pInicial = getPuntoInicialDeFigura(t);
            Point pFinal = getPuntoFinalDeFigura(t);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);

        } else if (figura instanceof Pentagono) {
            // Ajustar el centro y el punto de referencia (si existen getters/setters o son accesibles)
            // ((Pentagono) figura).centroGeometrico.translate(offset.x, offset.y); // Si fueran públicos
            // ((Pentagono) figura).verticeReferencia.translate(offset.x, offset.y); // Si fueran públicos
        } else if (figura instanceof Rombo) {
            // Ajustar el centro y el punto de referencia (si existen getters/setters o son accesibles)
            // ((Rombo) figura).centro.translate(offset.x, offset.y); // Si fueran públicos
            // ((Rombo) figura).puntoActual.translate(offset.x, offset.y); // Si fueran públicos
        } else if (figura instanceof Heptagono) {
            // Ajustar el centro y el punto de referencia (si existen getters/setters o son accesibles)
        } else if (figura instanceof Octagono) {
            // Ajustar el centro y el punto de referencia (si existen getters/setters o son accesibles)
        } else if (figura instanceof Estrella) {
            // Ajustar el centro y el punto de referencia (si existen getters/setters o son accesibles)
        } else if (figura instanceof Flecha) {
            // Ajustar el inicio y el fin
            Point inicio = getPuntoInicialDeFigura(figura); // Usando el helper temporal
            Point fin = getPuntoFinalDeFigura(figura); // Usando el helper temporal
            if (inicio != null) inicio.translate(offset.x, offset.y);
            if (fin != null) fin.translate(offset.x, offset.y);

        } else if (figura instanceof Corazon) {
            // Ajustar puntoInicial y puntoActual
            Point pInicial = getPuntoInicialDeFigura(figura);
            Point pFinal = getPuntoFinalDeFigura(figura);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);

        } else if (figura instanceof Trapecio) {
            // Ajustar puntoInicial y puntoActual
            Point pInicial = getPuntoInicialDeFigura(figura);
            Point pFinal = getPuntoFinalDeFigura(figura);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);
        } else if (figura instanceof Semicirculo) {
            // Ajustar puntoInicial y puntoActual
            Point pInicial = getPuntoInicialDeFigura(figura);
            Point pFinal = getPuntoFinalDeFigura(figura);
            if (pInicial != null) pInicial.translate(offset.x, offset.y);
            if (pFinal != null) pFinal.translate(offset.x, offset.y);
        }
        else if (figura instanceof DibujoLibre) {
            // Para DibujoLibre/Lapiz, necesitas iterar sobre la lista de puntos y desplazarlos
            // ArrayList<Point> puntos = ((DibujoLibre) figura).getPuntos(); // Si existiera getPuntos()
            // if (puntos != null) {
            //     for (Point p : puntos) {
            //         p.translate(offset.x, offset.y);
            //     }
            // }
        }
        // Borrador no se copia/pega de esta manera.
        // --- FIN IMPLEMENTACIÓN NECESARIA ---
    }

    // Métodos de ayuda para acceder a puntoInicial y puntoFinal de figuras si son protegidos o privados
    // (Estos son temporales y deberían ser reemplazados por getters públicos en la clase Figura)
    private Point getPuntoInicialDeFigura(Figura figura) {
        try {
            java.lang.reflect.Field field = Figura.class.getDeclaredField("puntoInicial");
            field.setAccessible(true);
            return (Point) field.get(figura);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // e.printStackTrace(); // Considera un manejo de errores más robusto
            return null;
        }
    }

    private Point getPuntoFinalDeFigura(Figura figura) {
        try {
            java.lang.reflect.Field field = Figura.class.getDeclaredField("puntoFinal");
            field.setAccessible(true);
            return (Point) field.get(figura);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // e.printStackTrace(); // Considera un manejo de errores más robusto
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
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

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("File");
        jMenuBar6.add(jMenu11);

        jMenu12.setText("Edit");
        jMenuBar6.add(jMenu12);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/copiar.png"))); // NOI18N
        jMenuItem2.setText("Copiar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pegar.png"))); // NOI18N
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
        // Comando para copiar - IMPLEMENTADO ARRIBA
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // Comando para pegar - IMPLEMENTADO ARRIBA
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


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
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}