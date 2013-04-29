/*
 * jlib - The Free Java Library
 * 
 * http://www.jlib.org
 * 
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 * 
 * COMMON PUBLIC LICENSE VERSION 1.0
 * 
 * http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.operator;

import java.util.HashSet;

import java.util.Set;

import org.jlib.container.Container;
import org.jlib.container.sequence.ArraySequenceCreator;
import org.jlib.container.sequence.IndexSequence;
import org.jlib.core.operator.BinaryOperator;
import org.jlib.core.operator.IdentityItemBinaryOperator;

/**
 * Utility class providing methods applying {@link BinaryOperator
 * BinaryOperators} on {@link Container Containers}.
 * 
 * @author Igor Akkerman
 */
public final class ContainerOperatorUtility {

    /** no visible constructor */
    private ContainerOperatorUtility() {}

    /**
     * Creates a Set containing the items of the specified array.
     * 
     * @param <SetItem>
     *        type of the items in the Set; super type of
     *        {@code <ArrayItem>}
     * @param <ArrayItem>
     *        type of the items in the array
     * @param array
     *        the source array
     * @return Set containing the Items of {@code array}
     */
    public static <SetItem, ArrayItem extends SetItem> Set<SetItem> asSet(final ArrayItem[] array) {
        final Set<SetItem> set = new HashSet<SetItem>(array.length);
        for (final ArrayItem item : array)
            set.add(item);
        return set;
    }

    /**
     * Applies the specified BinaryOperator on the items of the specified non
     * empty Container with left associativity. Let <i>a</i>, <i>b</i>,
     * <i>c</i>, ... be the first items returned by the Container's Traverser
     * in the correct order, and let the BinaryOperator be represented by
     * <i>*</i>. Then this method returns the result of <i>((a*b)*c)*...</i>.
     * 
     * @param <Value>
     *        type of the values to which the BinaryOperator is applied
     * @param collection
     *        non empty Container of Values on which the BinaryOperator is
     *        applied
     * @param operator
     *        BinaryOperator applied to {@code collection}
     * @return Value returned after {@code operator} has been applied to all
     *         items of {@code collection}
     * @throws IllegalArgumentException
     *         if {@code collection} is empty
     */
    public static <Value> Value applyToAll(final Container<Value> collection,
                                           final BinaryOperator<Value, Value, Value> operator)
    throws IllegalArgumentException {
        if (collection.isEmpty())
            throw new IllegalArgumentException();

        final Traverser<Value> collectionTraverser = collection.iterator();

        Value result = collectionTraverser.next();

        while (collectionTraverser.hasNext())
            result = operator.operate(result, collectionTraverser.next());

        return result;
    }

    /**
     * Applies the specified IdentityItemBinaryOperator on the items of
     * the specified Container with left associativity, returning the operator's
     * identity item if the Container is empty. Let <i>a</i>, <i>b</i>,
     * <i>c</i>, ... be the first items returned by the Container's Traverser
     * in the correct order, and let the operator be represented by <i>*</i>.
     * Then this method returns the result of <i>((a*b)*c)*...</i>. If the
     * Container is empty, the value returned by the operator's
     * {@link IdentityItemBinaryOperator#identityItem()} method is
     * returned.
     * 
     * @param <Value>
     *        type of the values to which the IdentityItemBinaryOperator is
     *        applied
     * @param collection
     *        Container of Values on which the IdentityItemBinaryOperator is
     *        applied
     * @param operator
     *        IdentityItemBinaryOperator applied to {@code collection}
     * @return Value returned after {@code operator} has been applied to all
     *         items of {@code collection}; the {@code operator}'s identity
     *         item if {@code collection} is empty
     */
    public static <Value> Value applyToAll(final Container<Value> collection,
                                           final IdentityItemBinaryOperator<Value> operator) {
        return applyToAll(collection, operator, operator.identityItem());
    }

    /**
     * Applies the specified BinaryOperator on the items of the specified
     * Container with left associativity, returning the specified identity
     * item if the Container is empty. Let <i>a</i>, <i>b</i>, <i>c</i>, ...
     * be the first items returned by the non empty Container's Traverser in
     * the correct order, and let the BinaryOperator be represented by <i>*</i>.
     * Then this method returns the result of <i>((a*b)*c)*...</i>.
     * 
     * @param <Value>
     *        type of the values to which the IdentityItemBinaryOperator is
     *        applied
     * @param collection
     *        Container of Values on which the IdentityItemBinaryOperator is
     *        applied
     * @param operator
     *        BinaryOperator applied to {@code collection}
     * @param identityItem
     *        Value to return if {@code collection} is empty
     * @return Value returned after {@code operator} has been applied to all
     *         items of {@code collection}; {@code identityItem} if
     *         {@code collection} is empty
     */
    public static <Value> Value applyToAll(final Container<Value> collection,
                                           final BinaryOperator<Value, Value, Value> operator,
                                           final Value identityItem) {
        if (collection.isEmpty())
            return identityItem;

        return applyToAll(collection, operator);
    }

