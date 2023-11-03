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

    @Test
    public void add_value(){
        mat = new Matrix(2, 2);
        mat.add(2);
        assertEquals(2, mat.getValue(1, 1));
    }

    @Test
    public void add_matrix(){
        mat = new Matrix(2, 2);
        mat.add(2);
        Matrix mat_1 = new Matrix(2, 2);
        mat_1.setRow(1, 2,3);
        mat_1.setRow(2, 5,6); 
        mat.add(mat_1);
        assertEquals(Arrays.hashCode(new double[]{4,5}), Arrays.hashCode(mat.getRowArray(1)));
        assertEquals(Arrays.hashCode(new double[]{7,8}), Arrays.hashCode(mat.getRowArray(2)));
    }

    @Test
    public void ExceptionAdd_matrix(){
        mat = new Matrix(2, 2);
        Matrix mat_1 = new Matrix(3, 3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mat.add(mat_1));
        String expecterMessage = "Matrix must have same dimension: 2x2, 3x3"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));        
    }

    @Test
    public void addToRowTest(){
        mat = new Matrix(2, 2);
        mat.add(2);
        mat.addToRow(1, 1);
        assertEquals(3, mat.getValue(1, 1));
    }

    @Test
    public void addToColumnTest(){
        mat = new Matrix(2, 2);
        mat.add(2);
        mat.addToColumn(2, 1);
        assertEquals(3, mat.getValue(1, 2));
    }

    @Test
    public void multiplyByScalar(){
        mat = new Matrix(2, 2);
        mat.setRow(1, 2, 4);
        mat.setRow(2, 6, 1);

        mat.multiply(3);
        assertEquals(Arrays.hashCode(new double[]{6,12}), Arrays.hashCode(mat.getRowArray(1)));
        assertEquals(Arrays.hashCode(new double[]{18,3}), Arrays.hashCode(mat.getRowArray(2)));
    }

    @Test
    public void ExceptionMultiply(){
        mat = new Matrix(2, 2);
        Matrix mat_1 = new Matrix(3, 3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mat.multiply(mat_1));
        String expecterMessage = "Column from matrix A and Rows from matrix B must be equal: 2x2, 3x3"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));        
    }

    @Test
    public void MultiplyMatrix(){
        mat = new Matrix(2, 3);
        mat.setRow(1, 1,2,3);
        mat.setRow(2, 6,5,6);

        Matrix mat_1 = new Matrix(3, 2);
        mat_1.setColumn(1, 2,1,2);
        mat_1.setColumn(2, 3,5,5);

        mat.multiply(mat_1);
        assertEquals(Arrays.hashCode(new double[]{10,28}), Arrays.hashCode(mat.getRowArray(1)));
        assertEquals(Arrays.hashCode(new double[]{29,73}), Arrays.hashCode(mat.getRowArray(2)));
    }

    @Test
    public void transposeMatrix(){
        mat = new Matrix(2, 3);
        mat.setRow(1, 2,5,2);
        mat.setRow(2, 4,5,6);
        mat.transpose();
        assertEquals("3x2", mat.getDimension());
        assertEquals(Arrays.hashCode(new double[]{2,5,2}), Arrays.hashCode(mat.getColumnArray(1)));
        assertEquals(Arrays.hashCode(new double[]{4,5,6}), Arrays.hashCode(mat.getColumnArray(2)));
    }

    @Test
    public void diagonalizeMatrix(){
        mat = new Matrix();
        mat.diagonalize(1,-2,5);
        assertEquals("3x3", mat.getDimension());
        assertEquals(1, mat.getValue(1, 1));
        assertEquals(0, mat.getValue(2, 1));
        assertEquals( -2, mat.getValue(2, 2));
        assertEquals(5, mat.getValue(3, 3));
    }
    
    @Test
    public void unitMatrix(){
        mat = new Matrix();
        mat.unit(3);
        assertEquals("3x3", mat.getDimension());
        assertEquals(Arrays.hashCode(new double[]{1,0,0}), Arrays.hashCode(mat.getColumnArray(1)));
        assertEquals(Arrays.hashCode(new double[]{0,1,0}), Arrays.hashCode(mat.getColumnArray(2)));
        assertEquals(Arrays.hashCode(new double[]{0,0,1}), Arrays.hashCode(mat.getColumnArray(3)));
    }

    @Test
    public void ExceptionUnitMatrix(){
        mat = new Matrix();

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mat.unit(-1));
        String expecterMessage = "Order of matrix can't be negative: -1"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));        
    }

    @Test
    public void setRandomRowMatrixTest(){
        mat = new Matrix();
        mat.setRandomRowMatrix(3);
        assertEquals("1x3", mat.getDimension());
    }

    @Test
    public void ExceptionsetRandomRowMatrix(){
        mat = new Matrix();

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mat.setRandomRowMatrix(-3));
        String expecterMessage = "Row lenght can't be negative: -3"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));        
    }

    @Test
    public void ExceptionsetRandomColumnMatrix(){
        mat = new Matrix();

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mat.setRandomColumnMatrix(-3));
        String expecterMessage = "Column lenght can't be negative: -3"; 
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expecterMessage));        
    }
    @Test
    public void setRandomColumnMatrixTest(){
    mat = new Matrix();
    mat.setRandomColumnMatrix(3);
    assertEquals("3x1", mat.getDimension());
    }

    @Test
    public void triangularShapeUpperMatrix(){
    mat = new Matrix(3, 2);
    mat.setColumn(1, 2, 1, 3);
    mat.setColumn(2, 1, 0, 5);

    mat.triangularShapeUpper();
    assertEquals(Arrays.hashCode(new double[]{2,0,0}), Arrays.hashCode(mat.getColumnArray(1)));
    assertEquals(Arrays.hashCode(new double[]{1,-0.5,0}), Arrays.hashCode(mat.getColumnArray(2)));
    }

    @Test
    public void triangularShapeUpperMatrix_1(){
    mat = new Matrix(3, 2);
    mat.setColumn(1, 0,2,3);
    mat.setColumn(2, 2,1,4);

    mat.triangularShapeUpper();
    assertEquals(Arrays.hashCode(new double[]{2,0,0}), Arrays.hashCode(mat.getColumnArray(1)));
    assertEquals(Arrays.hashCode(new double[]{1,2,0}), Arrays.hashCode(mat.getColumnArray(2)));
    }

    @Test
    public void triangularShapeUpperMatrix_2(){
    mat = new Matrix(2, 3);
    mat.setRow(1, 0,2,3);
    mat.setRow(2, 2,1,4);

    mat.triangularShapeUpper();
    assertEquals(Arrays.hashCode(new double[]{2,1,4}), Arrays.hashCode(mat.getRowArray(1)));
    assertEquals(Arrays.hashCode(new double[]{0,2,3}), Arrays.hashCode(mat.getRowArray(2)));
    }

    @Test
    public void triangularShapeUpperMatrix_3(){
    mat = new Matrix(3, 3);
    mat.setColumn(1, 2,1,2);
    mat.setColumn(2, 3,4,5);
    mat.setColumn(3, 0,0,0);

    mat.triangularShapeUpper();
    assertEquals(Arrays.hashCode(new double[]{2,3,0}), Arrays.hashCode(mat.getRowArray(1)));
    assertEquals(Arrays.hashCode(new double[]{0,2.5,0}), Arrays.hashCode(mat.getRowArray(2)));
    assertEquals(Arrays.hashCode(new double[]{0,0,0}), Arrays.hashCode(mat.getRowArray(3)));
    }

    @Test
    public void triangularShapeUpperMatrix_4(){
    mat = new Matrix(3, 3);
    mat.setColumn(1, 0,0,0);
    mat.setColumn(2, 2,1,2);
    mat.setColumn(3, 1,6,3);

    mat.triangularShapeUpper();
    assertEquals(Arrays.hashCode(new double[]{0,2,1}), Arrays.hashCode(mat.getRowArray(1)));
    assertEquals(Arrays.hashCode(new double[]{0,1,6}), Arrays.hashCode(mat.getRowArray(2)));
    assertEquals(Arrays.hashCode(new double[]{0,0,-9}), Arrays.hashCode(mat.getRowArray(3)));
    }

    @Test
    public void triangularShapeLowerMatrix(){
    mat = new Matrix(2, 3);
    mat.setRow(1, 2,3,4);
    mat.setRow(2, 1,2,1);

    mat.triangularShapeLower();
    assertEquals(Arrays.hashCode(new double[]{-2,-5,0}), Arrays.hashCode(mat.getRowArray(1)));
    assertEquals(Arrays.hashCode(new double[]{1,2,1}), Arrays.hashCode(mat.getRowArray(2)));
    }

    @Test
    public void triangularShapeLowerMatrix_1(){
    mat = new Matrix(3, 2);
    mat.setColumn(1, 2,3,4);
    mat.setColumn(2, 1,2,1);

    mat.triangularShapeLower();
    assertEquals(Arrays.hashCode(new double[]{0,-5,4}), Arrays.hashCode(mat.getColumnArray(1)));
    assertEquals(Arrays.hashCode(new double[]{0,0,1}), Arrays.hashCode(mat.getColumnArray(2)));
    }

    @Test
    public void triangularShapeLowerMatrix_2(){
    mat = new Matrix(3, 3);
    mat.setColumn(1, 3,4,6);
    mat.setColumn(2, 2,2,1);
    mat.setColumn(3, 1,0,0);

    mat.triangularShapeLower();
    assertEquals(Arrays.hashCode(new double[]{4,0,0}), Arrays.hashCode(mat.getRowArray(1)));
    assertEquals(Arrays.hashCode(new double[]{4,2,0}), Arrays.hashCode(mat.getRowArray(2)));
    assertEquals(Arrays.hashCode(new double[]{3,2,1}), Arrays.hashCode(mat.getRowArray(3)));
    }

    @Test
    public void triangularShapeLowerMatrix_3(){
    mat = new Matrix(3, 3);
    mat.setColumn(1, 2,3,4);
    mat.setColumn(2, 1,2,1);
    mat.setColumn(3, 0,0,0);

    mat.triangularShapeLower();
    assertEquals(Arrays.hashCode(new double[]{0.5,0,0}), Arrays.hashCode(mat.getRowArray(1)));
    assertEquals(Arrays.hashCode(new double[]{3,2,0}), Arrays.hashCode(mat.getRowArray(2)));
    assertEquals(Arrays.hashCode(new double[]{4,1,0}), Arrays.hashCode(mat.getRowArray(3)));
    }
    
    @Test
    public void GenericMatrixTest(){
        GenericMatrix<String> stringMatrix = new GenericMatrix<>(2, 2);
        stringMatrix.setValue(1, 1, "Hello");
        stringMatrix.setValue(1, 2, "World");
        stringMatrix.setValue(2, 1, "OOP");
        stringMatrix.setValue(2, 2, "Java");
        
        System.out.println(stringMatrix.toString());

        GenericMatrix<String> stringMatrix1 = new GenericMatrix<>(2, 2);
        stringMatrix1.setValue(1, 1, " World");
        stringMatrix1.setValue(1, 2, " Hello");     
        stringMatrix1.setValue(2, 1, " Java");
        stringMatrix1.setValue(2, 2, " OOP");

        stringMatrix.add(stringMatrix1);
        assertEquals("Hello World", stringMatrix.getElement(1, 1).toString());
        assertEquals("World Hello", stringMatrix.getElement(1, 2).toString());
        assertEquals("OOP Java", stringMatrix.getElement(2, 1).toString());
        assertEquals("Java OOP", stringMatrix.getElement(2, 2).toString());
    }

    @Test
    public void GenericMatrixTest_1(){
        GenericMatrix<Double> Matrix1 = new GenericMatrix<>(1,2);
        Matrix1.setValue(1, 1, 2.1);
        Matrix1.setValue(1, 2, 3.1);

        GenericMatrix<Double> Matrix2 = new GenericMatrix<>(1,2);
        Matrix2.setValue(1, 1, 2.1);
        Matrix2.setValue(1, 2, 3.1);

        Matrix1.add(Matrix2);

        assertEquals(4.2, Matrix1.getElement(1, 1).getValue());
        assertEquals(6.2, Matrix1.getElement(1, 2).getValue());
    }
    @Test
    public void ExceptionGenericMatrixTest(){
        GenericMatrix<Double> Matrix1 = new GenericMatrix<>(1,2);
        Matrix1.setValue(1, 2, 3.1);

        GenericMatrix<Double> Matrix2 = new GenericMatrix<>(1,2);
        Matrix2.setValue(1, 1, 2.1);
        Matrix2.setValue(1, 2, 3.1);

        assertThrows(NullPointerException.class,
         () ->Matrix1.add(Matrix2));

    }

    @Test
    public void ExceptionGenericMatrixTest_1(){
        GenericMatrix<Double> Matrix1 = new GenericMatrix<>(1,2);
        Matrix1.setValue(1, 2, 3.1);

        GenericMatrix<Double> Matrix2 = new GenericMatrix<>(2,2);
        Matrix2.setValue(1, 1, 2.1);
        Matrix2.setValue(1, 2, 3.1);

        assertThrows(IllegalArgumentException.class,
         () ->Matrix1.add(Matrix2));

    }

    @Test
    public void ExceptionGenericMatrixTest_2(){
        GenericMatrix<Double> Matrix1 = new GenericMatrix<>(1,2);
    
        assertThrows(IllegalArgumentException.class,
         () ->Matrix1.setValue(1, 3, 3.1));
        assertThrows(IllegalArgumentException.class,
         () ->Matrix1.getElement(1, 3));
    }

    @Test
    public void ExceptionGenericMatrixTest_3(){
        
    
        assertThrows(IllegalArgumentException.class,
         () -> new GenericMatrix<>(-1,2));
        assertThrows(IllegalArgumentException.class,
         () -> new GenericMatrix<>(0,2));
    }
}
