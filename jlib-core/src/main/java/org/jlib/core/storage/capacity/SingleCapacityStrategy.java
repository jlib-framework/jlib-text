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

import org.jlib.core.storage.LinearIndexStorage;

/**
 * Strategy of a single capacity provision in a {@link LinearIndexStorage}.
 *
 * @author Igor Akkerman
 */
public interface SingleCapacityStrategy {

    /**
     * Ensures that the specified {@link LinearIndexStorage} fits the specified number of Items at the
     * corresponding position of the {@link LinearIndexStorage}. The indices of the stored Items are modified,
     * if necessary.
     *
     * @param capacity
     *        integer specifying the head capacity
     *
     * @throws NegativeCapacityException
     *         if {@code capacity < 0}
     */
    public void ensureCapacity(int capacity)
    throws NegativeCapacityException;
}
