package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public class Pawn extends AbstractFigure implements Figure {

    private boolean moved;

    /**
     * Creates new knight with given color and coordinates.
     *
     * @param row     row coordinate.
     * @param col     column coordinate.
     * @param moved   false if pawn has not made a first move, true otherwise.
     * @param isWhite color of the rook.
     */
    public Pawn(int row, int col, boolean isWhite, boolean moved) {
        super(row, col, isWhite);
        this.moved = moved;
    }

    /**
     * Detailed figure info.
     * @return Detailed figure info as string.
     */
    public String toString() {
        return "pesiak" + getState();
    }

    /**
     * Moves figure to given field.
     *
     * @param moveTo destination field.
     * @return true is moved, false otherwise.
     */
    @Override
    public boolean move(Field moveTo) {
        this.moved = true;
        if (this.isWhite()) {
            return (this.Go(moveTo, Field.Direction.U) ||
                    this.Go(moveTo, Field.Direction.LU) ||
                    this.Go(moveTo, Field.Direction.RU));
        } else {
            return (this.Go(moveTo, Field.Direction.D) ||
                    this.Go(moveTo, Field.Direction.RD) ||
                    this.Go(moveTo, Field.Direction.LD));
        }
    }

    /**
     * Move logic.
     *
     * @param moveTo    destination field.
     * @param direction direction of movement.
     * @return true if move succeeded, false otherwise.
     */
    public boolean Go(Field moveTo, Field.Direction direction) {

        Field tmp = moveTo.nextField(direction);

        int check = 0;
        while (tmp != null && check <= 1 && !tmp.toString().equals("Border Field")) {
            if ((!tmp.isEmpty() && (this.isWhite() == tmp.getFigure().isWhite())) && !this.equals(tmp.getFigure())) {
                return false;
            }
            if (!tmp.isEmpty() && (this.isWhite() == tmp.getFigure().isWhite()) && (direction == Field.Direction.LD ||
                    direction == Field.Direction.RD ||
                    direction == Field.Direction.LU ||
                    direction == Field.Direction.RU)) {
                moveTo.remove(moveTo.getFigure());
                tmp.remove(this);
                this.setCol(moveTo.getCol());
                this.setRow(moveTo.getRow());
                return moveTo.putFigure(this);
            }
            if (this.equals(tmp.getFigure()) && (direction == Field.Direction.U || direction == Field.Direction.D)) {
                tmp.remove(this);
                this.setCol(moveTo.getCol());
                this.setRow(moveTo.getRow());
                return moveTo.putFigure(this);
            }
            if (direction == Field.Direction.U || direction == Field.Direction.D) {
                tmp = tmp.nextField(direction);
                check++;
            } else {
                break;
            }
        }
        return false;
    }
}
