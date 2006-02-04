/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    QuotedPrintableUtility.java
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

import org.jlib.core.util.number.NumberUtility;

/**
 * Utility class for quoted printable encoding operations.
 * 
 * @author Igor Akkerman
 */
public abstract class QuotedPrintableUtility {

    /** no constructor */
    private QuotedPrintableUtility() {}

    /**
     * Creates an octet for the specified value.
     * 
     * @param value
     *        byte specifying the value of the octet to create
     * @return byte array containing the octet characters
     */
    public static byte[] createOctet(byte value) {
        int intValue = NumberUtility.toUnsignedInt(value);

        byte[] octet = new byte[3];
        octet[0] = '=';
        octet[1] = NumberUtility.HEXCHARACTERS[(intValue & 0xF0) >> 4];
        octet[2] = NumberUtility.HEXCHARACTERS[intValue & 0x0F];

        return octet;
    }
}
