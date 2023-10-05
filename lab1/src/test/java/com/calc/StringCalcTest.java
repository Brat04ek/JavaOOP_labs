package com.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    
}
 