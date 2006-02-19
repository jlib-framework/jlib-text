/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    MatrixRowIterator.java
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

import java.util.ListIterator;

/**
 * Iterator over a single row of a Matrix.
 *
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
class MatrixRowIterator<Element>
extends MatrixIterator<Element>
implements ListIterator<Element> {

    /** no default constructor */
    private MatrixRowIterator() {
        super(null);
    }

    /**
     * Creates a new MatrixRowIterator.
     *
     * @param matrix
     *        Matrix to traverse
     * @param rowIndex
     *        integer specifying the index of the row to traverse
     */
    protected MatrixRowIterator(Matrix<Element> matrix, int rowIndex) {
        super(matrix);
        this.columnIndex = matrix.getMinColumnIndex() - 1;
        this.rowIndex = rowIndex;
    }

    /**
     * Creates a new MatrixRowIterator traversing the row partially.
     *
     * @param matrix
     *        Matrix to traverse
     * @param rowIndex
     *        integer specifying the index of the row to traverse
     * @param minColumnIndex
     *        integer specifying the minimum column index to traverse
     * @param maxColumnIndex
     *        integer specifying the maximum column index to traverse
     */
    protected MatrixRowIterator(Matrix<Element> matrix, int rowIndex, int minColumnIndex, int maxColumnIndex) {
        this(matrix, rowIndex);
        this.minColumnIndex = minColumnIndex;
        this.maxColumnIndex = maxColumnIndex;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementColumnIndex()
    @Override
    protected int getNextElementColumnIndex() {
        return columnIndex + 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementRowIndex()
    @Override
    protected int getNextElementRowIndex() {
        return rowIndex;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementColumnIndex()
    @Override
    protected int getPreviousElementColumnIndex() {
        return columnIndex - 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementRowIndex()
    @Override
    protected int getPreviousElementRowIndex() {
        return rowIndex;
    }

    // @see java.util.ListIterator#nextIndex()
    public int nextIndex() {
        return columnIndex + 1;
    }

    // @see java.util.ListIterator#previousIndex()
    public int previousIndex() {
        return columnIndex - 1;
    }
}
