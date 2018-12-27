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
public class Rook extends Piece{
    
    public Rook(ChessColour colour){
        super(colour,ChessPieces.ROOK);
    }
    
    public boolean isLegalMove(ChessBoard board, Coordinate src,Coordinate dest){
        if((src.getColumnNumber() == dest.getColumnNumber() && src.getRowNumber() != dest.getRowNumber()) || (src.getColumnNumber() != dest.getColumnNumber() && src.getRowNumber() == dest.getRowNumber())){
            
            if(super.isLegalMove(board, src, dest) == true) {
                return true;
            }
        }
        
        return false;
    }
}
