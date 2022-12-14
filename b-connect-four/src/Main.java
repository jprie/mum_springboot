import game.Game;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
//        game.testSetPieceAtPosition(3,4);
//        game.testSetPieceAtPosition(2,5);
//        game.testSetPieceAtPosition(1,3);
        game.printBoard();
        game.start();

//        // 2-Dimensionales String-Array
//        String[][] array2D = new String[][] {{"Hans", "Peter"}, {"Birgit", "Lisa"}, {"Hugo", "Lukas"}};
//
////        array2D[1][1] = "Andreas";
//
//        for (int row = 0; row < array2D.length; row++) {
//
//            for (int col = 0; col < array2D[row].length; col++) {
//
//                //if (row == 1 && col == 1) {
//                if (array2D[row][col].equals("Lisa")) {
//                    array2D[row][col] = "Andreas";
//                }
//                System.out.print(array2D[row][col] + ", ");
//            }
//            // nach jeder Zeile mach einen Zeilenumbruch
//            System.out.println();
//        }
    }
}