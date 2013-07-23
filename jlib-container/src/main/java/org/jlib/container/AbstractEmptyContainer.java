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
import java.util.Iterator;
import java.util.List;

import org.jlib.core.language.AbstractObject;
import org.jlib.core.traverser.Traverser;

import static java.util.Collections.emptyIterator;
import static java.util.Collections.emptyList;
import static org.jlib.core.array.ArrayUtility.getEmptyArray;

public abstract class AbstractEmptyContainer<Item>
extends AbstractObject
implements Container<Item> {

    protected AbstractEmptyContainer() {
        super();
    }

    @Override
    public Traverser<Item> createTraverser() {
        return EmptyContainerTraverser.getInstance();
    }

    @Override
    public final int getItemsCount() {
        return 0;
    }

    @Override
    public final boolean isEmpty() {
        return true;
    }

    @Override
    public final boolean contains(final Item item) {
        return false;
    }

    @Override
    public final boolean contains(final Container<? extends Item> items) {
        return false;
    }

    @Override
    public final boolean contains(final Collection<? extends Item> items) {
        return false;
    }

    @Override
    @SafeVarargs
    public final boolean contains(final Item... items) {
        return false;
    }

    @Override
    public final List<Item> toList() {
        return emptyList();
    }

    @Override
    public List<Item> toSequentialList() {
        return emptyList();
    }

    @Override
    public final Item[] toArray() {
        return getEmptyArray();
    }

    @Override
    public final Iterator<Item> iterator() {
        return emptyIterator();
    }

    @Override
    public final boolean containsEqualItems(final Container<Item> otherContainer) {
        return otherContainer.isEmpty();
    }

    @Override
    public final boolean containsEqualItems(final Collection<Item> collection) {
        return collection.isEmpty();
    }
}