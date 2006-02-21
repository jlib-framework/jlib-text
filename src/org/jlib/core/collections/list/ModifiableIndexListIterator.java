/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    ModifiableIndexListIterator.java
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
 * EditableIndexListIterator over a modifiable random access IndexList.
 *
 * @param <Element>
 *        type of the elements held in the IndexList
 * @author Igor Akkerman
 */
public class ModifiableIndexListIterator<Element>
extends EditableIndexListIterator<Element>
implements ModifiableListIterator<Element> {

    /** EditableIndexList traversed by this Iterator */
    private ModifiableIndexList<Element> list;

    /** ready for modifying operation (add/remove) */
    private boolean modificationReady;

    /** no default constructor */
    private ModifiableIndexListIterator() {
        super(new Array<Element>());
    }

    /**
     * Creates a new ModifiableIndexListIterator for the specified ModifiableIndexList.
     *
     * @param <List>
     *        type of the list
     * @param list
     *        EditableIndexList {@literal &} ModifiableIndexList to traverse
     */
    public <List extends EditableIndexList<Element> & ModifiableIndexList<Element>> ModifiableIndexListIterator(
                                                                                                                List list) {
        super(list);
        this.list = list;
    }

    /**
     * Creates a new ModifiableIndexListIterator for the specified ModifiableIndexList.
     *
     * @param <List>
     *        type of the list
     * @param list
     *        EditableIndexList {@literal &} ModifiableIndexList to traverse
     * @param index
     *        integer specifying the start index of the traversal
     */
    public <List extends EditableIndexList<Element> & ModifiableIndexList<Element>> ModifiableIndexListIterator(
                                                                                                                List list,
                                                                                                                int index) {
        super(list, index);
        this.list = list;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException
     *         if {@code next()} or {@code prev()} have not been called initially or after the last
     *         call to {@code add} or {@code remove()}
     */
    public void add(Element element)
    throws IllegalStateException {
        if (!modificationReady)
            throw new IllegalStateException();

        list.add(nextElementIndex ++, element);

        modificationReady = false;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException
     *         if {@code next()} or {@code prev()} have not been called initially or after the last
     *         call to {@code add} or {@code remove()}
     */
    @Override
    public void remove() {
        if (!modificationReady)
            throw new IllegalStateException();

        list.remove(getLastRetreivedElementIndex());

        modificationReady = false;
    }

    // @see org.jlib.core.collections.list.EditableIndexListIterator#next()
    @Override
    public Element next() {
        // this order in case of an exception
        Element nextElement = super.next();
        modificationReady = true;
        return nextElement;
    }

    // @see org.jlib.core.collections.list.EditableIndexListIterator#previous()
    @Override
    public Element previous() {
        // this order in case of an exception
        Element previousElement = super.previous();
        modificationReady = true;
        return previousElement;
    }
}
