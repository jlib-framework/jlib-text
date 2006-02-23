/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    DefaultIndexListIterator.java
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

package org.jlib.core.collections.list;

import java.util.NoSuchElementException;

/**
 * IndexListIterator traversing the Elements in the proper order.
 *
 * @param <Element>
 *        type of elements held in the List
 * @author Igor Akkerman
 */
public class DefaultIndexListIterator<Element>
implements IndexListIterator<Element> {

    /** IndexList to traverse */
    private IndexList<Element> list;

    /** index of the next Element in the IndexList */
    protected int nextElementIndex;

    /** index of last retreived Element */
    private int lastRetreivedElementIndex;

    /** no default constructor */
    private DefaultIndexListIterator() {}

    /**
     * Creates a new DefaultIndexListIterator over the Elements of the specified EditableIndexList.
     *
     * @param list
     *        IndexList to traverse
     */
    public DefaultIndexListIterator(IndexList<Element> list) {
        this(list, list.minIndex());
    }

    /**
     * Creates a new DefaultEditableIndexListIterator over the Elements of the specified IndexList
     * starting the traversal at the specified index.
     *
     * @param list
     *        EditableIndexList to traverse
     * @param startIndex
     *        integer specifying the start index of the traversal
     * @throws ListIndexOutOfBoundsException
     *         if {@code startIndex < minIndex() || startIndex > maxIndex()}
     */
    public DefaultIndexListIterator(IndexList<Element> list, int startIndex)
    throws ListIndexOutOfBoundsException {
        super();
        this.list = list;
        nextElementIndex = startIndex;
        lastRetreivedElementIndex = -1;
    }

    // @see org.jlib.core.collections.list.ListIterator#hasNext()
    public boolean hasNext() {
        return nextIndex() <= list.maxIndex();
    }

    // @see org.jlib.core.collections.list.ListIterator#next()
    public Element next()
    throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();

        lastRetreivedElementIndex = nextElementIndex;

        return list.get(nextElementIndex ++);
    }

    // @see org.jlib.core.collections.list.ListIterator#hasPrevious()
    public boolean hasPrevious() {
        return previousIndex() >= list.minIndex();
    }

    // @see org.jlib.core.collections.list.ListIterator#previous()
    public Element previous()
    throws NoSuchElementException {
        if (!hasPrevious())
            throw new NoSuchElementException();

        lastRetreivedElementIndex = -- nextElementIndex;

        return list.get(nextElementIndex);
    }

    // @see org.jlib.core.collections.list.IndexListIterator#previousIndex()
    public int previousIndex() {
        return nextElementIndex - 1;
    }

    // @see org.jlib.core.collections.list.IndexListIterator#nextIndex()
    public int nextIndex() {
        return nextElementIndex;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     * <p>
     * This implementation of this method always throws an {@code UnsupportedOperationException}.
     * </p>
     *
     * @throws UnsupportedOperationException
     *         always
     */
    public void remove()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the index of the last retreived Element.
     *
     * @return integer specifying the index
     */
    protected int getLastRetreivedElementIndex() {
        return lastRetreivedElementIndex;
    }

}
