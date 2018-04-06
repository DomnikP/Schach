package com.Schach.Board;

public class BoardUtils {

    public final static  boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHTH_COLUMN = null;

    private BoardUtils() {
        throw  new RuntimeException();
    }

    public static boolean isValidTileCoordinate(int coordinate) {

        return coordinate >= 0 && coordinate < 64; //Check if the Coordinate is on the chessboard or not
    }


}
