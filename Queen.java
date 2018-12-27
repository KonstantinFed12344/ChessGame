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
public class Queen extends Piece {

    public Queen(ChessColour colour) {
        super(colour, ChessPieces.QUEEN);
    }

    public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {

        int moveVertical = src.getRowNumber() - dest.getRowNumber();
        int moveHorizontal = src.getColumnNumber() - dest.getColumnNumber();

        if (moveVertical < 0) {//Makes sure that horizontal and vertical movement is a positive number
            moveVertical = -1 * moveVertical;
        }
        
        if (moveHorizontal < 0) {
            moveHorizontal = -1 * moveHorizontal;
        }

        if ((moveVertical == moveHorizontal) || (src.getColumnNumber() == dest.getColumnNumber() && src.getRowNumber() != dest.getRowNumber()) || (src.getColumnNumber() != dest.getColumnNumber() && src.getRowNumber() == dest.getRowNumber())) {
            if (super.isLegalMove(board, src, dest) == true) {
                return true;
            }
        }

        return false;
    }
}
