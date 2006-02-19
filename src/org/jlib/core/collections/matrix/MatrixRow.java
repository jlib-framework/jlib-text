/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    MatrixRow.java
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

import org.jlib.core.collections.ListIndexOutOfBoundsException;

/**
 * Row or partial row of a Matrix.
 *
 * @author Igor Akkerman
 * @param <Element>
 *        type of the elements held in the Matrix
 */
public class MatrixRow<Element>
implements List<Element>, RandomAccess {

    /** Matrix of which this MatrixRow represents the portion of a row */
    private Matrix<Element> matrix;

    /** integer specifying the index of the row in the Matrix that this MatrixRow represents */
    private int rowIndex;

    /** integer specifying the minimum column index of the portion of the row */
    private int minColumnIndex;

    /** integer specifying the maximum column index of the portion of the row */
    private int maxColumnIndex;

    /** no default constructor */
    private MatrixRow() {}

    /**
     * Creates a new MatrixRow representing the specified row of the specified Matrix.
     *
     * @param matrix
     *        Matrix of which this MatrixRow represents a row
     * @param rowIndex
     *        integer specifying the index of the row in the Matrix that this MatrixRow represents
     * @throws ListIndexOutOfBoundsException
     *         if {@code rowIndex} is not a valid row index of {@code matrix}
     */
    protected MatrixRow(Matrix<Element> matrix, int rowIndex)
    throws ListIndexOutOfBoundsException {
        this(matrix, rowIndex, matrix.getMinColumnIndex(), matrix.getMaxColumnIndex());
    }

    /**
     * Creates a new MatrixRow representing the specified portion of the specified row of the
     * specified Matrix.
     *
     * @param matrix
     *        Matrix of which this MatrixRow represents the portion of a row
     * @param rowIndex
     *        integer specifying the index of the row in the Matrix that this MatrixRow represents
     * @param minColumnIndex
     *        integer specifying the minimum column index of the portion of the row
     * @param maxColumnIndex
     *        integer specifying the maximum column index of the portion of the row
     * @throws IllegalArgumentException
     *         if {@code minColumnIndex > maxColumnIndex}
     * @throws ListIndexOutOfBoundsException
     *         if {@code nextElementRowIndex} is not a valid row index of {@code matrix} or either
     *         {@code minColumnIndex} or {@code maxColumnIndex} is not a valid column index of
     *         {@code matrix}
     */
    public MatrixRow(Matrix<Element> matrix, int rowIndex, int minColumnIndex, int maxColumnIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {
        super();
        this.matrix = matrix;
        this.rowIndex = rowIndex;
        this.minColumnIndex = minColumnIndex;
        this.maxColumnIndex = maxColumnIndex;

        if (minColumnIndex > maxColumnIndex)
            throw new IllegalArgumentException();

        if (rowIndex < matrix.getMinRowIndex() || rowIndex > matrix.getMaxRowIndex())
            throw new ListIndexOutOfBoundsException(rowIndex);

        if (minColumnIndex < matrix.getMinColumnIndex())
            throw new ListIndexOutOfBoundsException(minColumnIndex);

        if (maxColumnIndex > matrix.getMaxColumnIndex())
            throw new ListIndexOutOfBoundsException(maxColumnIndex);
    }

    // @see java.util.List#contains(java.lang.Object)
    public boolean contains(Object element) {
        for (Element rowElement : this)
            if (element == rowElement || element != null && element.equals(rowElement))
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
        return matrix.get(index, rowIndex);
    }

    // @see java.util.List#indexOf(java.lang.Object)
    public int indexOf(Object element) {
        Element rowElement;
        for (int columnIndex = minColumnIndex; columnIndex <= maxColumnIndex; columnIndex ++) {
            rowElement = get(columnIndex);
            if (element == rowElement || element != null && element.equals(rowElement))
                return columnIndex;
        }
        return -1;
    }

    /**
     * Returns {@code false} since this MatrixRow always contains at least one Element
     *
     * @return always {@code false}
     */
    public boolean isEmpty() {
        return false;
    }

    // @see java.util.List#iterator()
    public Iterator<Element> iterator() {
        return matrix.newRowIterator(rowIndex);
    }

    // @see java.util.List#lastIndexOf(java.lang.Object)
    public int lastIndexOf(Object element) {
        Element rowElement;
        for (int columnIndex = maxColumnIndex; columnIndex >= minColumnIndex; columnIndex --) {
            rowElement = get(columnIndex);
            if (element == rowElement || element != null && element.equals(rowElement))
                return columnIndex;
        }
        return -1;
    }

    // @see java.util.List#listIterator()
    public ListIterator<Element> listIterator() {
        return new MatrixRowIterator<Element>(matrix, rowIndex, minColumnIndex, maxColumnIndex);
    }

    // @see java.util.List#listIterator(int)
    public ListIterator<Element> listIterator(int index) {
        return new MatrixRowIterator<Element>(matrix, rowIndex, index, maxColumnIndex);
    }

    // @see java.util.List#set(int, java.lang.Object)
    public Element set(int index, Element element)
    throws ListIndexOutOfBoundsException {
        Element oldElement = get(index);
        matrix.set(index, rowIndex, element);
        return oldElement;
    }

    // @see java.util.List#size()
    public int size() {
        return maxColumnIndex - minColumnIndex + 1;
    }

    // @see java.util.List#subList(int, int)
    public List<Element> subList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException();

        ArrayList<Element> subList = new ArrayList<Element>(toIndex - fromIndex + 1);
        for (int columnIndex = fromIndex; columnIndex <= toIndex; columnIndex ++)
            subList.add(get(columnIndex));

        return subList;
    }

    // @see java.util.List#toArray()
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int columnIndex = minColumnIndex, arrayIndex = 0; columnIndex <= maxColumnIndex; columnIndex ++, arrayIndex ++)
            array[arrayIndex] = get(columnIndex);

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
     * Returns the maximum column index of the portion of the row that this MatrixRow represents.
     *
     * @return integer specifying the maximum column index of the portion of the row that this
     *         MatrixRow represents
     */
    public final int getMaxColumnIndex() {
        return maxColumnIndex;
    }

    /**
     * Returns the minimum column index of the portion of the row that this MatrixRow represents.
     *
     * @return integer specifying the minimum column index of the portion of the row that this
     *         MatrixRow represents
     */
    public final int getMinColumnIndex() {
        return minColumnIndex;
    }

    /**
     * Returns the index of the row that this MatrixRow represents.
     *
     * @return integer specifying the index of the row that this MatrixRow represents
     */
    public final int getRowIndex() {
        return rowIndex;
    }

    /**
     * Returns the Matrix of which this MatrixRow represents the row or the portion of a row.
     *
     * @return Matrix of which this MatrixRow represents the row or the portion of a row
     */
    public final Matrix<Element> getMatrix() {
        return matrix;
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a MatrixRow has a fixed size, no Elements can be added to it.
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
     * Since a MatrixRow has a fixed size, no Elements can be added to it.
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
     * Since a MatrixRow has a fixed size, no Elements can be added to it.
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
     * Since a MatrixRow has a fixed size, no Elements can be added to it.
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
     * Since a MatrixRow has a fixed size, no Elements can be removed from it.
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
     * Since a MatrixRow has a fixed size, no Elements can be removed from it.
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
     * Since a MatrixRow has a fixed size, no Elements can be removed from it.
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
     * Since a MatrixRow has a fixed size, no Elements can be removed from it.
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
     * Since a MatrixRow has a fixed size, no Elements can be removed from it.
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
