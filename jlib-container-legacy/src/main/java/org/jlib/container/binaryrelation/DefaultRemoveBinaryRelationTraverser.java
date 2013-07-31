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

package org.jlib.container.binaryrelation;

import org.jlib.core.traverser.NoItemToRemoveException;
import org.jlib.core.traverser.RemoveTraverser;
import org.jlib.core.traverser.Traverser;
import org.jlib.core.value.ValueNotAccessibleException;

/**
 * Default implementation of a {@link Traverser} over the Pairs of a
 * {@link RetainItemsByTraversableBinaryRelation}.
 *
 * @param <LeftValue>
 *        type of the values on the left hand side of the binaryRelation
 *
 * @param <RightValue>
 *        type of the values on the right hand side of the binaryRelation
 *
 * @param <Relation>
 *        type of the traversed {@link RetainItemsByTraversableBinaryRelation}
 *
 * @author Igor Akkerman
 */
public class DefaultRemoveBinaryRelationTraverser<LeftValue, RightValue, Relation extends RetainItemsByTraversableBinaryRelation<LeftValue, RightValue>>
extends DefaultBinaryRelationTraverser<LeftValue, RightValue, Relation>
implements RemoveTraverser<Pair<LeftValue, RightValue>> {

    /**
     * Creates a new RemoveBinaryRelationTraverser.
     *
     * @param binaryRelation
     *        RetainItemsByTraversableBinaryRelation traversed by this Traverser
     */
    public DefaultRemoveBinaryRelationTraverser(final Relation binaryRelation) {
        super(binaryRelation);
    }

    @Override
    public void remove()
    throws NoItemToRemoveException {
        try {
            final Pair<LeftValue, RightValue> lastAccessedItem = getLastAccessedItem();
//            getBinaryRelation().retain(lastAccessedItem);

            unsetLastAccessedItem();
        }
        catch (final ValueNotAccessibleException exception) {
            throw new NoItemToRemoveException(getBinaryRelation(), exception);
        }
    }
}