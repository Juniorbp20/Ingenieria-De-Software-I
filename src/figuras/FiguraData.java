package figuras; // Asumiendo que FiguraData estará en el mismo paquete que tus figuras

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase para almacenar los datos de una figura de manera serializable,
 * permitiendo su transferencia a través del portapapeles.
 * Contiene campos para los datos comunes a varias figuras.
 */
public class FiguraData implements Serializable {
    private static final long serialVersionUID = 2L; // Incrementado el serialVersionUID tras modificaciones

    private String tipoFigura; // Para identificar el tipo de figura (e.g., "Linea", "Rectangulo")
    private Point puntoInicial;
    private Point puntoFinal; // Utilizado por figuras basadas en dos puntos (Rectangulo, Ovalo, Linea, etc.)
    private Point centro; // Utilizado por figuras basadas en un centro y radio/punto de referencia (Circulo, Poligonos, Estrella)
    private ArrayList<Point> puntosTrazo; // Utilizado para DibujoLibre o Lapiz
    private Color colorDePrimerPlano;
    private Color colorDeRelleno;
    private boolean estaRelleno;
    private int tamanoBorrador; // Específico para Borrador, si decides copiarlo

    /**
     * Constructor básico de FiguraData.
     * @param tipoFigura El nombre del tipo de figura (e.g., "Rectangulo").
     */
    public FiguraData(String tipoFigura) {
        this.tipoFigura = tipoFigura;
        this.puntosTrazo = new ArrayList<>(); // Inicializar lista de puntos por si acaso
    }

    // --- Getters ---
    public String getTipoFigura() {
        return tipoFigura;
    }

    public Point getPuntoInicial() {
        return puntoInicial;
    }

    public Point getPuntoFinal() {
        return puntoFinal;
    }

    public Point getCentro() {
        return centro;
    }

    public ArrayList<Point> getPuntosTrazo() {
        return puntosTrazo;
    }

    public Color getColorDePrimerPlano() {
        return colorDePrimerPlano;
    }

    public Color getColorDeRelleno() {
        return colorDeRelleno;
    }

    public boolean isEstaRelleno() {
        return estaRelleno;
    }

    public int getTamanoBorrador() {
        return tamanoBorrador;
    }

    // --- Setters (para ser usados al crear FiguraData desde un objeto Figura) ---
    public void setPuntoInicial(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
    }

    public void setPuntoFinal(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    public void setCentro(Point centro) {
        this.centro = centro;
    }

    public void setPuntosTrazo(ArrayList<Point> puntosTrazo) {
        // Crear una copia para evitar referencias directas si la lista original cambia
        this.puntosTrazo = new ArrayList<>(puntosTrazo);
    }

    public void setColorDePrimerPlano(Color colorDePrimerPlano) {
        this.colorDePrimerPlano = colorDePrimerPlano;
    }

    public void setColorDeRelleno(Color colorDeRelleno) {
        this.colorDeRelleno = colorDeRelleno;
    }

    public void setEstaRelleno(boolean estaRelleno) {
        this.estaRelleno = estaRelleno;
    }

    public void setTamanoBorrador(int tamanoBorrador) {
        this.tamanoBorrador = tamanoBorrador;
    }

    // Método de ayuda para depuración
    @Override
    public String toString() {
        return "FiguraData{" +
                "tipo='" + tipoFigura + '\'' +
                ", pInicial=" + puntoInicial +
                ", pFinal=" + puntoFinal +
                ", centro=" + centro +
                ", colorBorde=" + colorDePrimerPlano +
                ", colorRelleno=" + colorDeRelleno +
                ", relleno=" + estaRelleno +
                ", puntosTrazoSize=" + (puntosTrazo != null ? puntosTrazo.size() : 0) +
                '}';
    }
}
