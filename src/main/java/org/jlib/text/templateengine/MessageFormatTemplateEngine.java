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

package org.jlib.text.templateengine;

import java.text.MessageFormat;

import static lombok.AccessLevel.PRIVATE;
import lombok.NoArgsConstructor;

/**
 * {@link TemplateEngine} using the {@link MessageFormat} template format and processing routine. Implemented as a
 * singleton.
 *
 * @see MessageFormat#format(String, Object...)
 *
 * @author Igor Akkerman
 */
@NoArgsConstructor(access = PRIVATE)
public final class MessageFormatTemplateEngine
    implements TemplateEngine<Object> {

    public static final MessageFormatTemplateEngine MESSAGE_FORMAT_TEMPLATE_ENGINE = new MessageFormatTemplateEngine();
    public static final MessageFormatTemplateEngine INSTANCE = new MessageFormatTemplateEngine();

    @Override
    public final String applyArguments(final CharSequence template, final Object... arguments) {
        return MessageFormat.format(template.toString(), arguments);
    }
}
