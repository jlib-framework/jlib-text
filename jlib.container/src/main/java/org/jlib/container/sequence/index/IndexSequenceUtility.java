package org.jlib.container.sequence.index;

import java.util.Collection;

import org.jlib.container.Container;
import org.jlib.container.sequence.InvalidSequenceItemsCountException;
import org.jlib.container.sequence.Sequence;
import org.jlib.core.observer.ValueObserver;

/**
 * {@link IndexSequence} utility.
 * 
 * @author Igor Akkerman
 */
public final class IndexSequenceUtility {

    /** no visible constructor */
    private IndexSequenceUtility() {}

    /**
     * Asserts that the specified index is inside the valid bounds of the
     * specified {@link IndexSequence}.
     * 
     * @param sequence
     *        verified {@link IndexSequence}
     * 
     * @param index
     *        integer specifying the index to verify
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code index} is out of the {@link IndexSequence} bounds
     */
    public static void assertIndexValid(final IndexSequence<?> sequence, final int index)
    throws SequenceIndexOutOfBoundsException {
        final int firstIndex = sequence.getFirstIndex();

        if (index < firstIndex)
            throw new SequenceIndexOutOfBoundsException(sequence, index, "index == " + index + " < " + firstIndex +
                                                                         " == firstIndex");

        final int lastIndex = sequence.getLastIndex();

        if (index > lastIndex)
            throw new SequenceIndexOutOfBoundsException(sequence, index, "index == " + index + " > " + lastIndex +
                                                                         " == lastIndex");
    }

    /**
     * Asserts that the specified <em>from</em> and <em>to</em> indices are
     * valid, that is,
     * {@code sequence.getFirstIndex() <= fromIndex <= toIndex <= sequence.getLastIndex()}
     * .
     * 
     * @param sequence
     *        verified {@link IndexSequence}
     * 
     * @param fromIndex
     *        integer specifying the from index
     * 
     * @param toIndex
     *        integer specifying the to index
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if
     *         {@code fromIndex < getFirstIndex() || toIndex > getLastIndex()}
     * 
     * @throws InvalidSequenceIndexRangeException
     *         if {@code fromIndex > toIndex}
     */
    public static void assertIndexRangeValid(final IndexSequence<?> sequence, final int fromIndex, final int toIndex)
    throws SequenceIndexOutOfBoundsException, InvalidSequenceIndexRangeException {
        final int firstIndex = sequence.getFirstIndex();

        if (fromIndex < firstIndex)
            throw new SequenceIndexOutOfBoundsException(sequence, fromIndex, "fromIndex == " + fromIndex + " < " +
                                                                             firstIndex + " == firstIndex");

        final int lastIndex = sequence.getLastIndex();

        if (toIndex > lastIndex)
            throw new SequenceIndexOutOfBoundsException(sequence, toIndex, "toIndex == " + toIndex + " < " + lastIndex +
                                                                           " == lastIndex");

        if (toIndex < fromIndex)
            throw new InvalidSequenceIndexRangeException(sequence, fromIndex, toIndex);
    }

