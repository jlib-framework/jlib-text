package org.jlib.container.sequence.index.array;

import java.util.Collection;

import org.jlib.container.Container;
import org.jlib.container.IllegalContainerArgumentException;
import org.jlib.container.sequence.ObservedAppendSequence;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.index.DefaultReplaceIndexSequenceTraverser;
import org.jlib.container.sequence.index.IndexSequenceCreator;
import org.jlib.container.sequence.index.InvalidSequenceIndexRangeException;
import org.jlib.container.sequence.index.ReplaceIndexSequenceTraverser;
import org.jlib.core.observer.ValueObserver;

import static org.jlib.container.sequence.SequenceUtility.singleton;
import static org.jlib.core.array.ArrayUtility.iterable;

/**
 * {@link ReplaceArraySequence} to which Items can be added.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public class ReplaceAppendArraySequence<Item>
extends ReplaceArraySequence<Item>
implements ObservedAppendSequence<Item> {

    /**
     * {@link IndexSequenceCreator} of {@link ReplaceAppendArraySequence}
     * insstances
     */
    private static final IndexSequenceCreator<?, ? extends ReplaceAppendArraySequence<?>> CREATOR =
        new IndexSequenceCreator<Object, ReplaceAppendArraySequence<Object>>() {

            @Override
            public ReplaceAppendArraySequence<Object> createSequence(final int firstIndex, final int lastIndex)
            throws InvalidSequenceIndexRangeException {
                return new ReplaceAppendArraySequence<Object>(firstIndex, lastIndex);
            }
        };

    /**
     * Returns the {@link IndexSequenceCreator} of
     * {@link ReplaceAppendArraySequence} instances.
     * 
     * @return {@link IndexSequenceCreator} of
     *         {@link ReplaceAppendArraySequence} instances
     */
    @SuppressWarnings("unchecked")
    public static <Item> IndexSequenceCreator<Item, ReplaceAppendArraySequence<Item>> getCreator() {
        return (IndexSequenceCreator<Item, ReplaceAppendArraySequence<Item>>) CREATOR;
    }

    /**
     * Creates a new {@link ReplaceAppendArraySequence} with the specified first
     * and last indices.
     * 
     * @param firstIndex
     *        integer specifying the first index
     * 
     * @param lastIndex
     *        integer specifying the last index
     * 
     * @throws IllegalArgumentException
     *         if {@code lastIndex > firstIndex}
     */
    protected ReplaceAppendArraySequence(final int firstIndex, final int lastIndex) {
        super(firstIndex, lastIndex);
    }

    @Override
    public void append(final Item item) {
        // intentionally not using SequenceUtility for efficiency
        append(singleton(item));
    }

    @Override
    public void append(final Container<? extends Item> items) {
        // intentionally not using SequenceUtility for efficiency
        append(items, items.getItemsCount());
    }

    @Override
    public void append(final Collection<? extends Item> items) {
        // intentionally not using SequenceUtility for efficiency
        append(items, items.size());
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void append(final Item... items) {
        // intentionally not using SequenceUtility for efficiency
        append(iterable(items), items.length);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void append(final Item item, final ValueObserver<Item>... observers)
    throws IllegalContainerArgumentException {
        append(singleton(item), 1, observers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void append(final Container<? extends Item> items, final ValueObserver<Item>... observers)
    throws IllegalContainerArgumentException {
        append(items, items.getItemsCount(), observers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void append(final Collection<? extends Item> items, final ValueObserver<Item>... observers)
    throws IllegalContainerArgumentException {
        append(items, items.size(), observers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void append(final ValueObserver<Item>[] observers, final Item... items)
    throws IllegalContainerArgumentException {
        append(iterable(items), items.length, observers);
    }

    /**
     * Appends all Items contained by the specified {@link Container} to this
     * {@link ObservedAppendSequence}.
     * 
     * @param items
     *        {@link Iterable} providing the Items to add
     * 
     * @param addedItemsCount
     *        integer specifying the number of added Items; {@code items} must
     *        provide at least these {@code addedItemsCount} Items
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     * 
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SafeVarargs
    private final void append(final Iterable<? extends Item> items, final int addedItemsCount,
                              final ValueObserver<Item>... observers) {
        assertCapacity(getItemsCount() + addedItemsCount);

        int itemArrayIndex = getItemsCount();

        for (final Item item : items)
            replaceDelegateArrayItem(item, itemArrayIndex ++, observers);

        setLastIndex(getLastIndex() + addedItemsCount);
    }

    @Override
    public ReplaceIndexSequenceTraverser<Item> createReplaceIndexSequenceTraverser() {
        return new DefaultReplaceIndexSequenceTraverser<Item, ReplaceAppendArraySequence<Item>>(this);
    }
}
