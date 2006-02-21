/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    AbstractModifiableIndexList.java
 * Project: jlib.core
 *
 * Copyright (c) 2006 Igor Akkerman
 *
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.collections.list;

/**
 * Skeletal implementation of a ModifiableIndexList using a random access data store.
 *
 * @param <Element>
 *        type of elements stored in the list
 * @author Igor Akkerman
 */
public abstract class AbstractModifiableIndexList<Element>
extends AbstractIndexList<Element>
implements ModifiableIndexList<Element>, EditableIndexList<Element> {



}
