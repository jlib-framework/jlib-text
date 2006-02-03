package org.jlib.core.reflection;

import org.jlib.core.system.PropertyNotSetException;

/**
 * <p>
 * Utility class providing an easy-to-use interface for creating objects using reflection. It consists of static methods
 * solely. Objects can be created providing their class, an Object of their class, their class name or the name of a
 * system property specifying their class name.
 * </p>
 * <p>
 * Additionally, when using the methods of the class {@link Class} for instantiating classes using reflection, a heap of
 * Exceptions have to be catched. Usually, the application that uses these methods makes no difference why the
 * instantiation failed. They simply catch {@code Throwable} to perform the exception handling. This is not a clean way
 * to handle this problem.
 * </p>
 * <p>
 * In contrast, the methods of this class throw one single checked exception, {@link ClassInstantiationException}, so
 * that the application only needs to catch that single one. The {@code ClassInstantiationException} contains the
 * information about the original Throwable that caused it so if the actual reason is of interest, it still can be
 * retreived.
 * </p>
 * 
 * @author Igor Akkerman
 */
public class ReflectionUtility {

    /** no constructor */
    private ReflectionUtility() {};

    /**
     * <p>
     * Creates a new instance of the specified class.
     * </p>
     * <p>
     * This method calls {@link Class#newInstance()}. If it throws an Exception, it is wrapped into a
     * {@code ClassInstantiationException} and thrown by this method.
     * </p>
     * 
     * @param <Type>
     *        type of the object to create
     * @param clazz
     *        Class to instantiate
     * @return a new instance of {@code clazz}
     * @throws ClassInstantiationException
     *         if the instantiation of the specified class fails
     */
    public static <Type> Type newInstanceOf(Class<? extends Type> clazz)
    throws ClassInstantiationException {
        try {
            return clazz.newInstance();
        }
        catch (InstantiationException exception) {
            throw new ClassInstantiationException(clazz, exception);
        }
        catch (IllegalAccessException exception) {
            throw new ClassInstantiationException(clazz, exception);
        }
    }

    /**
     * <p>
     * Creates a new instance of the class of the specified Object.
     * </p>
     * <p>
     * This method calls {@link Class#newInstance()}. If it throws a Throwable of any kind, it is wrapped into a
     * {@code ClassInstantiationException} and thrown by this method.
     * </p>
     * 
     * @param <Type>
     *        type of the object to create
     * @param object
     *        Object instance of the class to instantiate
     * @return a new instance of the specified class
     * @throws ClassInstantiationException
     *         if the instantiation of the specified class fails
     */
    public static <Type> Type newInstanceOf(Type object)
    throws ClassInstantiationException {
        return newInstanceOf((Class<? extends Type>) object.getClass());
    }

    /**
     * <p>
     * Creates a new instance of the class specified by its name.
     * </p>
     * <p>
     * This method calls {@link Class#newInstance()}. If it throws a Throwable of any kind, it is wrapped into a
     * {@code ClassInstantiationException} and thrown by this method.
     * </p>
     * 
     * @param <Type>
     *        type of the object to create
     * @param className
     *        String specifying the name of the class to instantiate
     * @return a new instance of the specified class
     * @throws ClassInstantiationException
     *         if the instantiation of the specified class fails or the instantiated object is not an instance of the
     *         class represented by {@code Type} or a subclass
     */
    @SuppressWarnings("unchecked")
    public static <Type> Type newInstanceOf(String className)
    throws ClassInstantiationException {
        Class<? extends Type> clazz;
        try {
            clazz = (Class<? extends Type>) Class.forName(className);
            Type instance = newInstanceOf(clazz);
            return instance;
        }
        catch (ClassNotFoundException exception) {
            throw new ClassInstantiationException(className, exception);
        }
        catch (ClassCastException exception) {
            throw new ClassInstantiationException(className, exception);
        }
    }

    /**
     * Creates a new instance of the class defined in the specified system property.
     * 
     * @param <Type>
     *        type of the object to create
     * @param propertyName
     *        String specifying the name of the system property in which the class name is defined
     * @return a new instance of the specified class
     * @throws NullPointerException
     *         if {@code propertyName == null}
     * @throws IllegalArgumentException
     *         if (@code propertyName} is the empty String
     * @throws PropertyNotSetException
     *         if the specified system property is not set
     * @throws ClassInstantiationException
     *         <ul>
     *         <li>if the specified system property is not set (cause is a {@link PropertyNotSetException}) or</li>
     *         <li>if the instantiation of the specified class fails (cause is one of the exceptions thrown by
     *         {@link Class#forName(String)}) or</li>
     *         <li>the instantiated object is not an instance of the class represented by {@code Type} or a subclass
     *         (cause is a {@link ClassCastException}).
     *         </ul>
     */
    public static <Type> Type newInstanceByProperty(String propertyName)
    throws PropertyNotSetException, ClassInstantiationException {
        String className = System.getProperty(propertyName);
        if (className == null)
            throw new PropertyNotSetException(propertyName);

        return newInstanceOf(className);
    }
}
