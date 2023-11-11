package com.matrix;

import java.util.Arrays;

public class Matrix {

    private double[][] matrix;

    public Matrix(){
        this(0,0);        
    }

    public Matrix(int rows, int columns){
        if(rows < 0 || columns < 0){
            throw new IllegalArgumentException(String.format("Dimension can 0x0 but not: %dx%d",rows, columns));
        }
        if(rows == 0 ^ columns == 0){
            throw new IllegalArgumentException(String.format("Dimension can't have only one 0: %dx%d",rows, columns));
        }   
        this.matrix = new double[rows][columns];
    }

    public Matrix(Matrix matrix_old){
        this(matrix_old, new int[]{1,1}, new int[]{matrix_old.getNumRows(),matrix_old.getNumColumns()});
    }

    public Matrix(Matrix matrix_old, int[] start_elem, int[] end_elem){
        if (start_elem[0] <= 0 || start_elem[1] <= 0 || end_elem[0] <= 0 || end_elem[1] <= 0) {
            throw new IllegalArgumentException(String.format("Num of elem can't be negative"));
        }
        if (start_elem[0] > end_elem[0] || start_elem[1] > end_elem[1]){
            throw new IllegalArgumentException(String.format("Num of start element must be smaller"));
        }
        if (end_elem[0] > matrix_old.getNumRows() || end_elem[1] > matrix_old.getNumColumns()){
            throw new IllegalArgumentException(String.format("Element num is bigger than matrix dim"));
        }
        
        int rows = end_elem[0] - start_elem[0] + 1;
        int columns = end_elem[1] - start_elem[1] + 1;

        this.matrix = new double[rows][columns];
        int i_i = 0;
        int j_j = 0;
        for (int i = start_elem[0] - 1; i <= end_elem[0] - 1; i++, i_i++) {
            j_j = 0;
            for(int j = start_elem[1] - 1; j <= end_elem[1] - 1; j++, j_j++){
                this.matrix[i_i][j_j] = matrix_old.matrix[i][j];
            }
        }

    }

    public void setValue(int rows, int columns, double valueToSet){
        this.CheckForNumInMatrix(rows, columns);
        this.matrix[rows - 1][columns - 1] = valueToSet;
    }

    public double getValue(int rows, int columns){
        this.CheckForNumInMatrix(rows, columns);
        return this.matrix[rows - 1][columns -1];
    }

    public void setRow(int row_n, double... values){
        if(values.length != this.getNumColumns()){
            throw new IllegalArgumentException(
                String.format("Number of values and row in matrix dim must be equal: %d != %d", values.length, this.getNumRows()));
        }
        this.CheckForNumInMatrix(row_n, values.length);
        for (int i = 0; i < values.length; i++){
            this.matrix[row_n - 1][i] = values[i];
        }
    }

    public Matrix getRow(int row_n){
        CheckForNumInMatrix(row_n, 1);
        Matrix row = new Matrix(this, new int[]{row_n, 1}, new int[]{row_n, this.getNumColumns()});
        return row;
    }

    public double[] getRowArray(int row_n){
        CheckForNumInMatrix(row_n, 1);
        double[] row = new double[this.getNumColumns()];
        for (int i = 0; i < row.length; i++) {
            row[i] = this.matrix[row_n - 1][i];
        }
        return row;
    }

    public void setColumn(int column_n, double... values){
        if(values.length != this.getNumRows()){
            throw new IllegalArgumentException(
                String.format("Number of values and row in matrix dim must be equal: %d != %d", values.length, this.getNumColumns()));
        }
        CheckForNumInMatrix(1, column_n);
        for (int i = 0; i < values.length; i++){
            this.matrix[i][column_n - 1] = values[i];
        }
    }

    public Matrix getColumn(int column_n){
        CheckForNumInMatrix(column_n, 1);
        Matrix column = new Matrix(this, new int[]{1, column_n}, new int[]{this.getNumRows(), column_n});
        return column;
    }

    public double[] getColumnArray(int column_n){
        CheckForNumInMatrix(1,column_n);
        double[] column = new double[this.getNumRows()];
        for (int i = 0; i < column.length; i++) {
            column[i] = this.matrix[i][column_n - 1];
        }
        return column;
    }

    public int getNumRows(){
        return matrix.length;
    }

    public int getNumColumns(){
        if (getNumRows() == 0){
            return 0;
        }
        return matrix[0].length;

    }
    public String getDimension(){
        return String.valueOf(this.getNumRows()) + "x" + String.valueOf(this.getNumColumns()); 
    }

