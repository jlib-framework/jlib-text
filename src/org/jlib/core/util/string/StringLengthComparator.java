/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    StringLengthComparator.java
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

package org.jlib.core.util.string;

import java.util.Comparator;

/**
 * Comparator performing the comparison of two objects by comparing the lengths of their String representations as
 * returned by the {@code toString()} method.
 * 
 * @param <ComparisonObject> 
 *        type of the objects to compare
 * 
 * @author Igor Akkerman
 */
public class StringLengthComparator<ComparisonObject>
implements Comparator<ComparisonObject> {

    /**
     * Creates a new StringLengthComparator.
     */
    public StringLengthComparator() {
        super();
    }
    
    // @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
    public int compare(ComparisonObject object1, ComparisonObject object2) {
        return object1.toString().length() - object2.toString().length();
    }
}
