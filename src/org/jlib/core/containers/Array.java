/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    Array.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 by Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * <p>
 * Fixed sized array. Replacement for the standard Java arrays with special features.
 * </p>
 * <p>
 * The only syntactical difference to Java arrays lies in the syntax of setting and getting objects and getting the
 * array size:
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
 * <li> Minimum and maximum index:<br/> On instantiation, you can specify the minimum and the maximum index of the
 * Array. Thus, no offset is necessary for Arrays starting at other indexes than 0. The following example illustrates
 * how an Array is filled with numbers from 1 to 10:
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
 * <li> Conformance to the Java Collections framework<br/> The class implements the {@code java.util.List} interface
 * and thus behaves like all Java collections. </li>
 * <li> Full support for generics:<br/> The Java arrays do not support generic classes. For example, you cannot create
 * an array of String lists:
 * 
 * <pre>
 * {@literal
 * // FORBIDDEN!                                           // ALLOWED!
 * List<String>[] stringListArray = new List<String>[10];  Array<List<String>> stringListArray = new Array<List<String>>(10);}
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
 * A small problem arises if you want to create Arrays of Integers. The Java autoboxing feature forbids the following
 * Array creation:
 * 
 * <pre>
 * {@literal
 * // FORBIDDEN!
 * Array<Integer> integerArray = new Array<Integer>(1, 2, 3, 4, 5, 6);}
 * </pre>
 * 
 * The compiler claims:
 * <pre>
 * {@literal The constructor Array<Integer>(Integer[]) is ambiguous}
 * </pre>
 * It doesn't know whether the
 * first parameter is meant as the minimum index of the Array or the first element of the list. You could pass a Java
 * array of Integers instead which is the equivalent to the list form for the argument {@code Integer... elements} but
 * this class provides an easier way: Use one of the factory methods {@link #newIntegerArray(Integer[])} or
 * {@link #newIntegerArrayFrom(int, Integer[])}. The latter form takes the minimum index as first argument.
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
 * </li>
 * </ul>
 * <p>
 * An Array has a fixed size, thus no Elements can be added to or removed from it. The corresponding methods for adding
 * and removing Elements all throw an {@link UnsupportedOperationException}.
 * </p>
 * 
 * @param <Element>
 *        type of elements stored in the Array
 * @author Igor Akkerman
 */
public class Array<Element>
implements List<Element>, RandomAccess, Cloneable {

    /** ArrayList containing the Elements of this Array */
    private ArrayList<Element> arrayList;

    /** minimum index of this array */
    private int minIndex;

    /** maximum index of this array */
    private int maxIndex;

    /**
     * Creates a new empty Array.
     */
    public Array() {
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
     * Creates a new Array containing the specified elements. That is, the index of the first Element of the specified
     * list in this Array is 0. The fixed size of this Array is the size of the specified list.
     * 
     * @param elements
     *        comma separated list of elements to store or Java array containing those elements
     */
    public Array(Element... elements) {
        this(0, elements);
    }

    /**
     * Creates a new Array containing the specified Integer elements. That is, the index of the first Element of the
     * specified list in this Array is 0. The fixed size of this Array is the size of the specified list.
     * 
     * @param elements
     *        comma separated list of Integer elements to store or Java array containing those elements
     * @return the new Array of Integers
     */
    public static Array<Integer> newIntegerArray(Integer... elements) {
        return new Array<Integer>(0, elements);
    }

    /**
     * Creates a new Array containing the specified elements having a specified minimum index. That is, the index of the
     * first Element of the specified list in this Array can be specified. The fixed size of this Array is the size of
     * the specified list.
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
            arrayList.set(index, elements[index]);
    }

    /**
     * Creates a new Array containing the specified Integer elements having a specified minimum index. That is, the
     * index of the first Element of the specified list in this Array can be specified. The fixed size of this Array is
     * the size of the specified list.
     * 
     * @param minIndex
     *        integer specifying the minimum index of this Array
     * @param elements
     *        comma separated list of Integer elements to store or Java array containing those elements
     * @return the new Array of Integers
     */
    public static Array<Integer> newIntegerArrayFrom(int minIndex, Integer... elements) {
        return new Array<Integer>(minIndex, elements);
    }

    /**
     * Creates a new Array containing the Elements of the specified Collection. The index of the first Element of the
     * specified Collection in this Array is 0. The fixed size of this Array is the size of the specified Collection.
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
     * Creates a new Array containing the Elements of the specified Collection having a specified minimum index. That
     * is, the index of the first Element of the specified collection in this Array can be specified. The fixed size of
     * this Array is the size of the specified Collection.
     * 
     * @param minIndex
     *        integer specifying the minimum index of this Array. The first Element of {@code collection} is stored at
     *        this index of this Array.
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

        arrayList = new ArrayList<Element>(collection);

        this.minIndex = minIndex;
        this.maxIndex = minIndex + arrayList.size() - 1;
    }

    /**
     * Constructs this Array as empty.
     */
    private void construct() {
        arrayList = new ArrayList<Element>(0);
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
        arrayList = new ArrayList<Element>(size);
        for (int i = 0; i < size; i ++)
            arrayList.add(null);

        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
    }

    /**
     * Returns the Element stored at the specified index in this Array.
     * 
     * @param index
     *        integer specifying the index of the stored Element
     * @return Element stored at {@code index}
     * @throws IndexOutOfBoundsException
     *         if {@code index < getMinIndex() || index > getMaxIndex()}
     */
    public Element get(int index)
    throws IndexOutOfBoundsException {
        try {
            return arrayList.get(index - minIndex);
        }
        catch (IndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    /**
     * Registers the Element to store at the specified index in this Array.
     * 
     * @param index
     *        integer specifying the index of the Element to store
     * @param element
     *        Element to store
     * @return Element formerly stored at {@code index}
     * @throws IndexOutOfBoundsException
     *         if {@code index < getMinIndex() || index > getMaxIndex()}
     */
    public Element set(int index, Element element)
    throws IndexOutOfBoundsException {
        try {
            Element oldElement = get(index);
            arrayList.set(index - minIndex, element);
            return oldElement;
        }
        catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }

    /**
     * Returns the minimum index of this Array.
     * 
     * @return int specifying the minimum index
     */
    public int getMinIndex() {
        return minIndex;
    }

    /**
     * Returns the maximum index of this Array.
     * 
     * @return int specifying the maximum index
     */
    public int getMaxIndex() {
        return maxIndex;
    }

    // @see java.util.List#subList(int, int)
    public List<Element> subList(int fromIndex, int toIndex)
    throws IndexOutOfBoundsException {
        if (fromIndex < minIndex)
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        if (toIndex < maxIndex)
            throw new ArrayIndexOutOfBoundsException(toIndex);
        return arrayList.subList(fromIndex - minIndex, toIndex - minIndex);
    }

    /**
     * Returns whether this Array is empty. This is true if and only if the size of this Array is 0.
     * 
     * @return {@code true} if {@code size() = 0}; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    // @see java.util.List#contains(java.lang.Object)
    public boolean contains(Object object) {
        return arrayList.contains(object);
    }

    // @see java.util.List#containsAll(java.util.Collection)
    public boolean containsAll(Collection<?> collection) {
        return arrayList.containsAll(collection);
    }

    // @see java.util.List#size()
    public int size() {
        return arrayList.size();
    }

    // @see java.lang.Iterable#iterator()
    public Iterator<Element> iterator() {
        return arrayList.iterator();
    }

    // @see java.util.List#listIterator()
    public ListIterator<Element> listIterator() {
        return arrayList.listIterator();
    }

    // @see java.lang.Object#clone()
    @Override
    public Object clone() {
        return new Array<Element>(minIndex, this);
    }

    /**
     * Returns a Java array containing all of the Elements of this Array in the correct order. The Element stored at
     * {@code getMinIndex() + i} in this Array is stored at index {@code i} in the Java array.
     * 
     * @return Java array containing the Elements of this Array
     */
    public Object[] toArray() {
        return arrayList.toArray();
    }

    /**
     * <p>
     * Returns a Java array containing all of the Elements of this Array in the correct order. The Element stored at
     * {@code getMinIndex() + i} in this Array is stored at index {@code i} in the Java array. The runtime type of the
     * returned Java array is that of the specified Java array.
     * </p>
     * <p>
     * If this Array fits in the specified Java array, it is returned therein. Otherwise, a new Java array is allocated
     * with the runtime type of the specified Java array and the size of this Array. If this Array fits in the specified
     * array with room to spare (i.e., the Java array has more elements than the list), the element in the array
     * immediately following the end of the collection is set to {@code null} . This is useful in determining the length
     * of the list only if the caller knows that the list does not contain any {@code null} elements.
     * </p>
     * 
     * @param <ArrayElement>
     *        type of the elements of the Java array
     * @param array
     *        Java array into which the elements of this Array are to be stored, if it is big enough; otherwise, a new
     *        Java array of the same runtime type is allocated for this purpose
     * @return Java array containing the Elements of this Array
     */
    public <ArrayElement> ArrayElement[] toArray(ArrayElement[] array) {
        return arrayList.toArray(array);
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be added to it.
     * </p>
     * 
     * @param index
     *        any integer
     * @param element
     *        any Element
     * @throws UnsupportedOperationException
     *         always
     */
    public void add(int index, Element element)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param index
     *        any integer
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public Element remove(int index)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // @see java.util.List#indexOf(java.lang.Object)
    public int indexOf(Object object) {
        int index = arrayList.indexOf(object);
        return (index != -1 ? index + minIndex : -1);
    }

    // @see java.util.List#lastIndexOf(java.lang.Object)
    public int lastIndexOf(Object object) {
        int index = arrayList.lastIndexOf(object);
        return (index != -1 ? index + minIndex : -1);
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be added to it.
     * </p>
     * 
     * @param element
     *        any Element
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean add(Element element)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // @see java.util.List#listIterator(int)
    public ListIterator<Element> listIterator(int index)
    throws IndexOutOfBoundsException {
        try {
            return arrayList.listIterator(index - minIndex);
        }
        catch (IndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be added to it.
     * </p>
     * 
     * @param collection
     *        any Collection
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean addAll(Collection<? extends Element> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be added to it.
     * </p>
     * 
     * @param index
     *        any integer
     * @param collection
     *        any Collection
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean addAll(int index, Collection<? extends Element> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param object
     *        any Object
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean remove(Object object)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param collection
     *        any Collection
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean removeAll(Collection<?> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @param collection
     *        any Collection
     * @return never returns
     * @throws UnsupportedOperationException
     *         always
     */
    public boolean retainAll(Collection<?> collection)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Always throws a {@code UnsupportedOperationException}.
     * </p>
     * <p>
     * Since an Array has a fixed size, no Elements can be removed from it.
     * </p>
     * 
     * @throws UnsupportedOperationException
     *         always
     */
    public void clear()
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // @see java.lang.Object#equals(java.lang.Object)
    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Array<?>))
            return false;
        Array<?> array = (Array<?>) object;
        if (minIndex != array.minIndex || maxIndex != array.maxIndex)
            return false;
        return arrayList.equals(array.arrayList);
    }

    // @see java.lang.Object#toString()
    @Override
    public String toString() {
        return arrayList.toString();
    }
}
