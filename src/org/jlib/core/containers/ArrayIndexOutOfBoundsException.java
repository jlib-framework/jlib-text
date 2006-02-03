/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    ArrayIndexOutOfBoundsException.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 by Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.containers;

/**
 * Exception thrown when a jlib Array is accessed with an illegal index. The index is either lower than
 * {@link Array#getMinIndex()} or greater than {@link Array#getMaxIndex()}.
 * 
 * @author Igor Akkerman
 */
public class ArrayIndexOutOfBoundsException
extends IndexOutOfBoundsException {

    /** illegal index */
    private int index;

    /** no default constructor */
    private ArrayIndexOutOfBoundsException() {};

    /**
     * Creates a new ArrayIndexOutOfBoundsException with the specified message.
     * 
     * @param message
     *        String specifying the message
     */
    ArrayIndexOutOfBoundsException(String message) {
        super(message);

    }

    /**
     * Creates a new ArrayIndexOutOfBoundsException with the specified illegal index.
     * 
     * @param index
     *        integer specifying the illegal index
     */
    public ArrayIndexOutOfBoundsException(int index) {
        super(String.valueOf(index));
        this.index = index;

    }

    /**
     * Returns the illegal index of this ArrayIndexOutOfBoundsException.
     * 
     * @return int specifying the illegal index
     */
    public int getIndex() {
        return index;
    }
}
