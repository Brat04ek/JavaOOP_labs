package com.matrix;

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
        if(values.length != this.getNumRows()){
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
        if(values.length != this.getNumColumns()){
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
        CheckForNumInMatrix(column_n, 1);
        double[] column = new double[this.getNumColumns()];
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
}
