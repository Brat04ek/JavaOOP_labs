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
        if (start_elem[0] > matrix_old.getNumRows() || end_elem[0] > matrix_old.getNumColumns()){
            throw new IllegalArgumentException(String.format("Element num is bigger than matrix dim"));
        }
        if (start_elem[0] > end_elem[0] || start_elem[1] > end_elem[1]){
            throw new IllegalArgumentException(String.format("Num of start element must be smaller"));
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

    public void add(double value){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = this.matrix[i][j] + value;
            }
        }
    }

    public void add(Matrix mat){
        if (this.matrix.length != mat.matrix.length || this.matrix[0].length != mat.matrix[0].length){
            throw new IllegalArgumentException(String.format("Matrix must have same dimension: %s, %s", 
                                                this.getDimension(), mat.getDimension()));
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = this.matrix[i][j] + mat.matrix[i][j];
            }
        }
    }

    public void addToRow(int row_n, double value){
        CheckForNumInMatrix(row_n, 1);
        for (int i = 0; i < matrix[0].length; i++) {
            this.matrix[row_n - 1][i] = this.matrix[row_n - 1][i] + value;
        }
    }
    
    public void addToColumn(int column_n, double value){
        CheckForNumInMatrix(column_n, 1);
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i][column_n - 1] = this.matrix[i][column_n - 1] + value;
        }
    }

    public void multiply(double scalar){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = this.matrix[i][j] * scalar;
            }
        }        
    }

    public void multiply(Matrix mat){
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
        this.matrix = matrix_new.matrix;
    }
    
    public void transpose(){
        Matrix matrix_T = new Matrix(getNumColumns(), getNumRows());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix_T.matrix[j][i] = this.matrix[i][j];
            }
        }
        this.matrix = matrix_T.matrix;
    }
    
    public void diagonalize(double... vector){
        this.matrix = new double[vector.length][vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.matrix[i][i] = vector[i]; 
        }
    }
}