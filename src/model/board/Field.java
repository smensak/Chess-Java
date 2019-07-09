package model.board;

import model.figures.Figure;

/**
 * @author  Samuel Mensak
 * Field interface.
 */
public interface Field {

    /**
     * Represents directions of movement on board.
     */
    public enum Direction {
        D,
        L,
        LD,
        LU,
        R,
        RD,
        RU,
        U
    }

    /**
     * Gets row of field.
     * @return row.
     */
    public int getRow();

    /**
     * Gets column of field.
     * @return column.
     */
    public int getCol();

    /**
     * Adds field in given direction.
     * @param direction direction in which field should be added.
     * @param field field to be added.
     */
    public void addNextField(Field.Direction direction, Field field);

    /**
     * Returns figure on this field.
     * @return figure on this field.
     */
    public Figure getFigure();

    /**
     * Gets next field in given direction.
     * @param direction direction to the field which should be returned.
     * @return field in given direction.
     */
    public Field nextField(Field.Direction direction);

    /**
     * Places figure onto this field.
     * @param figure figure to be placed.
     * @return true if successful, false if not.
     */
    public boolean putFigure(Figure figure);

    /**
     * Checks if field is occupied.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * removes figure from this field.
     * @param figure figure to be removed.
     * @return true if removed, false otherwise.
     */
    public boolean remove(Figure figure);

    /**
     * Prints field info.
     * @return Field info as string.
     */
    public String toString();
}
