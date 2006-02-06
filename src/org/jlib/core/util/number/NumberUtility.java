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

import org.jlib.core.util.string.StringUtility;
import org.jlib.core.util.string.StringUtility.PaddingType;

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
     * Parses a hexadecimal number out of the specified signed byte array containing the
     * hexadecimal digit characters into a signed byte.
     *
     * @param hexCharacters
     *        array of bytes containing the hexadecimal digit characters
     *
     * @return byte specifying the parsed value
     *
     * @throws NumberFormatException
     *         if the array contains illegal characters
     */
    public static byte parseHexNumberToByte(byte[] hexCharacters)
    throws NumberFormatException {
        return parseHexNumberToByte(hexCharacters, hexCharacters.length);
    }

    /**
     * Parses a hexadecimal number out of the specified signed byte array containing the
     * hexadecimal digit characters into a signed byte.
     *
     * @param hexCharacters
     *        array of bytes containing the hexadecimal digit characters
     * @param length
     *        integer specifying the number of integers to convert
     *
     * @return byte specifying the parsed value
     *
     * @throws NumberFormatException
     *         if the array contains illegal characters
     */
    public static byte parseHexNumberToByte(byte[] hexCharacters, int length)
    throws NumberFormatException {
        return parseHexNumberToByte(hexCharacters, length);
    }

    /**
     * Parses a hexadecimal number out of the specified signed byte array containing the
     * hexadecimal digit characters starting at the specified offset into a signed byte.
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
        for (int hexCharacterIndex = offset + length - 1, j = 0; hexCharacterIndex >= offset; hexCharacterIndex --, j += 4) {
            digit = parseHexDigit((char) hexCharacters[hexCharacterIndex]);
            value |= (digit << j);
        }

        return toSignedByte(value);
    }

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
        return StringUtility.pad(Integer.toString(integer), length, ' ', PaddingType.front);
    }
}
