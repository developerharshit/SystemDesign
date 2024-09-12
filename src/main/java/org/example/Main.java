package org.example;

import org.example.Entities.Board;
import org.example.Entities.Piece;
import org.example.Entities.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // This is auto assignment
        var player1 = new Player("Harshit", Piece.X);
        var player2 = new Player("Muzammil", Piece.O);

        var board = new Board(3);
        var currentPlayer = player1;

        while (!board.isBoardFull() && !board.isWinner()){
            board.printBoard();
            System.out.println(currentPlayer.name + ": Enter coordinate X:");
            Scanner scanner = new Scanner(System.in);
            var x = scanner.nextInt();
            System.out.println(currentPlayer.name + ": Enter coordinate Y:");
            var y = scanner.nextInt();

            // switch player
            try {
                board.updateCellWithPiece(currentPlayer, x, y);
                if(currentPlayer == player1) currentPlayer = player2;
                else currentPlayer = player1;
            }
            catch (Exception e) {
                System.out.println("Ileagal move");
            }
        }
        var winner = board.getWinner();
        if(winner != null) {
            System.out.println(winner.name() + " won!!");
        }
        else {
            System.out.println("Match Draw!!");
        }
    }
}