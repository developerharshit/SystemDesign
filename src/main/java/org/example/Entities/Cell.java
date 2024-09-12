package org.example.Entities;

public class Cell {
    private Piece piece;
    int positionX;
    int positionY;

    public Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Piece getPiece() {
        if(piece == null) return Piece.EM;
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }
}
