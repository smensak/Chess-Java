import model.board.Board;
import model.board.Field;
import model.figures.Figure;
import model.game.Chess;

public class TestMain {

    public static void main(String[] args) {
        Board board = new Board(8);
        Chess chess = new Chess(board);
        Print(board);
        Figure figure = board.getField(2,1).getFigure();
        Field field = board.getField(3,1);
        chess.move(figure, field);
        Print(board);

    }
    private static void Print(Board board) {
        System.out.println("\n______________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        for (int i = 1; i < 9; i++) {
            System.out.print("| ");
            for (int j = 1; j < 9; j++) {
               // System.out.print(board.getField(i, j).toString() + " ");
                System.out.printf("%-22s |    ", board.getField(i, j).toString());
            }
            System.out.println("\n______________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        }
        System.out.print("\n\n");
    }
}
