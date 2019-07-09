package model.game;

import model.board.Board;
import model.figures.Figure;
import model.board.Field;
import model.figures.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author  Samuel Mensak
 */
public class Chess {

    private Board board;
    private color player;

    private int fileCount = 1;

    private Stack<Field> fieldStack = new Stack<Field>();
    private Stack<Figure> figureStack = new Stack<Figure>();

    private List<int[]> moves = new ArrayList<int[]>();
    private int moveNo = 0; //0

    private enum color{
        white, black;
    }

    private Figure[] whitePawns = new Pawn[8]; // TODO figure to all arrays
    private Rook[] whiteRooks = new Rook[2];
    private Bishop[] whiteBishops = new Bishop[2];
    private Knight[] whiteKnights = new Knight[2];
    private King whiteKing;
    private Queen whiteQueen;

    private Pawn[] blackPawns = new Pawn[8];
    private Rook[] blackRooks = new Rook[2];
    private Bishop[] blackBishops = new Bishop[2];
    private Knight[] blackKnights = new Knight[2];
    private King blackKing;
    private Queen blackQueen;

    public Chess(Board board) {

        player = color.white;

        this.board = board;

        this.initFiguresW();
        this.initFiguresB();

        this.putFiguresW();
        this.putFiguresB();

        initMoves();
    }

    private void initMoves() {
        int[] arr = {0,0,0,0};
        for (int i = 0; i < 128; i++) {
            moves.add(arr);
        }
    }

    private void initFiguresW() {
        whiteQueen = new Queen(1,4,true);
        whiteKing = new King(1,5,true);

        whiteBishops[0] = new Bishop(1,3,true);
        whiteBishops[1] = new Bishop(1,6,true);

        whiteKnights[0] = new Knight(1,2,true);
        whiteKnights[1] = new Knight(1,7,true);

        whiteRooks[0] = new Rook(1,1,true);
        whiteRooks[1] = new Rook(1,8,true);

        for (int i = 1; i < 9; i++) {
            whitePawns[i-1] = new Pawn(2,i,true, false);

        }
    }

    private void initFiguresB() {
        blackQueen = new Queen(8,4,false);
        blackKing = new King(8,5,false);

        blackBishops[0] = new Bishop(8,3,false);
        blackBishops[1] = new Bishop(8,6,false);

        blackKnights[0] = new Knight(8,2,false);
        blackKnights[1] = new Knight(8,7,false);

        blackRooks[0] = new Rook(8,1,false);
        blackRooks[1] = new Rook(8,8,false);

        for (int i = 1; i < 9; i++) {
            blackPawns[i-1] = new Pawn(7,i,false, false);
        }
    }

    private void putFiguresW() {

        board.getField(1,4).putFigure(whiteQueen);
        board.getField(1,5).putFigure(whiteKing);

        board.getField(1,3).putFigure(whiteBishops[0]);
        board.getField(1,6).putFigure(whiteBishops[1]);

        board.getField(1,2).putFigure(whiteKnights[0]);
        board.getField(1,7).putFigure(whiteKnights[1]);

        board.getField(1,1).putFigure(whiteRooks[0]);
        board.getField(1,8).putFigure(whiteRooks[1]);

        for (int i = 1; i < 9; i++) {
            board.getField(2,i).putFigure(whitePawns[i-1]);
        }
    }

    private void putFiguresB() {

        board.getField(8,4).putFigure(blackQueen);
        board.getField(8,5).putFigure(blackKing);

        board.getField(8,3).putFigure(blackBishops[0]);
        board.getField(8,6).putFigure(blackBishops[1]);

        board.getField(8,2).putFigure(blackKnights[0]);
        board.getField(8,7).putFigure(blackKnights[1]);

        board.getField(8,1).putFigure(blackRooks[0]);
        board.getField(8,8).putFigure(blackRooks[1]);

        for (int i = 1; i < 9; i++) {
            board.getField(7,i).putFigure(blackPawns[i-1]);
        }
    }

    public boolean move(Figure figure, Field field) {

        //int[] tmp = {0,0,0,0,0};

        //for (int i = moveNo; i < moves.size(); i++) {
        //    moves.set(i, tmp);
        //}

        if (player == (figure.isWhite() ? color.white : color.black)) {
            //
            Field tField = this.board.getField(figure.getRow(),figure.getCol());
            this.figureStack.push(figure);
            this.figureStack.push(field.getFigure());
            this.fieldStack.push(tField);
            this.fieldStack.push(field);
            //
            int[] arr = new int[5];
            arr[0] = figure.getRow();
            arr[1] = figure.getCol();
            arr[2] = field.getRow();
            arr[3] = field.getCol();
            StringBuilder fig = new StringBuilder();
            fig.append(figure.toString());
            arr[4] = fig.charAt(0);

            boolean moved = figure.move(field);
            if (moved) {
                player = figure.isWhite() ? color.black : color.white;

                moves.set(moveNo, arr);

                if (moveNo == 127) { initMoves(); }
                moveNo++;

            } else {
                this.fieldStack.pop();
                this.fieldStack.pop();
                this.figureStack.pop();
                this.figureStack.pop();
            }
            return moved;
        } else {
            return false;
        }
    }

