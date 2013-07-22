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

import org.jlib.container.sequence.ObservedRemoveFirstSequence;
import org.jlib.container.sequence.ObservedRemoveLastSequence;
import org.jlib.container.sequence.Sequence;
import org.jlib.core.observer.ValueObserver;

/**
 * {@link RemoveIndexSequence} .
 *
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *
 * @author Igor Akkerman
 */
public interface ObservedRemoveIndexSequence<Item>
extends RemoveIndexSequence<Item>, ObservedRemoveFirstSequence<Item>, ObservedRemoveLastSequence<Item> {

    /**
     * Removes from this IndexSequence the Item stored at the specified index.
     *
     * @param index
     *        integer specifying the index
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     *
     */
    @SuppressWarnings("unchecked")
    public void remove(int index, ValueObserver<Item>... observers);
}
