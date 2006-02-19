/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    VerticalMatrixIterator.java
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
 * MatrixIterator traversing the elements of a Matrix vertically. That is, the traversal algorithm is as follows:
 *
 * <pre>{@literal
 * for each column
 *     for each row
 *         process element at (column, row)}
 * </pre>
 *
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
class VerticalMatrixIterator<Element>
extends MatrixIterator<Element> {

    /** no default constructor */
    private VerticalMatrixIterator() {
        super(null);
    }

    /**
     * Creates a new VerticalMatrixIterator for the specified Matrix.
     *
     * @param matrix
     *        Matrix to traverse
     */
    protected VerticalMatrixIterator(Matrix<Element> matrix) {
        super(matrix);
        columnIndex = matrix.getMinColumnIndex() - 1;
        rowIndex = matrix.getMinRowIndex() - 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementColumnIndex()
    @Override
    protected int getNextElementColumnIndex() {
        return rowIndex <= matrix.getMaxRowIndex() - 1 ? columnIndex : columnIndex + 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementRowIndex()
    @Override
    protected int getNextElementRowIndex() {
        return rowIndex <= matrix.getMaxRowIndex() - 1 ? rowIndex + 1 : matrix.getMinRowIndex();
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementColumnIndex()
    @Override
    protected int getPreviousElementColumnIndex() {
        return rowIndex >= matrix.getMinRowIndex() + 1 ? columnIndex : columnIndex - 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementRowIndex()
    @Override
    protected int getPreviousElementRowIndex() {
        return rowIndex >= matrix.getMinRowIndex() + 1 ? rowIndex - 1 : matrix.getMaxRowIndex();
    }

    // @see java.util.ListIterator#nextIndex()
    public int nextIndex() {
        return (columnIndex - 1) * matrix.getHeight() + rowIndex + 1;
    }

    // @see java.util.ListIterator#previousIndex()
    public int previousIndex() {
        return (columnIndex - 1) * matrix.getHeight() + rowIndex - 1;
    }
}
