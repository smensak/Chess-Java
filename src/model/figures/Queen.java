package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public class Queen extends AbstractFigure implements Figure {

    /**
     * Creates new bishop with given color and coordinates.
     * @param row row coordinate.
     * @param col column coordinate.
     * @param isWhite color of the rook.
     */
    public Queen(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    /**
     * Detailed figure info.
     * @return Detailed figure info as string.
     */
    public String toString() {
        return "Dama" + getState();
    }
}
