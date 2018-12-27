/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Coordinate;
import chess.Piece;

/**
 *
 * @author Konstantin
 */
public class Square {

    private Coordinate coordinate;
    private Piece piece;

    public Square(Coordinate c) {

        this.coordinate = c;
        this.piece = null;

    }

    public Square(Coordinate c, Piece p) {

        this.coordinate = c;
        this.piece = p;

    }

    public char getColumn() {
        return this.coordinate.getColumn();
    }

    public char getRow() {
        return this.coordinate.getRow();
    }

    public int getColumnNumber() {
        return this.coordinate.getColumnNumber();
    }

    public int getRowNumber() {
        return this.coordinate.getRowNumber();
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Piece addPiece(Piece Piece) {
        if (this.piece == null) {
            this.piece = Piece;
            return null;
        } else {
            Piece copy = this.piece;
            this.piece = Piece;
            return copy;
        }
    }

    public Piece deletePiece() {

        Piece copy = this.piece;
        this.piece = null;
        return copy;

    }

    public boolean isOccupied() {
        if (this.piece != null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        if (this.piece == null) {
            return "Square" + this.coordinate.toString() + ": ";
        } else {
            return "Square" + this.coordinate.toString() + ":" + this.piece.toString();
        }
    }
}
