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

    @Override
    public Matrix add(Matrix mat){
        return new Matrix(this).add(mat);
    }

  
    @Override
    public Matrix add(double scalar){  
        return new Matrix(this).add(scalar);
    }

    @Override
    public Matrix multiply(Matrix mat){
        return new Matrix(this).multiply(mat);
    }
    @Override
    public Matrix multiply(double scalar){
        return new Matrix(this).multiply(scalar);
    }

    @Override
    public Matrix transpose(){
        return new Matrix(this).transpose();
    }
    

    @Override
    public Matrix addToColumn(int column_n, double value){
        return new Matrix(this).addToColumn(column_n, value);
    }
    @Override
    public Matrix addToRow(int row_n, double value){
        return new Matrix(this).addToRow(row_n, value);
    }

    @Override
    public Matrix triangularShapeUpper(){
        return new Matrix(this).triangularShapeUpper();
    }

    @Override
    public Matrix triangularShapeLower(){
        return new Matrix(this).triangularShapeLower();
    }

    @Override
    public void setRandomValues(int min, int max){
        throw new IllegalStateException("Can't change imutable matrix");
    }
}