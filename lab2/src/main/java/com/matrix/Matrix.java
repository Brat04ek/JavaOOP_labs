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
}
