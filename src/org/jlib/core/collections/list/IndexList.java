/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    IndexList.java
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
import java.util.RandomAccess;


/**
 * <p>
 * Ordered random access sequence with minimum and maximum indices.
 * <p>
 * <p>
 * Since the {@link List} interface of the Java Collections Framework is zero-based, this interface
 * extends {@link Collection} instead.
 * </p>
 *
 * @param <Element>
 *        type of the elements held in the IndexList
 * @author Igor Akkerman
 */
public interface IndexList<Element>
extends Collection<Element>, RandomAccess {

    /**
     * Returns the Element stored at the specified index in this IndexList.
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
     * Returns the minimum index of this IndexList.
     *
     * @return int specifying the minimum index
     */
    public int getMinIndex();

    /**
     * Returns the maximum index of this IndexList.
     *
     * @return int specifying the maximum index
     */
    public int getMaxIndex();

    /**
     * Returns the index of the first occurence of the specified Element in this IndexList.
     *
     * @param element
     *        Element to find
     * @return integer specifying the index of the first occurence of {@code element}
     */
    public int indexOf(Element element);

    /**
     * Returns the index of the first occurence of the specified Element in this IndexList.
     *
     * @param element
     *        Element to find
     * @return integer specifying the index of the first occurence of {@code element}
     */
    public int lastIndexOf(Element element);

    /**
     * Returns a List containing all Elements stored in this IndexList.
     *
     * @return List containing the Elements stored in this IndexList
     */
    public List<Element> toList();

    /**
     * Returns a List containing the Elements stored in this IndexList in the specified index range.
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
     * Returns an IndexList containing the Elements stored in this IndexList in the specified index
     * range. The Elements in the result IndexList will have the same index as they had in this
     * IndexList.
     *
     * @param fromIndex
     *        integer specifying the index of the first Element
     * @param toIndex
     *        integer specifying the index of the last Element
     * @return IndexList containing the specified Elements
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ListIndexOutOfBoundsException
     *         if {@code fromIndex < getMinIndex() || toIndex > getMaxIndex()}
     */
    public IndexList<Element> subIndexList(int fromIndex, int toIndex)
    throws IllegalArgumentException, ListIndexOutOfBoundsException;

    /**
     * Returns an Iterator traversing the Elements of this IndexList in proper sequence.
     *
     * @return Iterator over this IndexList
     */
    public Iterator<Element> iterator();

    /**
     * Returns a ListIterator traversing the Elements of this IndexList in proper sequence.
     * Initially, the ListIterator points to the beginning of this IndexList, that is, the Element
     * returned by the first call to {@code nextIndex()} is the Element stored at
     * {@code getMinIndex()}.
     *
     * @return ListIterator over this IndexList initially pointing to the beginning of this
     *         IndexList
     */
    public ListIterator<Element> listIterator();

    /**
     * Returns a ListIterator traversing the Elements of this IndexList in proper sequence.
     * Initially, the Element returned by the first call to {@code next()} is the Element stored at
     * the specified index.
     *
     * @param index
     *        integer specifying the index of the Element returned by the first call to
     *        {@code next()}
     * @return ListIterator over this IndexList initially pointing to the specified Element
     */
    public ListIterator<Element> listIterator(int index);

    /**
     * Returns whether the specified Object is an IndexList with the same minimum and maximum
     * indices and contains equal Elements at the same indices. Two Elements {@code element1} and
     * {@code element2} are equal if and only if both are {@code null} or both are equal by the
     * {@code equals()} method.
     *
     * @param indexList
     *        Object to compare to this IndexList
     * @return {@code true} if {@code indexList} is equal to this IndexList; {@code false} otherwise
     */
    public boolean equals(Object indexList);
}
