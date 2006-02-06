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

package org.jlib.core.collections;

/**
 * Iterator over a single column of a Matrix.
 * 
 * @param <Element>
 *        type of elements stored in the Matrix
 * @author Igor Akkerman
 */
class MatrixColumnIterator<Element>
extends MatrixIterator<Element> {

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
    }

    // @see org.jlib.core.collections.MatrixIterator#gotoNextElement()
    @Override
    protected void gotoNextElement() {
        rowIndex ++;
    }

}
