/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    MatrixColumnIterator.java
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
class MatrixColumnIterator<Element>
extends MatrixIterator<Element>
implements ListIterator<Element> {

    /** no default constructor */
    private MatrixColumnIterator() {
        super(null);
    }

    /**
     * Creates a new MatrixColumnIterator.
     *
     * @param matrix
     *        Matrix to traverse
     * @param columnIndex
     *        integer specifying the index of the column to traverse
     */
    protected MatrixColumnIterator(Matrix<Element> matrix, int columnIndex) {
        super(matrix);
        this.columnIndex = columnIndex;
        this.rowIndex = matrix.getMinRowIndex() - 1;
    }

    /**
     * Creates a new MatrixColumnIterator traversing the column partially.
     *
     * @param matrix
     *        Matrix to traverse
     * @param columnIndex
     *        integer specifying the index of the column to traverse
     * @param minRowIndex
     *        integer specifying the minimum row index to traverse
     * @param maxRowIndex
     *        integer specifying the maximum row index to traverse
     */
    protected MatrixColumnIterator(Matrix<Element> matrix, int columnIndex, int minRowIndex, int maxRowIndex) {
        this(matrix, columnIndex);
        this.minRowIndex = minRowIndex;
        this.maxRowIndex = maxRowIndex;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementColumnIndex()
    @Override
    protected int getNextElementColumnIndex() {
        return columnIndex;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getNextElementRowIndex()
    @Override
    protected int getNextElementRowIndex() {
        return rowIndex + 1;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementColumnIndex()
    @Override
    protected int getPreviousElementColumnIndex() {
        return columnIndex;
    }

    // @see org.jlib.core.collections.matrix.MatrixIterator#getPreviousElementRowIndex()
    @Override
    protected int getPreviousElementRowIndex() {
        return rowIndex - 1;
    }

    // @see java.util.ListIterator#nextIndex()
    public int nextIndex() {
        return rowIndex + 1;
    }

    // @see java.util.ListIterator#previousIndex()
    public int previousIndex() {
        return rowIndex - 1;
    }
}
