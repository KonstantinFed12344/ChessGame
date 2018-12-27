/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 *
 * @author Konstantin
 */
public class ChessBoard {

    private ChessColour activeColour;
    private int fullMove;
    private Square board[][];

    private ObservableList<Piece> whiteTakenPieces = FXCollections.observableList(new ArrayList<Piece>());

    private ObservableList<Piece> blackTakenPieces = FXCollections.observableList(new ArrayList<Piece>());

    public ChessBoard() {
        board = new Square[8][8];
        for (int c = 0; c < 8; c++) {
            for (int r = 0; r < 8; r++) {
                board[c][r] = new Square(new Coordinate(c, r));
            }
        }
        reset();
        activeColour = ChessColour.WHITE;
        fullMove = 1;
    }

    public ChessBoard(Coordinate positions[], Piece pieces[]) throws IllegalArgumentException {
        if (positions.length != pieces.length) {
            throw new IllegalArgumentException("The list of positions must correspond to the list of pieces");
        }

        board = new Square[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board[r][c] = new Square(new Coordinate(r, c));
            }
        }
        for (int i = 0; i < positions.length; i++) {
            board[positions[i].getColumnNumber()][positions[i].getRowNumber()].addPiece(pieces[i]);
        }
        activeColour = ChessColour.WHITE;
        fullMove = 1;
        whiteTakenPieces.clear();
        blackTakenPieces.clear();
    }

    private void reset() {

        activeColour = ChessColour.WHITE;
        fullMove = 1;
        whiteTakenPieces.clear();
        blackTakenPieces.clear();   

        Piece R = new Rook(ChessColour.WHITE);
        Piece N = new Knight(ChessColour.WHITE);
        Piece B = new Bishop(ChessColour.WHITE);
        Piece Q = new Queen(ChessColour.WHITE);
        Piece K = new King(ChessColour.WHITE);
        Piece P = new Pawn(ChessColour.WHITE);
        Piece p = new Pawn(ChessColour.BLACK);
        Piece r = new Rook(ChessColour.BLACK);
        Piece n = new Knight(ChessColour.BLACK);
        Piece b = new Bishop(ChessColour.BLACK);
        Piece q = new Queen(ChessColour.BLACK);
        Piece k = new King(ChessColour.BLACK);

        this.board[0][0].addPiece(R);
        this.board[1][0].addPiece(N);
        this.board[2][0].addPiece(B);
        this.board[3][0].addPiece(Q);
        this.board[4][0].addPiece(K);
        this.board[5][0].addPiece(B);
        this.board[6][0].addPiece(N);
        this.board[7][0].addPiece(R);
        for (int i = 0; i <= 7; i++) {
            this.board[i][1].addPiece(new Pawn(ChessColour.WHITE));
            this.board[i][2].addPiece(null);
            this.board[i][3].addPiece(null);
            this.board[i][4].addPiece(null);
            this.board[i][5].addPiece(null);
            this.board[i][6].addPiece(new Pawn(ChessColour.BLACK));
        }
        this.board[0][7].addPiece(r);
        this.board[1][7].addPiece(n);
        this.board[2][7].addPiece(b);
        this.board[3][7].addPiece(q);
        this.board[4][7].addPiece(k);
        this.board[5][7].addPiece(b);
        this.board[6][7].addPiece(n);
        this.board[7][7].addPiece(r);
    }

    public void addTakenObserver(ListChangeListener listener) {
        whiteTakenPieces.addListener(listener);
        blackTakenPieces.addListener(listener);
    }

    public int getFullMove() {
        return fullMove;
    }

    public ChessColour getActiveColor() {
        return activeColour;
    }

    protected Square getSquare(Coordinate c) {
        return this.board[c.getColumnNumber()][c.getRowNumber()];
    }

    public boolean move(Coordinate src, Coordinate dest) {

        Square srcSquare = this.getSquare(src);
        if (!srcSquare.isOccupied()) {
            return false;
        }

        Piece p = srcSquare.getPiece();
        if (!p.getColour().equals(activeColour)) {
            return false;
        }

        if (p.isLegalMove(this, src, dest)) {
            Square destSquare = this.getSquare(dest);
            Piece takenPiece = destSquare.addPiece(p);

            if (p.getColour() != ChessColour.WHITE) {
                this.whiteTakenPieces.add(takenPiece);

            } else {
                this.blackTakenPieces.add(takenPiece);
            }

            srcSquare.deletePiece();
            activeColour = (activeColour == ChessColour.BLACK) ? ChessColour.WHITE : ChessColour.BLACK;
            if (activeColour == ChessColour.WHITE) {
                fullMove++;
            }
            // fullMove is incremented only *AFTER* BLACK has moved. 
            return true;

        } else {
            return false;
        }
    }

    public String toString() {
        String Board = new String();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j <= 7; j++) {

                if (this.board[j][i].getPiece() != null) {
                    Board = Board + this.board[j][i].getPiece().getShortName();
                } else {
                    Board = Board + " ";
                }

                Board = Board + "_";
            }
            Board = Board + "\n";
        }

        return Board;
    }

    public String toFEN() {
        String FEN = new String();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j <= 7; j++) {
                if (this.board[j][i].getPiece() != null) {
                    FEN = FEN + this.board[j][i].getPiece().getShortName();
                } else {
                    FEN = FEN + " ";
                }
            }
            FEN = FEN + "/";
        }
        if (this.activeColour == ChessColour.WHITE) {
            FEN = FEN + " w ";
        } else {
            FEN = FEN + " b ";
        }
        return FEN + this.fullMove;
    }

}
