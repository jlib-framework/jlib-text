package org.jlib.container.sequence;

import java.util.NoSuchElementException;

/**
 * Skeletal implementation of a {@link SequenceIterator} using
 * {@link SequenceIteratorState} instances.
 * 
 * @param <Element>
 *        type of the elements held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public class AbstractSequenceStateIterator<Element>
extends AbstractSequenceIterator<Element> {

    /** current {@link SequenceIteratorState} */
    private SequenceIteratorState<Element> currentState;

    /**
     * Creates a new {@link AbstractSequenceStateIterator}.
     * 
     * @param initialState
     *        initial {@link SequenceIteratorState}
     */
    protected AbstractSequenceStateIterator(final SequenceIteratorState<Element> initialState) {
        super();

        currentState = initialState;
    }

    /**
     * Registers the current {@link SequenceIteratorState} of this
     * {@link AbstractSequenceStateIterator}.
     * 
     * @param currentState
     *        current {@link SequenceIteratorState}
     */
    protected void setCurrentState(final SequenceIteratorState<Element> currentState) {
        this.currentState = currentState;
    }

    @Override
    public final boolean hasPrevious() {
        return currentState.hasPrevious();
    }

    @Override
    public final Element previous()
    throws NoSuchElementException {
        final Element previousElement = currentState.previous();

        currentState = currentState.getPreviousState();

        return previousElement;
    }

    @Override
    public final boolean hasNext() {
        return currentState.hasNext();
    }

    @Override
    public final Element next()
    throws NoSuchElementException {
        final Element nextElement = currentState.next();

        currentState = currentState.getNextState();

        return nextElement;
    }
}