    /**
     * Applies the specified BinaryOperator on the items of the specified non
     * empty Container with right associativity. Let ..., <i>x</i>, <i>y</i>,
     * <i>z</i> be the last items returned by the Container's Traverser in the
     * correct order, and let the BinaryOperator be represented by <i>*</i>.
     * Then this method returns the result of <i>...*(x*(y*z))</i>.
     * 
     * @param <Value>
     *        type of the values to which the BinaryOperator is applied
     * @param container
     *        non empty Container of Values on which the BinaryOperator is
     *        applied
     * @param operator
     *        BinaryOperator applied to {@code collection}
     * @return Value returned after {@code operator} has been applied to all
     *         items of {@code collection}
     * @throws IllegalArgumentException
     *         if {@code collection} is empty
     */
    public static <Value> Value applyToAllRightAssociative(final Container<Value> container,
                                                           final BinaryOperator<Value, Value, Value> operator)
    throws IllegalArgumentException {

        if (container.isEmpty())
            throw new IllegalArgumentException();

        final IndexSequence<Value> sequence =
            container instanceof IndexSequence<?> ? (IndexSequence<Value>) container
                                                 : ArraySequenceCreator.<Value> getInstance().createSequence(1,
                                                                                                             container);

        int sequenceIndex = sequence.getLastIndex();
        final int firstSequenceIndex = sequence.getMinimumindex();

        Value result = sequence.get(sequenceIndex);
        while (-- sequenceIndex >= firstSequenceindex)
            result = operator.operate(sequence.get(sequenceIndex), result);

        return result;
    }

    /**
     * Applies the specified IdentityItemBinaryOperator on the items of
     * the specified Container with right associativity, returning the
     * operator's identity item if the Container is empty. Let ..., <i>x</i>,
     * <i>y</i>, <i>z</i> be the last items returned by the Container's
     * Traverser in the correct order, and let the operator be represented by
     * <i>*</i>. Then this method returns the result of <i>...*(x*(y*z))</i>. If
     * the Container is empty, the value returned by the operator's
     * {@link IdentityItemBinaryOperator#identityItem()} method is
     * returned.
     * 
     * @param <Value>
     *        type of the values to which the IdentityItemBinaryOperator is
     *        applied
     * @param collection
     *        Container of Values on which the IdentityItemBinaryOperator is
     *        applied
     * @param operator
     *        IdentityItemBinaryOperator applied to {@code collection}
     * @return Value returned after {@code operator} has been applied to all
     *         items of {@code collection}; the {@code operator}'s identity
     *         item if {@code collection} is empty
     */
    public static <Value> Value applyToAllRightAssociative(final Container<Value> collection,
                                                           final IdentityItemBinaryOperator<Value> operator) {
        return applyToAllRightAssociative(collection, operator, operator.identityItem());
    }

    /**
     * Applies the specified IdentityItemBinaryOperator on the items of
     * the specified Container with right associativity, returning the specified
     * identity item if the Container is empty. Let ..., <i>x</i>, <i>y</i>,
     * <i>z</i> be the last items returned by the Container's Traverser in the
     * correct order, and let the operator be represented by <i>*</i>. Then this
     * method returns the result of <i>...*(x*(y*z))</i>.
     * 
     * @param <Value>
     *        type of the values to which the IdentityItemBinaryOperator is
     *        applied
     * @param collection
     *        Container of Values on which the IdentityItemBinaryOperator is
     *        applied
     * @param operator
     *        BinaryOperator applied to {@code collection}
     * @param identityItem
     *        Value to return if {@code collection} is empty
     * @return Value returned after {@code operator} has been applied to all
     *         items of {@code collection}; {@code identityItem} if
     *         {@code collection} is empty
     */
    public static <Value> Value applyToAllRightAssociative(final Container<Value> collection,
                                                           final BinaryOperator<Value, Value, Value> operator,
                                                           final Value identityItem) {
        if (collection.isEmpty())
            return identityItem;

        return applyToAll(collection, operator);
    }
}