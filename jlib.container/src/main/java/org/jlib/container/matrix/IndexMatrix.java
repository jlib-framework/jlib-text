package org.jlib.container.matrix;

import java.util.Iterator;

import org.jlib.container.sequence.SequenceIndexOutOfBoundsException;

//@formatter:off
/**
* <p>
* Fixed sized matrix. Replacement for two-dimensional arrays with special
* features.
* </p>
* <p>
* The only syntactical difference to two-dimensinal arrays lies in the syntax
* of setting and getting objects:
* </p>
* 
* <pre>
* {
*     &#064;literal
*     // good(?) old two-dimensional array           // cool(!) new jlib ArraySequenceMatrix class
*     String[][] stringMatrix = new String[10][5];
*     ArraySequenceMatrix&lt;String&gt; stringMatrix = new ArraySequenceMatrix&lt;String&gt;(10, 5);
*     stringMatrix[2][3] = &quot;Too old!&quot;;
*     stringMatrix.set(2, 3, &quot;Brand-new!&quot;);
*     String s = stringMatrix[2][3];
*     String s = stringMatrix.get(2, 3);
* }
* </pre>
* 
* <p>
* Special features:
* </p>
* <ul>
* <li>Minimum and maximum width and height:<br/>
* On instantiation, you can specify the minimum and the maximum width and
* height of the ArraySequenceMatrix. Thus, no offset is necessary for matrices
* starting at other indices than 0. The following example illustrates how a
* (4x2)-ArraySequenceMatrix with indices starting at 1, in which every entry is
* the product of the column and row number:
* 
* <pre>
* 
* 
* {
*     &#064;literal
*     // good(?) old two-dimensional array             // cool(!) new jlib ArraySequenceMatrix class
*     Integer[][] integerMatrix = new Integer[4][2];
*     ArraySequenceMatrix&lt;Integer&gt; integerMatrix = new ArraySequenceMatrix&lt;Integer&gt;(4, 2);
*     for (int row = 1; row &lt;= 2; row ++)
*         for (int row = 1; row &lt;= 2; row ++)
*             for (int col = 1; col &lt;= 4; col ++)
*                 for (int col = 1; col &lt;= 4; col ++)
*                     integerMatrix[col - 1][row - 1] = col * row;
*     integerMatrix.set(col, row, col * row);
* }
* </pre>
* 
* </li>
* <li>Conformance to the Collections Framework <br/>
* The class implements the {@code Collection} interface and thus behaves like
* all Collections.</li>
* <br />
* <li>Full support for generics:<br/>
* The Java arrays do not support generic classes. For example, you cannot
* create an array of String Sequences:
* 
* <pre>
* 
* 
* {
*     &#064;literal
*     // FORBIDDEN!
*     Set&lt;String&gt;[][] stringSetMatrix = new Set&lt;String&gt;[4][2];
* 
*     // PERMITTED!
*     ArraySequenceMatrix&lt;Set&lt;String&gt;&gt; stringSetMatrix = new ArraySequenceMatrix&lt;Set&lt;String&gt;&gt;(4, 2);
* }
* </pre>
* 
* </li>
* </ul>
* <p>
* <!-- TODO: update documentation --> A default algorithm of how this
* ArraySequenceMatrix is traversed by {@link Iterator Iterators} returned by
* {@link #iterator()} can be defined specifying a default {@link Iterable} as
* iterable provider.
* </p>
* <p>
* A ArraySequenceMatrix has a fixed size, thus no Elements can be added to or
* removed from it. The corresponding methods for adding and removing Elements
* all throw an {@link UnsupportedOperationException}.
* </p>
* 
* @param <Element>
*        type of the elements held in the ArraySequenceMatrix
* @author Igor Akkerman
* @param <ColumnIndex>
*        type of column indices
* @param <RowIndex>
*        type of row indices
*/
//@formatter:on

