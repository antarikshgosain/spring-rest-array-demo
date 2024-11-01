package com.leetcode;

public class OneReplaceToMax {
    public static int maximumSwap(int num) {

        while(num>10){
            System.out.println("Step 1: "+num);
            num = num%10;
            System.out.println("Step 2: "+num);
            num = num/10;
            System.out.println("Step 3: "+num);
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println("Test Case 1 :" + maximumSwap(62828) );
    }
}
