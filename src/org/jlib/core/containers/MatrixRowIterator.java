/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    MatrixRowIterator.java
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

/**
 * Iterator over a single row of a Matrix.
 * 
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
class MatrixRowIterator<Element>
extends MatrixIterator<Element> {

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
        this.rowIndex = rowIndex;
    }

    // @see org.jlib.core.containers.MatrixIterator#gotoNextElement()
    @Override
    protected void gotoNextElement() {
        columnIndex ++;
    }

}
