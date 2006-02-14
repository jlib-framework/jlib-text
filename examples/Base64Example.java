import static org.jlib.core.io.encoding.base64.Base64Utility.decodeBase64;
import static org.jlib.core.io.encoding.base64.Base64Utility.encodeBase64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.jlib.core.io.encoding.base64.Base64InputStream;
import org.jlib.core.io.encoding.base64.Base64OutputStream;

/**
 * Example showing how easy and handy it is to encode and decode streams and byte array using the
 * {@code Base64InputStream} and {@code Base64OutputStream} classes and the {@code Base64Utility}.
 * 
 * @author Igor Akkerman
 */
public abstract class Base64Example {

    /**
     * Gzips the specified byte array and encodes the result using base64 encoding.
     * 
     * @param inputBytes
     *        array of bytes containing the bytes to gzip and encode
     * @return String containing the base64 encoded output of the gzipped {@code inputBytes}
     * @throws IOException
     *         if an i/o exception occurs
     */
    public static String gzipAndEncodeBase64(byte[] inputBytes)
    throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        OutputStream base64OutputStream = new Base64OutputStream(outputStream);
        GZIPOutputStream zipOutputStream = new GZIPOutputStream(base64OutputStream);

        zipOutputStream.write(inputBytes);
        zipOutputStream.finish();
        zipOutputStream.flush();
        zipOutputStream.close();

        return outputStream.toString();
    }

    /**
     * Decodes the specified String using base64 decoding and ungzips the result.
     * 
     * @param base64EncodedGzippedString
     *        base64 encoded gzipped String
     * @return array of decoded und ungzipped bytes from {@code base64EncodedGzippedString}
     * @throws IOException
     *         if an i/o exception occurs
     */
    public static byte[] decodeBase64AndUnGZip(String base64EncodedGzippedString)
    throws IOException {
        InputStream inputStream = new ByteArrayInputStream(base64EncodedGzippedString.getBytes());
        InputStream base64InputStream = new Base64InputStream(inputStream);
        InputStream zipInputStream = new GZIPInputStream(base64InputStream);

        byte[] readBytes = new byte[1024];

        int len = zipInputStream.read(readBytes);
        if (len == -1) {
            len = 0;
        }
        zipInputStream.close();

        byte[] outputBytes = new byte[len];
        for (int i = 0; i < len; i ++) {
            outputBytes[i] = readBytes[i];
        }

        return (byte[]) outputBytes;
    }

    /**
     * Performs various base64 encodings and decodings with the specified command line arguments.
     * 
     * @param commandLineArguments
     *        array of Strings to encode and decode
     */
    public static void main(String[] commandLineArguments) {

        if (commandLineArguments.length == 0)
            System.out.println("Usage: java Base64Example [string1 [string2 ...]]");

        try {
            String inputString;
            String outputString;
            String base64String;
            String gzippedAndBase64EncodedString;

            for (int index = 0; index < commandLineArguments.length; index ++) {

                // get next argument
                inputString = commandLineArguments[index];
                System.out.println("Input string:                 " + inputString);

                // encoding
                base64String = encodeBase64(inputString.getBytes());
                System.out.println("Base64 encoded:               " + base64String);

                // decoding
                outputString = new String(decodeBase64(base64String));
                System.out.println("Base64 decoded:               " + outputString);

                // as an alternative, zencode and zdecode can be used,
                // which zip/unzip the stream before/after encoding/decoding it

                // gzip and encoding
                gzippedAndBase64EncodedString = gzipAndEncodeBase64(inputString.getBytes());
                System.out.println("Gzipped and base64 encoded:   " + gzippedAndBase64EncodedString);

                // decoding and gunzip
                outputString = new String(decodeBase64AndUnGZip(gzippedAndBase64EncodedString));
                System.out.println("Base64 decoded and ungzipped: " + outputString);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
