package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */
public abstract class AbstractFigure implements Figure {

    private boolean isWhite;
    private int row;
    private int col;

    /**
     * Creates new bishop with given color and coordinates.
     * @param row row coordinate.
     * @param col column coordinate.
     * @param isWhite color of the rook.
     */
    public AbstractFigure(int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
    }

    /**
     * Gets column on which rook is.
     * @return column.
     */
    @Override final public int getCol() {
        return this.col;
    }

    /**
     * Gets row on which rook is.
     * @return row.
     */
    @Override final public int getRow() {
        return this.row;
    }

    /**
     * Sets column on which figure is.
     * @param col column.
     */
    @Override final public void setCol(int col) {
        this.col = col;
    }

    /**
     * Sets row on which figure is.
     * @param row row.
     */
    @Override final public void setRow(int row) {
        this.row = row;
    }

    /**
     * Creates string with figure info.
     * @return info about figure as string.
     */
    @Override public String getState() {
        String tmp = "[";
        if (isWhite) { tmp += "W]";}
        else { tmp += "B]";}
        tmp += getPosition();
        return tmp;
    }

    /**
     * Returns figure coordinates.
     * @return column and row coordinate.
     */
    @Override final public String getPosition(){
        return row + ":" + col;
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
                this.Go(moveTo, Field.Direction.RU) ||
                this.Go(moveTo, Field.Direction.L) ||
                this.Go(moveTo, Field.Direction.R) ||
                this.Go(moveTo, Field.Direction.U) ||
                this.Go(moveTo, Field.Direction.D));
    }

    /**
     * Move logic.
     * @param moveTo destination field.
     * @param direction direction of movement.
     * @return true if move succeeded, false otherwise.
     */
    public boolean Go(Field moveTo, Field.Direction direction) {

        Field tmp = moveTo.nextField(direction);

        while (tmp != null && !tmp.toString().equals("Border Field")) {
            if ((!tmp.isEmpty() && (this.isWhite() == tmp.getFigure().isWhite())) && !this.equals(tmp.getFigure())){
                return false;
            }
            if ((!tmp.isEmpty() && (this.isWhite() != tmp.getFigure().isWhite()))) {
                moveTo.remove(moveTo.getFigure());
                tmp.remove(this);
                this.col = moveTo.getCol();
                this.row = moveTo.getRow();
                return moveTo.putFigure(this);
            }
            if (this.equals(tmp.getFigure())) {
                tmp.remove(this);
                this.col = moveTo.getCol();
                this.row = moveTo.getRow();
                return moveTo.putFigure(this);
            }
            tmp = tmp.nextField(direction);
        }
        return false;
    }

    /**
     * Returns color of the figure.
     * @return true if white, false if black.
     */
    @Override final public boolean isWhite() {
        return isWhite;
    }
}
