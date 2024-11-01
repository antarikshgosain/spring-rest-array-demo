package com.hackerrank;

import java.util.List;

public class QueenAttack2 {
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // Write your code here
        int length = n;
        int obstacleClount = k;
        int rQ = n - r_q;
        int cQ = c_q - 1;
        int[][] board = new int [n][n];

        //placing queen(9) on board
        board[rQ][cQ] = 9;

        //placing obstacles(1) on board
        obstacles.forEach(coordinate -> {
            System.out.println("row: "+ coordinate.get(0) + " column: " + coordinate.get(1));
            int currRow = coordinate.get(0);
            int currCol = coordinate.get(1);
            int obsRow = length - currRow;
            int obsCol = currCol - 1 ;
            board[obsRow][obsCol] = 1;
            System.out.println("Putting obstacle at board " + obsRow + " " + obsCol );
            displayBoard(length, board);
        });

        //System.out.println("Obstacles: " + obstacles);
        displayBoard(n,board);
        return 0;
    }

    public static void main(String[] args) {
        queensAttack(8,3, 4, 4, List.of( List.of(5,5),List.of(4,2),List.of(2,3) ) );
    }

    public static void displayBoard(int length, int[][] board) {
        System.out.println("\nDisplaying Board ");
        if(length<2) System.out.println("Nothing to display");
        else {
            for(int i=0; i<length; i++){
                for(int j=0; j<length; j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }

}
