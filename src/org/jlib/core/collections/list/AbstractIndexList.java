/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    AbstractIndexList.java
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

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;


/**
 * Skeletal implementation of an IndexList using a random access data store.
 *
 * @param <Element>
 *        type of elements stored in the AbstractIndexList
 * @author Igor Akkerman
 */
public abstract class AbstractIndexList<Element>
extends AbstractCollection<Element>
implements IndexList<Element> {

    /** current minimum index of this list */
    protected int minIndex;

    /** current maximum index of this list */
    protected int maxIndex;

    /**
     * Creates a new AbstractIndexList initialized as empty.
     */
    protected AbstractIndexList() {
        super();
        minIndex = -1;
        maxIndex = -2;
        // now the size can be computed by maxIndex - minIndex + 1 == 0
    }

    /**
     * Creates a new AbstractIndexList initialized with the correct values for minimum and maximum
     * indices. Classes extending this class must initialize the Element store.
     *
     * @param minIndex
     *        integer specifying the minimum index of this list
     * @param maxIndex
     *        integer specifying the maximum index of this list
     * @throws IllegalArgumentException
     *         if {@code  minIndex < 0 || minIndex > maxIndex}
     */
    protected AbstractIndexList(int minIndex, int maxIndex) {
        super();
        if (minIndex < 0 || minIndex > maxIndex)
            throw new IllegalArgumentException();

        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
    }

    // @see org.jlib.core.collections.list.IndexList#getMinIndex()
    public int getMinIndex() {
        return minIndex;
    }

    // @see org.jlib.core.collections.list.IndexList#getMaxIndex()
    public int getMaxIndex() {
        return maxIndex;
    }

    // @see java.util.AbstractCollection#size()
    @Override
    public int size() {
        return maxIndex - minIndex + 1;
    }

    // @see org.jlib.core.collections.list.IndexList#indexOf(java.lang.Object)
    public int indexOf(Element element) {
        Element listElement;
        for (int index = minIndex; index <= maxIndex; index ++) {
            listElement = get(index);
            if (listElement != null ? listElement.equals(element) : element == null)
                return index;
        }
        return -1;
    }

    // @see org.jlib.core.collections.list.IndexList#lastIndexOf(java.lang.Object)
    public int lastIndexOf(Element element) {
        Element listElement;
        for (int index = maxIndex; index >= minIndex; index --) {
            listElement = get(index);
            if (listElement != null ? listElement.equals(element) : element == null)
                return index;
        }
        return -1;
    }

    // @see org.jlib.core.collections.list.IndexList#toList()
    public List<Element> toList() {
        return subList(minIndex, maxIndex);
    }

    // @see org.jlib.core.collections.list.IndexList#subList(int, int)
    public List<Element> subList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException();

        List<Element> list = new ArrayList<Element>(toIndex - fromIndex + 1);
        for (int index = fromIndex; index <= toIndex; index ++)
            list.add(get(index));
        return list;
    }

    // @see org.jlib.core.collections.list.IndexList#subIndexList(int, int)
    public IndexList<Element> subIndexList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException();

        EditableIndexList<Element> list = new Array<Element>(fromIndex, toIndex);
        for (int index = fromIndex; index <= toIndex; index ++)
            list.set(index, get(index));

        return list;
    }
}
