package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public class Bishop extends AbstractFigure implements Figure {

    /**
     * Creates new bishop with given color and coordinates.
     * @param row row coordinate.
     * @param col column coordinate.
     * @param isWhite color of the rook.
     */
    public Bishop(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    /**
     * Moves figure to given field.
     * @param moveTo destination field.
     * @return true is moved, false otherwise.
     */
    @Override public boolean move(Field moveTo) {
        return (this.Go(moveTo, Field.Direction.LD) ||
                this.Go(moveTo, Field.Direction.LU) ||
                this.Go(moveTo, Field.Direction.RD) ||
                this.Go(moveTo, Field.Direction.RU));
    }

    /**
     * Detailed figure info.
     * @return Detailed figure info as string.
     */
    public String toString() {
        return "Strelec" + getState();
    }
}
