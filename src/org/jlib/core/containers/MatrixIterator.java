/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    MatrixIterator.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 by Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.containers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator over a Matrix.
 * 
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
abstract class MatrixIterator<Element>
implements Iterator<Element> {

    /** matrix containing the elements */
    protected Matrix<Element> matrix;

    /** column index */
    protected int columnIndex;

    /** row index */
    protected int rowIndex;

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
        columnIndex = matrix.getMinColumnIndex();
        rowIndex = matrix.getMinRowIndex();
    }

    // @see java.util.Iterator#hasNext()
    public boolean hasNext() {
        return columnIndex <= matrix.getMaxColumnIndex() && rowIndex <= matrix.getMaxRowIndex();
    }

    // @see java.util.Iterator#next()
    public Element next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Element element = matrix.get(columnIndex, rowIndex);

        gotoNextElement();

        return element;
    }

    /**
     * Sets the column and row of this MatrixIterator to the column and row of the next Element to be traversed.
     */
    protected abstract void gotoNextElement();

    /**
     * Always throws a {@code UnsupportedOperationException} since Elements in a Matrix cannot be removed.
     * 
     * @throws UnsupportedOperationException
     *         always
     */
    public void remove()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
