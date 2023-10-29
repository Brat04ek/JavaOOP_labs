package com.matrix;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MatrixTest {
    private Matrix mat;
    
    @Test
    public void zeroMatrix(){
        mat = new Matrix();
        assertEquals("0x0", mat.getDimension());
    }

    @Test 
    public void simpleMatrixConstruct(){
        mat = new Matrix(3, 5);
        assertEquals("3x5", mat.getDimension());
    }

    @Test
    public void ThrowExceptionOnWrongDim(){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(-1, 2));
        
    String expectedMessage = "Dimension can 0x0 but not: -1x2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnWrongDim_1(){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(1, 0));
        
    String expectedMessage = "Dimension can't have only one 0: 1x0";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test 
    public void simpleCopyMatrixConstruct(){
        mat = new Matrix(3, 5);
        assertEquals("3x5", mat.getDimension());
        Matrix mat_1 = new Matrix(mat);
        assertEquals("3x5", mat_1.getDimension());
    }
    @Test
    public void specificCopyMatrixConstuct(){
        mat = new Matrix(3, 5);
        assertEquals("3x5", mat.getDimension());
        Matrix mat_1 = new Matrix(mat, new int[]{2, 1}, new int[]{2, 4});
        assertEquals("1x4", mat_1.getDimension());
    }
    

}
