/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Konstantin
 */
public class Knight extends Piece {

    public Knight(ChessColour colour) {
        super(colour, ChessPieces.KNIGHT);
    }

    public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {
        if ((dest.getRowNumber() == src.getRowNumber() + 2 && (dest.getColumnNumber() + 1 == src.getColumnNumber() || dest.getColumnNumber() - 1 == src.getColumnNumber())) || (dest.getRowNumber() == src.getRowNumber() + 1 && (dest.getColumnNumber() + 2 == src.getColumnNumber() || dest.getColumnNumber() - 2 == src.getColumnNumber())) || (dest.getRowNumber() == src.getRowNumber() - 1 && (dest.getColumnNumber() + 2 == src.getColumnNumber() || dest.getColumnNumber() - 2 == src.getColumnNumber())) || (dest.getRowNumber() == src.getRowNumber() - 2 && (dest.getColumnNumber() + 1 == src.getColumnNumber() || dest.getColumnNumber() - 1 == src.getColumnNumber()))) {
            if (super.isLegalMove(board, src, dest) == true) {
                return true;
            }
        }
        return false;
    }
}
