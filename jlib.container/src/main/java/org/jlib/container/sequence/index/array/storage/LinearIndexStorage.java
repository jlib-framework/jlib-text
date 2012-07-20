package org.jlib.container.sequence.index.array.storage;

/**
 * Storage of <em>n</em> items indexed from <em>0</em> to <em>n-1</em>.
 * 
 * @param <Item>
 *        type of the Items stored in the {@link LinearIndexStorage}
 * 
 * @author Igor Akkerman
 */
public interface LinearIndexStorage<Item> {

    /**
     * Descriptor of an Item copy operation specifying a source Item index range
     * <em>from</em> which and destination Item index <em>to</em> which a
     * specified amount of {@link LinearIndexStorage} Items is copied.
     * 
     * @author Igor Akkerman
     */
    public static class Copy {

        /** begin index of the copied range */
        private final int sourceBeginIndex;

        /** copied number of Items */
        private final int itemsCount;

        /** index to which the range is copied */
        private final int targetIndex;

        /**
         * Creates a new {@link Copy}.
         * 
         * @param sourceBeginIndex
         *        integer specifying the begin index of the copied range
         * 
         * @param itemsCount
         *        integer specifying the copied number of Items
         * 
         * @param targetIndex
         *        integer specifying the index to which the range is copied
         */
        public Copy(final int sourceBeginIndex, final int itemsCount, final int targetIndex) {
            super();

            this.sourceBeginIndex = sourceBeginIndex;
            this.itemsCount = itemsCount;
            this.targetIndex = targetIndex;
        }

        /**
         * Returns the begin index of the copied range.
         * 
         * @return integer specifying the begin index of the copied range
         */
        public int getSourceBeginIndex() {
            return sourceBeginIndex;
        }

        /**
         * Returns the copied number of Items.
         * 
         * @return integer specifying the copied number of Items
         */
        public int getItemsCount() {
            return itemsCount;
        }

        /**
         * Returns the index to which the range is copied.
         * 
         * @return integer specifying the index to which the range is copied
         */
        public int getTargetIndex() {
            return targetIndex;
        }
    }

    /**
     * Initializes or re-initializes this {@link LinearIndexStorage} performing
     * the copy operations on its Items as specified by a number of {@link Copy}
     * descritors. Their source indices reference the Item indices
     * <em>before</em>, their target indices the Item indices <em>after</em> the
     * operation. The Items stored at the former indices are not overwritten and
     * can be reused in each specified {@link Copy} operation.
     * 
     * @param capacity
     *        integer specifying the capacity
     * 
     * @param copyDescriptors
     *        comma separated sequence of {@link Copy} descriptors
     */
    public void initialize(final int capacity, Copy... copyDescriptors);

    /**
     * Returns the Item stored at the specified index.
     * 
     * @param index
     *        integer specifying the index of the Item
     * 
     * @return Item stored at {@code index}
     * 
     * @throws IndexOutOfBoundsException
     *         if the index is out of the valid bounds of this
     *         {@link LinearIndexStorage}
     */
    public Item getItem(int index)
    throws IndexOutOfBoundsException;

    /**
     * Replaces the Item stored at the specified index by the specified Item.
     * 
     * @param index
     *        integer specifying the index of the Item
     * 
     * @param newItem
     *        new Item replacing the former
     * 
     * @throws IndexOutOfBoundsException
     *         if the index is out of the valid bounds of this
     *         {@link LinearIndexStorage}
     */
    public void replaceItem(int index, Item newItem)
    throws IndexOutOfBoundsException;

    /**
     * Performs the copy operations defined by the specified {@link Copy}
     * descriptors within this {@link LinearIndexStorage}. Their source indices
     * reference the Item indices <em>before</em>, their target indices the Item
     * indices <em>after</em> the operation. the Items stored at the former
     * indices are overwritten in each specified {@link Copy} operation
     * 
     * @param copyDescriptors
     *        comma separated sequence of {@link Copy} descriptors
     * 
     * @throws IndexOutOfBoundsException
     *         if a {@link Copy} descriptor specifies a copy operation on an
     *         index outside the valid bounds
     */
    public void copy(Copy... copyDescriptors)
    throws IndexOutOfBoundsException;

    /**
     * Returns the capacity, that is, number of storable Items.
     * 
     * @return integer specifying the capacity
     */
    public int getCapacity();

    /**
     * Returns the index of the first Item.
     * 
     * @return integer specifying the index of the first Item
     */
    public int getFirstItemIndex();

    /**
     * Registers the index of the first Item.
     * 
     * @param firstItemIndex
     *        integer specifying the index of the first Item
     */
    public void setFirstItemIndex(int firstItemIndex);

    /**
     * Returns the index of the last Item.
     * 
     * @return integer specifying the index of the last Item
     */
    public int getLastItemIndex();

    /**
     * Registers the index of the last Item.
     * 
     * @param lastItemIndex
     *        integer specifying the index of the last Item
     */
    public void setLastItemIndex(int lastItemIndex);

    /**
     * Returns the number of stored Items.
     * 
     * @return integer spacifying the number of stored Items
     */
    public int getItemsCount();
}
