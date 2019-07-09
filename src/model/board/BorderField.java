package model.board;

import model.figures.Figure;

/**
 * @author  Samuel Mensak
 * Implements one field of chess board.
 */
public class BorderField implements Field {


    /**
     * Creates border field.
     */
    public BorderField() {
    }

    /**
     * Gets row of field.
     * @return 0.
     */
    public int getRow() {
        return -99;
    }

    /**
     * Gets column of field.
     * @return 99.
     */
    public int getCol() {
        return -99;
    }

    /**
     * Does nothing.
     * @param direction direction of field to be added.
     * @param field field to be added.
     */
    public void addNextField(Field.Direction direction, Field field) {
    }

    /**
     * Does nothing
     * @param direction direction to neighbour.
     * @return neighbouring field in given direction or null if non-existent.
     */
    public Field nextField(Field.Direction direction) {
        return null;
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
     * @return returns Hash code.
     */
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Does nothing, unsuccessfully.
     * @param figure figure to be placed.
     * @return false.
     */
    public boolean putFigure(Figure figure) {
        return false;
    }

    /**
     * Does nothing, unsuccessfully.
     * @param figure figure to be removed.
     * @return false.
     */
    public boolean remove(Figure figure) {
        return false;
    }

    /**
     * Does nothing.
     * @return null.
     */
    @Override public Figure getFigure() {
        return null;
    }

    /**
     * Checks if field is empty.
     * @return false.
     */
    @Override public boolean isEmpty() {
        return false;
    }

    /**
     * Field status sa string.
     * @return Field status as string.
     */
    @Override public String toString() {
        return ("Border Field");
    }
}