    /**
     * Returns a new {@link IndexSequence} created by the specified
     * {@link IndexSequenceCreator} with the specified first and last indices.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} used for creation
     * 
     * @param firstIndex
     *        integer specifying the first index
     * 
     * @param lastIndex
     *        integer specifying the lastindex
     * 
     * @return newly created {@link IndexSequence}
     */
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
                  Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                                          final int firstIndex, final int lastIndex) {
        return indexSequenceCreator.createSequence(firstIndex, lastIndex);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * specified Items. That is, the index of the first Item of the
     * specified sequence in this Sequence is 0. The fixed size of the
     * {@link AbstractInitializeableIndexSequence} is the size of the specified
     * sequence.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param items
     *        comma separated sequence of Items to store
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    @SafeVarargs
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
                  Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                                           final Item... items) {
    // @formatter:on
        return createSequence(indexSequenceCreator, 0, items);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * specified Items having a specified first index. That is, the index of the
     * first Item of the specified sequence in the
     * {@link AbstractInitializeableIndexSequence} can be specified. The fixed
     * size of the {@link AbstractInitializeableIndexSequence} is the size of
     * the specified sequence.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the first index of the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @param items
     *        comma separated sequence of Items to store
     * 
     * @return newly created {@link AbstractInitializeableIndexSequence}
     */
    @SafeVarargs
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
                  Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                                          final int firstIndex, final Item... items) {
    // @formatter:on
        final int itemsCount = items.length;

        final int lastIndex = firstIndex + itemsCount - 1;

        final Sequenze sequence = createSequence(indexSequenceCreator, firstIndex, lastIndex);

        for (int index = firstIndex, arrayItemIndex = 0; index <= lastIndex; index ++, arrayItemIndex ++)
            sequence.replace(index, items[arrayItemIndex]);

        return sequence;
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} with a first
     * index of {@code 0} and the specified size.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param size
     *        integer specifying the size of the new
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @return the new {@link AbstractInitializeableIndexSequence}
     * 
     * @throws InvalidSequenceItemsCountException
     *         if {@code size < 1}
     */
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final int size)
                            throws InvalidSequenceItemsCountException {
        // @formatter:on

        if (size < 1)
            throw new InvalidSequenceItemsCountException(size, "size == {0} < 1");

        return createSequence(indexSequenceCreator, 0, size - 1);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Container. The index of the first Item of the
     * specified Container in the {@link AbstractInitializeableIndexSequence} is
     * 0. The fixed size of the {@link AbstractInitializeableIndexSequence} is
     * the size of the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param items
     *        Container of which the Items are copied to the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final Container<? extends Item> items) {
    // @formatter:on
        return createSequence(indexSequenceCreator, 0, items);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Java Container. The index of the first Item of the
     * specified Container in the {@link AbstractInitializeableIndexSequence} is
     * 0. The fixed size of the {@link AbstractInitializeableIndexSequence} is
     * the size of the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param items
     *        Collection of which the Items are copied to the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final Collection<? extends Item> items) {
    // @formatter:on
        return createSequence(indexSequenceCreator, 0, items);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Container having a specified first index. That is,
     * the index of the first Item of the specified collection in the
     * {@link AbstractInitializeableIndexSequence} can be specified. The fixed
     * size of the {@link AbstractInitializeableIndexSequence} is the size of
     * the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the first index of the
     *        {@link AbstractInitializeableIndexSequence}. The first Item of
     *        {@code collection} is stored at this index of the newly created
     *        {@link AbstractInitializeableIndexSequence}.
     * 
     * @param items
     *        Container of which the Items are copied to the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final int firstIndex, final Container<? extends Item> items) {
    // @formatter:on
        final Sequenze sequence =
            createSequence(indexSequenceCreator, firstIndex, firstIndex + items.getItemsCount() - 1);

        int index = firstIndex;

        for (final Item item : items)
            sequence.replaceStoredItem(index ++, item);

        return sequence;
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Container having a specified first index. That is,
     * the index of the first Item of the specified collection in the
     * {@link AbstractInitializeableIndexSequence} can be specified. The fixed
     * size of the {@link AbstractInitializeableIndexSequence} is the size of
     * the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the first index of the
     *        {@link AbstractInitializeableIndexSequence}. The first Item of
     *        {@code collection} is stored at this index of the newly created
     *        {@link AbstractInitializeableIndexSequence}.
     * 
     * @param items
     *        {@link Collection} containing the Items for the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @return newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final int firstIndex, final Collection<? extends Item> items) {
    // @formatter:on
        final Sequenze sequence = createSequence(indexSequenceCreator, firstIndex, firstIndex + items.size() - 1);

        int index = firstIndex;

        for (final Item item : items)
            sequence.replaceStoredItem(index ++, item);

        return sequence;
    }

    // @formatter:off
    /**
     * <p>
     * Creates a new {@link IndexSequence} containing the specified Integer
     * Items having a specified first index. That is, the index of the first
     * Item of the specified sequence in the newly created
     * {@link IndexSequence} can be specified. The fixed size of the newly
     * created {@link IndexSequence} is the size of the specified sequence.
     * </p>
     * <p>
     * It doesn't know whether the first parameter is meant to be the minimum
     * index of the {@link IndexSequence} or the first Item of the sequence.
     * You could pass an array of {@link Integer} values instead which is the
     * equivalent to the sequence form for the argument
     * {@code Integer... items} but the newly created class provides an
     * easier way: the factory methods
     * {@link #createIntegerIndexSequence(IndexSequenceCreator, Integer...)} or
     * {@link #createIntegerIndexSequenceFrom(IndexSequenceCreator, int, Integer[])}. The latter form takes
     * the minimum index as first argument.
     * </p>
     * 
     * {@literal
     *     // possible but not handy
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerIndexSequence&lt;Integer&gt;new Integer[] 1, 2, 3, 4, 5, 6 });
     * 
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerIndexSequence&lt;Integer&gt;(1, new Integer[] { 1, 2, 3, 4, 5, 6 });
     * 
     *     // easier to use (needs the static import of the factory method(s))
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerSequence(1, 2, 3, 4, 5);
     * 
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerSequenceFrom(1, 1, 2, 3, 4, 5);
     * }
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the minimum index
     * 
     * @param items
     *        comma separated sequence of {@link Integer} Items to store
     * 
     * @return new {@link IndexSequence} of {@link Integer} Items
     */
    public static <Sequenze extends AbstractInitializeableIndexSequence<Integer>> 
                  Sequenze createIntegerIndexSequenceFrom(final IndexSequenceCreator<Integer, Sequenze> indexSequenceCreator,
                                                     final int firstIndex, final Integer... items) {
    // @formatter:on
        return createSequence(indexSequenceCreator, firstIndex, items);
    }

    /**
     * Creates a new {@link Sequence} containing the specified Integer Items
     * having a first index of {@code 0}. The fixed size of the {@link Sequence}
     * is the size of the specified sequence.
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param items
     *        comma separated sequence of {@link Integer} items to store
     * 
     * @return the newly created {@link Sequence}
     */
    // @formatter:off
    public static <Sequenze extends AbstractInitializeableIndexSequence<Integer>> 
                  Sequenze createIntegerIndexSequence(final IndexSequenceCreator<Integer, Sequenze> indexSequenceCreator,
                                                      final Integer... items) {
    // @formatter:on
        return createIntegerIndexSequenceFrom(indexSequenceCreator, 0, items);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * specified Items. That is, the index of the first Item of the specified
     * sequence in this Sequence is 0. The fixed size of the
     * {@link AbstractInitializeableIndexSequence} is the size of the specified
     * sequence.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param observers
     *        array of {@link ValueObserver} instances attending the insertion
     *        of Items
     * 
     * @param items
     *        comma separated sequence of Items to store
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    @SafeVarargs
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
                  Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                                          final ValueObserver<Item>[] observers, final Item... items) {
    // @formatter:on
        return createSequence(indexSequenceCreator, 0, observers, items);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * specified Items having a specified first index. That is, the index of the
     * first Item of the specified sequence in the
     * {@link AbstractInitializeableIndexSequence} can be specified. The fixed
     * size of the {@link AbstractInitializeableIndexSequence} is the size of
     * the specified sequence.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the first index of the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @param observers
     *        array of {@link ValueObserver} instances attending the insertion
     *        of Items
     * 
     * @param items
     *        comma separated sequence of Items to store
     * 
     * @return newly created {@link AbstractInitializeableIndexSequence}
     */
    @SafeVarargs
    // @formatter:off
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
                  Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                                          final int firstIndex,
                                          final ValueObserver<Item>[] observers, final Item... items) {
    // @formatter:on
        final int itemsCount = items.length;

        final int lastIndex = firstIndex + itemsCount - 1;

        final Sequenze sequence = createSequence(indexSequenceCreator, firstIndex, lastIndex);

        for (int index = firstIndex, arrayItemIndex = 0; index <= lastIndex; index ++, arrayItemIndex ++)
            sequence.replace(index, items[arrayItemIndex], observers);

        return sequence;
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Container. The index of the first Item of the
     * specified Container in the {@link AbstractInitializeableIndexSequence} is
     * 0. The fixed size of the {@link AbstractInitializeableIndexSequence} is
     * the size of the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param items
     *        Container of which the Items are copied to the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the insertion of Items
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    @SafeVarargs
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final Container<? extends Item> items, final ValueObserver<Item>... observers) {
    // @formatter:on
        return createSequence(indexSequenceCreator, 0, items, observers);
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Java Container. The index of the first Item of the
     * specified Container in the {@link AbstractInitializeableIndexSequence} is
     * 0. The fixed size of the {@link AbstractInitializeableIndexSequence} is
     * the size of the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param items
     *        Collection of which the Items are copied to the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the insertion of Items
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    @SafeVarargs
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final Collection<? extends Item> items, final ValueObserver<Item>... observers) {
    // @formatter:on
        return createSequence(indexSequenceCreator, 0, items, observers);
    }

    /*
     * @SafeVarargs
     * 
     * @param observers comma separated sequence of {@link ValueObserver}
     * instances attending the insertion of Items
     * 
     * 
     * , final ValueObserver<Item>... observers
     */

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Container having a specified first index. That is,
     * the index of the first Item of the specified collection in the
     * {@link AbstractInitializeableIndexSequence} can be specified. The fixed
     * size of the {@link AbstractInitializeableIndexSequence} is the size of
     * the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the first index of the
     *        {@link AbstractInitializeableIndexSequence}. The first Item of
     *        {@code collection} is stored at this index of the newly created
     *        {@link AbstractInitializeableIndexSequence}.
     * 
     * @param items
     *        Container of which the Items are copied to the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the insertion of Items
     * 
     * @return the newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    @SafeVarargs
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final int firstIndex, final Container<? extends Item> items, final ValueObserver<Item>... observers) {
    // @formatter:on
        final Sequenze sequence =
            createSequence(indexSequenceCreator, firstIndex, firstIndex + items.getItemsCount() - 1);

        int index = firstIndex;

        for (final Item item : items)
            sequence.replaceStoredItem(index ++, item, observers);

        return sequence;
    }

    /**
     * Creates a new {@link AbstractInitializeableIndexSequence} containing the
     * Items of the specified Container having a specified first index. That is,
     * the index of the first Item of the specified collection in the
     * {@link AbstractInitializeableIndexSequence} can be specified. The fixed
     * size of the {@link AbstractInitializeableIndexSequence} is the size of
     * the specified Container.
     * 
     * @param <Item>
     *        type of items held in the {@link Sequence}
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the first index of the
     *        {@link AbstractInitializeableIndexSequence}. The first Item of
     *        {@code collection} is stored at this index of the newly created
     *        {@link AbstractInitializeableIndexSequence}.
     * 
     * @param items
     *        {@link Collection} containing the Items for the
     *        {@link AbstractInitializeableIndexSequence}
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the insertion of Items
     * 
     * @return newly created {@link AbstractInitializeableIndexSequence}
     */
    // @formatter:off
    @SafeVarargs
    public static <Item, Sequenze extends AbstractInitializeableIndexSequence<Item>> 
    Sequenze createSequence(final IndexSequenceCreator<Item, Sequenze> indexSequenceCreator,
                            final int firstIndex, final Collection<? extends Item> items, final ValueObserver<Item>... observers) {
    // @formatter:on
        final Sequenze sequence = createSequence(indexSequenceCreator, firstIndex, firstIndex + items.size() - 1);

        int index = firstIndex;

        for (final Item item : items)
            sequence.replaceStoredItem(index ++, item, observers);

        return sequence;
    }

    // @formatter:off
    /**
     * <p>
     * Creates a new {@link IndexSequence} containing the specified Integer
     * Items having a specified first index. That is, the index of the first
     * Item of the specified sequence in the newly created
     * {@link IndexSequence} can be specified. The fixed size of the newly
     * created {@link IndexSequence} is the size of the specified sequence.
     * </p>
     * <p>
     * It doesn't know whether the first parameter is meant to be the minimum
     * index of the {@link IndexSequence} or the first Item of the sequence.
     * You could pass an array of {@link Integer} values instead which is the
     * equivalent to the sequence form for the argument
     * {@code Integer... items} but the newly created class provides an
     * easier way: the factory methods
     * {@link #createIntegerIndexSequence(IndexSequenceCreator, Integer...)} or
     * {@link #createIntegerIndexSequenceFrom(IndexSequenceCreator, int, Integer[])}. The latter form takes
     * the minimum index as first argument.
     * </p>
     * 
     * {@literal
     *     // possible but not handy
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerIndexSequence&lt;Integer&gt;new Integer[] 1, 2, 3, 4, 5, 6 });
     * 
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerIndexSequence&lt;Integer&gt;(1, new Integer[] { 1, 2, 3, 4, 5, 6 });
     * 
     *     // easier to use (needs the static import of the factory method(s))
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerSequence(1, 2, 3, 4, 5);
     * 
     *     IndexSequence&lt;Integer&gt; integerSequence = createIntegerSequenceFrom(1, 1, 2, 3, 4, 5);
     * }
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param firstIndex
     *        integer specifying the minimum index
     * 
     * @param observers
     *        array of {@link ValueObserver} instances attending the insertion
     *        of Items
     * 
     * @param items
     *        comma separated sequence of {@link Integer} Items to store
     * 
     * @return new {@link IndexSequence} of {@link Integer} Items
     */
    public static <Sequenze extends AbstractInitializeableIndexSequence<Integer>> 
                  Sequenze createIntegerIndexSequenceFrom(final IndexSequenceCreator<Integer, Sequenze> indexSequenceCreator,
                                                     final int firstIndex, final ValueObserver<Integer>[] observers, final Integer... items) {
    // @formatter:on
        return createSequence(indexSequenceCreator, firstIndex, observers, items);
    }

    /**
     * Creates a new {@link Sequence} containing the specified Integer Items
     * having a first index of {@code 0}. The fixed size of the {@link Sequence}
     * is the size of the specified sequence.
     * 
     * @param <Sequenze>
     *        concrete type of the created {@link IndexSequence}
     * 
     * @param indexSequenceCreator
     *        {@link IndexSequenceCreator} to use
     * 
     * @param observers
     *        array of {@link ValueObserver} instances attending the insertion
     *        of Items
     * 
     * @param items
     *        comma separated sequence of {@link Integer} items to store
     * 
     * @return the newly created {@link Sequence}
     */
    // @formatter:off
    public static <Sequenze extends AbstractInitializeableIndexSequence<Integer>> 
                  Sequenze createIntegerIndexSequence(final IndexSequenceCreator<Integer, Sequenze> indexSequenceCreator, final ValueObserver<Integer>[] observers,
                                                      final Integer... items) {
    // @formatter:on
        return createIntegerIndexSequenceFrom(indexSequenceCreator, 0, observers, items);
    }
}
