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

package org.jlib.core.storage.capacity;

import org.jlib.core.storage.InvalidIndexException;
import org.jlib.core.storage.LinearIndexStorage;

/**
 * Strategy of split capacity provision in a {@link LinearIndexStorage}.
 *
 * @author Igor Akkerman
 */
public interface SplitCapacityStrategy {

    /**
     * Ensures that the referenced {@link LinearIndexStorage} fits the specified number of Items at the
     * specified position within the {@link LinearIndexStorage}. The indices of the stored Items are modified,
     * if necessary.
     *
     * @param splitIndex
     *        integer specifying the index at which the capacity should be provided
     *
     * @param splitCapacity
     *        integer specifying the capacity for the split
     *
     * @throws InvalidIndexException
     *         if {@code splitIndex} is not within the range of stored Items
     *
     * @throws InvalidPartialCapacityException
     *         if {@code capacity < 0}
     */
    public void ensureCapacity(int splitIndex, int splitCapacity)
    throws InvalidIndexException, InvalidPartialCapacityException;
}
