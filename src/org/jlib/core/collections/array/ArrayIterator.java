/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    ArrayIterator.java
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

package org.jlib.core.collections.array;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * List Iterator over an Array.
 *
 * @param <Element>
 *        type of elements stored in the Array
 * @author Igor Akkerman
 */
class ArrayIterator<Element>
implements ListIterator<Element> {

    /** Array traversed by this ArrayIterator */
    private Array<Element> array;

    /** index of the current Element in the Array */
    private int currentElementIndex;

    /** no default constructor */
    private ArrayIterator() {}

    /**
     * Creates a new ArrayIterator over the Elements of the specified Array.
     *
     * @param array
     *        Array to traverse
     */
    ArrayIterator(Array<Element> array) {
        this(array, array.getMinIndex());
    }

    /**
     * Creates a new ArrayIterator over the Elements of the specified Array starting the
     * traversal at the specified index.
     *
     * @param array
     *        Array to traverse
     * @param index
     *        integer specifying the start index of the traversal
     */
    ArrayIterator(Array<Element> array, int index) {
        super();
        this.array = array;
        currentElementIndex = index - 1;
    }

    // @see java.util.ListIterator#hasNext()
    public boolean hasNext() {
        return nextIndex() <= array.getMaxIndex();
    }

    // @see java.util.ListIterator#next()
    public Element next()
    throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();
        return array.get(nextIndex());
    }

    // @see java.util.ListIterator#hasPrevious()
    public boolean hasPrevious() {
        return previousIndex() >= array.getMinIndex();
    }

    // @see java.util.ListIterator#previous()
    public Element previous()
    throws NoSuchElementException {
        if (!hasPrevious())
            throw new NoSuchElementException();
        return array.get(previousIndex());
    }

    // @see java.util.ListIterator#nextIndex()
    public int nextIndex() {
        return currentElementIndex + 1;
    }

    // @see java.util.ListIterator#previousIndex()
    public int previousIndex() {
        return currentElementIndex - 1;
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be removed from it.
     * </p>
     *
     * @throws UnsupportedOperationException
     *         always
     */
    public void remove()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // @see java.util.ListIterator#set(java.lang.Object)
    public void set(Element element) {
        array.set(currentElementIndex, element);
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be added to it.
     * </p>
     *
     * @param element
     *        any Element
     * @throws UnsupportedOperationException
     *         always
     */
    public void add(Element element)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
