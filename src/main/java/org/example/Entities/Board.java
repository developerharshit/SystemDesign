package org.example.Entities;

public class Board {
    private final Cell[][] cells;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell(i,j);
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j].getPiece() + " ");
            }
            System.out.println();
        }
    }

    public void updateCellWithPiece(Player player, int positionX, int positionY) {
        if(!isValidCoordinate(positionX, positionY)){
            // TODO: create a new exception and handle it in validate coordinates
            throw new RuntimeException();
        }
        Cell cell = cells[positionX][positionY];
        cell.setPiece(player.getPiece());
    }

    public boolean isBoardFull() {
        var count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(!cells[i][j].isEmpty())
                    count++;
            }
        }
        return count == size * size;
    }

    public Piece getWinner() {
        // Check rows
        for (int i = 0; i < size; i++) {
            boolean rowWon = true;
            Piece piece = cells[i][0].getPiece();
            if (piece == Piece.EM) continue; // If the first cell is null, no need to check further
            for (int j = 1; j < size; j++) {
                if (cells[i][j].getPiece() != piece) {
                    rowWon = false;
                    break;
                }
            }
            if (rowWon) return piece;
        }

        // Check columns
        for (int i = 0; i < size; i++) {
            boolean columnWon = true;
            Piece piece = cells[0][i].getPiece();
            if (piece == Piece.EM) continue; // If the first cell is null, no need to check further
            for (int j = 1; j < size; j++) {
                if (cells[j][i].getPiece() != piece) {
                    columnWon = false;
                    break;
                }
            }
            if (columnWon) return piece;
        }

        // Check first diagonal (top-left to bottom-right)
        boolean diagonal1Won = true;
        Piece piece = cells[0][0].getPiece();
        if (piece != Piece.EM) {
            for (int i = 1; i < size; i++) {
                if (cells[i][i].getPiece() != piece) {
                    diagonal1Won = false;
                    break;
                }
            }
            if (diagonal1Won) return piece;
        }

        // Check second diagonal (top-right to bottom-left)
        boolean diagonal2Won = true;
        piece = cells[0][size - 1].getPiece();
        if (piece != Piece.EM) {
            for (int i = 1; i < size; i++) {
                if (cells[i][size - i - 1].getPiece() != piece) {
                    diagonal2Won = false;
                    break;
                }
            }
            if (diagonal2Won) return piece;
        }

        // No winner
        return null;
    }


    public boolean isWinner(){

        for(int i=0;i<size;i++){
            boolean areRowWon = true;
            for (int j=1;j<size;j++){
                if(cells[i][j].getPiece() != cells[i][j-1].getPiece() || cells[i][j].isEmpty())areRowWon=false;
            }
            if(areRowWon)return true;
        }

        for(int i=0;i<size;i++){
            boolean areColumnsWon = true;
            for(int j=1;j<size;j++){
                if(cells[j][i].isEmpty() || cells[j][i].getPiece()!=cells[j-1][i].getPiece())areColumnsWon=false;
            }
            if(areColumnsWon)return true;
        }

        boolean areDiagonal1Won = true;
        boolean areDiagonal2Won = true;

        for(int i=1;i<size;i++){
            if(cells[i][i].getPiece() != cells[i-1][i-1].getPiece() || cells[i][i].isEmpty())areDiagonal1Won=false;
            if(cells[i][size-i-1].getPiece() != cells[i-1][size-i].getPiece() || cells[i][size-1-i].isEmpty())areDiagonal2Won=false;
        }

        if(areDiagonal1Won)return true;
        if(areDiagonal2Won)return true;

        return false;
    }

    private boolean isValidCoordinate(int positionX, int positionY) {
        if(positionX < 0 || positionX >= size) return false;
        if(positionY < 0 || positionY >= size) return false;
        if(!cells[positionX][positionY].isEmpty()) return false;

        return true;
    }
}
