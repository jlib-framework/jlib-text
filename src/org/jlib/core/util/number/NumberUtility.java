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
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.util.number;

import org.jlib.core.util.string.StringUtility;
import static org.jlib.core.util.string.StringUtility.PaddingType.FRONT;

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
     * Parses a hexadecimal digit of the specified character.
     * 
     * @param hexDigitCharacter
     *        character holding a hexadecimal digit
     * @return integer holding the value of hexDigitCharacter
     * @throws NumberFormatException
     *         if {@code hexDigitCharacter} does not hold a hexadecimal digit
     */
    public static int parseHexDigit(char hexDigitCharacter) {
        if (hexDigitCharacter >= '0' && hexDigitCharacter <= '9')
            return hexDigitCharacter - '0';
        else if (hexDigitCharacter >= 'A' && hexDigitCharacter <= 'F')
            return hexDigitCharacter - 'A' + 10;
        else if (hexDigitCharacter >= 'a' && hexDigitCharacter <= 'f')
            return hexDigitCharacter - 'a' + 10;
        else
            throw new NumberFormatException(Character.toString(hexDigitCharacter));
    }

    /**
     * Represents the specified integer to a String padded with the blank character at the front to
     * the specified length.
     * 
     * @param integer
     *        integer to represent as a padded String
     * @param length
     *        integer specifying the desired length of the String
     * @return padded String. If {@code string.length() >= length} then {@code string} is returned.
     */
    public static String toPaddedString(int integer, int length) {
        return StringUtility.pad(Integer.toString(integer), length, ' ', FRONT);
    }

    /**
     * Creates a binary String representation of the specified byte padded with zeroes. The bit
     * order is big-endian, that is, the most significant bit first, as usual in both big-endian and
     * little-endian byte order systems.
     * 
     * @param bite
     *        byte to represent as a binary String
     * @return binary String representation of {@code bite}
     */
    public static String toBinaryString(byte bite) {
        StringBuffer bitStringBuffer = new StringBuffer(8);

        for (int digitIndex = 7; digitIndex >= 0; digitIndex --)
            bitStringBuffer.append((bite >> digitIndex) & 1);

        return bitStringBuffer.toString();
    }
}
