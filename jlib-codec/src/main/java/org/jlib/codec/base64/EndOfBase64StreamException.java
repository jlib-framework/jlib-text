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

package org.jlib.codec.base64;

/**
 * Exception thrown if the end of a base64 coded stream was reached. This Exception does not signal an error.
 * 
 * @author Igor Akkerman
 */
public class EndOfBase64StreamException
extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = -4336342109627286232L;

    /**
     * Creates a new EndOfBase64StreamException.
     */
    public EndOfBase64StreamException() {
        super();
    }
}