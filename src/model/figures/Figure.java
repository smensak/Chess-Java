package model.figures;

import model.board.Field;

/**
 * @author  Samuel Mensak
 */

public interface Figure {

    String getState();

    String getPosition();

    int getCol();
    int getRow();

    void setCol(int col);
    void setRow(int row);

    boolean move(Field field);

    boolean isWhite();

    String toString();
}
