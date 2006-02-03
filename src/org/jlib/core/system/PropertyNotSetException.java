package org.jlib.core.system;

/**
 * Exception thrown when trying to retreive the value of a System property that is not set.
 * 
 * @author Igor Akkerman
 */
public class PropertyNotSetException
extends RuntimeException {

    /** name of the property that is not set */
    private String propertyName;

    /** no default constructor */
    private PropertyNotSetException() {}

    /**
     * Creates a new PropertyNotSetException.
     * 
     * @param propertyName
     *        String specifying the name of the property that is not set
     */
    public PropertyNotSetException(String propertyName) {
        super();
        this.propertyName = propertyName;
    }

    /**
     * Returns the name of the property that is not set.
     * 
     * @return String specifying the name of the property that is not set
     */
    public String getPropertyName() {
        return propertyName;
    }

    // @see java.lang.Throwable#toString()
    public String toString() {
        return getClass().getName() + "[" + propertyName + "]";
    }
}
