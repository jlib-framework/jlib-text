/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    EditableIndexedList.java
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

import org.jlib.core.collections.ListIndexOutOfBoundsException;

/**
 * <p>
 * IndexedList that allows modification of the Elements that it contains.
 * </p>
 * <p>
 * It does not necessarily allow modification of the list, that is, this interface does not provide
 * methods for adding and removing Elements. See {@link ModifiableIndexedList} for this behaviour.
 * </p>
 *
 * @param <Element>
 *        type of the elements held in the IndexedList
 * @author Igor Akkerman
 */
public interface EditableIndexedList<Element>
extends IndexedList<Element> {

    /**
     * Stores the specified Element at the specified index in this IndexedList.
     *
     * @param index
     *        integer specifying the index
     * @param element
     *        Element to store
     * @return Element formerly stored at {@code index}
     * @throws ListIndexOutOfBoundsException
     *         if {@code index < getMinIndex() || index > getMaxIndex()}
     */
    public Element set(int index, Element element)
    throws ListIndexOutOfBoundsException;

}
