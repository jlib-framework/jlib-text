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
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.collections;

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

    // @see org.jlib.core.collections.MatrixIterator#nextElement()
    @Override
    protected void gotoNextElement() {
        if (++ columnIndex > matrix.getMaxColumnIndex()) {
            rowIndex ++;
            columnIndex = 0;
        }
    }
}