public interface IndexMatrix<ColumnIndex, RowIndex, Element>
extends Matrix<Element> {

    /**
     * Returns the Element stored at the specified column and row in this
     * ArraySequenceMatrix.
     * 
     * @param columnIndex
     *        integer specifying the column of the stored Element
     * 
     * @param rowIndex
     *        integer specifying the row of the stored Element
     * 
     * @return Element stored at the specified position in this
     *         ArraySequenceMatrix
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code nextColumnIndex < getMinColumnIndex() ||
     *         nextColumnIndex > getMaxColumnIndex() || nextRowIndex <
     *         getMinRowIndex || nextRowIndex > getMaxRowIndex()}
     */
    public Element get(final ColumnIndex columnIndex, final RowIndex rowIndex)
    throws SequenceIndexOutOfBoundsException;

    /**
     * Registers the Element to store at the specified column and row in this
     * ArraySequenceMatrix.
     * 
     * @param columnIndex
     *        integer specifying the column of the Element to store
     * 
     * @param rowIndex
     *        integer specifying the row of the Element to store
     * 
     * @param element
     *        Element to store. {@code null} is a valid Element.
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code nextColumnIndex < getMinColumnIndex() ||
     *         nextColumnIndex > getMaxColumnIndex() || nextRowIndex <
     *         getMinRowIndex || nextRowIndex > getMaxRowIndex()}
     */
    public void set(final ColumnIndex columnIndex, final RowIndex rowIndex, final Element element);

    /**
     * Returns a MatrixColumn representing the specified column of this
     * ArraySequenceMatrix.
     * 
     * @param columnIndex
     *        integer specifying the index of the column
     * 
     * @return MatrixColumn representing the column with {@code nextColumnIndex}
     */
    public MatrixColumn<Element> getColumn(final ColumnIndex columnIndex);

    /**
     * Returns a MatrixColumn representing the specified portion of the
     * specified column of this ArraySequenceMatrix.
     * 
     * @param columnIndex
     *        integer specifying the index of the column
     * 
     * @param minimumRowIndex
     *        integer specifying the minimum row index of the portion of the
     *        column
     * 
     * @param maximumRowIndex
     *        integer specifying the maximum row index of the portion of the
     *        column
     * 
     * @return MatrixColumn representing the specified portion of the column
     *         with {@code nextColumnIndex}
     */
    public MatrixColumn<Element> getColumn(final ColumnIndex columnIndex, final RowIndex minimumRowIndex,
                                           final int maximumRowIndex);

    /**
     * Returns a MatrixRow representing the specified row of this
     * ArraySequenceMatrix.
     * 
     * @param rowIndex
     *        integer specifying the index of the row
     * 
     * @return MatrixRow representing the row with {@code nextRowIndex}
     */
    public MatrixRow<Element> getRow(final RowIndex rowIndex);

    /**
     * Returns a MatrixRow representing the specified portion of the specified
     * row of this ArraySequenceMatrix.
     * 
     * @param rowIndex
     *        integer specifying the index of the row
     * 
     * @param minimumColumnIndex
     *        integer specifying the minimum column index of the portion of the
     *        row
     * 
     * @param maximumColumnIndex
     *        integer specifying the maximum column index of the portion of the
     *        row
     * 
     * @return MatrixRow representing the specified portion of the row with
     *         {@code nextRowIndex}
     */
    // TODO: maybe it would be more appropriate to name these kinds of parameters using start/end or first/last
    public MatrixRow<Element> getRow(final RowIndex rowIndex, final int minimumColumnIndex, final int maximumColumnIndex);

    /**
     * Returns the minimum column index of this ArraySequenceMatrix.
     * 
     * @return integer specifying the minimum column of this ArraySequenceMatrix
     */
    public ColumnIndex firstColumnIndex();

    /**
     * Returns the maximum column index of this ArraySequenceMatrix.
     * 
     * @return integer specifying the maximum column of this ArraySequenceMatrix
     */
    public ColumnIndex lastColumnIndex();

    /**
     * Returns the minimum row index of this ArraySequenceMatrix.
     * 
     * @return integer specifying the minimum row of this ArraySequenceMatrix
     */
    public RowIndex firstRowIndex();

    /**
     * Returns the maximum row index of this ArraySequenceMatrix.
     * 
     * @return integer specifying the maximum row of this ArraySequenceMatrix
     */
    public RowIndex lastRowIndex();
}
