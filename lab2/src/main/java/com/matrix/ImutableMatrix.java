package com.matrix;

public class ImutableMatrix extends Matrix {

    public ImutableMatrix(){
        super();
    }

    public ImutableMatrix(int rows, int columns){
        super(rows, columns);
    }

    public ImutableMatrix(Matrix matrix_old){
        super(matrix_old);
    }

    public ImutableMatrix(Matrix matrix_old, int[] start_elem, int[] end_elem){
        super(matrix_old, start_elem, end_elem);
    }

    @Override
    public void setValue(int rows, int columns, double valueToSet) {
        throw new IllegalStateException("Can't change imutable matrix");
    } 

    @Override
    public void setRow(int row_n, double... values){
        throw new IllegalStateException("Can't change imutable matrix");

    }

    @Override
    public void setColumn(int column_n, double... values){
        throw new IllegalStateException("Can't change imutable matrix");

    }

}