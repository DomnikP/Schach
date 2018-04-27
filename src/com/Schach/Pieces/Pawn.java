package com.Schach.Pieces;

import com.Schach.Alliance;
import com.Schach.Board.Board;
import com.Schach.Board.BoardUtils;
import com.Schach.Board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {8}; //The pawn can only move forward


    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {

            int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset); //Determine whether the pawn is white or black

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                continue;

            }

            if (currentCandidateOffset == 8 && !board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {

                //TODO
                legalMoves.add(new Move.NormalMove(board,this,  candidateDestinationCoordinate));

            }


        }





        return legalMoves;
    }
}
