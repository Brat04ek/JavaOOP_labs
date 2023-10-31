package com.matrix;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

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
    
    @Test
    public void ThrowExceptionOnCoping(){
    mat = new Matrix(3, 5);

    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(mat,new int[]{-1,2}, new int[]{2,2}));
        
    String expectedMessage = "Num of elem can't be negative";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnCoping_1(){
    mat = new Matrix(3, 5);

    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(mat,new int[]{3,2}, new int[]{2 ,2}));
        
    String expectedMessage = "Num of start element must be smaller";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnCoping_2(){
    mat = new Matrix(3, 5);

    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(mat,new int[]{4,2}, new int[]{2 ,2}));
        
    String expectedMessage = "Element num is bigger than matrix dim";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void setCertainValue(){
        mat = new Matrix(2, 2);
        mat.setValue(2, 2, 16);
        assertEquals(16, mat.getValue(2,2));
    }

    @Test
    public void ExceptionOnSetCertainValue(){
    mat = new Matrix(2, 2);

    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.setValue(2, 3, 2));
        
    String expectedMessage = "Element num is bigger than matrix dim";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ExceptionOnSetCertainValue_2(){
    mat = new Matrix(2, 2);
    mat.setValue(1, 1, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.getValue(1, 3));
        
    String expectedMessage = "Element num is bigger than matrix dim";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ExceptionOnSetCertainValue_3(){
    mat = new Matrix(2, 2);
    mat.setValue(1, 1, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.getValue(0, 2));
        
    String expectedMessage = "Element num is bigger than matrix dim";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void SetCertainRows(){
        mat = new Matrix(2, 2);
        mat.setRow(1, 2,3);

        Matrix mat_1 = mat.getRow(1);
        assertEquals(2, mat_1.getValue(1, 1));
        assertEquals(3, mat_1.getValue(1, 2));
    }

    @Test
    public void ExceptionOnSetCertainRows(){
    mat = new Matrix(2, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.setRow(1, 2,3,3));
        
    String expectedMessage = "Number of values and row in matrix dim must be equal: 3 != 2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ExceptionOnSetCertainRows_1(){
    mat = new Matrix(2, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.setRow(1, 2));
        
    String expectedMessage = "Number of values and row in matrix dim must be equal: 1 != 2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void GetRowsArray(){
        mat = new Matrix(2, 2);
        mat.setRow(1, 6,5);
        double[] expectedRows = {6,5};
        double[] actualRows = mat.getRowArray(1);
        assertTrue(Arrays.equals(expectedRows, actualRows));
    }

    @Test
    public void SetCertainColumn(){
        mat = new Matrix(2, 2);
        mat.setColumn(1, 2,3);

        Matrix mat_1 = mat.getColumn(1);
        assertEquals(2, mat_1.getValue(1, 1));
        assertEquals(3, mat_1.getValue(2, 1));
    }

    @Test
    public void GetColumnArray(){
        mat = new Matrix(2, 2);
        mat.setColumn(1, 6,5);
        double[] expectedColumn = {6,5};
        double[] actualColumn = mat.getColumnArray(1);
        assertTrue(Arrays.equals(expectedColumn, actualColumn));
    }
    
    @Test
    public void ExceptionOnSetCertainColumn_1(){
    mat = new Matrix(2, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.setColumn(1, 2));
        
    String expectedMessage = "Number of values and row in matrix dim must be equal: 1 != 2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void hashCode_test(){
        mat = new Matrix(2, 2);
        mat.setRow(1, 1,2);
        mat.setRow(2, 3,4);
        
        Matrix mat_new = new Matrix(mat);
        assertEquals(mat_new.hashCode(), mat.hashCode());
    }

    @Test
    public void equals_test(){
        mat = new Matrix(2, 2);
        mat.setRow(1, 1,2);
        mat.setRow(2, 3,4);
        
        Matrix mat_new = new Matrix(mat);

        assertTrue(mat.equals(mat_new));
    }

    @Test
    public void ImutableMatrix_Constuct(){
        mat = new Matrix(2, 2);
        mat.setRow(1, 1,2);
        mat.setRow(2, 4, 5);
        ImutableMatrix im_mat = new ImutableMatrix(mat);
        assertEquals(mat.getValue(1, 1), im_mat.getValue(1, 1));
    }

    @Test
    public void ExceptionImutableMatrix(){
        ImutableMatrix im_mat = new ImutableMatrix(1, 2);
        Exception exception = assertThrows(IllegalStateException.class, () -> im_mat.setValue(1, 2, 22));
        String expecterMessage = "Can't change imutable matrix"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));
    }

    @Test
    public void ExceptionImutableMatrix_1(){
        ImutableMatrix im_mat = new ImutableMatrix(1, 2);
        Exception exception = assertThrows(IllegalStateException.class, () -> im_mat.setRow(1, 2, 22));
        String expecterMessage = "Can't change imutable matrix"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));
    }

    @Test
    public void ExceptionImutableMatrix_2(){
        ImutableMatrix im_mat = new ImutableMatrix(1, 2);
        Exception exception = assertThrows(IllegalStateException.class, () -> im_mat.setColumn(1, 2, 22));
        String expecterMessage = "Can't change imutable matrix"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));
    }

    @Test
    public void ImutableMatrix_Constuct_1(){
        ImutableMatrix im_mat = new ImutableMatrix();
        assertEquals("0x0", im_mat.getDimension());
    }

    @Test
    public void ImutableMatrix_Constuct_2(){
        mat = new Matrix(2, 2);
        ImutableMatrix im_mat = new ImutableMatrix(mat, new int[]{1,1}, new int[]{2,1});
        assertEquals("2x1", im_mat.getDimension());
    }

    @Test
    public void equals_test_1(){
        mat = new Matrix(2, 2);
        assertTrue(mat.equals(mat));
    }

    @Test
    public void equals_test_2(){
        mat = new Matrix(2, 2);
        assertFalse(mat.equals(null));
    }
}
