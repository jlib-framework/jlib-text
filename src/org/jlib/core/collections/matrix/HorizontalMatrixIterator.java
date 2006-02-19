/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    HorizontalMatrixIterator.java
 * Project: jlib.core
 *
 * Copyright (c) 2006 Igor Akkerman
 *
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.collections.matrix;

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
        columnIndex = matrix.getMinColumnIndex() - 1;
        rowIndex = matrix.getMinRowIndex() - 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementColumnIndex()
    @Override
    protected int getNextElementColumnIndex() {
        return columnIndex <= matrix.getMaxColumnIndex() - 1 ? columnIndex + 1 : matrix.getMinColumnIndex();
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementRowIndex()
    @Override
    protected int getNextElementRowIndex() {
        return columnIndex <= matrix.getMaxColumnIndex() - 1 ? rowIndex : rowIndex + 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementColumnIndex()
    @Override
    protected int getPreviousElementColumnIndex() {
        return columnIndex >= matrix.getMinColumnIndex() + 1 ? columnIndex - 1 : matrix.getMaxColumnIndex();
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementRowIndex()
    @Override
    protected int getPreviousElementRowIndex() {
        return columnIndex >= matrix.getMinColumnIndex() + 1 ? rowIndex : rowIndex - 1;
    }

    // @see java.util.ListIterator#nextIndex()
    public int nextIndex() {
        return (rowIndex - 1) * matrix.getWidth() + columnIndex + 1;
    }

    // @see java.util.ListIterator#previousIndex()
    public int previousIndex() {
        return (rowIndex - 1) * matrix.getWidth() + columnIndex - 1;
    }
}
