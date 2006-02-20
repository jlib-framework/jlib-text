/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    IndexedList.java
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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jlib.core.collections.ListIndexOutOfBoundsException;

/**
 * <p>
 * Ordered sequence with minimum and maximum indices.
 * <p>
 * <p>
 * Since the {@link List} interface of the Java Collections Framework is zero-based, this interface
 * extends {@link Collection} instead.
 * </p>
 *
 * @param <Element>
 *        type of the elements held in the IndexedList
 * @author Igor Akkerman
 */
public interface IndexedList<Element>
extends Collection<Element> {

    /**
     * Returns the Element stored at the specified index in this IndexedList.
     *
     * @param index
     *        integer specifying the index of the stored Element
     * @return Element stored at {@code index}
     * @throws IndexOutOfBoundsException
     *         if {@code index < getMinIndex() || index > getMaxIndex()}
     */
    public Element get(int index)
    throws IndexOutOfBoundsException;

    /**
     * Returns the minimum index of this IndexedList.
     *
     * @return int specifying the minimum index
     */
    public int getMinIndex();

    /**
     * Returns the maximum index of this IndexedList.
     *
     * @return int specifying the maximum index
     */
    public int getMaxIndex();

    /**
     * Returns the index of the first occurence of the specified Element in this IndexedList.
     *
     * @param element
     *        Element to find
     * @return integer specifying the index of the first occurence of {@code element}
     */
    public int indexOf(Element element);

    /**
     * Returns the index of the first occurence of the specified Element in this IndexedList.
     *
     * @param element
     *        Element to find
     * @return integer specifying the index of the first occurence of {@code element}
     */
    public int lastIndexOf(Element element);

    /**
     * Returns a List containing all Elements stored in this IndexedList.
     *
     * @return List containing the Elements stored in this IndexedList
     */
    public List<Element> toList();

    /**
     * Returns a List containing the Elements stored in this IndexedList in the specified index
     * range.
     *
     * @param fromIndex
     *        integer specifying the index of the first Element
     * @param toIndex
     *        integer specifying the index of the last Element
     * @return List containing the specified Elements
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ListIndexOutOfBoundsException
     *         if {@code fromIndex < getMinIndex() || toIndex > getMaxIndex()}
     */
    public List<Element> subList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException;

    /**
     * Returns an IndexedList containing the Elements stored in this IndexedList in the specified
     * index range. The Elements in the result IndexedList will have the same index as they had in
     * this IndexedList.
     *
     * @param fromIndex
     *        integer specifying the index of the first Element
     * @param toIndex
     *        integer specifying the index of the last Element
     * @return IndexedList containing the specified Elements
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ListIndexOutOfBoundsException
     *         if {@code fromIndex < getMinIndex() || toIndex > getMaxIndex()}
     */
    public IndexedList<Element> subIndexedList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException;

    /**
     * Returns an Iterator traversing the Elements of this IndexedList in proper sequence.
     *
     * @return Iterator over this IndexedList
     */
    public Iterator<Element> iterator();

    /**
     * <p>
     * Returns a ListIterator traversing the Elements of this IndexedList in proper sequence.
     * Initially, the ListIterator points to the beginning of this IndexedList, that is, the Element
     * returned by the first call to {@code nextIndex()} is the Element stored at
     * {@code getMinIndex()}.
     * </p>
     * <p>
     * The indexes returned by {@link ListIterator#nextIndex()} and
     * {@link ListIterator#previousIndex()} return the proper indices used in this IndexedList.
     * {@code previousIndex()} returns {@code getMinIndex() - 1} if the ListIterator points to the
     * beginning of this IndexedList.
     * </p>
     *
     * @return ListIterator over this IndexedList initially pointing to the beginning of this
     *         IndexList
     */
    public ListIterator<Element> listIterator();

    /**
     * <p>
     * Returns a ListIterator traversing the Elements of this IndexedList in proper sequence.
     * Initially, the Element returned by the first call to {@code nextIndex()} is the Element
     * stored at the specified index.
     * </p>
     * <p>
     * The indexes returned by {@link ListIterator#nextIndex()} and
     * {@link ListIterator#previousIndex()} return the proper indices used in this IndexedList.
     * {@code previousIndex()} returns {@code getMinIndex() - 1} if the ListIterator points to the
     * beginning of this IndexedList.
     * </p>
     *
     * @param nextElementIndex
     *        index of the Element returned by the first call to {@code previousIndex()}
     * @return ListIterator over this IndexedList initially pointing to the specified Element
     */
    public ListIterator<Element> listIterator(int nextElementIndex);

    /**
     * Returns whether the specified Object is an IndexedList with the same minimum and maximum
     * indices and contains equal Elements at the same indices. Two Elements {@code element1} and
     * {@code element2} are equal if and only if both are {@code null} or both are equal by the
     * {@code equals()} method.
     *
     * @param indexedList
     *        Object to compare to this IndexedList
     * @return {@code true} if {@code indexedList} is equal to this IndexedList; {@code false}
     *         otherwise
     */
    public boolean equals(Object indexedList);
}
