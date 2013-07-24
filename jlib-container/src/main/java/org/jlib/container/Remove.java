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

package org.jlib.container;

import java.util.Collection;

import org.jlib.core.traverser.InvalidTraversableArgumentException;
import org.jlib.core.traverser.InvalidTraversableStateException;
import org.jlib.core.traverser.Traversable;

/**
 * {@link TraversableContainer} allowing Items to be removed.
 *
 * @param <Item>
 *        type of items held in the {@link TraversableContainer}
 *
 * @author Igor Akkerman
 */
public interface Remove<Item> {

    /**
     * Removes all Items from this {@link Remove}
     * <em>except</em> the Items contained by the specified {@link Traversable}.
     *
     * @param items
     *        {@link Traversable} containing the Items to retain
     *
     * @throws InvalidTraversableArgumentException
     *         if the operation cannot be completed due to some property of one
     *         Item in {@code items}
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    public void retain(Traversable<? extends Item> items)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException;

    /**
     * Removes all Items from this {@link Remove}
     * <em>except</em> the Items contained by the specified {@link TraversableContainer}.
     *
     * @param items
     *        {@link TraversableContainer} containing the Items to retain
     *
     * @throws InvalidTraversableArgumentException
     *         if the operation cannot be completed due to some property of one
     *         Item in {@code items}
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    public void retain(Contains<? extends Item> items)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException;

    /**
     * Removes all Items from this {@link Remove}
     * <em>except</em> the Items contained by the specified {@link Collection}.
     *
     * @param items
     *        {@link Collection} containing the Items to retain
     *
     * @throws InvalidTraversableArgumentException
     *         if the operation cannot be completed due to some property of one
     *         Item in {@code items}
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    public void retain(Collection<? extends Item> items)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException;

    /**
     * Removes all Items from this {@link Remove}
     * <em>except</em> the specified Items.
     *
     * @param items
     *        comma separated sequence of Items to retain
     *
     * @throws InvalidTraversableArgumentException
     *         if the operation cannot be completed due to some property of one
     *         Item in {@code items}
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    @SuppressWarnings("unchecked")
    public void retain(Item... items)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException;
}