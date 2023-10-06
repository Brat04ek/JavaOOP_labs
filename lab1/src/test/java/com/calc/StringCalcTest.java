package com.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



public class StringCalcTest {

    private StringCalc calc;

    @BeforeEach
    public void setUp() throws Exception {
        calc = new StringCalc();
    }


    @Test
    public void EmptyString(){
        assertEquals(0, calc.add(""));
    }

    @Test
    public void BlankString(){
        assertEquals(0, calc.add(" "));
    }

    @Test
    public void SingleNum(){
        assertEquals(10, calc.add("10"));
    }

    @Test
    public void TwoNumWithComa(){
        assertEquals(60, calc.add(" 9,51"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"40,3,1,3", "25,15,7", "1,41,1,1,1,1,1"})
    public void ManyNumWithComa(String input){
        assertEquals(47, calc.add(input));
    }
    
    @Test
    public void EnterAsDelimetr(){
        assertEquals(73,calc.add("70\n2,1"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"1,,2,,", "1\n\n2", "1\n,2"})
    public void ThrowExceptionOnTwodelimetr(String input){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> calc.add(input));
        
    String expectedMessage = "Incorrect delimeter input";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\n1;2,3\n5", "//(\n1(2(3(5"})
    public void CustomDelimeter(String input){
        assertEquals(11, calc.add(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\n;1;2", ",1\n2", "\n1,2", "1+2"})
    public void IncorrectinputException(String input){
    Exception exception = assertThrows(IllegalArgumentException.class,
    () -> calc.add(input));
        
    String expectedMessage = "Incorrect input";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"//;\n1;2;", "1\n2,", "1,2\n"})
    public void EndWithDelimeterException(String input){
    Exception exception = assertThrows(IllegalArgumentException.class,
    () -> calc.add(input));
        
    String expectedMessage = "Ends with delimeter";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void IncorrectCustomDelimeter(){
    Exception exception = assertThrows(IllegalArgumentException.class,
    () -> calc.add("/;\n1;2"));
        
    String expectedMessage = "Incorrect custom delimeter";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void NegativeNumberException(){
    Exception exception = assertThrows(IllegalArgumentException.class,
    () -> calc.add("//n\n-1,-2"));
        
    String expectedMessage = "Passed negative number: -1, -2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void NumberAbove1000Ignored(){
        assertEquals(1999, calc.add("1000,999\n1001"));
    }

    @Test
    public void CustomDelimeterAnyLenght(){
        assertEquals(12, calc.add("//[***]\n2***3,6\n1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//[++][\n1,2", "//][h]\n1,2", "//[[]]\n1,2", "//[\n1,2", "//][[]\n1,2", "//]\n1,2"})
    public void CustomDelimeterInvalidInputException(String input){
    Exception exception = assertThrows(IllegalArgumentException.class,
    () -> calc.add(input));
        
    String expectedMessage = "Incorrect custom delimeter";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void MultipleCustomDelimetes(){
        assertEquals(19, calc.add("//[*][%]\n2*3%7,6\n1"));
    }

    @Test
    public void MultipleCustomDelimetes_1(){
        assertEquals(20, calc.add("//[*][%][&]\n2*3%7&1,6\n1"));
    }
    @Test
    public void MultipleCustomDelimetesAnyLenghts(){
        assertEquals(20, calc.add("//[***][%%][&]\n2***3%%7&1,6\n1"));
    }
    @Test
    public void MultipleCustomDelimetesAnyLenghts_1(){
        assertEquals(13, calc.add("//[******][%]\n2******3%1,6\n1"));
    }
}
 