    public void undo() {

        moveNo--;

        player = (player == color.white) ? color.black : color.white;

        Field f2 = this.fieldStack.pop();
        Field f1 = this.fieldStack.pop();
        Figure fig2 = this.figureStack.pop();
        Figure fig1 = this.figureStack.pop();

        if (fig2 != null) {
            f1.putFigure(fig1);
            f2.remove(f2.getFigure());
            f2.putFigure(fig2);
            this.board.setField(f1, f1.getRow(), f1.getCol());
            this.board.setField(f2, f2.getRow(), f2.getCol());
        } else {
            fig1.setRow(f1.getRow());
            fig1.setCol(f1.getCol());
            f1.putFigure(fig1);
            f2.remove(f2.getFigure());
            f2.putFigure(null);
            this.board.setField(f1, f1.getRow(), f1.getCol());
            this.board.setField(f2, f2.getRow(), f2.getCol());
        }
        if (fig1 != null) {
            f2.putFigure(fig2);
            f1.remove(f1.getFigure());
            f1.putFigure(fig1);
            this.board.setField(f1, f1.getRow(), f1.getCol());
            this.board.setField(f2, f2.getRow(), f2.getCol());
        } else {
            fig2.setRow(f2.getRow());
            fig2.setCol(f2.getCol());
            f2.putFigure(fig2);
            f1.remove(f1.getFigure());
            f1.putFigure(null);
            this.board.setField(f1, f1.getRow(), f1.getCol());
            this.board.setField(f2, f2.getRow(), f2.getCol());
        }
    }

    private boolean moveBasedOnCoord(int fromX, int fromY, int toX, int toY) {
        Figure figure = board.getField(fromX,fromY).getFigure();
        Field field = board.getField(toX, toY);
        return move(figure, field);
    }

    public boolean moveForward() {
        System.out.println("Forward");

        int[] tmp = moves.get(moveNo);
        boolean ret = moveBasedOnCoord(tmp[0], tmp[1], tmp[2], tmp[3]);
        return ret;

    }

    public boolean moveBackwards() {
        this.undo();
        return true;
    }

    public boolean loadGame(String gameFile) {
        int cnt = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(gameFile));
            String line = reader.readLine();
            while (line != null) {
                String[] splitStr = line.trim().split("\\s+", 3);

                int length = splitStr[1].length();
                if (length == 5) {
                    splitStr[1] = splitStr[1].substring(1, 5);
                }
                length = splitStr[2].length();
                if (length == 5) {
                    splitStr[2] = splitStr[2].substring(1, 5);
                }

                if (length == 4 || length == 5) {

                    int[] arr = proccess(splitStr[1]);
                    //moves.add(arr);
                    System.out.println(arr);
                    if (!moveBasedOnCoord(arr[0], arr[1], arr[2], arr[3])) { return false; }
                    cnt++;

                    arr = proccess(splitStr[2]);
                    //moves.add(arr);
                    System.out.println(arr);
                    if (!moveBasedOnCoord(arr[0], arr[1], arr[2], arr[3])) { return false; }
                    cnt++;
                } else {
                    //error
                    return false;
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < cnt; i++) {
            undo();
        }
        return true;
    }

    public boolean saveToFile(String no) {
        try{
            FileWriter fw=new FileWriter("data/custom_game_" + no);
            int j = 1;
            for (int i = 0; i < moveNo; i++) {
                String ret = arrToStr(moves.get(i++));
                fw.write((j++) + ". " + ret);
                String ret2 = arrToStr(moves.get(i));
                fw.write( "\t" + ret2 + "\n");
            }
            fw.close();
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public void goTo(String a) {
        int tmp = Character.getNumericValue(a.charAt(0));
        if (tmp == moveNo) {

        } else if (tmp > moveNo) {
            for (int i = 0; i < tmp - moveNo; i++) {
                this.moveForward();
            }
        } else {
            for (int i = 0; i < moveNo - tmp; i++) {
                this.moveBackwards();
            }
        }
    }

    private String arrToStr(int[] arr) {
        StringBuilder ret = new StringBuilder();

        int a = arr[0];
        int b = arr[1];
        int c = arr[2];
        int d = arr[3];

        arr[0] = b;
        arr[1] = a;
        arr[2] = d;
        arr[3] = c;
        if ((char) arr[4] != 'p') {
            ret.append((char) arr[4]);
        }

        for (int i = 0; i < 4; i++) {

            if (i % 2 == 1) {
                ret.append(arr[i]);
                continue;
            }
            if (i % 2 == 0) {
                switch (arr[i]) {
                    case 1:
                        ret.append("a");
                        break;
                    case 2:
                        ret.append("b");
                        break;
                    case 3:
                        ret.append("c");
                        break;
                    case 4:
                        ret.append("d");
                        break;
                    case 5:
                        ret.append("e");
                        break;
                    case 6:
                        ret.append("f");
                        break;
                    case 7:
                        ret.append("g");
                        break;
                    case 8:
                        ret.append("h");
                        break;
                }
                continue;
            }
        }
        return ret.toString();
    }

    private int[] proccess(String string) {
        String from = string.substring(0, 2);
        String to = string.substring(2, 4);

        String Fx = from.substring(0,1);
        String Fy = from.substring(1,2);

        String Tx = to.substring(0,1);
        String Ty = to.substring(1,2);

        int[] arr = new int[4];
        arr[1] = ((int) Fx.charAt(0) - 'a' + 1);
        arr[0] = (int) Fy.charAt(0) - '0';
        arr[3] = ((int) Tx.charAt(0) - 'a' + 1);
        arr[2] = (int) Ty.charAt(0) - '0';
        return arr;
    }
}
