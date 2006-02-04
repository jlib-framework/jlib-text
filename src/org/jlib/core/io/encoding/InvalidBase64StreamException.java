/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    InvalidBase64StreamException.java
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

import java.io.IOException;

/**
 * Exception thrown if a stream is an invalid base64 stream.
 *
 * @author Igor Akkerman
 */
public abstract class InvalidBase64StreamException
extends IOException {

    /**
     * Creates a new InvalidBase64StreamException.
     */
    protected InvalidBase64StreamException() {
        super();
    }
}
