/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    Array.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * <p>
 * Fixed sized array. Replacement for the standard Java arrays with special features.
 * </p>
 * <p>
 * The only syntactical difference to Java arrays lies in the syntax of setting and getting objects
 * and getting the array size:
 * </p>
 *
 * <pre>
 * {@literal
 * // good(?) old Java array                      // cool(!) new jlib Array class
 * String[] stringArray = new String[10];         Array<String> stringArray = new Array<String>(10);
 * stringArray[4] = "good(?) old Java array";     stringArray.set(4, "cool(!) new jlib Array class");
 * String s = stringArray[4];                     String s = stringArray.get(4);
 * int size = stringArray.length;                 int size = stringArray.size(); }
 * </pre>
 *
 * <p>
 * Special features:
 * </p>
 * <ul>
 * <li> Minimum and maximum index: <br/> On instantiation, you can specify the minimum and the
 * maximum index of the Array. Thus, no offset is necessary for Arrays starting at other indices
 * than 0. The following example illustrates how an Array is filled with numbers from 1 to 10:
 *
 * <pre>
 * {@literal
 * // good(?) old Java array                      // cool(!) new jlib Array class
 * Integer[] integerArray = new Integer[10];      Array<Integer> integerArray = new Array<Integer>(1, 10);
 * for (int i = 1; i <= 10; i ++)                 for (int i = 1; i <= 10; i ++)
 *     integerArray[i - 1] = i;                       integerArray.set(i, i); }
 * </pre>
 *
 * </li>
 * <li> Conformance to the Java Collections framework <br/> The class implements the
 * {@code java.util.Collection} interface and thus behaves like all Java Collections. </li>
 * <br />
 * <li> Full support for generics:<br/> The Java arrays do not support generic classes. For
 * example, you cannot create an array of String lists:
 *
 * <pre>
 * {@literal
 * // FORBIDDEN!
 * List<String>[] stringListArray = new List<String>[10];
 *
 * // PERMITTED!
 * Array<List<String>> stringListArray = new Array<List<String>>(10);}
 * </pre>
 *
 * </li>
 * <li> Easy to create:<br />
 *
 * <pre>
 * {@literal
 * // creating an Array with three Strings
 * Array<String> stringArray = new Array<String>("cool", "Array", "class!");
 *
 * // creating an Array with three Strings starting at index 1
 * Array<String> stringArray = new Array<String>(1, "jlib", "is", "cool!");}
 * </pre>
 *
 * A small problem arises if you want to create Arrays of Integers. The Java autoboxing feature
 * forbids the following Array creation:
 *
 * <pre>
 * {@literal
 * // FORBIDDEN!
 * Array<Integer> integerArray = new Array<Integer>(1, 2, 3, 4, 5, 6);}
 * </pre>
 *
 * The compiler claims:
 *
 * <pre>
 * {@literal The constructor Array<Integer>(Integer[]) is ambiguous}
 * </pre>
 *
 * It doesn't know whether the first parameter is meant as the minimum index of the Array or the
 * first element of the list. You could pass a Java array of Integers instead which is the
 * equivalent to the list form for the argument {@code Integer... elements} but this class provides
 * an easier way: Use one of the factory methods {@link #newIntegerArray(Integer[])} or
 * {@link #newIntegerArrayFrom(int, Integer[])}. The latter form takes the minimum index as first
 * argument.
 *
 * <pre>
 * {@literal
 * // possible but not handy
 * Array<Integer> integerArray = new Array<Integer>(new Integer[] {1, 2, 3, 4, 5, 6});
 * Array<Integer> integerArray = new Array<Integer>(1, new Integer[] {1, 2, 3, 4, 5, 6});
 *
 * // easier to use (needs the static import of the method(s))
 * Array<Integer> integerArray = newIntegerArray(1, 2, 3, 4, 5);
 * Array<Integer> integerArray = newIntegerArrayFrom(1, 1, 2, 3, 4, 5);}
 * </pre>
 *
 * </li>
 * </ul>
 * <p>
 * An Array has a fixed size, thus no Elements can be added to or removed from it. The corresponding
 * methods for adding and removing Elements all throw an {@link UnsupportedOperationException}.
 * </p>
 *
 * @param <Element>
 *        type of elements stored in the Array
 * @author Igor Akkerman
 */
public class Array<Element>
extends AbstractIndexList<Element>
implements EditableIndexList<Element>, Cloneable {

    /** ArrayList containing the Elements of this Array */
    private ArrayList<Element> backingList;

    /**
     * Creates a new empty Array.
     */
    public Array() {
        super();
        construct();
    }

    /**
     * Creates a new Array initially filled with {@code null} Elements.
     *
     * @param size
     *        integer specifying the size of this Array
     * @throws IllegalArgumentException
     *         if {@code size < 0}
     */
    public Array(int size) {
        super();
        if (size != 0)
            construct(0, size - 1);
        else
            construct();
    }

    /**
     * Creates a new Array initially filled with {@code null} Elements.
     *
     * @param minIndex
     *        integer specifying the minimum index of this Array
     * @param maxIndex
     *        integer specifying the maximum index of this Array
     * @throws IllegalArgumentException
     *         if {@code  minIndex < 0 || maxIndex < minIndex}
     */
    public Array(int minIndex, int maxIndex) {
        super();
        construct(minIndex, maxIndex);
    }

    /**
     * Creates a new Array containing the specified elements. That is, the index of the first
     * Element of the specified list in this Array is 0. The fixed size of this Array is the size of
     * the specified list.
     *
     * @param elements
     *        comma separated list of elements to store or Java array containing those elements
     */
    public Array(Element... elements) {
        this(0, elements);
    }

    /**
     * Creates a new Array containing the specified Integer elements. That is, the index of the
     * first Element of the specified list in this Array is 0. The fixed size of this Array is the
     * size of the specified list.
     *
     * @param elements
     *        comma separated list of Integer elements to store or Java array containing those
     *        elements
     * @return the new Array of Integers
     */
    public static Array<Integer> newIntegerArray(Integer... elements) {
        return new Array<Integer>(0, elements);
    }

    /**
     * Creates a new Array containing the specified elements having a specified minimum index. That
     * is, the index of the first Element of the specified list in this Array can be specified. The
     * fixed size of this Array is the size of the specified list.
     *
     * @param minIndex
     *        integer specifying the minimum index of this Array
     * @param elements
     *        comma separated list of elements to store or Java array containing those elements
     */
    public Array(int minIndex, Element... elements) {
        super();
        if (elements.length != 0)
            construct(minIndex, minIndex + elements.length - 1);
        else
            construct();

        for (int index = 0; index < elements.length; index ++)
            backingList.set(index, elements[index]);
    }

    /**
     * Creates a new Array containing the specified Integer elements having a specified minimum
     * index. That is, the index of the first Element of the specified list in this Array can be
     * specified. The fixed size of this Array is the size of the specified list.
     *
     * @param minIndex
     *        integer specifying the minimum index of this Array
     * @param elements
     *        comma separated list of Integer elements to store or Java array containing those
     *        elements
     * @return the new Array of Integers
     */
    public static Array<Integer> newIntegerArrayFrom(int minIndex, Integer... elements) {
        return new Array<Integer>(minIndex, elements);
    }

    /**
     * Creates a new Array containing the Elements of the specified Collection. The index of the
     * first Element of the specified Collection in this Array is 0. The fixed size of this Array is
     * the size of the specified Collection.
     *
     * @param collection
     *        Collection of which the Elements are copied to this Array
     * @throws NullPointerException
     *         if {@code collection} is {@code null}
     */
    public Array(Collection<Element> collection) {
        this(0, collection);
    }

    /**
     * Creates a new Array containing the Elements of the specified Collection having a specified
     * minimum index. That is, the index of the first Element of the specified collection in this
     * Array can be specified. The fixed size of this Array is the size of the specified Collection.
     *
     * @param minIndex
     *        integer specifying the minimum index of this Array. The first Element of
     *        {@code collection} is stored at this index of this Array.
     * @param collection
     *        Collection of which the Elements are copied to this Array
     * @throws NullPointerException
     *         if {@code collection} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code  minIndex < 0}
     */
    public Array(int minIndex, Collection<Element> collection) {
        super();
        if (minIndex < 0)
            throw new IllegalArgumentException();

        backingList = new ArrayList<Element>(collection);

        this.minIndex = minIndex;
        this.maxIndex = minIndex + backingList.size() - 1;
    }

    /**
     * Constructs this Array as empty.
     */
    private void construct() {
        backingList = new ArrayList<Element>(0);
        minIndex = -1;
        maxIndex = -1;
    }

    /**
     * Constructs this Array.
     *
     * @param minIndex
     *        integer specifying the minimum index of this Array
     * @param maxIndex
     *        integer specifying the maximum index of this Array
     * @throws IllegalArgumentException
     *         if {@code  minIndex < 0 || maxIndex < minIndex}
     */
    @SuppressWarnings("hiding")
    private void construct(int minIndex, int maxIndex) {
        if (minIndex < 0 || maxIndex < minIndex)
            throw new IllegalArgumentException();

        int size = maxIndex - minIndex + 1;
        backingList = new ArrayList<Element>(size);
        for (int i = 0; i < size; i ++)
            backingList.add(null);

        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
    }

    // -------------------------------------------------------------------------------------

    public Element get(int index)
    throws IndexOutOfBoundsException {
        try {
            return backingList.get(index - minIndex);
        }
        catch (IndexOutOfBoundsException exception) {
            throw new ListIndexOutOfBoundsException(index);
        }
    }

    public Element set(int index, Element element)
    throws IndexOutOfBoundsException {
        try {
            Element oldElement = get(index);
            backingList.set(index - minIndex, element);
            return oldElement;
        }
        catch (IndexOutOfBoundsException exception) {
            throw new ListIndexOutOfBoundsException(index);
        }
    }

    // @see java.util.AbstractCollection#contains(java.lang.Object)
    @Override
    // overridden for efficiency
    public boolean contains(Object element) {
        return backingList.contains(element);
    }

    // @see java.util.AbstractCollection#containsAll(java.util.Collection)
    @Override
    // overridden for efficiency
    public boolean containsAll(Collection<?> collection) {
        return backingList.containsAll(collection);
    }

    // @see java.lang.Iterable#iterator()
    @Override
    public Iterator<Element> iterator() {
        return new EditableIndexListIterator<Element>(this);
    }

    // @see org.jlib.core.collections.list.IndexList#listIterator()
    public EditableIndexListIterator<Element> listIterator() {
        return new EditableIndexListIterator<Element>(this);
    }

    // @see org.jlib.core.collections.list.IndexList#listIterator(int)
    public EditableIndexListIterator<Element> listIterator(int index)
    throws IndexOutOfBoundsException {
        if (index < minIndex || index > maxIndex)
            throw new java.lang.ArrayIndexOutOfBoundsException(index);
        return new EditableIndexListIterator<Element>(this, index);
    }

    // @see java.lang.Object#clone()
    @Override
    public Object clone() {
        return new Array<Element>(minIndex, this);
    }

    // @see org.jlib.core.collections.list.IndexList#equals(java.lang.Object)
    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Array<?>))
            return false;
        Array<?> array = (Array<?>) object;
        return minIndex == array.minIndex && maxIndex == array.maxIndex && backingList.equals(array.backingList);
    }

    // @see java.lang.Object#toString()
    @Override
    public String toString() {
        EditableIndexListIterator<Element> iterator = listIterator();
        boolean hasNext = iterator.hasNext();

        StringBuffer stringBuffer = new StringBuffer(8 * size());

        stringBuffer.append('[');
        while (hasNext) {
            stringBuffer.append(iterator.nextIndex());
            stringBuffer.append('=');
            stringBuffer.append(iterator.next());
            hasNext = iterator.hasNext();
            if (hasNext)
                stringBuffer.append(", ");
        }
        stringBuffer.append(']');

        return stringBuffer.toString();
    }
}
