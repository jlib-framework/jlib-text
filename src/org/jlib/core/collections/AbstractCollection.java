/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.collections;

import java.util.Iterator;
import java.util.LinkedList;

import org.jlib.core.common.ObjectUtility;

/**
 * Skeletal implementation of a Collection. A concrete Collection implementation
 * needs only to extend this class and implement the
 * {@link Collection#iterator()} method. Other methods may be overridden for
 * efficiency reasons.
 * 
 * @param <Element>
 *        type of elements held in the Collection
 * @author Igor Akkerman
 */
public abstract class AbstractCollection<Element>
implements Collection<Element> {

    /**
     * Creates a new empty Collection.
     */
    protected AbstractCollection() {
        super();
    }
    
    // @see org.jlib.core.collections.Collection#contains(java.lang.Object)
    @Override
    public boolean contains(Object object) {
        for (Object collectionObject : this)
            if (collectionObject.equals(object))
                return true;
        return false;
    }

    // @see org.jlib.core.collections.Collection#containsAll(org.jlib.core.collections.Collection)
    @Override
    public boolean containsAll(Collection<?> otherCollection) {
        return containsAll((Iterable<?>) otherCollection);
    }

    // @see org.jlib.core.collections.Collection#containsAll(java.lang.Object[])
    @Override
    public boolean containsAll(Object... objects) {
        for (Object object : objects)
            if (!contains(object))
                return false;
        return true;
    }

    // @see org.jlib.core.collections.Collection#containsAll(org.jlib.core.collections.Collection)
    @Override
    public boolean containsAll(java.util.Collection<?> javaCollection) {
        return containsAll((Iterable<?>) javaCollection);
    }

    /**
     * Returns whether this Collection contains all of the Elements returned by
     * the Iterator of the specified Iterable.
     * 
     * @param iterable
     *        Iterable creating the Iterator returning the Elements to verify
     * @return {@code true} if this Collection contains all of the Elements
     *         contained by {@code otherCollection}; {@code false} otherwise
     */
    private boolean containsAll(Iterable<?> iterable) {
        for (Object object : iterable)
            if (!contains(object))
                return false;
        return true;
    }

    // @see org.jlib.core.collections.Collection#toArray()
    @Override
    @SuppressWarnings("unchecked")
    public Element[] toArray() {
        int size = size();
        Object[] array = new Object[size];
        int index = 0;
        for (Element element : this)
            array[index ++] = element;
        return (Element[]) array;
    }

    // @see org.jlib.core.collections.Collection#toJavaCollection()
    @Override
    public java.util.Collection<Element> toJavaCollection() {
        java.util.Collection<Element> javaCollection = new LinkedList<Element>();
        for (Element element : this)
            javaCollection.add(element);
        return javaCollection;
    }

    // @see java.lang.Object#equals(java.lang.Object)
    @Override
    public boolean equals(Object otherObject) {
        if (!getClass().equals(otherObject.getClass()) || otherObject.hashCode() != hashCode())
            return false;
        Collection<?> otherCollection = (Collection<?>) otherObject;
        Iterator<?> thisIterator = otherCollection.iterator();
        Iterator<?> otherIterator = otherCollection.iterator();
        boolean thisHasNext;
        while (true) {
            thisHasNext = thisIterator.hasNext();
            if (thisHasNext != otherIterator.hasNext())
                return false;
            if (!thisHasNext)
                return true;
            if (!ObjectUtility.equalOrNull(thisIterator.next(), otherIterator.next()))
                return false;
        }
    }

    // @see java.lang.Object#hashCode()
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Element element : this)
            hashCode += element != null ? element.hashCode() : 0;
        return hashCode;
    }

    // @see org.jlib.core.collections.Collection#isEmpty()
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
