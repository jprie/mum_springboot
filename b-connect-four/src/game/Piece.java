package game;

/**
 * Beschreibt einen Spielstein des Spiels Vier-Gewinnt. Jeder Spielstein hat eine Farbe.
 */
public class Piece {

    /**
     * Ein enum definiert einen eigenen Datentyp und darüber hinaus alle Objekte dieses
     * Datentyps als Konstanten.
     * Ich kann nur Color.RED oder Color.YELLOW als Objekt verwenden und keine weiteren
     * Objekte (new Color()) erstellen.
     */
    enum Color {
        RED, YELLOW; // auch weitere Farben GREEN, BLUE wären möglich!!

        @Override
        public String toString() {
            String output = "r";
            if (this == Color.YELLOW) {
                output = "y";
            }
            return output;
        }
    }

    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {

        return color.toString();
    }
}
