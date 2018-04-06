package com.Schach.Pieces;

import com.Schach.Alliance;
import com.Schach.Board.Board;
import com.Schach.Board.Move;
import com.Schach.Board.Square;
import com.Schach.Board.BoardUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

   static final int[] POSSIBLE_MOVE_CANDIDATES = {-17,-15,-10,-6,6,10,15,17}; //Possible Squares for a knight according to the chess rules


    public Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for(int currentCandidateOffset: POSSIBLE_MOVE_CANDIDATES) { //loop through the candidateMoves

            candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset; //current position plus CANDIDATEMOVE

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isSecondColumnExclusion(this.piecePosition,currentCandidateOffset)
                        || isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue;
                }
                final Square candidateDestinationTile = board.getSquare(candidateDestinationCoordinate);

                if(!candidateDestinationTile.isSquareOccupied()) {
                    legalMoves.add(new Move());

                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance) { //If the piece on that square is an enemy pice its a legal move and we take the square
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset ) {

      return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset== -17 || candidateOffset == -10 || candidateOffset == 6
                || candidateOffset == 15);

    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10
                || candidateOffset ==17);
    }


}
