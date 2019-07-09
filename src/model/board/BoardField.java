package model.board;

import model.figures.Figure;

/**
 * @author  Samuel Mensak
 * Implements one field of chess board.
 */
public class BoardField implements Field {

    /**
     * Specifies row.
     */
    private int row;
    /**
     * Specifies column.
     */
    private int col;

    /**
     * Figure on this field.
     */
    private Figure figure = null;

    /**
     * Fields around this one.
     */
    private Field[] nextTo;

    /**
     * Creates field with given coordinates.
     * @param row row.
     * @param col column.
     */
    public BoardField( int row, int col) {
        this.col = col;
        this.row = row;
        this.nextTo = new Field[8];
    }

    /**
     * Gets row of field.
     * @return row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets column of field.
     * @return column.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Adds neighboring field
     * @param direction direction of field to be added.
     * @param field field to be added.
     */
    public void addNextField(Field.Direction direction, Field field) {
        this.nextTo[findDirection(direction)] = field;
    }

    /**
     * Get neighbouring field in direction.
     * @param direction direction to neighbour.
     * @return neighbouring field in given direction or null if non-existent.
     */
    public Field nextField(Field.Direction direction) {
        return (this.nextTo[findDirection(direction)]);
    }

    /**
     * Decodes direction from enum.
     * @param direction direction.
     * @return value.
     */
    private int findDirection(Direction direction) {
        int counter = 0;
        for (Direction c : Direction.values()) {
            if (direction == c) { break; }
            counter++;
        }
        return counter;
    }


    /**
     * Checks if two fields are equal.
     * @param obj field to compare with this field.
     * @return true if equal, false otherwise.
     */
    public boolean equals(java.lang.Object obj) {
        return super.equals(obj);
    }

    /**
     * Hash code for field.
     * @return Hash code.
     */
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Puts figure onto this field.
     * @param figure figure to be placed.
     * @return true if successful, false otherwise.
     */
    public boolean putFigure(Figure figure) {
        if (this.getFigure() == null) {
            this.figure = figure;
            return true;
        }
        return false;
    }

    /**
     * removes figure from this field.
     * @param figure figure to be removed.
     * @return true if removed, false otherwise.
     */
    public boolean remove(Figure figure) {
        if (!(this.isEmpty()) && this.figure.equals(figure)) {
            this.figure = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets figure from this field.
     * @return figure on this field, or null if empty.
     */
    @Override public Figure getFigure() {
        return this.figure;
    }

    /**
     * Checks if field is empty.
     * @return true if empty, false otherwise.
     */
    @Override public boolean isEmpty() {
        return (this.figure == null);
    }

    /**
     * Field status sa string.
     * @return Field status as string.
     */
    @Override public String toString() {
        return ("[" + row + "," + col + "] - " + figure);
    }
}
