/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    IOUtility.java
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

package org.jlib.core.io;

import static org.jlib.core.util.number.NumberUtility.parseHexDigit;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class providing static methods for input/output operations.
 * 
 * @author Igor Akkerman
 */
public abstract class IOUtility {

    /**
     * <p>
     * Byte order: little-endian (Intel) or big-endian (Network).
     * </p>
     * <p>
     * In the little-endian (Intel) representation, the number starts with the least significant
     * byte. For example, the hex representation for 0x1A2B3C4D is "4D3C2B1A".
     * </p>
     * <p>
     * In the little-endian (network) representation, the number starts with the most significant
     * byte. For example, the hex representation for 0x1A2B3C4D is "1A2B3C4D".
     * </p>
     * <p>
     * The order does usually not apply to the order of the bits within a byte, which is always
     * big-endian.
     * </p>
     */
    public enum ByteOrder {
        /** little endian */
        LITTLE_ENDIAN,

        /** big endian */
        BIG_ENDIAN
    }

    /**
     * <p>
     * Converts the specified signed byte value into an unsigned integer value taking in account the
     * sign of the byte.
     * </p>
     * <p>
     * Many stream operations like the {@link OutputStream#write(int)} method require a byte to be
     * specified by an integer. Using this method, an unsigned byte can be converted into such an
     * integer.
     * </p>
     * 
     * @param signedByte
     *        the signed byte value (-128 to 127)
     * @return the unsigned integer value (0 to 255)
     */
    public static int toUnsignedInt(byte signedByte) {
        return signedByte >= 0 ? signedByte : signedByte + 256;
    }

    /**
     * <p>
     * Converts the specified unsigned integer value into an signed byte value taking in account the
     * sign of the byte.
     * </p>
     * <p>
     * The {@link InputStream#read() read()} method of the {@link InputStream} class returns a byte
     * as an integer. Using this method, such an integer can be converted into a signed byte.
     * </p>
     * <p>
     * This method has the same functionality as a simple cast to a byte.
     * </p>
     * 
     * @param unsignedInt
     *        the unsigned integer value of a signed byte (0 to 255). The 24 higher-order bits of
     *        this integer are ignored.
     * @return the signed byte value (-128 to 127)
     */
    public static byte toSignedByte(int unsignedInt) {
        return (byte) unsignedInt;
        // identical functionality to:
        // return (byte) (unsignedInt <= 127 ? unsignedInt : unsignedInt - 256);
    }

    /**
     * Parses a hexadecimal number out of the specified signed byte array containing the hexadecimal
     * digit characters into a signed byte.
     * 
     * @param hexCharacters
     *        array of bytes containing the hexadecimal digit characters
     * @return byte specifying the parsed value
     * @throws IllegalArgumentException
     *         if {@code hexCharacters.length < 0 || hexCharacters.length > 2}
     * @throws NumberFormatException
     *         if the array contains illegal characters
     */
    public static byte parseHexNumberToByte(byte[] hexCharacters)
    throws NumberFormatException {
        return parseHexNumberToByte(hexCharacters, 0, hexCharacters.length);
    }

    /**
     * Parses a hexadecimal number out of the specified signed byte array containing the hexadecimal
     * digit characters, starting at the specified offset, into a signed byte. The character order
     * is assumed to be big-endian, that is, {@code hexCharacter[offset]} holds the most significant
     * hexadecimal character.
     * 
     * @param hexCharacters
     *        array of bytes containing the hexadecimal digit characters
     * @param offset
     *        integer specifying the offset in the array to start from
     * @param length
     *        integer specifying the number of characters to parse
     * @return byte specifying the parsed value
     * @throws IllegalArgumentException
     *         if {@code length < 0 || length > 2}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code offset + length > hexCharacters.length}
     * @throws NumberFormatException
     *         if the array contains illegal characters at the parsed indexes
     */
    public static byte parseHexNumberToByte(byte[] hexCharacters, int offset, int length)
    throws IllegalArgumentException, NumberFormatException {
        if (length < 0 || length > 2)
            throw new IllegalArgumentException("length=" + length);

        int value = 0;
        int digit;
        for (int hexCharacterIndex = offset + length - 1, bitOffset = 0; hexCharacterIndex >= offset; hexCharacterIndex --, bitOffset += 4) {
            digit = parseHexDigit((char) hexCharacters[hexCharacterIndex]);
            value |= (digit << bitOffset);
        }

        return toSignedByte(value);
    }
}
