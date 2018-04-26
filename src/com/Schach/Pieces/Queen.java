package com.Schach.Pieces;

import com.Schach.Alliance;
import com.Schach.Board.Board;
import com.Schach.Board.BoardUtils;
import com.Schach.Board.Move;
import com.Schach.Board.Square;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece {

    private static final int[] POSSIBLE_MOVE_VECTORCOORDINATES = {-8, -1, 1, 8, -9, -7, 7, 9}; //Possible moves according to the current position of the piece
    public Queen(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(int candidateCoordinateOffset : POSSIBLE_MOVE_VECTORCOORDINATES) { //Loop through the vectors

            int candidateDestinationCoordindate = this.piecePosition;

            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordindate)) {

                if (isFirstColumnExclusion(candidateDestinationCoordindate, candidateCoordinateOffset) || //"Exceptions"
                        isEighthColumnExclusion(candidateDestinationCoordindate, candidateCoordinateOffset)) {
                    break;
                }

                candidateDestinationCoordindate += candidateCoordinateOffset;

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordindate)) {

                    final Square candidateDestinationTile = board.getSquare(candidateDestinationCoordindate);

                    if(!candidateDestinationTile.isSquareOccupied()) {
                        legalMoves.add(new Move.NormalMove(board,this, candidateDestinationCoordindate)); //the rook performs a legal move

                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if(this.pieceAlliance != pieceAlliance) { //If the piece on that square is an enemy pice its a legal move and we take the square
                            legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordindate,pieceAtDestination)); //attack the piece

                        }
                        break;

                    }





                }
            }


        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1) || (candidateOffset == -9) || (candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1) || (candidateOffset == -7) || (candidateOffset == 9);
    }
}
