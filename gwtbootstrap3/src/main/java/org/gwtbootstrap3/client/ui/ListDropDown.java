package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.base.AbstractListItem;
import org.gwtbootstrap3.client.ui.base.button.AbstractToggleButton;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * Drop down item within a list, e.g. {@link NavTabs}, {@link NavPills} or
 * {@link Navbar}.
 *
 * <h3>UiBinder example</h3>
 *
 * <pre>
 * {@code
 *     <b:NavTabs>
 *         <b:ListItem active="true">Item 1</b:ListItem>
 *         <b:ListItem>Item 2</b:ListItem>
 *         <b:ListDropDown>
 *             <b:AnchorButton toggle="DROPDOWN">Dropdown</b:AnchorButton>
 *             <b:DropDownMenu>
 *                 <b:ListItem>Dropdown 1</b:ListItem>
 *                 <b:ListItem>Dropdown 2</b:ListItem>
 *                 <b:ListItem>Dropdown 3</b:ListItem>
 *             </b:DropDownMenu>
 *         </b:ListDropDown>
 *         <b:ListItem>Item 3</b:ListItem>
 *     </b:NavTabs>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @see NavTabs
 * @see NavPills
 * @see Navbar
 */
public class ListDropDown extends AbstractListItem {

    public ListDropDown() {
        setStyleName(Styles.DROPDOWN);
    }

    @Override
    public void add(final Widget child) {
        if (child instanceof AbstractToggleButton) {
            if (!(child instanceof AnchorButton)) {
                throw new IllegalArgumentException("Only buttons of type AnchorButton can be added to ListDropDown");
            }

            child.setStyleName(Styles.DROPDOWN_TOGGLE);
        }

        add(child, (Element) getElement());
    }
}