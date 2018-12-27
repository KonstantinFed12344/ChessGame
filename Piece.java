/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.ChessColour;
import chess.ChessPieces;

/**
 *
 * @author Konstantin
 */
public class Piece {

    private ChessColour colour;
    private ChessPieces name;
    private char shortName;
    private String imageName;

    public Piece(ChessColour colour, ChessPieces name) {

        this.colour = colour;
        this.name = name;
        this.imageName = this.colour.name().toLowerCase() + "_" + this.name.name().toLowerCase() + ".png";
        
        if (colour.equals(ChessColour.WHITE)) {
            this.shortName = name.getShortName();
        } else {
            this.shortName = (char) ((int) name.getShortName() + 32);
        }
    }

    public ChessColour getColour() {
        return this.colour;
    }

    public ChessPieces getName() {
        return this.name;
    }

    public char getShortName() {
        return this.shortName;
    }

    public String getImageName() {
        return imageName;
    }

    public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {
        if (board.getSquare(dest).isOccupied() && board.getSquare(dest).getPiece().getColour() != board.getSquare(src).getPiece().getColour()) {
            return true;
        } else if (!board.getSquare(dest).isOccupied()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.colour.name() + " " + this.name.name();
    }

}
