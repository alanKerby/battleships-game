package com.alan.battleships;

import java.util.Scanner;

public class Boats {

    int boatCount;
    int[] coOrds = new int[4];

    public Boats() {
        boatCount = 0;
    }

    enum boatsEnum {

        AIRCRAFT_CARRIER(0, 5, "Aircraft Carrier"),
        BATTLESHIP(1, 4, "Battleship"),
        SUBMARINE(2, 3, "Submarine"),
        CRUISER(3, 3, "Cruiser"),
        DESTROYER(4, 2, "Destroyer");

        int boatNumber;
        int boatLength;
        String boatName;

        boatsEnum(int boatNumber, int boatLength, String boatName) {
            this.boatNumber = boatNumber;
            this.boatLength = boatLength;
            this.boatName = boatName;
        }

        public static String getBoatName(int boatNumber) {
            for (boatsEnum value : values())
                if (value.boatNumber == boatNumber) {
                    return value.boatName;
                }
            return null;
        }

        public static Integer getBoatLength(int boatNumber) {
            for (boatsEnum value : values())
                if (value.boatNumber == boatNumber) {
                    return value.boatLength;
                }
            return null;
        }
    }

    public void inputBoats(int boatCount) {
        System.out.println("\n\nEnter the coordinates of the " + boatsEnum.getBoatName(boatCount) + " (" + boatsEnum.getBoatLength(boatCount) + " cells):");
    }

    public boolean boatChecking() {
        Scanner sc = new Scanner(System.in);
        try {
            String[] input = sc.nextLine().toUpperCase().split(" ");
            coOrds[0] = input[0].charAt(0) - 64;
            coOrds[1] = Integer.parseInt(input[0].substring(1));
            coOrds[2] = input[1].charAt(0) - 64;
            coOrds[3] = Integer.parseInt(input[1].substring(1));
            if (coOrds[0] == coOrds[2]) {
                if (coOrds[1] > coOrds[3]) {
                    int temp = coOrds[1];
                    coOrds[1] = coOrds[3];
                    coOrds[3] = temp;
                }
            }
            if (coOrds[1] == coOrds[3]) {
                if (coOrds[0] > coOrds[2]) {
                    int temp = coOrds[0];
                    coOrds[0] = coOrds[2];
                    coOrds[2] = temp;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println();
        }
        return false;
    }

    public boolean lengthCheck() {
        if (coOrds[0] != coOrds[2] && coOrds[1] != coOrds[3]) {
            System.out.println("\nError! Wrong ship location! Try again:");
            return false;
        }
        if (coOrds[0] == coOrds[2]) {
            if (Math.abs(coOrds[1] - coOrds[3]) != boatsEnum.getBoatLength(boatCount) - 1) {
                System.out.println("\nError! Wrong length of the " + boatsEnum.getBoatName(boatCount) + "! Try again:\n");
                return false;
            } else {
                return true;
            }
        }
        if (coOrds[1] == coOrds[3]) {
            if (Math.abs(coOrds[0] - coOrds[2]) != boatsEnum.getBoatLength(boatCount) - 1) {
                System.out.println("\nError! Wrong length of the " + boatsEnum.getBoatName(boatCount) + "! Try again:\n");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean notTooClose(char[][] board) {
        if (coOrds[0] == coOrds[2]) {
            for (int col = coOrds[1] - 1; col <= coOrds[3] + 1; col++) {
                for (int row = coOrds[0] - 1; row <= coOrds[2] + 1; row++) {
                    if (board[row][col] == 'O') {
                        tooClose();
                        return false;
                    }
                }
            }
        }
        if (coOrds[1] == coOrds[3]) {
            for (int col = coOrds[1] - 1; col <= coOrds[3] + 1; col++) {
                for (int row = coOrds[0] - 1; row <= coOrds[2] + 1; row++) {
                    if (board[row][col] == 'O') {
                        tooClose();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean placing(char[][] board) {
        if (coOrds[0] == coOrds[2]) {
            for (int col = coOrds[1]; col <= coOrds[3]; col++) {
                board[coOrds[0]][col] = 'O';
            }
        }

        if (coOrds[1] == coOrds[3]) {
            for (int row = coOrds[0]; row <= coOrds[2]; row++) {
                board[row][coOrds[1]] = 'O';
            }
        }
        boatCount++;
        return false;
    }

    static void tooClose() {
        System.out.println("\nError! You placed it too close to another one. Try again:");
    }
}