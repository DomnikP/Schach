package com.Schach.Pieces;

import com.Schach.Alliance;
import com.Schach.Board.Board;
import com.Schach.Board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    public Piece(final int piecePosition, final Alliance pieceAlliance ) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }


    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
}
