package com.calc;

public class StringCalc {
    int add(String numbers){
        int sum = 0;
        int number_1 = 0;
        if (numbers.isEmpty() || numbers.isBlank()  ){
            return sum;
        }

        if (numbers.startsWith("//") == false && numbers.startsWith("/") ) {
            throw new IllegalArgumentException("Incorrect custom delimeter");
        }

        if (numbers.startsWith("//")){
            String[] array = numbers.split("\n", 2);
            numbers = array[1];
            
            String delimeter = array[0];
            delimeter = delimeter.substring(2);
            if (delimeter.contains("[") || delimeter.contains("]")){
                if ((delimeter.chars().filter(ch -> ch == '[').count()
                 != (delimeter.chars().filter(ch -> ch == ']').count())) ){

                        throw new IllegalArgumentException("Incorrect custom delimeter");
                    
                } else {
                    array = delimeter.split("]");
                    for (String delimeter_i : array) {

                        if (delimeter_i.contains("[") != true){
                            throw new IllegalArgumentException("Incorrect custom delimeter");
                        }

                        delimeter_i = delimeter_i.substring(1, delimeter_i.length());

                        if (delimeter_i.contains("[")){
                            throw new IllegalArgumentException("Incorrect custom delimeter");
                        }
                        numbers = numbers.replace(delimeter_i, ",");
                    }
                }
            
            } else {
                numbers = numbers.replace(delimeter, ",");
            }
        }

        numbers = numbers.replace("\n", ",");

        if (numbers.contains(",,")) {
            throw new IllegalArgumentException("Incorrect delimeter input");
        } else if (Character.isDigit(numbers.charAt(0)) == false
                && numbers.charAt(0) != ' '
                && numbers.charAt(0) != '-'){
            throw new IllegalArgumentException("Incorrect input");
        } else if (Character.isDigit(numbers.charAt(numbers.length() - 1)) == false){
            throw new IllegalArgumentException("Ends with delimeter");
        }

        String negative_numbers = "";
        for (String number : numbers.split(",")) {
            try {
                number_1 = Integer.parseInt(number.strip());                
            } catch (Exception e) {
                throw new IllegalArgumentException("Incorrect input: " + number);
            }
            if (number_1 < 0) {
                negative_numbers = negative_numbers + number + ", "; 
            }
            if (number_1 <= 1000) {
                sum = sum + number_1; 
            }
                           
        }
        
        if (negative_numbers != ""){
            negative_numbers =  negative_numbers.substring(0, negative_numbers.length()-2);
            throw new IllegalArgumentException("Passed negative number: " + negative_numbers);
        }  
        return sum;
    }
    public static void main(String[] args){
        StringCalc calc = new StringCalc();
        System.out.println(calc.add("//[****][%%]\n2****3%%7,6\n1"));
    }
}
