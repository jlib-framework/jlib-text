/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2018 Igor Akkerman
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

package org.jlib.text;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jlib.text.Text.EMPTY;
import static org.jlib.text.Text.removeFirst;

public class TextTest {

    @Test
    public void testRemoveOnceEmptyFromEmpty() {
        assertThat(removeFirst(EMPTY, EMPTY)).isEmpty();
    }

    @Test
    public void testRemoveOnceEmptyFromFull() {
        assertThat(removeFirst("Blubber", EMPTY)).isEqualTo("Blubber");
    }

    @Test
    public void testRemoveOnceAll() {
        assertThat(removeFirst("Blub", "Blub")).isEmpty();
    }

    @Test
    public void testRemoveOnceBeginning() {
        assertThat(removeFirst("Blubber", "Blub")).isEqualTo("ber");
    }

    @Test
    public void testRemoveOnceEnd() {
        assertThat(removeFirst("Blubber", "ber")).isEqualTo("Blub");
    }

    @Test
    public void testRemoveOnceBeginningFromDouble() {
        assertThat(removeFirst("BlaBlubBla", "Bla")).isEqualTo("BlubBla");
    }

    @Test
    public void testRemoveOnceMiddleFromDouble() {
        assertThat(removeFirst("BlaBlubBlub", "Blub")).isEqualTo("BlaBlub");
    }
}
