package org.jlib.core.containers;

import java.util.Collection;
import java.util.Iterator;

import static java.lang.reflect.Array.newInstance;

/**
 * Fixed sized 2D matrix.
 * 
 * @param <Element>
 *        type of the elements held in the Matrix
 * @author Igor Akkerman
 */
public class Matrix<Element>
implements Collection<Element> {

    /** number of columns */
    private int width;

    /** number of rows */
    private int height;

    /** minimum column index */
    private int minColumnIndex;

    /** maximum column index */
    private int maxColumnIndex;

    /** minimum row index */
    private int minRowIndex;

    /** maximum row index */
    private int maxRowIndex;

    /** Matrix data */
    private Array<Array<Element>> matrixData;

    /**
     * Creates a new empty Matrix.
     */
    public Matrix() {
        this(0, 0);
    }

    /**
     * Creates a new Matrix of the specified width and height.
     * 
     * @param width
     *        integer specifying the number of columns of this matrix
     * @param height
     *        integer specifying the number of rows of this matrix
     * @throws IllegalArgumentException
     *         if {@code width < 0 || height < 0}
     */
    public Matrix(int width, int height) {
        super();
        if (width != 0 && height != 0) {
            construct(0, width - 1, 0, height - 1);
        }
        else {
            minRowIndex = -1;
            maxRowIndex = -1;
            minColumnIndex = -1;
            maxColumnIndex = -1;
            this.width = width;
            this.height = height;
            matrixData = new Array<Array<Element>>();
        }
    }

    /**
     * Creates a new Matrix with the specified minimum and maximum column and row indexes.
     * 
     * @param minColumnIndex
     *        minimum column index
     * @param maxColumnIndex
     *        maximum column index
     * @param minRowIndex
     *        minimum row index
     * @param maxRowIndex
     *        maximum row index
     * @throws IllegalArgumentException
     *         if
     *         {@code minColumnIndex < 0 || maxColumnIndex < minColumnIndex || minRowIndex < 0 || maxRowIndex < minRowIndex}
     */
    public Matrix(int minColumnIndex, int maxColumnIndex, int minRowIndex, int maxRowIndex) {
        super();
        construct(minColumnIndex, maxColumnIndex, minRowIndex, maxRowIndex);
    }

    /**
     * Constructs this Matrix.
     * 
     * @param minColumnIndex
     *        minimum column index
     * @param maxColumnIndex
     *        maximum column index
     * @param minRowIndex
     *        minimum row index
     * @param maxRowIndex
     *        maximum row index
     * @throws IllegalArgumentException
     *         if
     *         {@code minColumnIndex < 0 || maxColumnIndex < minColumnIndex || minRowIndex < 0 || maxRowIndex < minRowIndex}
     */
    @SuppressWarnings("hiding")
    private void construct(int minColumnIndex, int maxColumnIndex, int minRowIndex, int maxRowIndex) {
        if (minColumnIndex < 0 || maxColumnIndex < minColumnIndex || minRowIndex < 0 || maxRowIndex < minRowIndex) {
            throw new IllegalArgumentException();
        }

        this.minColumnIndex = minColumnIndex;
        this.maxColumnIndex = maxColumnIndex;
        this.minRowIndex = minRowIndex;
        this.maxRowIndex = maxRowIndex;
        this.width = maxColumnIndex - minColumnIndex + 1;
        this.height = maxRowIndex - minRowIndex + 1;

        matrixData = new Array<Array<Element>>(minRowIndex, maxRowIndex);
        for (int row = minRowIndex; row <= maxRowIndex; row ++) {
            matrixData.set(row, new Array<Element>(minColumnIndex, maxColumnIndex));
        }
    }

    /**
     * Returns the Element stored at the specified column and row in this Matrix.
     * 
     * @param columnIndex
     *        integer specifying the column of the stored Element
     * @param rowIndex
     *        integer specifying the row of the stored Element
     * @return Element stored at the specified position in this Matrix
     * @throws IndexOutOfBoundsException
     *         if
     *         {@code columnIndex < getMinColumnIndex() || columnIndex > getMaxColumnIndex() || rowIndex < getMinRowIndex || rowIndex > getMaxRowIndex()}
     */
    public Element get(int columnIndex, int rowIndex) {
        return matrixData.get(rowIndex).get(columnIndex);
    }

    /**
     * Registers the Element to store at the specified column and row in this Matrix.
     * 
     * @param columnIndex
     *        integer specifying the column of the Element to store
     * @param rowIndex
     *        integer specifying the row of the Element to store
     * @param element
     *        Element to store. {@code null} is a valid Element.
     * @throws IndexOutOfBoundsException
     *         if
     *         {@code columnIndex < getMinColumnIndex() || columnIndex > getMaxColumnIndex() || rowIndex < getMinRowIndex || rowIndex > getMaxRowIndex()}
     */
    public void set(int columnIndex, int rowIndex, Element element) {
        matrixData.get(rowIndex).set(columnIndex, element);
    }

    /**
     * Returns the minimum column index of this Matrix.
     * 
     * @return integer specifying the minimum column of this Matrix
     */
    public int getMinColumnIndex() {
        return minColumnIndex;
    }

    /**
     * Returns the maximum column index of this Matrix.
     * 
     * @return integer specifying the maximum column of this Matrix
     */
    public int getMaxColumnIndex() {
        return maxColumnIndex;
    }

    /**
     * Returns the minimum row index of this Matrix.
     * 
     * @return integer specifying the minimum row of this Matrix
     */
    public int getMinRowIndex() {
        return minRowIndex;
    }

    /**
     * Returns the maximum row index of this Matrix.
     * 
     * @return integer specifying the maximum row of this Matrix
     */
    public int getMaxRowIndex() {
        return maxRowIndex;
    }

    /**
     * Returns the width of this Matrix.
     * 
     * @return integer specifying the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this Matrix.
     * 
     * @return integer specifying the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns an Iterator of the Elements of this Matrix traversing it vertically. That is, the traversal algorithm is
     * as follows:
     * 
     * <pre>{@literal
     * for each row
     *     for each column
     *         process element at (column, row)}
     * </pre>
     * 
     * The {@link #newHorizontalIterator()} method has the same functionality as this method.
     * 
     * @return a new Iterator for this Matrix
     */
    public Iterator<Element> iterator() {
        return newHorizontalIterator();
    }

    /**
     * Returns an Iterator of the Elements of this Matrix traversing it horizontally. That is, the traversal algorithm
     * is as follows:
     * 
     * <pre>{@literal
     * for each row
     *     for each column
     *         process element at (column, row)}
     * </pre>
     * 
     * The {@link #iterator()} method has the same functionality as this method.
     * 
     * @return a new rows Iterator for this Matrix
     */
    public Iterator<Element> newHorizontalIterator() {
        return new VerticalMatrixIterator<Element>(this);
    }

    /**
     * Returns an Iterator of the Elements of this Matrix traversing it vertically. That is, the traversal algorithm is
     * as follows:
     * 
     * <pre>{@literal
     * for each column
     *     for each row
     *         process element at (column, row)}
     * </pre>
     * 
     * @return a new columns Iterator for this Matrix
     */
    public Iterator<Element> newVerticalIterator() {
        return new HorizontalMatrixIterator<Element>(this);
    }

    /**
     * Returns an Iterator of the Elements in a single column of this Matrix.
     * 
     * @param columnIndex
     *        integer specifying the index of the column to traverse
     * @return a new column Iterator for this Matrix
     */
    public Iterator<Element> newColumnIterator(int columnIndex) {
        return new MatrixColumnIterator<Element>(this, columnIndex);
    }

    /**
     * Returns an Iterator of the Elements in a single row of this Matrix.
     * 
     * @param rowIndex
     *        integer specifying the index of the row to traverse
     * @return a new row Iterator for this Matrix
     */
    public Iterator<Element> newRowIterator(int rowIndex) {
        return new MatrixRowIterator<Element>(this, rowIndex);
    }

    /**
     * Returns the number of fields in this Matrix. The number of fields is equal to {@code getWidth() * getHeight()}.
     * 
     * @return integer specifying the number of fields
     */
    public int size() {
        return width * height;
    }

    // @see java.util.Collection#isEmpty()
    public boolean isEmpty() {
        return size() == 0;
    }

    // @see java.util.Collection#contains(java.lang.Object)
    public boolean contains(Object element) {
        for (Array<Element> columnArray : matrixData)
            if (columnArray.contains(element))
                return true;
        return false;
    }

    // @see java.util.Collection#containsAll(java.util.Collection)
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection)
            if (!contains(object))
                return false;
        return true;
    }

    // @see java.util.Collection#toArray()
    public Object[] toArray() {
        Object[] resultArray = new Object[size()];

        int index = 0;
        for (Element element : this)
            resultArray[index ++] = element;

        return resultArray;
    }

    // @see java.util.Collection#toArray(T[])
    @SuppressWarnings("unchecked")
    public <ArrayElement> ArrayElement[] toArray(ArrayElement[] array) {
        if (array == null)
            throw new NullPointerException();

        int size = size();

        Object[] resultArray = array;
        Class<?> arrayType = resultArray.getClass().getComponentType();
        if (resultArray.length < size)
            resultArray = (ArrayElement[]) newInstance(arrayType, size);

        int index = 0;
        for (Element element : this)
            resultArray[index ++] = element;

        if (resultArray.length > size)
            resultArray[size] = null;

        return array;
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a Matrix has a fixed size, no Elements can be added to it.
     * </p>
     * 
     * @param element
     *        any Element
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean add(Element element)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a Matrix has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param element
     *        any Object
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean remove(Object element)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a Matrix has a fixed size, no Elements can be added to it.
     * </p>
     * 
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean addAll(Collection<? extends Element> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a Matrix has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean removeAll(Collection<?> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a Matrix has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param collection
     *        any Collection
     * @return never returns regulary
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean retainAll(Collection<?> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws an {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since a Matrix has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @throws UnsupportedOperationException
     *         always
     */
    public void clear()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
