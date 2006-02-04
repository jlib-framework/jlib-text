/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    NumberUtility.java
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

package org.jlib.core.util.number;

/**
 * Utility class providing static methods for Number operations and manipulations.
 *
 * @author Igor Akkerman
 */
public class NumberUtility {

    /** hexadecimal characters */
    public static final byte[] HEXCHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /** no default constructor */
    private NumberUtility() {}

    /**
     * Converts the specified signed byte value into an unsigned integer value
     * respecting the sign of the byte.
     *
     * @param signedByte
     *        the signed byte value (-128 to 127)
     *
     * @return the unsigned integer value (0 to 255)
     */
    public static int toUnsignedInt(byte signedByte) {
        return signedByte >= 0 ? signedByte : 256 + signedByte;
    }

    /**
     * Converts the specified unsigned integer value into an signed byte value
     * respecting the sign of the byte.
     *
     * @param unsignedInt
     *        the unsigned integer value (0 to 255)
     *
     * @return the signed byte value (-128 to 127)
     */
    public static byte toSignedByte(int unsignedInt) {
        return (byte) (unsignedInt <= 127 ? unsignedInt : unsignedInt - 256);
    }

    /**
     * Parses a hexadecimal number out of a signed byte array containing the
     * hexadecimal digit characters into a signed byte.
     *
     * @param hexCharacters
     *        array of bytes containing the hexadecimal digit characters
     * @param offset
     *        integer specifying the offset in the array to start from
     * @param length
     *        integer specifying the number of integers to convert
     *
     * @return byte specifying the parsed value
     *
     * @throws NumberFormatException
     *         if the array contains illegal characters
     */
    public static byte parseHexNumberToByte(byte[] hexCharacters, int offset, int length)
    throws NumberFormatException {
        int value = 0;
        int digit;
        for (int i = offset + length - 1, j = 0; j < length; i--, j++) {
            digit = hexCharacters[i];
            if (digit >= '0' && digit <= '9') {
                digit -= '0';
            }
            else if (digit >= 'A' && digit <= 'F') {
                digit = (digit - 'A') + 10;
            }
            else if (digit >= 'a' && digit <= 'f') {
                digit = (digit - 'a') + 10;
            }
            else {
                throw new NumberFormatException("" + (char) digit);
            }
            value |= (digit << (4 * j));
        }

        return toSignedByte(value);
    }
}
