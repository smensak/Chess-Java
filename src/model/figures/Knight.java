package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public class Knight extends AbstractFigure implements Figure {

    /**
     * Creates new knight with given color and coordinates.
     * @param row row coordinate.
     * @param col column coordinate.
     * @param isWhite color of the rook.
     */
    public Knight(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    /**
     * Moves figure to given field.
     * @param moveTo destination field.
     * @return true is moved, false otherwise.
     */
    @Override public boolean move(Field moveTo) {
        return (this.Go(moveTo, Field.Direction.U, Field.Direction.LU) ||
                this.Go(moveTo, Field.Direction.U, Field.Direction.RU) ||
                this.Go(moveTo, Field.Direction.D, Field.Direction.LD) ||
                this.Go(moveTo, Field.Direction.D, Field.Direction.RD) ||
                this.Go(moveTo, Field.Direction.L, Field.Direction.LU) ||
                this.Go(moveTo, Field.Direction.L, Field.Direction.LD) ||
                this.Go(moveTo, Field.Direction.R, Field.Direction.RU) ||
                this.Go(moveTo, Field.Direction.R, Field.Direction.RD));
    }

    /**
     * Move logic.
     * @param moveTo destination field.
     * @param direction direction of movement.
     * @return true if move succeeded, false otherwise.
     */
    private boolean Go(Field moveTo, Field.Direction direction, Field.Direction direction2) {

        Field tmp = moveTo.nextField(direction);
        tmp = tmp.nextField(direction2);

        if (tmp != null && !tmp.toString().equals("Border Field")) {

            if ((!tmp.isEmpty() && (this.isWhite() == tmp.getFigure().isWhite())) && !this.equals(tmp.getFigure())){
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
        return "Jazdec" + getState();
    }
}
