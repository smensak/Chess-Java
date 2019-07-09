package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public class King extends AbstractFigure implements Figure {

    /**
     * Creates new king with given color and coordinates.
     * @param row row coordinate.
     * @param col column coordinate.
     * @param isWhite color of the rook.
     */
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    /**
     * Move logic.
     * @param moveTo destination field.
     * @param direction direction of movement.
     * @return true if move succeeded, false otherwise.
     */
    public boolean Go(Field moveTo, Field.Direction direction) {

        Field tmp = moveTo.nextField(direction);

        if (tmp != null && !tmp.toString().equals("Border Field")) {

            if ((!tmp.isEmpty() && (this.isWhite() == tmp.getFigure().isWhite())) && !this.equals(tmp.getFigure())) {
                return false;
            }
            if ((!tmp.isEmpty() && (this.isWhite() == tmp.getFigure().isWhite()))) {
                moveTo.remove(moveTo.getFigure());
                tmp.remove(this);
                this.setCol(moveTo.getCol());
                this.setRow(moveTo.getRow());
                return moveTo.putFigure(this);
            }
            if (this.equals(tmp.getFigure())) {
                tmp.remove(this);
                this.setCol(moveTo.getCol());
                this.setRow(moveTo.getRow());
                return moveTo.putFigure(this);
            }
        }
        return false;
    }

    /**
     * Detailed figure info.
     * @return Detailed figure info as string.
     */
    public String toString() {
        return "Kral" + getState();
    }
}
