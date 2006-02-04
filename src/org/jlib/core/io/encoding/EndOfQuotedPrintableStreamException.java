/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    EndOfQuotedPrintableStreamException.java
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
 * Exception thrown if the end of a quoted-printable coded stream was reached.
 * This exception does not signal an error.
 *
 * @author Igor Akkerman
 */
public class EndOfQuotedPrintableStreamException
extends IOException {

    /**
     * Creates a new EndOfQuotedPrintableStreamException.
     */
    public EndOfQuotedPrintableStreamException() {
        super();
    }
}
