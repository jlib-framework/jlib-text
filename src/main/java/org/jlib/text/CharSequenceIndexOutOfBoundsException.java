package org.jlib.text;

/**
 * Exception thrown when a {@link CharSequence} character index is out of
 * bounds.
 *
 * @author Igor Akkerman
 */
public class CharSequenceIndexOutOfBoundsException
extends IndexOutOfBoundsException {

    private static final long serialVersionUID = -4883064375950444974L;

    /** {@link CharSequence} for which the character index is out of bounds */
    private final CharSequence charSequence;

    /**
     * integer specifying the invalid character index that caused this
     * CharSequenceIndexOutOfBoundsException
     */
    private final int invalidCharacterIndex;

    /**
     * Creates a new CharSequenceIndexOutOfBoundsException for the specified
     * {@link CharSequence}.
     *
     * @param charSequence
     *        {@link CharSequence} for which the character index is out of
     *        bounds
     * @param invalidCharacterIndex
     *        integer specifying the invalid character index
     */
    public CharSequenceIndexOutOfBoundsException(final CharSequence charSequence, final int invalidCharacterIndex) {
        this.charSequence = charSequence;
        this.invalidCharacterIndex = invalidCharacterIndex;
    }

    /**
     * Returns the {@link CharSequence} for which the character index is
     * out of bounds.
     *
     * @return {@link CharSequence} for which the character index is out
     *         of bounds
     */
    public CharSequence getCharSequence() {
        return charSequence;
    }

    /**
     * Returns the invalid character index that caused this
     * CharSequenceIndexOutOfBoundsException.
     *
     * @return integer specifying the invalid character index
     */
    public int getInvalidCharacterIndex() {
        return invalidCharacterIndex;
    }


    @Override
    public String getMessage() {
        return "[\'" + charSequence + "\', " + invalidCharacterIndex + "]";
    }
}
