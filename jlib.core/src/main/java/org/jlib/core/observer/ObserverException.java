package org.jlib.core.observer;

import org.jlib.core.IllegalJlibStateException;

/**
 * {@link IllegalJlibStateException} thrown during the operation of an observer.
 * 
 * @author Igor Akkerman
 */
public abstract class ObserverException
extends IllegalJlibStateException {

    /**
     * Creates a new {@link ObserverException}.
     * 
     * @param messagePattern
     *        {@link String} specifying the message pattern
     * 
     * @param cause
     *        {@link Throwable} that caused this {@link ObserverException}
     * 
     * @param messageArguments
     *        comma separated sequence of {@link Object} message arguments
     */
    public ObserverException(final String messagePattern, final Throwable cause, final Object... messageArguments) {
        super(messagePattern, cause, messageArguments);
    }
}