package com.arena.regex;

public class SplitStringDemo {

    public static void main(String[] args) {
        String str = "He is a very very good boy, isn't he?";
        System.out.println(str);
        String[] arr =  str.split("[A-Za-z !,?._'@]+");
        System.out.println(arr.toString());
        for(String word : arr){
            System.out.println(word);
        }
    }
}
