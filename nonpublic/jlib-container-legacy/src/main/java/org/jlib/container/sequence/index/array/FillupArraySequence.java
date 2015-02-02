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

package org.jlib.container.operation.sequence.index.array;

import org.jlib.container.operation.sequence.AppendSequence;

/**
 * Default implementation of a {@link ReplaceSequence} and
 * {@link AppendSequence}. It is created empty and can be filled up with Items.
 *
 * @param <Item>
 *        type of items held int he {@link FillupArraySequence}
 *
 * @author Igor Akkerman
 */
public class FillupArraySequence<Item> {

//    /**
//     * Creates a new {@link FillupArraySequence}.
//     */
//    public FillupArraySequence() {
//        super();
//
//        setDelegateSequence(new EmptyDelegateSequence());
//    }
//
//    /**
//     * {@link InitiallyEmptySequence} registering a new {@link ReplaceSequence} and
//     * {@link AppendSequence} as new delegate of the surrounding
//     * {@link FillupArraySequence}.
//     *
//     * @author Igor Akkerman
//     */
//    private class EmptyDelegateSequence
//    extends InitiallyEmptySequence<Item>
//    implements ObservedReplaceContainer<Item>,
//               ObservedAppendSequence<Item> {
//
//        /**
//         * Creates a new {@link EmptyDelegateSequence}.
//         */
//        public EmptyDelegateSequence() {
//            super();
//        }
//
//        @Override
//        public void append(final Item item) {
//            setDelegateSequence(new ReplaceInsertRemoveArraySequence<Item>(item));
//        }
//
//        @Override
//        public void append(final IterableContainer<? extends Item> items)
//        throws InvalidContainerArgumentException {
//            setDelegateSequence(new ReplaceInsertRemoveArraySequence<Item>(items));
//        }
//
//        @Override
//        public void append(final Collection<? extends Item> items)
//        throws InvalidContainerArgumentException {
//            setDelegateSequence(new ReplaceInsertRemoveArraySequence<Item>(items));
//        }
//
//        @Override
//        @SafeVarargs
//        public final void append(final Item... items)
//        throws InvalidContainerArgumentException {
//            setDelegateSequence(new ReplaceInsertRemoveArraySequence<Item>(items));
//        }
//
//        @SafeVarargs
//        @Override
//        public final void append(final Item item, final ValueObserver<Item>... observers)
//        throws InvalidContainerArgumentException {
//            operate(new HandledOperator() {
//                @Override
//                public void operate() {
//                    append(item);
//                }
//            }, /*
//         */ item, observers);
//        }
//
//        @SafeVarargs
//        @Override
//        public final void append(final IterableContainer<? extends Item> items, final ValueObserver<Item>... observers)
//        throws InvalidContainerArgumentException {
//            if (items.isEmpty())
//                return;
//
//            final Iterator<? extends Item> iterator = items.iterator();
//            append(iterator.next(), observers);
//
//            final ObservedReplaceAppendRemoveSequence<Item> delegateSequence = getDelegateSequence();
//
//            while (iterator.hasNext())
//                delegateSequence.append(iterator.next(), observers);
//        }
//
//        @SafeVarargs
//        @Override
//        public final void append(final Collection<? extends Item> items, final ValueObserver<Item>... observers)
//        throws InvalidContainerArgumentException {
//            if (items.isEmpty())
//                return;
//
//            append((Iterable<? extends Item>) items, observers);
//        }
//
//        @SafeVarargs
//        @Override
//        public final void append(final ValueObserver<Item>[] observers, final Item... items)
//        throws InvalidContainerArgumentException {
//            if (items.length == 0)
//                return;
//
//            append(iterable(items), observers);
//        }
//
//        /**
//         * Creates a delegate {@link ObservedReplaceAppendRemoveSequence} for
//         * the surrounding {@link FillupArraySequence} containing the first Item
//         * traversed by the specified {@link Iterator}, then appends the further
//         * traversed Items to the delegate
//         * {@link ObservedReplaceAppendRemoveSequence}.
//         *
//         * @param items
//         *        Items to append to the surrounding {@link FillupArraySequence}
//         *
//         * @param observers
//         *        comma separated sequence of {@link Observer} instances
//         *        attending the operations
//         */
//        @SuppressWarnings("unchecked")
//        private void append(final Iterable<? extends Item> items, final ValueObserver<Item>... observers) {
//            final Iterator<? extends Item> iterator = items.iterator();
//            append(iterator.next(), observers);
//
//            final ObservedReplaceAppendRemoveSequence<Item> delegateSequence = getDelegateSequence();
//
//            while (iterator.hasNext())
//                delegateSequence.append(iterator.next(), observers);
//        }
//    }
}