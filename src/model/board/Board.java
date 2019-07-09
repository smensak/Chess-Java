package model.board;

/**
 * @author  Samuel Mensak
 * Implements board.
 */
public class Board {
    private int size;
    private Field[][] array;


    /**
     * Creates new board.
     * @param size size of the board.
     */
    public Board(int size) {
        this.size = size;
        this.array = new Field[this.size + 2][this.size + 2];
        this.generateField();
        this.setNeighbors();
    }

    /**
     * Gets field.
     * @param col field column.
     * @param row field row.
     * @return field if found, null otherwise.
     */
    public Field getField(int row, int col) {
        if ((col >= 0) && (col <= this.size +1) && (row >= 0) && (row <= this.size +1)) {
            return array[row][col];
        } else {
            return null;
        }
    }

    /**
     * Sets field at given coordinates.
     * @param field field to set.
     * @param col column coordinate.
     * @param row row coordinate.
     * @return true.
     */
    public boolean setField(Field field, int row, int col) {
        this.array[row][col] = field;
        return true;
    }

    /**
     * Gets size of this board.
     * @return size of the board.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Generates board fields.
     */
    private void generateField() {
        int size = this.getSize();
        Field border = new BorderField();
        for (int row = 0; row <= size + 1; row++) {
            for (int col = 0; col <= size + 1; col++) {
                if ((row == 0) || (row == size + 1)) {
                    this.array[row][col] = border;
                } else if ((col == 0) || (col == size + 1)) {
                    this.array[row][col] = border;
                } else {
                    this.array[row][col] = new BoardField(row, col);
                }
            }
        }
    }

    /**
     * Sets neighbours for all fields on board.
     */
    private void setNeighbors() {
        for (int row = 1; row <= this.getSize(); row++) {
            for (int col = 1; col <= this.getSize(); col++)
            {
                array[row][col].addNextField(Field.Direction.D, this.array[row + 1][col]);
                array[row][col].addNextField(Field.Direction.L, this.array[row][col - 1]);
                array[row][col].addNextField(Field.Direction.LD, this.array[row + 1][col - 1]);
                array[row][col].addNextField(Field.Direction.LU, this.array[row - 1][col - 1]);
                array[row][col].addNextField(Field.Direction.R, this.array[row][col + 1]);
                array[row][col].addNextField(Field.Direction.RD, this.array[row + 1][col + 1]);
                array[row][col].addNextField(Field.Direction.RU, this.array[row - 1][col + 1]);
                array[row][col].addNextField(Field.Direction.U, this.array[row - 1][col]);
            }
        }

    }
}
