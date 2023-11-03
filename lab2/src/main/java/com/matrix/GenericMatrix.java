package com.matrix;

public class GenericMatrix<T> {
    protected ElementOfMatrix<T>[][] matrix;

    @SuppressWarnings("unchecked")
    public GenericMatrix(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException(String.format("Dimension can be 0x0 but not: %dx%d", rows, columns));
        }
        if (rows == 0 ^ columns == 0) {
            throw new IllegalArgumentException(String.format("Dimension can't have only one 0: %dx%d", rows, columns));
        }

        // Create a generic type array and check the type using reflection
        matrix = (ElementOfMatrix<T>[][]) new ElementOfMatrix[rows][columns]; 
    }

    public int getNumRows(){
        return matrix.length;
    }

    public int getNumColumns(){
        return matrix[0].length;
    }

    public ElementOfMatrix<T> getElement(int row, int column) {
        if (row < 0 || row > matrix.length || column < 0 || column > matrix[0].length) {
            throw new IllegalArgumentException("Invalid row or column index");
        }
        return matrix[row-1][column-1];
    }



    public void setValue(int row, int column, T value) {
        if (row < 0 || row > matrix.length || column < 0 || column > matrix[0].length) {
            throw new IllegalArgumentException("Invalid row or column index");
        }
        matrix[row - 1][column - 1] = new ElementOfMatrix<>(value);
    }

    @SuppressWarnings("unchecked")
    public void add(GenericMatrix<T> matrixToAdd) {
        if (matrixToAdd.getNumRows() != this.getNumRows() || matrixToAdd.getNumColumns() != this.getNumColumns()) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }
    
        for (int i = 1; i <= this.getNumRows(); i++) {
            for (int j = 1; j <= this.getNumColumns(); j++) {

                T currentValue = this.getElement(i, j).getValue();
                T valueToAdd = matrixToAdd.getElement(i, j).getValue();
                
                if (currentValue instanceof Number && valueToAdd instanceof Number) {
                    // If both values are numeric, perform numeric addition
                    double sum = ((Number) currentValue).doubleValue() + ((Number) valueToAdd).doubleValue();
                    this.getElement(i, j).setValue((T) Double.valueOf(sum));
                } else if (currentValue instanceof String || valueToAdd instanceof String) {
                    // If any value is a String, perform string concatenation
                    String result = currentValue.toString() + valueToAdd.toString();
                    this.getElement(i, j).setValue((T) result);
                } 
            }
        }
    }

    @Override
    public String toString() {
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
    
    // public static void main(String[] args) {
    //     GenericMatrix<String> stringMatrix = new GenericMatrix<>(2, 2);
    //     stringMatrix.setValue(1, 1, "Hello");
    //     stringMatrix.setValue(1, 2, "World");
    //     stringMatrix.setValue(2, 2, "Java");
    //     stringMatrix.setValue(2, 1, "nothing");
    //     stringMatrix.setValue(2, 2, "nothing");

    //     System.out.println("String Matrix:");
    //     System.out.println(stringMatrix);

    //     GenericMatrix<String> stringMatrix1 = new GenericMatrix<>(2, 2);
    //     stringMatrix1.setValue(1, 1, "Hello");
    //     stringMatrix1.setValue(1, 2, "World");
    //     stringMatrix1.setValue(2, 2, "Java");
    //     stringMatrix1.setValue(2, 1, "nothing");

    //     stringMatrix.add(stringMatrix1);
    //     System.out.println("String Matrix:");
    //     System.out.println(stringMatrix);

    //     GenericMatrix<Double> doubleMatrix = new GenericMatrix<>(2, 2);

    //     doubleMatrix.setValue(1, 1, 1.1);
    //     doubleMatrix.setValue(1, 2, 2.2);
    //     doubleMatrix.setValue(2, 2, 3.3);
    //     doubleMatrix.setValue(2, 1, 3.3);

    //     GenericMatrix<Double> doubleMatrix1 = new GenericMatrix<>(2, 2);

    //     doubleMatrix1.setValue(1, 1, 1.1);
    //     doubleMatrix1.setValue(1, 2, 2.2);
    //     doubleMatrix1.setValue(2, 2, 3.3);
    //     doubleMatrix1.setValue(2, 1, 3.3);

    //     doubleMatrix.add(doubleMatrix1);

    //     System.out.println("Double Matrix:");
    //     System.out.println(doubleMatrix);
    
    // }
}