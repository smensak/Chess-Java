package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.board.Board;
import model.game.Chess;

import java.io.File;

/**
 * @author  Samuel Mensak
 * @author  Rici Soltis
 */

public class ChessBoard extends GridPane {

    private Board board = new Board(8);
    private Chess chess = new Chess(board);

    private MyNode selected;
    private boolean isSelected;

    private MyNode[][] playfield = new MyNode[9][9];

    public GridPane root;

    public ChessBoard() {

        root = new GridPane();

        // initialize playfield
        int size = 9;
        for (int row = 1; row < size; row++) {
            for (int col = 1; col < size; col++) {

                boolean isWhite = (col % 2) != (row % 2);
                Color color = (!isWhite) ? Color.LIGHTGRAY : Color.WHITE;

                Pane pane = new Pane();

                MyNode node = new MyNode(board.getField(row,col).toString(), row * 75, col * 75, 75, 75, color, board.getField(row,col));

                playfield[row][col] = node;

                EventHandler<Event> event =
                    e -> {
                        if (!isSelected) {
                            selected = node;
                            isSelected = true;
                        } else {
                            isSelected = false;
                            if (chess.move(board.getField(selected.field.getFigure().getRow(), selected.field.getFigure().getCol()).getFigure(), node.field)) {
                                //System.out.print("moved");
                                node.clearIMG();
                                node.changeImg();
                                selected.changeImg();
                                this.update();
                            }
                        }
                    };

                // set event handler to the tab
                node.setOnMousePressed(event);

                pane.getChildren().add(node);
                root.add(pane, col, row);
            }
        }
        for (int i = 1; i < size; i++) {
            int helper = (i - 1) + 'A';
            char tmp = (char) helper;
            String tmp2 = "" + tmp;
            Label label = new Label(tmp2);
            label.setMinHeight(20);
            label.setPadding(new Insets(0,0,0,32));
            root.add(label, i,0);
        }
        for (int i = 1; i < size; i++) {
            String tmp = Integer.toString(i);
            Label label = new Label(tmp);
            label.setMinWidth(20);
            label.setPadding(new Insets(0,0,0,5));
            root.add(label, 0,i);
        }
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

    public GridPane getChessBoard() {
        return this.root;
    }

    public boolean moveForward() {
        return this.chess.moveForward();
    }

    public boolean moveBackwards() {
        return this.chess.moveBackwards();
    }

    public boolean loadGame(File file) {
        return this.chess.loadGame(file.toString());
    }

    public boolean saveToFile(String no) {
        return this.chess.saveToFile(no);
    }

    public void goTo(String a) {
        this.chess.goTo(a);
    }

    public void update() {
        Print(board);
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                playfield[row][col].field = board.getField(row, col);
                playfield[row][col].clearIMG();
                playfield[row][col].changeImg();
            }
        }
    }

}
