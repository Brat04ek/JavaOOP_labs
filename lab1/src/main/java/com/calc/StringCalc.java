package com.calc;

public class StringCalc {
    int add(String numbers){
        int sum= 0;
        int number_1 = 0;
        System.out.println(numbers.split(","));
        for (String number : numbers.split(",")) {
            System.out.println(number);
            try {
                number_1 = Integer.parseInt(number.strip());
                sum = sum + number_1; 
            } catch (Exception e) {
                number_1 = 0;
            }
                           
        } 
        return sum;
    }
    public static void main(String[] args){
        StringCalc calc = new StringCalc();
        System.out.println(calc.add("1,-2"));
    }
}
