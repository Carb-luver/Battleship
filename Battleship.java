package com.Megan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {

        System.out.println("Welcome to Battleship:)");
        int ships = 5;

        String player1[][] = getPlayerBoard();
        printArray(player1);
        whiteSpace();

        String player2[][] = getPlayerBoard();
        printArray(player2);
        whiteSpace();

        do {
            //player 1 turn
            hitMissBoard(player2);
            player2 = playerTurn(player2, "player 1", "player 2");
            ships = opponentShipsLeft(player2);
            whiteSpace();

            //player 2 turn
            hitMissBoard(player1);
            player1 = playerTurn(player1, "player 2", "player 1");
            ships = opponentShipsLeft(player1);
            whiteSpace();

        }while (ships!=0);

    }

    public static void hitMissBoard(String Opponent[][]){

        String[][] hitmiss = new String[Opponent.length][Opponent[0].length];

        for (int row = 0; (row < Opponent.length); row++){

            for (int col = 0; (col < Opponent[row].length); col++){
                if(row == 0 || col == 0) {
                    hitmiss[row][col] = Opponent[row][col];
                }else if (Opponent[row][col] == "X") {
                    hitmiss[row][col] = "X";
                }else if(Opponent[row][col] == "O"){
                    hitmiss[row][col]="O";

                }else{
                    hitmiss[row][col]="-";
                }
            }
        }
        printArray(hitmiss);
    }

    public static int opponentShipsLeft(String array[][]){

        int ships=0;

        for (int row = 1; (row < array.length); row++){

            for (int col = 1; (col < array[row].length); col++){
                    if (array[row][col] == "@") {
                        ships++;
                    }
            }
        }

        //System.out.println("Your opponent has " + ships + " ships left.");

        return ships;

    }

    public static int yourShipsLeft(String array[][]){

        int ships=0;

        for (int row = 1; (row < array.length); row++){

            for (int col = 1; (col < array[row].length); col++){
                if (array[row][col] == "@") {
                    ships++;
                }
            }
        }

        System.out.println("You have " + ships + " ships left.");

        return ships;

    }

    public static String[][] playerTurn(String Opponent[][], String playerturn, String opponent){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Take a guess at where your opponents ship is at (row col): ");

        int a = (scanner.nextInt());
        int b = (scanner.nextInt());

        for (int row = 1; (row < Opponent.length); row++){

            for (int col = 1; (col < Opponent[row].length); col++){

                if (a>Opponent.length-2 || b>Opponent[col].length-2 || a<0 || b<0){
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    a = (scanner.nextInt());
                    b = (scanner.nextInt());
                    row = row-1;
                }

                if(a+1 == row && b+1 == col) {
                    if (Opponent[row][col] == "@") {
                        Opponent[row][col] = "X";
                        System.out.println(playerturn + " hit " + opponent + "\'s ship.");

                    }else if(Opponent[row][col] == "-"){
                        Opponent[row][col] = "O";
                        System.out.println(playerturn + " missed :(");

                    }else{
                        System.out.println("You already fired on this spot. Choose different coordinates.");
                        a = (scanner.nextInt());
                        b = (scanner.nextInt());
                        row = row-1;
                    }
                }
            }
        }

        return Opponent;

    }

    public static String[][] getPlayerBoard(){

        String[][] playerboard= new String[6][6];

        int[][]coordinate = getCoordinate(playerboard);

        for (int row = 0; (row < playerboard.length); row++){
            for (int col = 0; (col < playerboard[row].length); col++){
                if(row == 0 && col == 0){
                    playerboard[row][col]=" ";
                }else if(row == 0){
                    playerboard[row][col]=Integer.toString(col-1);
                }else if(col == 0){
                    playerboard[row][col]=Integer.toString(row-1);
                }else{
                    playerboard[row][col] = "-";
                }
            }
        }

        for (int row = 1; (row < playerboard.length); row++){

            for (int col = 1; (col < playerboard[row].length); col++){

                for(int row1 = 0; row1<coordinate.length; row1++) {

                    int col1 = 0;
                    if (row == coordinate[row1][col1] && col == coordinate[row1][col1 + 1]) {
                        playerboard[row][col] = "@";

                    }
                }
            }
        }

        return playerboard;
    }

    public static int[][] getCoordinate(String[][] player){

        Scanner scanner = new Scanner(System.in);

        int coordinate[][] = new int[5][2];

        for(int row = 0; row<coordinate.length; row ++){
            for(int col = 0; col<1; col++) {
                    System.out.println("Please enter the coordinates for ship of length 1 (row column): ");

                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    scanner.reset();
                    coordinate[row][col] = a;
                    coordinate[row][col + 1] = b;

                    for (int row1 = 0; row1 < row; row1++) {
                        for (int col1 = 0; col1 <= col; col1 += 2) {
                            if (coordinate[row1][col1] == a && coordinate[row1][col1 + 1] == b) {
                                System.out.println("You already have a ship there. Choose different coordinates.");
                                row = row-1;
                            }
                        }
                    }

                    if(a>player.length || b>player[col].length || a<=0 || b<=0){
                        System.out.println("Invalid coordinates. Choose different coordinates.");
                        row = row-1;
                    }


            }
        }

        return coordinate;
    }

    public static void printArray(int Array[][]){

        for (int row = 0; (row < Array.length); row++){
            for (int col = 0; (col < Array[row].length); col=col+1){
                System.out.print(Array[row][col] + " ");
            }
            System.out.println();
        }

    }

    public static void printArray(String Array[][]){

        for (int row = 0; (row < Array.length); row++){
            for (int col = 0; (col < Array[row].length); col=col+1){
                System.out.print(Array[row][col] + " ");
            }
            System.out.println();
        }

    }

    public static void whiteSpace(){
        for(int i=0; i<100; i++){
            System.out.println();
        }
    }

}
