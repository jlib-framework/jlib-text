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

import static java.lang.reflect.Array.newInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

import org.jlib.core.collections.list.ListIndexOutOfBoundsException;

/**
 * Row or partial row of a Matrix.
 *
 * @author Igor Akkerman
 * @param <Element>
 *        type of the elements held in the Matrix
 */
public class MatrixColumn<Element>
implements List<Element>, RandomAccess {

    /** Matrix of which this MatrixColumn represents the portion of a column */
    private Matrix<Element> matrix;

    /** integer specifying the index of the column in the Matrix that this MatrixColumn represents */
    private int columnIndex;

    /** integer specifying the minimum row index of the portion of the column */
    private int minRowIndex;

    /** integer specifying the maximum row index of the portion of the column */
    private int maxRowIndex;

    /** no default constructor */
    private MatrixColumn() {}

    /**
     * Creates a new MatrixColumn representing the specified column of the specified Matrix.
     *
     * @param matrix
     *        Matrix of which this MatrixColumn represents a column
     * @param columnIndex
     *        integer specifying the index of the column in the Matrix that this MatrixColumn
     *        represents
     * @throws ListIndexOutOfBoundsException
     *         if {@code columnIndex} is not a valid column index of {@code matrix}
     */
    protected MatrixColumn(Matrix<Element> matrix, int columnIndex)
    throws ListIndexOutOfBoundsException {
        this(matrix, columnIndex, matrix.getMinRowIndex(), matrix.getMaxRowIndex());
    }

    /**
     * Creates a new MatrixColumn representing the specified portion of the specified column of the
     * specified Matrix.
     *
     * @param matrix
     *        Matrix of which this MatrixColumn represents the portion of a column
     * @param columnIndex
     *        integer specifying the index of the column in the Matrix that this MatrixColumn
     *        represents
     * @param minRowIndex
     *        integer specifying the minimum row index of the portion of the column
     * @param maxRowIndex
     *        integer specifying the maximum row index of the portion of the column
     * @throws IllegalArgumentException
     *         if {@code minRowIndex > maxRowIndex}
     * @throws ListIndexOutOfBoundsException
     *         if {@code nextElementRowIndex} is not a valid column index of {@code matrix} or
     *         either {@code minRowIndex} or {@code maxRowIndex} is not a valid row index of
     *         {@code matrix}
     */
    public MatrixColumn(Matrix<Element> matrix, int columnIndex, int minRowIndex, int maxRowIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {
        super();
        this.matrix = matrix;
        this.columnIndex = columnIndex;
        this.minRowIndex = minRowIndex;
        this.maxRowIndex = maxRowIndex;

        if (minRowIndex > maxRowIndex)
            throw new IllegalArgumentException();

        if (columnIndex < matrix.getMinColumnIndex() || columnIndex > matrix.getMaxColumnIndex())
            throw new ListIndexOutOfBoundsException(columnIndex);

        if (minRowIndex < matrix.getMinRowIndex())
            throw new ListIndexOutOfBoundsException(minRowIndex);

        if (maxRowIndex > matrix.getMaxRowIndex())
            throw new ListIndexOutOfBoundsException(maxRowIndex);
    }

    // @see java.util.List#contains(java.lang.Object)
    public boolean contains(Object element) {
        for (Element columnElement : this)
            if (element == columnElement || element != null && element.equals(columnElement))
                return true;
        return false;
    }

    // @see java.util.List#containsAll(java.util.Collection)
    public boolean containsAll(Collection<?> collection) {
        for (Object collectionElement : collection)
            if (!contains(collectionElement))
                return false;
        return true;
    }

    // @see java.util.List#get(int)
    public Element get(int index) {
        return matrix.get(columnIndex, index);
    }

    // @see java.util.List#indexOf(java.lang.Object)
    public int indexOf(Object element) {
        Element columnElement;
        for (int rowIndex = minRowIndex; rowIndex <= maxRowIndex; rowIndex ++) {
            columnElement = get(rowIndex);
            if (element == columnElement || element != null && element.equals(columnElement))
                return rowIndex;
        }
        return -1;
    }

    /**
     * Returns {@code false} since this MatrixColumn always contains at least one Element
     *
     * @return always {@code false}
     */
    public boolean isEmpty() {
        return false;
    }

    // @see java.util.List#iterator()
    public Iterator<Element> iterator() {
        return matrix.newRowIterator(columnIndex);
    }

    // @see java.util.List#lastIndexOf(java.lang.Object)
    public int lastIndexOf(Object element) {
        Element columnElement;
        for (int rowIndex = maxRowIndex; rowIndex >= minRowIndex; rowIndex --) {
            columnElement = get(rowIndex);
            if (element == columnElement || element != null && element.equals(columnElement))
                return rowIndex;
        }
        return -1;
    }

    // @see java.util.List#listIterator()
    public ListIterator<Element> listIterator() {
        return new MatrixColumnIterator<Element>(matrix, columnIndex, minRowIndex, maxRowIndex);
    }

    // @see java.util.List#listIterator(int)
    public ListIterator<Element> listIterator(int index) {
        return new MatrixColumnIterator<Element>(matrix, columnIndex, index, maxRowIndex);
    }

    // @see java.util.List#set(int, java.lang.Object)
    public Element set(int index, Element element)
    throws ListIndexOutOfBoundsException {
        Element oldElement = get(index);
        matrix.set(columnIndex, index, element);
        return oldElement;
    }

    // @see java.util.List#size()
    public int size() {
        return maxRowIndex - minRowIndex + 1;
    }

    // @see java.util.List#subList(int, int)
    public List<Element> subList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException();

        ArrayList<Element> subList = new ArrayList<Element>(toIndex - fromIndex + 1);
        for (int rowIndex = fromIndex; rowIndex <= toIndex; rowIndex ++)
            subList.add(get(rowIndex));

        return subList;
    }

    // @see java.util.List#toArray()
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int rowIndex = minRowIndex, arrayIndex = 0; rowIndex <= maxRowIndex; rowIndex ++, arrayIndex ++)
            array[arrayIndex] = get(rowIndex);

        return array;
    }

    // @see java.util.List#toArray(T[])
    @SuppressWarnings("unchecked")
    public <ArrayElement> ArrayElement[] toArray(ArrayElement[] array) {
        if (array == null)
            throw new NullPointerException();

        int size = size();

        Object[] resultArray = array;
        Class<?> arrayType = resultArray.getClass().getComponentType();
        if (resultArray.length < size)
            resultArray = (ArrayElement[]) newInstance(arrayType, size);

        int arrayIndex = 0;
        for (Element element : this)
            resultArray[arrayIndex ++] = element;

        if (resultArray.length > size)
            resultArray[size] = null;

        return array;
    }

    /**
     * Returns the minimum row index of the portion of the column that this MatrixColumn represents.
     *
     * @return integer specifying the minimum row index of the portion of the column that this
     *         MatrixColumn represents
     */
    public final int getMinRowIndex() {
        return minRowIndex;
    }

    /**
     * Returns the maximum row index of the portion of the column that this MatrixColumn represents.
     *
     * @return integer specifying the maximum row index of the portion of the column that this
     *         MatrixColumn represents
     */
    public final int getMaxRowIndex() {
        return maxRowIndex;
    }

    /**
     * Returns the index of the column that this MatrixColumn represents.
     *
     * @return integer specifying the index of the column that this MatrixColumn represents
     */
    public final int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Returns the Matrix of which this MatrixColumn represents the column or the portion of a column.
     *
     * @return Matrix of which this MatrixColumn represents the column or the portion of a column
     */
    public final Matrix<Element> getMatrix() {
        return matrix;
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be added to it.
     * </p>
     *
     * @param element
     *        any Element
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean add(Element element) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be added to it.
     * </p>
     *
     * @param index
     *        any integer
     * @param element
     *        any Element
     * @throws UnsupportedOperationException
     *         always
     */
    public void add(int index, Element element) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be added to it.
     * </p>
     *
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean addAll(Collection<? extends Element> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be added to it.
     * </p>
     *
     * @param index
     *        any integer
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean addAll(int index, Collection<? extends Element> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be removed from it.
     * </p>
     *
     * @throws UnsupportedOperationException
     *         always
     */
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be removed from it.
     * </p>
     *
     * @param index
     *        any integer
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public Element remove(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be removed from it.
     * </p>
     *
     * @param element
     *        any Object
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean remove(Object element) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be removed from it.
     * </p>
     *
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixColumn has a fixed size, no Elements can be removed from it.
     * </p>
     *
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
}
