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

import static org.jlib.core.util.number.NumberUtility.parseHexNumberToByte;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.jlib.core.util.number.NumberUtility;

/**
 * Utility class for quoted-printable encoding operations.
 * 
 * @author Igor Akkerman
 */
public abstract class QuotedPrintableUtility {

    /** no constructor */
    private QuotedPrintableUtility() {}

    /**
     * Encodes the specified byte array using quoted printable encoding.
     * 
     * @param inputBytes
     *        array of bytes containing the bytes to encode
     * @return String containing the quoted-printable encoded output of {@code inputBytes}
     * @throws IOException
     *         if an i/o exception occurs
     */
    public static String encodeQuotedPrintable(byte[] inputBytes)
    throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStream qpOutputStream = new QuotedPrintableOutputStream(outputStream);

        qpOutputStream.write(inputBytes);
        qpOutputStream.flush();
        qpOutputStream.close();

        return outputStream.toString();
    }

    /**
     * Decodes the specified String using quoted-printable decoding.
     * 
     * @param qpString
     *        quoted-printable encoded String
     * @return array of decoded bytes from {@code qpString}
     * @throws IOException
     *         if an i/o exception occurs
     */
    public static byte[] decodeQuotedPrintable(String qpString)
    throws IOException {
        InputStream inputStream = new ByteArrayInputStream(qpString.getBytes());
        InputStream qpInputStream = new QuotedPrintableInputStream(inputStream);

        byte[] readBytes = new byte[1024];

        int length = qpInputStream.read(readBytes);
        if (length == -1) {
            length = 0;
        }
        qpInputStream.close();

        byte[] outputBytes = new byte[length];
        for (int i = 0; i < length; i ++) {
            outputBytes[i] = readBytes[i];
        }

        return outputBytes;
    }

    /**
     * Creates an octet for the specified value.
     * 
     * @param value
     *        byte holding the value of the octet to create
     * @return array of bytes containing the octet characters
     */
    public static byte[] createOctet(byte value) {
        int intValue = NumberUtility.toUnsignedInt(value);

        byte[] octet = new byte[3];
        octet[0] = '=';
        octet[1] = NumberUtility.HEXCHARACTERS[(intValue & 0xF0) >> 4];
        octet[2] = NumberUtility.HEXCHARACTERS[intValue & 0x0F];

        return octet;
    }

    /**
     * Decodes an encoded octet from the specified array of bytes.
     * 
     * @param octetBytes
     *        array of bytes containing an octet
     * @return byte containing the octet value
     * @throws IllegalQuotedPrintableOctetException
     *         if illegal characters follow the encoding prefix
     */
    public static byte decodeOctet(byte[] octetBytes)
    throws IllegalQuotedPrintableOctetException {
        try {
            return parseHexNumberToByte(octetBytes, 1, 2);
        }
        catch (NumberFormatException nfe) {
            throw new IllegalQuotedPrintableOctetException(octetBytes[1], octetBytes[2]);
        }
    }

}
