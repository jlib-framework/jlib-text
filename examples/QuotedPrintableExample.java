import static org.jlib.core.io.encoding.quotedprintable.QuotedPrintableUtility.decodeQuotedPrintable;
import static org.jlib.core.io.encoding.quotedprintable.QuotedPrintableUtility.encodeQuotedPrintable;

/**
 * Example showing how easy and handy it is to encode and decode streams and byte array using the
 * {@code QuotedPrintableUtility}.
 * 
 * @author Igor Akkerman
 */
public abstract class QuotedPrintableExample {

    /**
     * Performs various quoted-printable encodings and decodings with the specified command line
     * arguments.
     * 
     * @param commandLineArguments
     *        array of Strings to encode and decode
     */
    public static void main(String[] commandLineArguments) {
        if (commandLineArguments.length == 0)
            System.out.println("Usage: java QuotedPrintableExample [string1 [string2 ...]]");

        try {
            String inputString;
            String outputString;
            String qpString;

            for (int index = 0; index < commandLineArguments.length; index ++) {

                inputString = commandLineArguments[index];
                System.out.println(inputString);

                // encoding
                qpString = encodeQuotedPrintable(inputString.getBytes());
                System.out.println(qpString);

                // decoding
                byte[] decodedBytes = decodeQuotedPrintable(qpString);
                outputString = new String(decodedBytes, 0, decodedBytes.length);
                System.out.println(outputString);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
