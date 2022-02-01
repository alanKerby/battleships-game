package com.alan.battleships;

import java.util.Scanner;

public class Game{
        Scanner sc = new Scanner(System.in);

        public void play() {

                System.out.println("\n\n" +
                        "***********************************\n" +
                        "***** Welcome To Battleships! *****\n" +
                        "***********************************\n\n" +
                        ">>>>>>>> How To Place Boats <<<<<<<<\n" +
                        "Enter a space-separated pair of\n" +
                        "co-ordinates for the length of the\n" +
                        "boat e.g.'a1 a5' for the Aircraft\n" +
                        "Carrier (5 cells)\n\n" +
                        "Player 1, place your ships to the game field");

                Board p1 = new Board();
                Boats b1 = new Boats();
                p1.setBoard(p1.startBoard());
                p1.printBoard(true, p1.getBoard());
                placeBoats(p1, b1);

                pass();
                System.out.println("\nPlayer 2, place your ships to the game field");

                Board p2 = new Board();
                Boats b2 = new Boats();
                p2.setBoard(p2.startBoard());
                p1.printBoard(true, p2.getBoard());
                placeBoats(p2, b2);

                System.out.println("\n\n" +
                        ">>>>>>>> How To Shoot <<<<<<<<\n" +
                        "Enter a single co-ordinate to shoot e.g. 'a1'\n\n" +
                        "The game starts!");
                turns(p1, p2, b1, b2);
                System.out.println("\n\n" +
                        "***********************************\n" +
                        "***** You sank the last ship! *****\n" +
                        "**** You won! Congratulations! ****\n" +
                        "***********************************\n\n");
                System.exit(0);
        }

        public void placeBoats(Board p, Boats b){
                while (b.boatCount < 5) {
                        b.inputBoats(b.boatCount);
                        if (b.boatChecking()) {
                                if (b.lengthCheck()) {
                                        if (b.notTooClose(p.getBoard())) {
                                                b.placing(p.getBoard());
                                                p.printBoard(false, p.board);
                                        }
                                }
                        }
                }
        }

        public void turns(Board p1, Board p2, Boats b1, Boats b2) {
                while (b1.boatCount > 0 && b2.boatCount > 0) {
                        p1.doubleBoard(p1.board, p2.board);
                        playGame(Board.p1Turn ? p2 : p1, Board.p1Turn ? b2 :b1);
                }
        }

        public void playGame(Board p, Boats b) {
                System.out.printf("\n\nPlayer %d, it's your turn:\n", Board.p1Turn ? 1 : 2);
                p.validShot = false;
                while (!p.validShot)
                        if (p.shotCheck()) {
                               if(p.shoot()) {
                                       b.boatCount--;
                               }
                        }
                pass();
                Board.p1Turn = !Board.p1Turn;
        }

        public void pass() {
                System.out.println("\n\nPress 'Enter' and pass to the other player");
                sc.nextLine();
        }
}