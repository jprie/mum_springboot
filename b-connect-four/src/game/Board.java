package game;

/**
 * Board stellt das Spielfeld des Spiels Vier-Gewinnt dar. Die Spielsteine werden in
 * einem 6x7 Array gespeichert. Zu Beginn sind alle Elemente auf null initialisiert.
 */
public class Board {

    public static final int NUMBER_ROWS = 6;
    public static final int NUMBER_COLS = 7;

    // static = gehört zur Klasse
    // Der Wert von static Attributen ist für jedes Objekt ident
    private static final String line = "--------------------------------------------------";

    // (non-static) = gehört zum Objekt
    // Der Wert von (non-static) Attributen is für jedes Objekt unterschiedlich
    private final Piece[][] pieces = new Piece[NUMBER_ROWS][NUMBER_COLS];

    public void setPieceAtPosition(Piece piece, int row, int col) {

        pieces[row][col] = piece;
    }

    public Piece getPieceAtPosition(int row, int col) {

        return pieces[row][col];
    }

    public String toString() {

        String outputString = "";

        // String-Darstellung des Spielfelds
        outputString += line + "\n";
        for (int row = 0; row < pieces.length; row++) {
            outputString += "|";
            for (int col = 0; col < pieces[row].length; col++) {

                // wo fügt man die line bzw. |-Zeichen ein.
                String pieceString =
                        pieces[row][col] == null ? "O" : pieces[row][col].toString();
                //stringFromPieceOrNull(pieces[row][col]);
                outputString += " " + pieceString + " |";
            }
            outputString += "\n";
            outputString += line + "\n";
        }

        return outputString;
    }

    /**
     * Gibt "O" zurück wenn piece == null, sonst die String-Repräsentation des Piece
     * @param piece
     * @return
     */
    private String stringFromPieceOrNull(Piece piece) {

        String pieceString;

        if (piece == null) {
            pieceString = "O";
        } else {
            pieceString = piece.toString();
        }

        return pieceString;
    }


}
