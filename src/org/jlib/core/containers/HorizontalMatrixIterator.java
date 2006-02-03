package org.jlib.core.containers;

/**
 * MatrixIterator traversing the elements of a Matrix horizontally. That is, the traversal algorithm is as follows:
 * 
 * <pre>{@literal
 * foreach row
 *     foreach column
 *         process element at (column, row)}
 * </pre>
 * 
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
class HorizontalMatrixIterator<Element>
extends MatrixIterator<Element> {

    /** no default constructor */
    private HorizontalMatrixIterator() {
        super(null);
    }

    /**
     * Creates a new HorizontalMatrixIterator for the specified Matrix.
     * 
     * @param matrix
     *        Matrix to traverse
     */
    protected HorizontalMatrixIterator(Matrix<Element> matrix) {
        super(matrix);
    }

    // @see org.jlib.core.containers.MatrixIterator#nextElement()
    @Override
    protected void gotoNextElement() {
        if (++ columnIndex > matrix.getMaxColumnIndex()) {
            rowIndex ++;
            columnIndex = 0;
        }
    }
}
