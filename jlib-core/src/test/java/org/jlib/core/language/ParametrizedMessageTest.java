/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2014 Igor Akkerman
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

package org.jlib.core.language;

import org.jlib.core.text.ParametrizedMessage;

import static org.jlib.core.text.ParametrizedMessageUtility.message;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParametrizedMessageTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thrownExceptionShouldHaveCorrectClassAndMessage() {

        expectedException.expect(ExceptionA.class);
        expectedException.expectMessage("Something went wrong. dummyName='Dummy Value'");

        throw new ExceptionA(message("Something went wrong.").with("dummyName", "Dummy Value"));
    }

    private static class ExceptionA extends InvalidStateException {
        private ExceptionA(final ParametrizedMessage message) {
            super(message);
        }
    }
}
