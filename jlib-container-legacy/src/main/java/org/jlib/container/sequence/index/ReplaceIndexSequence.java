/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2013 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.container.sequence.index;

import org.jlib.container.InvalidContainerArgumentException;
import org.jlib.container.InvalidContainerStateException;
import org.jlib.container.sequence.Sequence;

/**
 * {@link IndexSequence} and {@link ReplaceSequence}.
 *
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *
 * @author Igor Akkerman
 */
public interface ReplaceIndexSequence<Item>
extends IndexSequence<Item> {

    /**
     * Replaces the Item at the specified index in this
     * {@link ReplaceIndexSequence} by the specified Items.
     *
     * @param index
     *        integer specifying the index of the Item
     *
     * @param newItem
     *        new Item to store
     *
     * @throws InvalidSequenceIndexException
     *         if {@code index < getFirstIndex() || index > getLastIndex()}
     *
     * @throws InvalidContainerArgumentException
     *         if some property of {@code newItem} prevents the operation from being performed
     *
     * @throws InvalidContainerStateException
     *         if an error occurs performing the operation
     */
    public void replace(int index, Item newItem)
    throws InvalidSequenceIndexException, InvalidContainerArgumentException, InvalidContainerStateException;

    /**
     * @return {@link ReplaceIndexSequence} view of the specified subsequence
     */
    @Override
    public ReplaceIndexSequence<Item> getSubsequenceView(int fromIndex, int toIndex)
    throws InvalidSequenceIndexException;
}