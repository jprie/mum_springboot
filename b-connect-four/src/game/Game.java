package game;

import java.util.Scanner;

public class Game {

    private static final int COLUMN_FULL = -1;
    private static final int MAX_TURNS_PLAYED = Board.NUMBER_ROWS*Board.NUMBER_COLS;
    private static Scanner scanner = new Scanner(System.in);

    private Board board = new Board();

    // Spieler Rot beginnt!!
    private Piece.Color currentColor = Piece.Color.RED;

    public void printBoard() {
        // ruft toString-Methode von Board auf
        System.out.println(board);
    }

    /**
     * Beschreibt den Spielablauf, Schleife in der die Spieler abwechselnd ihre Züge eingeben
     */
    public void start() {
        int turnsPlayed = 0;
        boolean gameEnd = false;
        do {
            System.out.println("Spieler (" + currentColor + "): Gib eine Zahl zwischen 1-7 ein!");
            // überprüft, ob das nächste Token in einen int umgewandelt werden kann
            if (!scanner.hasNextInt()) {
                // Wenn das Token NICHT umgewandelt werden kann, nimm es vom Input-Stream
                System.out.println("Fehlerhafter Input: " + scanner.next());
                continue;
            }

            // Nimm int vom Input-Stream
            int column = scanner.nextInt();

            // Überprüfe, ob column in der korrekten Range
            if (column < 1 || column > 7) {
                System.out.println("Zahl nicht zwischen 1-7");
                continue;
            }
            System.out.println("Eingegebene Zahl: " + column);

            Piece currentColoredPiece = new Piece(currentColor);

            // Umrechnung auf Array-Koordinaten
            column = column - 1;

            // Versuche Stein in die Spalte einzuwerfen. Wenn voll nächster Versuch!
            int row = insertPieceIntoColumn(currentColoredPiece, column);
            if (row == COLUMN_FULL) {
                System.out.println("Spalte voll. Wähle eine andere Spalte");
                printBoard();
                continue;
            }

            // Überprüfung, ob Spieler gewonnen hat
            if (currentPlayerWinsAtPosition(row, column)) {
                System.out.println("Spieler (" + currentColor + ") gewinnt. Gratuliere.");
                gameEnd = true;
            }


            // Ende des Zugs: Spieler/Farbe wechselt
            turnsPlayed++;
            if (!gameEnd && turnsPlayed == MAX_TURNS_PLAYED) {
                System.out.println("Unentschieden!");
                gameEnd = true;
            }

            toggleColor();
            printBoard();

        } while (gameEnd == false);

    }

    private boolean currentPlayerWinsAtPosition(int row, int col) {

        return currentPlayerWinsHorizontally(row)
                || currentPlayerWinsVertically(col)
                || currentPlayerWinsDiagonallyTopLeftDown(row, col)
                || currentPlayerWinsDiagonallyTopRightDown(row, col);
    }

    private boolean currentPlayerWinsDiagonallyTopRightDown(int row, int col) {


        // Finde den Startpunkt der Diagonale
        while (row > 0 && col < Board.NUMBER_COLS-1) {
            row--;
            col++;
        }

        System.out.println("Startpunkt: " + row + ":" + col);

        int counter = 0;
        while (row < Board.NUMBER_ROWS && col >=0 ) {

            if (board.getPieceAtPosition(row, col) != null
            && board.getPieceAtPosition(row, col).getColor().equals(currentColor)) {

                counter++;
                if (counter == 4) {
                    return true;
                }
            } else {
                counter = 0;
            }

            row++;
            col--;
        }
        return false;
    }

    private boolean currentPlayerWinsDiagonallyTopLeftDown(int row, int col) {

        // Finde Startpunkt der Diagonale
        while (row > 0 && col > 0 ) {
            row--;
            col--;
        }

        int counter = 0;
        // Suche 4 Steine von Diagonale weg
        while(row < Board.NUMBER_ROWS && col < Board.NUMBER_COLS) {

            if (board.getPieceAtPosition(row, col) != null
            && board.getPieceAtPosition(row, col).getColor().equals(currentColor)) {

                counter++;
                if (counter == 4) {
                    return true;
                }
            } else {
                counter = 0;
            }

            row++;
            col++;
        }
        return false;
    }

    private boolean currentPlayerWinsVertically(int col) {

        int counter = 0;
        for (int row=0; row<Board.NUMBER_ROWS; row++) {

            if (board.getPieceAtPosition(row, col) != null &&
                    board.getPieceAtPosition(row,col).getColor().equals(currentColor)) {

                counter++;
                if (counter == 4) {
                    return true;
                }
            } else {
                counter = 0;
            }

        }
        return false;
    }

    private boolean currentPlayerWinsHorizontally(int row) {

        int countedPieces = 0;
        // Überprüfe nur Zeile row, in die der letzte STEIN eingeworfen wurde
        for (int i=0; i<Board.NUMBER_COLS; i++) {

            if (board.getPieceAtPosition(row, i) != null &&
                    board.getPieceAtPosition(row, i).getColor().equals(currentColor)) {
                // pieces hochzählen
                countedPieces++;
                if (countedPieces == 4) {
                    return true;
                }
            } else {
                countedPieces = 0;
            }
        }
        return false;
    }

    private int insertPieceIntoColumn(Piece coloredPiece, int col) {

        for (int row = Board.NUMBER_ROWS-1; row >= 0; row--) {

            if (board.getPieceAtPosition(row, col) == null) {
                board.setPieceAtPosition(coloredPiece, row, col);
                return row;
            }
        }
        // Damit ich hier ankomme, muss die gesamte Schleife durchlaufen worden sein,
        // ohne, dass das Piece gelegt werden konnte.
        return COLUMN_FULL;
    }

    /**
     * Ändert die aktuelle Farbe/Spieler
     */
    private void toggleColor() {

        currentColor = currentColor.equals(Piece.Color.RED) ? Piece.Color.YELLOW : Piece.Color.RED;
    }

    /**
     * Setzt einen Spielstein an eine bestimmte Position am Board
     */
    public void testSetPieceAtPosition(int row, int col) {

        // Spielstein erstellen (z.B einen Roten)
        // im Board: diesen Spielstein auf die Position
        // (row, col) setzen
        Piece piece = new Piece(Piece.Color.RED);
        board.setPieceAtPosition(piece, row, col);
    }
}




