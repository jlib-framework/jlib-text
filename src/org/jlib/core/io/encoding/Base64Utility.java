/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    Base64Utility.java
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
 * Utility class for base64 encoding operations.
 * 
 * @author Igor Akkerman
 */
public abstract class Base64Utility {

    /** base64 alphabet */
    public static final byte[] base64Alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /** base64 padding character */
    public static final byte PAD = '=';

    /** no constructor */
    private Base64Utility() {}
    
    /**
     * Returns the value represented by the specified base64 character.
     * 
     * @param base64Character
     *        integer specifying the base64 character
     * @return integer value represented by {@code base64Char}; -1 if {@code base64Char} is the padding
     *         character
     * @throws IllegalBase64CharacterException
     *         if the specified character does not belong to the base64 alphabet
     */
    public static int mapBase64Character(int base64Character)
    throws IllegalBase64CharacterException {

        if (base64Character >= 'A' && base64Character <= 'Z') {
            return base64Character - 'A';
        }

        if (base64Character >= 'a' && base64Character <= 'z') {
            return base64Character - 'a' + 26;
        }

        if (base64Character >= '0' && base64Character <= '9') {
            return base64Character - '0' + 52;
        }

        switch (base64Character) {
            case '+':
                return 62;
            case '/':
                return 63;
            case '=':
                return -1;
        }

        throw new IllegalBase64CharacterException(base64Character);
    }
}
