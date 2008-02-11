/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    CollectionAdapter.java
 * Project: jlib.core
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

import org.jlib.core.collections.list.EditableList;

/**
 * <p>
 * Adapter allowing an immutable jlib Collection to be used as an immutable Java
 * Collection. This Collection is backed by the jlib Collection.
 * </p>
 * <p>
 * Having a Java Collection backing an immutable jlib Collections makes sense
 * for a Collection like an {@link EditableList}, where the contained Elements
 * may change even though no Element can be added to the List or removed from
 * it.
 * </p>
 * 
 * @param <Element>
 *        type of the elements held in the Collection
 * @author Igor Akkerman
 */
public class ImmutableCollection<Element>
extends java.util.AbstractCollection<Element>
implements Collection<Element>, java.util.Collection<Element> {

    /** adapted jlib Collection */
    private final Collection<Element> collection;

    /**
     * Creates a new ImmutableCollection for the specified jlib Collection.
     * 
     * @param collection
     *        jlib Collection to adapt
     */
    public ImmutableCollection(Collection<Element> collection) {
        super();
        this.collection = collection;
    }

    // @see java.util.AbstractCollection#iterator()
    @Override
    public Iterator<Element> iterator() {
        return collection.iterator();
    }

    // @see java.util.AbstractCollection#size()
    @Override
    public int size() {
        return collection.size();
    }

    // @see java.util.AbstractCollection#contains(java.lang.Object)
    public boolean contains(Object object) {
        return collection.contains(object);
    }

    // @see org.jlib.core.collections.Collection#containsAll(org.jlib.core.collections.Collection)
    @Override
    public boolean containsAll(Collection<?> otherCollection) {
        return collection.containsAll(otherCollection);
    }

    // @see org.jlib.core.collections.Collection#containsAll(java.lang.Object[])
    @Override
    public boolean containsAll(Object... objects) {
        return collection.containsAll(objects);
    }

    // @see java.util.AbstractCollection#containsAll(java.util.Collection)
    public boolean containsAll(java.util.Collection<?> javaCollection) {
        return collection.containsAll(javaCollection);
    }

    // @see java.util.AbstractCollection#isEmpty()
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    // @see java.util.AbstractCollection#toArray()
    @SuppressWarnings("unchecked")
    public Element[] toArray() {
        return (Element[]) super.toArray();
    }

    // @see java.lang.Object#equals(java.lang.Object)
    public boolean equals(Object otherObject) {
        return collection.equals(otherObject);
    }

    // @see org.jlib.core.collections.Collection#toJavaCollection()
    @Override
    public java.util.Collection<Element> toJavaCollection() {
        return collection.toJavaCollection();
    }
}
