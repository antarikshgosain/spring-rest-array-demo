package com.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HiddenText {
    static int max = 0;
    public static void main(String[] args) {
        String filePath = "src/main/java/com/assignment/input/coding_qual_input.txt";
        Map<Integer,String> map = getMapFromInputFile(filePath);
        printPyramidRight(map, max);
    }

    private static void printPyramidRight(Map<Integer, String> map, int max) {
        int index = 0;
        int increment = 0;
        while ( index + increment <= max+1 ){
            if(null != map.get(index)){
                System.out.println(index+""+map.get(index));
            }else{
                System.out.println("Null found at "+index); //logging purpose
            }
            increment++;
            index += increment;
        }
    }

    public static Map<Integer,String> getMapFromInputFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            Map<Integer,String> map = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); //Split each line based on whitespace as delimiter
                int intValue = Integer.parseInt(parts[0]);
                String stringValue = parts[1];
                if(intValue > max){
                    max = intValue;
                }
                map.put(intValue, stringValue);
            }
            System.out.println("Max Value: "+ max);
            //System.out.println(map.toString()); //loggin purpose
            return map;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
