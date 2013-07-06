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

import org.jlib.container.sequence.InvalidSequenceArgumentException;
import org.jlib.container.sequence.InvalidSequenceStateException;
import org.jlib.container.sequence.ReplaceSequence;
import org.jlib.container.sequence.Sequence;
import org.jlib.core.observer.ValueObserver;

/**
 * {@link IndexSequence} and {@link ReplaceSequence}.
 *
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * @author Igor Akkerman
 */
public interface ObservedReplaceIndexSequence<Item>
extends ReplaceIndexSequence<Item> {

    /**
     * Replaces the Item at the specified index in this
     * {@link ObservedReplaceIndexSequence} by the specified Items.
     *
     * @param index
     *        integer specifying the index
     *
     * @param newItem
     *        new Item to store
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the replacement
     *
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code index < getFirstIndex() || index > getLastIndex()}
     *
     * @throws InvalidSequenceArgumentException
     *         if some property of {@code newItem} prevents the operation from
     *         being performed
     *
     * @throws InvalidSequenceStateException
     *         if an error occurs performing the operation
     */
    @SuppressWarnings("unchecked")
    public void replace(final int index, final Item newItem, final ValueObserver<Item>... observers)
    throws SequenceIndexOutOfBoundsException, InvalidSequenceArgumentException, InvalidSequenceStateException;
}
