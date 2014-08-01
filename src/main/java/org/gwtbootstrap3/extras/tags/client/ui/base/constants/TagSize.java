package org.gwtbootstrap3.extras.tags.client.ui.base.constants;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2014 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.gwtbootstrap3.client.ui.base.helper.EnumHelper;

import com.google.gwt.dom.client.Style;
/**
 * Determines the size of both the tags and input text box of the {@link org.gwtbootstrap3.extras.tags.client.ui.Tags}
 * @author John Hill
 *
 */
public enum TagSize implements Style.HasCssName {
    
    SMALL("sm"),
    MEDIUM("md"),
    LARGE("lg");
    
    private final String cssClass;

    private TagSize(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static TagSize fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, TagSize.class, MEDIUM);
    }

}
