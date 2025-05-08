package figuras; // Asumiendo que TransferableFigura estará en el mismo paquete que tus figuras

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Clase que implementa la interfaz Transferable para permitir que los objetos FiguraData
 * se coloquen y recuperen del portapapeles del sistema.
 */
public class TransferibleFigura implements Transferable {

    // Define un DataFlavor personalizado para tus objetos FiguraData
    // Esto permite al receptor saber qué tipo de datos está recibiendo
    public static final DataFlavor FIGURA_FLAVOR = new DataFlavor(FiguraData.class, "FiguraData");

    // Define los DataFlavor que soporta esta implementación Transferable
    private static final DataFlavor[] SUPPORTED_FLAVORS = { FIGURA_FLAVOR };

    private final FiguraData figura; // La instancia de FiguraData que se va a transferir

    /**
     * Constructor de TransferableFigura.
     * @param figura La instancia de FiguraData a transferir.
     */
    public TransferibleFigura(FiguraData figura) {
        this.figura = figura;
    }

    /**
     * Retorna un array de DataFlavor que representan los formatos en los que
     * los datos pueden ser proporcionados.
     * @return Un array de DataFlavor soportados.
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    /**
     * Indica si el DataFlavor especificado es soportado por este objeto.
     * @param flavor El DataFlavor a verificar.
     * @return true si el flavor es soportado, false en caso contrario.
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return FIGURA_FLAVOR.equals(flavor); // Solo soportamos nuestro DataFlavor personalizado
    }

    /**
     * Retorna los datos en el formato especificado por el DataFlavor.
     * @param flavor El DataFlavor solicitado.
     * @return Los datos en el formato solicitado (un objeto FiguraData en este caso).
     * @throws UnsupportedFlavorException Si el flavor no es soportado.
     * @throws IOException Si ocurre un error de E/S al obtener los datos (poco probable aquí).
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (FIGURA_FLAVOR.equals(flavor)) {
            return figura; // Retorna la instancia de FiguraData
        } else {
            // Si se solicita un flavor no soportado, lanzar excepción
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
