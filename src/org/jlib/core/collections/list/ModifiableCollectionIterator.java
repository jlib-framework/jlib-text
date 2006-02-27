/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    ModifiableCollectionIterator.java
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

import java.util.Iterator;

/**
 * <p>
 * Iterator over a ModifiableCollection.
 * </p>
 * <p>
 * Simply adds to a jlib Iterator the behaviour of a Java Iterator, that is, the
 * {@link Iterator#remove() remove} operation.
 * </p>
 * 
 * @param <Element>
 *        type of elements held in the Collection
 * @author Igor Akkerman
 */
public interface ModifiableCollectionIterator<Element>
extends Iterator<Element> {
    // intentionally left blank
}
