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
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.collections;

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
    }

    // @see org.jlib.core.collections.MatrixIterator#nextElement()
    @Override
    protected void gotoNextElement() {
        if (++ rowIndex > matrix.getMaxRowIndex()) {
            columnIndex ++;
            rowIndex = 0;
        }
    }
}
