package com.Schach.Board;

public class BoardUtils {

    public final static  boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final int NUM_SQUARES = 64;
    public static final int NUM_SQUARES_PER_ROW= 8;

    private BoardUtils() {
        throw  new RuntimeException();
    }

    public static boolean isValidTileCoordinate(int coordinate) {

        return coordinate >= 0 && coordinate < NUM_SQUARES; //Check if the Coordinate is on the chessboard or not
    }

    private static boolean[] initColumn(int columnNumber) {

        final boolean[] column = new boolean[NUM_SQUARES]; //declare an array of boolean with the size 64

        do {

            column[columnNumber] =true; //set the first square of the chess board to true
            columnNumber+=NUM_SQUARES_PER_ROW; //

        }while (columnNumber < NUM_SQUARES);
        return column;
    }


}
