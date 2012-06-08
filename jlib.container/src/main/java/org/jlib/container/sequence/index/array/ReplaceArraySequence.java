package org.jlib.container.sequence.index.array;

import org.jlib.container.sequence.IllegalSequenceArgumentException;
import org.jlib.container.sequence.IllegalSequenceStateException;
import org.jlib.container.sequence.ReplaceSequenceTraverser;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.index.IndexSequenceCreator;
import org.jlib.container.sequence.index.InvalidSequenceIndexRangeException;
import org.jlib.container.sequence.index.ObservedReplaceIndexSequence;
import org.jlib.container.sequence.index.ObservedReplaceIndexSequenceTraverser;
import org.jlib.container.sequence.index.ReplaceIndexSequenceTraverser;
import org.jlib.container.sequence.index.SequenceIndexOutOfBoundsException;
import org.jlib.core.observer.ItemObserver;

/**
 * {@link ArraySequence} allowing its Items to be replaced.
 * 
 * @param <Item>
 *        type of the items of the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public class ReplaceArraySequence<Item>
extends ArraySequence<Item>
implements ObservedReplaceIndexSequence<Item> {

    /**
     * {@link IndexSequenceCreator} of {@link ReplaceArraySequence} insstances
     */
    private static final IndexSequenceCreator<?, ? extends ReplaceArraySequence<?>> CREATOR =
        new IndexSequenceCreator<Object, ReplaceArraySequence<Object>>() {

            @Override
            public ReplaceArraySequence<Object> createSequence(final int firstIndex, final int lastIndex)
            throws InvalidSequenceIndexRangeException {
                return new ReplaceArraySequence<Object>(firstIndex, lastIndex);
            }
        };

    /**
     * Returns the {@link IndexSequenceCreator} of {@link ReplaceArraySequence}
     * instances.
     * 
     * @return {@link IndexSequenceCreator} of {@link ReplaceArraySequence}
     * 
     *         instances
     */
    @SuppressWarnings("unchecked")
    public static <Item> IndexSequenceCreator<Item, ? extends ReplaceArraySequence<Item>> getCreator() {
        return (IndexSequenceCreator<Item, ReplaceArraySequence<Item>>) CREATOR;
    }

    /**
     * Creates a new {@link ReplaceArraySequence}.
     * 
     * @param firstIndex
     *        first index of this {@link ReplaceArraySequence}
     * 
     * @param lastIndex
     *        last index of this {@link ReplaceArraySequence}
     */
    protected ReplaceArraySequence(final int firstIndex, final int lastIndex) {
        super(firstIndex, lastIndex);
    }

    @Override
    // raising visibility from protected to public
    public void replace(final int index, final Item item)
    throws SequenceIndexOutOfBoundsException {
        super.replace(index, item);
    }

    @Override
    public void replace(final int index, final Item newItem, final ItemObserver<Item>... observers)
    throws SequenceIndexOutOfBoundsException, IllegalSequenceArgumentException, IllegalSequenceStateException {
        for (final ItemObserver<Item> observer : observers)
            observer.handleBefore(newItem, this);

    }

    @Override
    public ReplaceIndexSequenceTraverser<Item> createReplaceIndexSequenceTraverser() {
        return null;
    }

    @Override
    public ReplaceIndexSequenceTraverser<Item> createReplaceIndexSequenceTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException {
        return null;
    }

    @Override
    public ReplaceSequenceTraverser<Item> createReplaceTraverser() {}

    @Override
    public ObservedReplaceIndexSequenceTraverser<Item> createTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException {
        return null;
    }

    @Override
    public ObservedReplaceIndexSequenceTraverser<Item> createObservedReplaceIndexSequenceTraverser()
    throws SequenceIndexOutOfBoundsException {
        return null;
    }

    @Override
    public ObservedReplaceIndexSequenceTraverser<Item> createObservedReplaceIndexSequenceTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException {
        return null;
    }
}
