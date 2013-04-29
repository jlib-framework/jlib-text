package org.jlib.container.matrix;

import org.jlib.core.traverser.NoNextItemException;
import org.jlib.core.traverser.ReplaceTraverser;

/**
 * {@link MatrixTraverser} of an {@link EmptyMatrix}. Implemented as a
 * singleton.
 * 
 * @param <Entry>
 *        type of entries of the {@link Matrix}
 * 
 * @author Igor Akkerman
 */
public class EmptyMatrixTraverser<Entry>
implements MatrixTraverser<Entry>, ReplaceTraverser<Entry> {

    /** sole {@link EmptyMatrixTraverser} instance */
    private static final EmptyMatrixTraverser<?> INSTANCE = new EmptyMatrixTraverser<>();

    /**
     * Returns the sole {@link EmptyMatrixTraverser} instance.
     * 
     * @param <Entry>
     *        type of the entries potentially held in the {@link EmptyMatrix}
     * 
     * @return sole {@link EmptyMatrixTraverser} instance
     */
    @SuppressWarnings("unchecked")
    public static <Entry> EmptyMatrixTraverser<Entry> getInstance() {
        return (EmptyMatrixTraverser<Entry>) INSTANCE;
    }

    /**
     * Creates a new {@link EmptyMatrixTraverser}.
     */
    private EmptyMatrixTraverser() {
        super();
    }

    @Override
    public boolean isNextItemAccessible() {
        return false;
    }

    @Override
    public Entry getNextItem() {
        throw new NoNextItemException(EmptyMatrix.getInstance());
    }

    @Override
    public boolean hasNextEntity() {
        return false;
    }

    @Override
    public void gotoNextEntity()
    throws IllegalStateException {
        throw new IllegalStateException();
    }

    @Override
    public void replace(final Entry item)
    throws IllegalStateException {
        throw new IllegalStateException();
    }
}