package com.Schach.Board;

import com.Schach.Pieces.Piece;
import com.google.common.collect.ImmutableMap;

import javax.management.ImmutableDescriptor;
import java.util.HashMap;
import java.util.Map;

public abstract class Square {

    protected final int coord;

    private static final Map<Integer, EmptySquare> EMPTY_SQUARES= createAllPossibleEmptySquares();

    private static Map<Integer,EmptySquare> createAllPossibleEmptySquares() {
        final Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();

        for (int i=0; i<64; i++) {
            emptySquareMap.put(i, new EmptySquare(i));

        }
        return ImmutableMap.copyOf(emptySquareMap); //https://github.com/google/guava/wiki/Release21
                                                    //https://google.github.io/guava/releases/21.0/api/docs/com/google/common/collect/ImmutableMap.html
    }

    public static Square createSquare(final int coord, final Piece piece) {
        return piece != null ? new OccupiedSquare(coord, piece) : EMPTY_SQUARES.get(coord);
    }


    private Square(int coord) {
        this.coord=coord;
    }

    public abstract boolean isSquareOccupied();

    public abstract Piece getPiece();

    public static final class EmptySquare extends Square {

        private EmptySquare(final int coordinate) {
            super(coordinate);
        }

        public boolean isSquareOccupied() {
            return false;
        }

        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedSquare extends Square {

        private final Piece pieceOnSquare;

        private OccupiedSquare( int coord, Piece pieceOnSquare) {
            super(coord);
            this.pieceOnSquare = pieceOnSquare;
        }

        public boolean isSquareOccupied() {
            return true;
        }

        public Piece getPiece() {
            return this.pieceOnSquare;
        }
    }

}
