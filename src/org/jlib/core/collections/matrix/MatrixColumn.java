/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    MatrixColumn.java
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

import org.jlib.core.collections.list.AbstractEditableIndexList;
import org.jlib.core.collections.list.ListIndexOutOfBoundsException;

/**
 * Row or partial row of a Matrix.
 *
 * @author Igor Akkerman
 * @param <Element>
 *        type of the elements held in the Matrix
 */
public class MatrixColumn<Element>
extends AbstractEditableIndexList<Element> {

    /** Matrix of which this MatrixColumn represents the range of a column */
    private Matrix<Element> matrix;

    /**
     * integer specifying the index of the column in the Matrix that this MatrixColumn
     * represents
     */
    private int columnIndex;

    /** integer specifying the minimum row index of the range of the column */
    private int minRowIndex;

    /** integer specifying the maximum row index of the range of the column */
    private int maxRowIndex;

    /** no default constructor */
    private MatrixColumn() {}

    /**
     * Creates a new MatrixColumn representing the specified column of the specified
     * Matrix.
     *
     * @param matrix
     *        Matrix of which this MatrixColumn represents a column
     * @param columnIndex
     *        integer specifying the index of the column in the Matrix that this
     *        MatrixColumn represents
     * @throws ListIndexOutOfBoundsException
     *         if {@code columnIndex} is not a valid column index of {@code matrix}
     */
    protected MatrixColumn(Matrix<Element> matrix, int columnIndex)
    throws ListIndexOutOfBoundsException {
        this(matrix, columnIndex, matrix.minRowIndex(), matrix.maxRowIndex());
    }

    /**
     * Creates a new MatrixColumn representing the specified range of the specified
     * column of the specified Matrix.
     *
     * @param matrix
     *        Matrix of which this MatrixColumn represents the range of a column
     * @param columnIndex
     *        integer specifying the index of the column in the Matrix that this
     *        MatrixColumn represents
     * @param minRowIndex
     *        integer specifying the minimum row index of the range of the column
     * @param maxRowIndex
     *        integer specifying the maximum row index of the range of the column
     * @throws IllegalArgumentException
     *         if {@code minRowIndex > maxRowIndex}
     * @throws ListIndexOutOfBoundsException
     *         if {@code columnIndex} is not a valid column index of {@code matrix} or
     *         either {@code minRowIndex} or {@code maxRowIndex} is not a valid row index
     *         of {@code matrix}
     */
    public MatrixColumn(Matrix<Element> matrix, int columnIndex, int minRowIndex, int maxRowIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {

        super(minRowIndex, maxRowIndex);

        this.matrix = matrix;
        this.columnIndex = columnIndex;
        this.minRowIndex = minRowIndex;
        this.maxRowIndex = maxRowIndex;

        if (columnIndex < matrix.minColumnIndex() || columnIndex > matrix.maxColumnIndex())
            throw new ListIndexOutOfBoundsException(columnIndex);

        if (minRowIndex < matrix.minRowIndex())
            throw new ListIndexOutOfBoundsException(minRowIndex);

        if (maxRowIndex > matrix.maxRowIndex())
            throw new ListIndexOutOfBoundsException(maxRowIndex);
    }

    // @see org.jlib.core.collections.list.IndexList#get(int)
    public Element get(int index) {
        return matrix.get(columnIndex, index);
    }

    // @see org.jlib.core.collections.list.EditableIndexList#set(int, java.lang.Object)
    public Element set(int index, Element element)
    throws ListIndexOutOfBoundsException {
        Element oldElement = get(index);
        matrix.set(columnIndex, index, element);
        return oldElement;
    }

    /**
     * Returns the Matrix of which this MatrixColumn represents the column or the range
     * of a column.
     *
     * @return Matrix of which this MatrixColumn represents the column or the range of a
     *         column
     */
    public final Matrix<Element> getMatrix() {
        return matrix;
    }

    /**
     * Returns the minimum row index of the range of the column that this MatrixColumn
     * represents.
     *
     * @return integer specifying the minimum row index of the range of the column that
     *         this MatrixColumn represents
     */
    public final int getMinRowIndex() {
        return minRowIndex;
    }

    /**
     * Returns the maximum row index of the range of the column that this MatrixColumn
     * represents.
     *
     * @return integer specifying the maximum row index of the range of the column that
     *         this MatrixColumn represents
     */
    public final int getMaxRowIndex() {
        return maxRowIndex;
    }

    /**
     * Returns the index of the column that this MatrixColumn represents.
     *
     * @return integer specifying the index of the column that this MatrixColumn
     *         represents
     */
    public final int getColumnIndex() {
        return columnIndex;
    }
}
