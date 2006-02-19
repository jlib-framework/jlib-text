/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    ArrayIndexOutOfBoundsException.java
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

package org.jlib.core.collections.array;

import org.jlib.core.collections.ListIndexOutOfBoundsException;

/**
 * Exception thrown when a jlib Array is accessed with an illegal index. The index is either lower than
 * {@link Array#getMinIndex()} or greater than {@link Array#getMaxIndex()}.
 *
 * @author Igor Akkerman
 */
public class ArrayIndexOutOfBoundsException
extends ListIndexOutOfBoundsException {

    /** no default constructor */
    private ArrayIndexOutOfBoundsException() { super(0); };

    /**
     * Creates a new ArrayIndexOutOfBoundsException with the specified illegal index.
     *
     * @param index
     *        integer specifying the illegal index
     */
    ArrayIndexOutOfBoundsException(int index) {
        super(index);
    }

    /**
     * Creates a new ArrayIndexOutOfBoundsException with the specified illegal index and the
     * specified message.
     *
     * @param index
     *        integer specifying the illegal index
     * @param message
     *        String specifying the message
     */
    public ArrayIndexOutOfBoundsException(int index, String message) {
        super(index, message);
    }
}
