/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    SystemUtility.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.system;

/**
 * Utility class providing static methods for system operations.
 * 
 * @author Igor Akkerman
 */
public class SystemUtility {

    /**
     * Returns the value of the system property indicated by the specified key. The difference to the
     * {@link System#getProperty(String)} method is that this method throws an Exception if the specified key is not
     * set.
     * 
     * @param propertyName
     *        String specifying the name of the system property
     * @return String specifying the value of the system property with {@code propertyName}
     * @throws SecurityException
     *         if a security manager exists and its {@code checkPropertyAccess} method doesn't allow access to the
     *         specified system property
     * @throws NullPointerException
     *         if {@code key} is null
     * @throws IllegalArgumentException
     *         if {@code key} is an empty String
     * @throws PropertyNotSetException
     *         if the specified system property is not set
     */
    public static String getProperty(String propertyName) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue == null)
            throw new PropertyNotSetException(propertyName);
        return propertyValue;
    }

}
