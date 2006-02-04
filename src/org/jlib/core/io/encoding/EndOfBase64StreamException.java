/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    EndOfBase64StreamException.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.io.encoding;

/**
 * Exception thrown if the end of a base64 coded stream was reached. This Exception does not signal an error.
 * 
 * @author Igor Akkerman
 */
public class EndOfBase64StreamException
extends Exception {

    /**
     * Creates a new EndOfBase64StreamException.
     */
    public EndOfBase64StreamException() {
        super();
    }
}
