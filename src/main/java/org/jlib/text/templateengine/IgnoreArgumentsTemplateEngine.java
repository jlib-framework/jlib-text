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

package org.jlib.text.templateengine;

import static lombok.AccessLevel.PRIVATE;
import lombok.NoArgsConstructor;

/**
 * {@link TemplateEngine} that always returns the plain template text ignoring the arguments.
 *
 * @param <Argument>
 */
@NoArgsConstructor(access = PRIVATE)
public final class IgnoreArgumentsTemplateEngine<Argument>
    implements TemplateEngine<Argument> {

    public static final IgnoreArgumentsTemplateEngine<?> INSTANCE = new IgnoreArgumentsTemplateEngine<>();

    @SuppressWarnings("unchecked")
    public static <Argument> IgnoreArgumentsTemplateEngine<Argument> instance() {
        return (IgnoreArgumentsTemplateEngine<Argument>) INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public static <Argument>
    IgnoreArgumentsTemplateEngine<Argument> instanceFor(final Class<? extends Argument> argumentClass) {
        return (IgnoreArgumentsTemplateEngine<Argument>) INSTANCE;
    }

    @Override
    @SafeVarargs
    public final String applyArguments(final CharSequence template, final Argument... arguments) {
        return template.toString();
    }
}
