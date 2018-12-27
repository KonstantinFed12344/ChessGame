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
public class Pawn extends Piece{
    
    private boolean pawnMoves;
       
    public Pawn(ChessColour colour){
        super(colour,ChessPieces.PAWN);
        this.pawnMoves = false;
    }
    
    public boolean isLegalMove(ChessBoard board, Coordinate src,Coordinate dest){
        if(board.getSquare(src).getPiece().getColour() == ChessColour.WHITE){               
            if(dest.getRowNumber() == src.getRowNumber()+1  && (dest.getColumnNumber() == src.getColumnNumber() || dest.getColumnNumber() + 1 == src.getColumnNumber() || dest.getColumnNumber() - 1 == src.getColumnNumber())){
                if(!(board.getSquare(dest).isOccupied() && dest.getRowNumber() == src.getRowNumber()+1  && dest.getColumnNumber() == src.getColumnNumber())){                    
                    if(super.isLegalMove(board, src, dest) == true) {
                        pawnMoves = true;
                        return true;
                    }
                }            
            }else if(dest.getRowNumber() == src.getRowNumber()+ 2 && (dest.getColumnNumber() == src.getColumnNumber() || dest.getColumnNumber() + 1 == src.getColumnNumber() || dest.getColumnNumber() - 1 == src.getColumnNumber())){                
                if(super.isLegalMove(board, src, dest) == true && !pawnMoves) {
                    pawnMoves = true;
                    return true;
                }               
            }              
        }else if(board.getSquare(src).getPiece().getColour() == ChessColour.BLACK){
            if(dest.getRowNumber() == src.getRowNumber()-1 && (dest.getColumnNumber() == src.getColumnNumber() || dest.getColumnNumber() + 1 == src.getColumnNumber() || dest.getColumnNumber() - 1 == src.getColumnNumber())){
                if(!(board.getSquare(dest).isOccupied() && dest.getRowNumber() == src.getRowNumber()-1  && dest.getColumnNumber() == src.getColumnNumber())){                   
                    if(super.isLegalMove(board, src, dest) == true) {
                        pawnMoves = true;
                        return true;
                    }
                }  
            } else if(dest.getRowNumber() == src.getRowNumber() - 2 && (dest.getColumnNumber() == src.getColumnNumber() || dest.getColumnNumber() + 1 == src.getColumnNumber() || dest.getColumnNumber() - 1 == src.getColumnNumber())){                
                if(super.isLegalMove(board, src, dest) == true && !pawnMoves) {
                    pawnMoves = true;
                    return true;
                }          
            }   
        }       
        return false;
    }
}
