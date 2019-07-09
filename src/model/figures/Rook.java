package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public class Rook extends AbstractFigure implements Figure {

    /**
     * Creates new rook with given color and coordinates.
     * @param row row coordinate.
     * @param col column coordinate.
     * @param isWhite color of the rook.
     */
    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    /**
     * Moves figure to given field.
     * @param moveTo destination field.
     * @return true is moved, false otherwise.
     */
    @Override public boolean move(Field moveTo) {
        return (this.Go(moveTo, Field.Direction.L) ||
                this.Go(moveTo, Field.Direction.R) ||
                this.Go(moveTo, Field.Direction.U) ||
                this.Go(moveTo, Field.Direction.D));
    }

    /**
     * Detailed figure info.
     * @return Detailed figure info as string.
     */
    public String toString() {
        return "Veza" + getState();
    }
}
