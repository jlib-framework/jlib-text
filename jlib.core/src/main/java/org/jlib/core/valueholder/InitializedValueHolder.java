package org.jlib.core.valueholder;

/**
 * {@link ModifiableValueHolder} actually holding a Value.
 * 
 * @param <Value>
 *        type of the value
 * 
 * @author Igor Akkerman
 */
public class InitializedValueHolder<Value>
implements ModifiableValueHolder<Value>, AccessibleValueHolder<Value> {

    /** registered Value */
    private Value value;

    /**
     * Creates a new {@link InitializedValueHolder}.
     * 
     * @param initialValue
     *        initial Value
     */
    public InitializedValueHolder(final Value initialValue) {
        super();

        value = initialValue;
    }

    @Override
    public Value get() {
        return value;
    }

    @Override
    public void set(final Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(final Object otherObject) {
        if (!(otherObject instanceof InitializedValueHolder<?>))
            return false;

        final InitializedValueHolder<?> otherInitializedValueHolder = (InitializedValueHolder<?>) otherObject;

        return value.equals(otherInitializedValueHolder.value);
    }

    @Override
    public int hashCode() {
        // TODO: use Apache Commons HashCodeBuilder
        return value.hashCode() * 2;
    }
}