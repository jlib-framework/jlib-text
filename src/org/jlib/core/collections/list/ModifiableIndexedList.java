/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    ModifiableIndexedList.java
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

import java.util.Collection;

/**
 * <p>
 * IndexedList that allows Elements to be added and removed.
 * </p>
 * <p>
 * It does not necessarily allow modification of the Elements in the list, that is, this interface
 * does not provide methods for updating Elements. See {@link EditableIndexedList} for this
 * behaviour.
 * </p>
 *
 * @param <Element>
 *        type of elements held in the IndexedList
 * @author Igor Akkerman
 */
public interface ModifiableIndexedList<Element>
extends IndexedList<Element> {

    /**
     * Appends the specified Element to the end of this IndexedList.
     *
     * @param element
     *        Element to add
     * @return always {@code true}
     */
    public boolean add(Element element);

    /**
     * Inserts the specified Element at the specified index in this IndexedList.
     *
     * @param index
     *        integer specifying the index
     * @param element
     *        Element to add
     */
    public void add(int index, Element element);

    /**
     * Appends all Elements of the specified Collection to the end of this IndexedList. The Elements
     * are appended in the order they are returned by the Collection's Iterator.
     *
     * @param collection
     *        Collection holding the Elements to add
     * @return {@code true} if this IndexedList changed as a result of this call, that is, if
     *         {@code collection} is not empty; {@code false} otherwise
     */
    public boolean addAll(Collection<? extends Element> collection);

    /**
     * Inserts all Elements of the specified Collection at the specified index of this IndexedList.
     * The Elements are inserted in the order they are returned by the Collection's Iterator.
     *
     * @param index
     *        integer specifying the index
     * @param collection
     *        Collection holding the Elements to add
     * @return {@code true} if this IndexedList changed as a result of this call, that is, if
     *         {@code collection} is not empty; {@code false} otherwise
     */
    public boolean addAll(int index, Collection<? extends Element> collection);

    /**
     * Removes from this IndexList the Element stored at the specified index.
     *
     * @param index
     *        integer specifying the index
     * @return the removed Element
     */
    public Element remove(int index);

    /**
     * Removes from this IndexList the specified Element at its first occurence. More formally, the
     * first {@code listElement} is removed such as
     * {@code listElement != null ? listElement.equals(element) : element == null}.
     *
     * @param element
     *        Element to remove
     * @return {@code true} if this IndexedList changed as a result of this call, that is, if this
     *         IndexedList contains {@code element}; {@code false} otherwise
     */
    public boolean remove(Object element);

    /**
     * Removes from this IndexList all Elements of the specified Collection. More formally, for each
     * {@collectionElement} the first {@code listElement} is removed such as
     * {@code listElement != null ? listElement.equals(collectionElement) : collectionElement == null}.
     *
     * @param collection
     *        Collection holding the Elements to remove
     * @return {@code true} if this IndexedList changed as a result of this call, that is, if
     *         {@code collection} is not empty and at least one Element of {@code collection} equals
     *         to an Element of this List; {@code false} otherwise
     */
    public boolean removeAll(Collection<?> collection);

    /**
     * Retains in this IndexList only the Elements contained by the specified Collection. That is,
     * removes from this IndexList all Elements that are not contained by the specified Collection.
     * More formally, each {@listElement} is removed if for no {@code collectionElement}
     * {@code listElement != null ? listElement.equals(collectionElement) : collectionElement == null}.
     *
     * @param collection
     *        Collection holding the Elements to retain
     * @return {@code true} if this IndexedList changed as a result of this call, that is, if
     *         {@code collection} is not empty and at least one Element of {@code collection} is not
     *         equal to an Element of this List; {@code false} otherwise
     */
    public boolean retainAll(Collection<?> collection);

    /**
     * Removes all Elements from this IndexedList.
     */
    public void clear();
}
