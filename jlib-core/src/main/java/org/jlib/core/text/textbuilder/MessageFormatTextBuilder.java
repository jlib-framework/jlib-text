/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2013 Igor Akkerman
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

package org.jlib.core.text.textbuilder;

import java.text.MessageFormat;

/**
 * {@link TextBuilder} using the {@link MessageFormat} template format
 * and processing routine. Implemented as a singleton.
 *
 * @see MessageFormat#format(String, Object...)
 *
 * @author Igor Akkerman
 */
// TODO: unit unittest
public final class MessageFormatTextBuilder
implements TextBuilder {

    /** sole {@link MessageFormatTextBuilder} instance */
    public static final MessageFormatTextBuilder INSTANCE = new MessageFormatTextBuilder();

    /**
     * Returns the sole {@link MessageFormatTextBuilder} instance.
     *
     * @return sole {@link MessageFormatTextBuilder} instance
     */
    @SuppressWarnings("TypeMayBeWeakened")
    public static MessageFormatTextBuilder getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new {@link MessageFormatTextBuilder}.
     */
    private MessageFormatTextBuilder() {
        super();
    }

    @Override
    @SuppressWarnings("UnnecessaryToStringCall") // wrong inspection warning!
    public String applyTemplateArguments(final CharSequence template, final Object... arguments) {
        return MessageFormat.format(template.toString(), arguments);
    }
}