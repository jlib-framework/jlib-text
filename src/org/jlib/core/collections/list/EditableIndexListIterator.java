/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    EditableIndexListIterator.java
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
 * Iterator over an EditableIndexList using a random access data store.
 *
 * @param <Element>
 *        type of elements stored in the IndexList
 * @author Igor Akkerman
 */
public class EditableIndexListIterator<Element>
implements IndexListIterator<Element>, EditableListIterator<Element> {

    /** EditableIndexList traversed by this Iterator */
    private EditableIndexList<Element> list;

    /** index of the next Element in the IndexList */
    protected int nextElementIndex;

    /** index of last retreived Element */
    private int lastRetreivedElementIndex;

    /** no default constructor */
    private EditableIndexListIterator() {}

    /**
     * Creates a new EditableIndexListIterator over the Elements of the specified EditableIndexList.
     *
     * @param list
     *        EditableIndexList to traverse
     */
    public EditableIndexListIterator(EditableIndexList<Element> list) {
        this(list, list.getMinIndex());
    }

    /**
     * Creates a new EditableIndexListIterator over the Elements of the specified IndexList starting the
     * traversal at the specified index.
     *
     * @param list
     *        EditableIndexList to traverse
     * @param index
     *        integer specifying the start index of the traversal
     */
    public EditableIndexListIterator(EditableIndexList<Element> list, int index) {
        super();
        this.list = list;
        nextElementIndex = index;
        lastRetreivedElementIndex = -1;
    }

    // @see org.jlib.core.collections.list.ListIterator#hasNext()
    public boolean hasNext() {
        return nextIndex() <= list.getMaxIndex();
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
        return previousIndex() >= list.getMinIndex();
    }

    // @see org.jlib.core.collections.list.ListIterator#previous()
    public Element previous()
    throws NoSuchElementException {
        if (!hasPrevious())
            throw new NoSuchElementException();

        lastRetreivedElementIndex = -- nextElementIndex;

        return list.get(nextElementIndex);
    }

    /**
     * Returns the index of the last retreived Element.
     *
     * @return integer specifying the index
     */
    protected int getLastRetreivedElementIndex() {
        return lastRetreivedElementIndex;
    }

    // @see org.jlib.core.collections.list.IndexListIterator#nextIndex()
    public int nextIndex() {
        return nextElementIndex;
    }

    // @see org.jlib.core.collections.list.IndexListIterator#previousIndex()
    public int previousIndex() {
        return nextElementIndex - 1;
    }

    // @see org.jlib.core.collections.list.EditableListIterator#set(java.lang.Object)
    public void set(Element element) {
        list.set(nextElementIndex, element);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     * <p>
     * This implementation always throws an {@code UnsupportedOperationException}.
     * </p>
     *
     * @throws UnsupportedOperationException
     *         always
     */
    public void remove()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
