/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.operator.observer;

import org.jlib.operator.HandledOperator;
import org.jlib.operator.OperatorException;

/**
 * Utility for the observer pattern.
 *
 * @author Igor Akkerman
 */
public final class ObserverUtility {

<<<<<<< HEAD
    public static <Value> ConsumersValueObserver<Value> observe() {
        return new ConsumersValueObserver<>();
=======
    /** no visible constructor */
    private ObserverUtility() {
    }

    public static <Value> BuiltValueObserver<Value> observe() {
        return new BuiltValueObserver<>();
>>>>>>> cabaecf59fdcd7e2645b812648df6b6261d832a1
    }

    /**
     * Operates on the specified Value using the specified {@link HandledOperator} .
     *
     * @param <Value>
     *        type of the value
     *
     * @param handledOperator
     *        {@link HandledOperator} performing the operation
     *
     * @param value
     *        Value operated on
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     *
     * @throws RuntimeException
     *         <ul>
     *         <li>if {@code operator} throws an {@link OperatorException} with
     *         this {@link RuntimeException} as its cause</li>
     *         <li>if {@code operator} throws this {@link RuntimeException}</li>
     *         <li>if a {@link ValueObserver} in {@code observers} throws this
     *         {@link RuntimeException}</li>
     *         </ul>
     */
    @SafeVarargs
    @SuppressWarnings({ "ProhibitedExceptionDeclared", "ProhibitedExceptionThrown" })
    public static <Value> void operate(final HandledOperator handledOperator, final Value value,
                                       final ValueObserver<Value>... observers)
    throws RuntimeException {
        try {
<<<<<<< HEAD
            for (final ValueObserver<Value> observer : observers)
                observer.before(value);

            handledOperator.operate();

            for (final ValueObserver<Value> observer : observers)
                observer.afterSuccess(value);
        }
        catch (final OperatorException exception) {
            // if "legal" exception is thrown
            for (final ValueObserver<Value> observer : observers)
                observer.afterFailure(value, exception);
=======
            for (final ValueObserver<Value> observer : observers) {
                observer.before(value);
            }

            handledOperator.operate();

            for (final ValueObserver<Value> observer : observers) {
                observer.afterSuccess(value);
            }
        }
        catch (final OperatorException exception) {
            // if "legal" exception is thrown
            for (final ValueObserver<Value> observer : observers) {
                observer.afterFailure(value, exception);
            }
>>>>>>> cabaecf59fdcd7e2645b812648df6b6261d832a1

            throw exception.getCause();
        }
    }

<<<<<<< HEAD
    private ObserverUtility() {}
=======
    public static void main(final String... commandLineArguments) {
        operate(null, null, observe().beforeDo(value -> System.out.println("Success: " + value))
                                     .afterFailure(value -> System.out.println("Failure: " + value)));
    }
>>>>>>> cabaecf59fdcd7e2645b812648df6b6261d832a1
}
