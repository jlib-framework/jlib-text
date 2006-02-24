/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    LegacyListIterator.java
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

/**
 * Iterator of a ModifiableList implementing the Java Iterator interface.
 *
 * @param <Element>
 *        type of elements held in the List
 * @author Igor Akkerman
 */
class LegacyModifiableListIterator<Element>
implements java.util.Iterator<Element> {

    /** backing Iterator */
    protected ModifiableListIterator<Element> backingIterator;

    /** no default constructor */
    private LegacyModifiableListIterator() {}

    /**
     * Creates a new Iterator over the Elements of the specified ModifiableList.
     *
     * @param list
     *        ModifiableList to traverse
     */
    LegacyModifiableListIterator(ModifiableList<Element> list) {
        super();
        backingIterator = list.modifiableListIterator();
    }

    // @see java.util.Iterator#hasNext()
    public boolean hasNext() {
        return backingIterator.hasNext();
    }

    // @see java.util.Iterator#next()
    public Element next() {
        return backingIterator.next();
    }

    // @see java.util.Iterator#remove()
    public void remove() {
        backingIterator.remove();
    }
}
