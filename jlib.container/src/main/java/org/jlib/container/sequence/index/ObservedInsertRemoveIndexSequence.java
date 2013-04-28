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

package org.jlib.container.sequence.index;

import org.jlib.container.sequence.ObservedRemoveFirstSequence;
import org.jlib.container.sequence.ObservedRemoveLastSequence;
import org.jlib.container.sequence.Sequence;

/**
 * {@link InsertRemoveIndexSequence}, {@link ObservedInsertIndexSequence},
 * {@link ObservedRemoveIndexSequence}, {@link ObservedRemoveFirstSequence} and
 * {@link ObservedRemoveLastSequence}.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public interface ObservedInsertRemoveIndexSequence<Item>
extends InsertRemoveIndexSequence<Item>, ObservedInsertIndexSequence<Item>, ObservedRemoveIndexSequence<Item> {

    @Override
    public ObservedInsertRemoveIndexSequence<Item> getSubsequenceView(int fromIndex, int toIndex)
    throws SequenceIndexOutOfBoundsException, InvalidSequenceIndexRangeException;

    @Override
    public ObservedInsertRemoveIndexSequenceTraverser<Item> createTraverser()
    throws SequenceIndexOutOfBoundsException;

    @Override
    public ObservedInsertRemoveIndexSequenceTraverser<Item> createTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException;
}