    public void CheckForNumInMatrix(int rows, int columns){
        if (rows <= 0 || rows > this.getNumRows()
                || columns <= 0 || columns > this.getNumColumns()){
                    throw new IllegalArgumentException("Element num is bigger than matrix dim");
                }
    }
    @Override
    public int hashCode(){
        return Arrays.deepHashCode(this.matrix);
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(o == null || o.getClass() != this.getClass()){
            return false;
        }
        Matrix mat_new = (Matrix) o;
        return Arrays.deepEquals(this.matrix, mat_new.matrix);
    }

    public Matrix add(double value){
        Matrix newMatrix = new Matrix(this);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix.matrix[i][j] = this.matrix[i][j] + value;
            }
        }
        return newMatrix;    
    }

    public Matrix add(Matrix mat){
        
        if (this.matrix.length != mat.matrix.length || this.matrix[0].length != mat.matrix[0].length){
            throw new IllegalArgumentException(String.format("Matrix must have same dimension: %s, %s", 
                                                this.getDimension(), mat.getDimension()));
        }
        Matrix newMatrix = new Matrix(this); 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix.matrix[i][j] = this.matrix[i][j] + mat.matrix[i][j];
            }
        }
        return newMatrix;
    }

    public Matrix addToRow(int row_n, double value){
        CheckForNumInMatrix(row_n, 1);
        Matrix newMatrix = new Matrix(this); 
        for (int i = 0; i < matrix[0].length; i++) {
            newMatrix.matrix[row_n - 1][i] = this.matrix[row_n - 1][i] + value;
        }
        return newMatrix;
    }
    
    public Matrix addToColumn(int column_n, double value){
        Matrix newMatrix = new Matrix(this); 
        CheckForNumInMatrix(column_n, 1);
        for (int i = 0; i < matrix.length; i++) {
            newMatrix.matrix[i][column_n - 1] = this.matrix[i][column_n - 1] + value;
        }
        return newMatrix;
    }

    public Matrix multiply(double scalar){
        Matrix newMatrix = new Matrix(this);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix.matrix[i][j] = this.matrix[i][j] * scalar;
            }
        }
        return newMatrix;        
    }

    public Matrix multiply(Matrix mat){
        if(this.getNumColumns() != mat.getNumRows()){
            throw new IllegalArgumentException(String.format
                ("Column from matrix A and Rows from matrix B must be equal: %s, %s",this.getDimension() ,mat.getDimension()));
        }
        Matrix matrix_new = new Matrix(this.getNumRows(), mat.getNumColumns());
        for (int i = 0; i < matrix_new.matrix.length; i++) {
            for (int j = 0; j < matrix_new.matrix[0].length; j++) {
                // matrix C
                for (int k = 0; k < this.getNumColumns(); k++) {
                    matrix_new.matrix[i][j] = matrix_new.matrix[i][j] + this.matrix[i][k] * mat.matrix[k][j];
                }
            }
        }
        return matrix_new;
    }
    
    public Matrix transpose(){
        Matrix matrix_T = new Matrix(getNumColumns(), getNumRows());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix_T.matrix[j][i] = this.matrix[i][j];
            }
        }
        return matrix_T;
    }

    public static Matrix diagonalize(double... vector){
        Matrix newMatrix = new Matrix(vector.length, vector.length);
        for (int i = 0; i < vector.length; i++) {
            newMatrix.matrix[i][i] = vector[i]; 
        }
        return newMatrix;
    }
    public static Matrix unit(int order){
        if(order<0){
            throw new IllegalArgumentException(String.format("Order of matrix can't be negative: %d", order));
        }

        Matrix newMatrix = new Matrix(order, order);
        for (int i = 0; i < newMatrix.matrix.length; i++) {
            newMatrix.matrix[i][i] = 1;
        }
        return newMatrix;
    }

    

    public static Matrix createRandomRowMatrix(int row_lenght, double min, double max){
        if(row_lenght < 0){
            throw new IllegalArgumentException(String.format("Row lenght can't be negative: %d", row_lenght));
        }
        if(min> max){
            throw new IllegalArgumentException("Minimum must be smaller than Maximum");
        }
        Matrix matrix1 = new Matrix(1,row_lenght);
        for (int i = 0; i < matrix1.matrix[0].length; i++) {
            matrix1.matrix[0][i] = Math.random()*(max - min) + min;
        }
        return matrix1;
    }

    public static Matrix createRandomRowMatrix(int row_lenght){
        return createRandomRowMatrix(row_lenght, -10, 10);
    }


    public static Matrix createRandomColumnMatrix(int column_lenght, double min, double max){
        if(column_lenght < 0){
            throw new IllegalArgumentException(String.format("Column lenght can't be negative: %d", column_lenght));
        }
        if(min> max){
            throw new IllegalArgumentException("Minimum must be smaller than Maximum");
        }
        Matrix matrix1 = new Matrix(column_lenght, 1);
        for (int i = 0; i < matrix1.matrix.length; i++) {
            matrix1.matrix[i][0] = Math.random()*(max - min) + min;
        }
        return matrix1;
    }

    public static Matrix createRandomColumnMatrix(int column_lenght){
        return createRandomColumnMatrix(column_lenght, -10, 10);
    }

    public Matrix triangularShapeUpper(){
        int LowestLenght;
        if (this.getNumColumns() < this.getNumRows()) {
            LowestLenght = this.getNumColumns();
        } else {
            LowestLenght = this.getNumRows();
        }
        Matrix newMatrix = new Matrix(this);
        for (int i = 0; i < LowestLenght; i++) {
            boolean matrix_swaped = false;
            for (int j = i; j < this.getNumRows(); j++) {
                if (matrix_swaped) {
                    
                } else {
                    if (newMatrix.matrix[j][i] != 0) {
                        double[] temp = newMatrix.matrix[i];
                        newMatrix.matrix[i] = newMatrix.matrix[j];
                        newMatrix.matrix[j] = temp;
                        matrix_swaped = true;
                    }
                }
            }
            double first_d = newMatrix.matrix[i][i];
            for (int j = 1 + i; j < newMatrix.getNumRows(); j++) {
                double other_d = newMatrix.matrix[j][i];
                if (first_d != 0) {
                    double alpha = other_d / first_d;
                    for (int k = 0; k < newMatrix.getNumColumns(); k++) {
                        newMatrix.matrix[j][k] = newMatrix.matrix[j][k] - alpha*newMatrix.matrix[i][k];
                    }
                } 
            }
        }
        return newMatrix;       
    }

    public Matrix triangularShapeLower(){
        int LastDigitOfTriangul;
        if (this.getNumColumns() < this.getNumRows()) {
            LastDigitOfTriangul = 0;
        } else {
            LastDigitOfTriangul = this.getNumColumns() - this.getNumRows();
        }
        Matrix newMatrix = new Matrix(this);
        int count_backing = 0;
        for (int i = newMatrix.getNumColumns() - 1; i > LastDigitOfTriangul - 1; i--) {
            count_backing++; 
            int row_pivot = newMatrix.getNumRows() - count_backing;

            Boolean matrix_swaped = false;
            for (int j = row_pivot; j > -1; j--) {
                if(matrix_swaped){

                } else {
                    if (newMatrix.matrix[j][i] != 0) {
                        double[] temp = newMatrix.matrix[row_pivot];
                        newMatrix.matrix[row_pivot] = newMatrix.matrix[j];
                        newMatrix.matrix[j] = temp;
                        matrix_swaped = true;
                    }
                }
                
            }
            
            double first_d = newMatrix.matrix[row_pivot][i];
            
            for (int j = row_pivot - 1; j > -1 ; j--) {
                double other_d = newMatrix.matrix[j][i];
                if(first_d != 0) {
                    double alpha = other_d / first_d;
                    for (int k = 0; k < newMatrix.getNumColumns(); k++) {
                        newMatrix.matrix[j][k] = newMatrix.matrix[j][k] - alpha*newMatrix.matrix[row_pivot][k];
                    }
                }
            }
        }
        return newMatrix;     
    }


    public void setRandomValues(int min, int max){
        if(min > max){
            throw new IllegalArgumentException("Minimum must be smaller than Maximum");
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = Math.random()*(max - min) + min;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            result.append("| ");
            for (int column = 0; column < matrix[0].length; column++) {
                result.append(matrix[row][column]).append(" | ");
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Matrix a1 = new Matrix(2, 3);
        a1.setRandomValues(1, 3);
        System.out.println(a1);
        ImutableMatrix a2 = new ImutableMatrix(a1);

        System.out.println(a2);
        
        a1.setValue(1, 1, -100);
        System.out.println(a1);


        ImutableMatrix a3 = new ImutableMatrix(a2);
        System.out.println(a3);

        ImutableMatrix a4 = new ImutableMatrix(a2.add(a3));
        System.out.println(a4);
    }
}
