package com.alan.battleships;

import java.util.Scanner;

public class Board {

    char[][] board;
    static boolean p1Turn = true;
    int[] coOrds = new int[4];
    boolean validShot;

    public Board() {
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char[][] startBoard() {
        char[][] board = new char[12][12];
        for (int r = 1; r < 11; r++) {
            for (int c = 1; c < 11; c++) {
                board[r][c] = '~';
            }
        }
        return board;
    }

    public void printBoard(boolean fog, char[][] board) {
        char firstRow = '\u0031';
        char firstCol = '\u0041';
        for (int r = 0; r < 11; r++) {
            System.out.println();
            for (int c = 0; c < 11; c++) {
                if (r == 0 && c == 0) {
                    System.out.print("  ");
                }
                if (r == 0 && c > 0) {
                    if (firstRow == ':') {
                        System.out.print("10");
                    } else {
                        System.out.print(firstRow + " ");
                        firstRow++;
                    }
                }
                if (r > 0 && c == 0) {
                    System.out.print(firstCol + " ");
                    firstCol++;
                }
                if (r > 0 && c > 0) {
                    if (!fog) {
                        System.out.print(board[r][c] + " ");
                    }
                    if (fog) {
                        if (board[r][c] == 'O') {
                            System.out.print("~ ");
                        }
                        if (board[r][c] == 'M') {
                            System.out.print("M ");
                        }
                        if (board[r][c] == '~') {
                            System.out.print("~ ");
                        }
                        if (board[r][c] == 'X') {
                            System.out.print("X ");
                        }
                    }
                }
            }
        }
    }

    public void doubleBoard(char[][] p1, char[][] p2) {
        System.out.print("\n****** Player 1 ******");
        printBoard(true, p1);
        System.out.print("\n****** Player 2 ******");
        printBoard(true, p2);
    }

    public boolean shotCheck() {
        Scanner sc = new Scanner(System.in);
        try {
            String input = sc.nextLine().toUpperCase();
            coOrds[0] = input.charAt(0) - 64;
            coOrds[1] = Integer.parseInt(input.substring(1));
            if(coOrds[0] >= 0 && coOrds[0] < 11 && coOrds[1] >= 0 && coOrds[1] < 11) {
                return true;
            } else {
                System.out.println("\nInput a letter A-J and a number 1-10\n");
                return false;
            }
        } catch (Exception e) {
            System.out.println("\nInput a letter A-J and a number 1-10\n");
        }
        return false;
    }

    public boolean shoot() {
        validShot = true;
        if (board[coOrds[0]][coOrds[1]] == 'X' | board[coOrds[0]][coOrds[1]] == 'M') {
            System.out.println("\nYou missed!");

        }
        if (board[coOrds[0]][coOrds[1]] == '~') {
            board[coOrds[0]][coOrds[1]] = 'M';
            System.out.println("\nYou missed!");

        }
        if (board[coOrds[0]][coOrds[1]] == 'O') {
            if (board[coOrds[0]][coOrds[1]] == 'O') {
                board[coOrds[0]][coOrds[1]] = 'X';
                int OCount = 0;
                for (int r = coOrds[0] - 1; r < coOrds[0] + 2; r++) {
                    for (int c = coOrds[1] - 1; c < coOrds[1] + 2; c++) {
                        if (board[r][c] == 'O') {
                            OCount++;
                        }
                    }
                }
                if (OCount == 0) {
                    System.out.println("\nYou sank a ship!");
                    return true;
                } else {
                    System.out.println("\nYou hit a ship!");
                }
            }
        }
        return false;
    }
}