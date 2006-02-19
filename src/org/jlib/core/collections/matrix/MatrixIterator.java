/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    MatrixIterator.java
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
import java.util.NoSuchElementException;

/**
 * Iterator over the elements of a Matrix.
 *
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
abstract class MatrixIterator<Element>
implements ListIterator<Element> {

    /** matrix containing the elements */
    protected Matrix<Element> matrix;

    /** column index of the current Element */
    protected int columnIndex;

    /** row index of the current Element */
    protected int rowIndex;

    /** minimum column index of the traversal */
    protected int minColumnIndex;

    /** minimum row index of the traveral */
    protected int minRowIndex;

    /** maximum column index of the traversal */
    protected int maxColumnIndex;

    /** maximum row index of the traveral */
    protected int maxRowIndex;

    /** no default constructor */
    private MatrixIterator() {};

    /**
     * Creates a new MatrixIterator for the specified Matrix.
     *
     * @param matrix
     *        Matrix to traverse
     */
    protected MatrixIterator(Matrix<Element> matrix) {
        super();
        this.matrix = matrix;
        minColumnIndex = matrix.getMinColumnIndex();
        minRowIndex = matrix.getMinRowIndex();
        maxColumnIndex = matrix.getMaxColumnIndex();
        maxRowIndex = matrix.getMaxRowIndex();
    }

    /**
     * Returns the column index of the next Element.
     *
     * @return integer specifying the column index of the next Element
     */
    protected abstract int getNextElementColumnIndex();

    /**
     * Returns the row index of the next Element.
     *
     * @return integer specifying the row index of the next Element
     */
    protected abstract int getNextElementRowIndex();

    /**
     * Returns the column index of the previous Element.
     *
     * @return integer specifying the column index of the previous Element
     */
    protected abstract int getPreviousElementColumnIndex();

    /**
     * Returns the row index of the previous Element.
     *
     * @return integer specifying the row index of the previous Element
     */
    protected abstract int getPreviousElementRowIndex();

    // @see java.util.Iterator#hasNext()
    public boolean hasNext() {
        return getNextElementColumnIndex() <= maxColumnIndex && getNextElementRowIndex() <= maxRowIndex;
    }

    // @see java.util.ListIterator#hasPrevious()
    public boolean hasPrevious() {
        return getPreviousElementColumnIndex() >= minColumnIndex && getPreviousElementRowIndex() >= minRowIndex;
    }

    // @see java.util.Iterator#next()
    public Element next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        columnIndex = getNextElementColumnIndex();
        rowIndex = getNextElementRowIndex();

        return matrix.get(columnIndex, rowIndex);
    }

    // @see java.util.ListIterator#previous()
    public Element previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }

        columnIndex = getPreviousElementColumnIndex();
        rowIndex = getPreviousElementRowIndex();

        return matrix.get(columnIndex, rowIndex);
    }

    // @see java.util.ListIterator#set(java.lang.Object)
    public void set(Element element) {
        matrix.set(columnIndex, rowIndex, element);
    }

    /**
     * Always throws a {@code UnsupportedOperationException} since Elements cannot be removed from a
     * Matrix.
     *
     * @throws UnsupportedOperationException
     *         always
     */
    public void remove()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Always throws a {@code UnsupportedOperationException} since Elements cannot be added to a
     * Matrix.
     *
     * @param element
     *        any Element
     * @throws UnsupportedOperationException
     *         always
     */
    public void add(Element element) {
        throw new UnsupportedOperationException();
    }
}
