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

package org.jlib.container.binaryrelation.bijection;

import org.jlib.container.Container;
import org.jlib.container.ContainerUtility;
import org.jlib.container.binaryrelation.Association;
import org.jlib.container.binaryrelation.DefaultRemoveBinaryRelationTraverser;
import org.jlib.container.binaryrelation.InvalidAssociationException;
import org.jlib.container.binaryrelation.LeftValueAlreadyAssociatedException;
import org.jlib.container.binaryrelation.NoSuchAssociationValueException;
import org.jlib.container.binaryrelation.RightValueAlreadyAssociatedException;
import org.jlib.core.traverser.RemoveTraverser;

import java.util.Collection;

/**
 * {@link HashAssociateBijection} allowing the removal of {@link Association}
 * items.
 *
 * @param <LeftValue>
 *        type of the values on the left hand side of the {@link Bijection}
 *
 * @param <RightValue>
 *        type of the values on the right hand side of the {@link Bijection}
 *
 * @author Igor Akkerman
 */
public class HashAssociateRemoveBijection<LeftValue, RightValue>
extends HashAssociateBijection<LeftValue, RightValue>
implements RemoveBijection<LeftValue, RightValue> {

    /** Creates a new initially empty HashAddBijection. */
    public HashAssociateRemoveBijection() {
        super();
    }

    /**
     * Creates a new HashAddBijection containing the Associations contained by
     * the specified jlib Container.
     *
     * @param associations
     *        Container of the Associations to add
     *
     * @throws LeftValueAlreadyAssociatedException
     *         if the LeftValue of one Item in {@code associations} is already
     *         associated to another RightValue; if an {@link Association} is
     *         equal to another {@link Association} in the
     *         {@link HashAssociateBijection}, it is ignored
     *
     * @throws RightValueAlreadyAssociatedException
     *         if the RightValue of one Item in {@code associations} is already
     *         associated to another LeftValue; if an {@link Association} is
     *         equal to another {@link Association} in the
     *         {@link HashAssociateBijection}, it is ignored
     *
     * @throws InvalidAssociationException
     *         if some property of one Item in {@code associations} prevents it
     *         from being added
     */
    public HashAssociateRemoveBijection(final Container<Association<LeftValue, RightValue>> associations)
    throws LeftValueAlreadyAssociatedException, RightValueAlreadyAssociatedException, InvalidAssociationException {
        super(associations);
    }

    /**
     * Creates a new HashAddBijection containing the Associations contained by
     * the specified Collection.
     *
     * @param associations
     *        Collection of the Associations to add
     *
     * @throws LeftValueAlreadyAssociatedException
     *         if the LeftValue of one Item in {@code associations} is already
     *         associated to another RightValue; if an {@link Association} is
     *         equal to another {@link Association} in the
     *         {@link HashAssociateBijection}, it is ignored
     *
     * @throws RightValueAlreadyAssociatedException
     *         if the RightValue of one Item in {@code associations} is already
     *         associated to another LeftValue; if an {@link Association} is
     *         equal to another {@link Association} in the
     *         {@link HashAssociateBijection}, it is ignored
     *
     * @throws InvalidAssociationException
     *         if some property of one Item in {@code associations} prevents it
     *         from being added
     */
    public HashAssociateRemoveBijection(final Collection<Association<LeftValue, RightValue>> associations)
    throws LeftValueAlreadyAssociatedException, RightValueAlreadyAssociatedException, InvalidAssociationException {
        super(associations);
    }

    /**
     * Creates a new HashAddBijection containing the Associations specified in a
     * comma separated sequence.
     *
     * @param associations
     *        Comma separated sequence of the Associations to add
     *
     * @throws LeftValueAlreadyAssociatedException
     *         if the LeftValue of one Item in {@code associations} is already
     *         associated to another RightValue; if an {@link Association} is
     *         equal to another {@link Association} in the
     *         {@link HashAssociateBijection}, it is ignored
     *
     * @throws RightValueAlreadyAssociatedException
     *         if the RightValue of one Item in {@code associations} is already
     *         associated to another LeftValue; if an {@link Association} is
     *         equal to another {@link Association} in the
     *         {@link HashAssociateBijection}, it is ignored
     *
     * @throws InvalidAssociationException
     *         if some property of one Item in {@code associations} prevents it
     *         from being added
     */
    @SuppressWarnings("unchecked")
    public HashAssociateRemoveBijection(final Association<LeftValue, RightValue>... associations)
    throws LeftValueAlreadyAssociatedException, RightValueAlreadyAssociatedException, InvalidAssociationException {
        super(associations);
    }

    @Override
    public void remove(final LeftValue leftValue, final RightValue rightValue)
    throws NoSuchAssociationValueException {
        leftToRightMap.remove(leftValue);
        rightToLeftMap.remove(rightValue);
    }

    @Override
    public void remove(final Association<LeftValue, RightValue> association) {
        remove(association.getLeftValue(), association.getRightValue());
    }

    @Override
    public void removeAll() {
        ContainerUtility.remove(this, this);
    }

    @Override
    public void remove(final Iterable<? extends Association<LeftValue, RightValue>> associations) {
        ContainerUtility.remove(this, associations);
    }

    @Override
    public void remove(final Container<? extends Association<LeftValue, RightValue>> associations) {
        ContainerUtility.remove(this, associations);
    }

    @Override
    public void remove(final Collection<? extends Association<LeftValue, RightValue>> associations) {
        ContainerUtility.remove(this, associations);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final Association<LeftValue, RightValue>... associations) {
        ContainerUtility.remove(this, associations);
    }

    @Override
    public void retain(final Container<? extends Association<LeftValue, RightValue>> associations) {
        ContainerUtility.retain(this, associations);
    }

    @Override
    public void retain(final Collection<? extends Association<LeftValue, RightValue>> associations) {
        ContainerUtility.retain(this, associations);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void retain(final Association<LeftValue, RightValue>... associations) {
        ContainerUtility.retain(this, associations);
    }

    @Override
    public RemoveTraverser<Association<LeftValue, RightValue>> createTraverser() {
        return new DefaultRemoveBinaryRelationTraverser<>(this);
    }
}
