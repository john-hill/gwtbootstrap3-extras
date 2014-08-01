package org.gwtbootstrap3.extras.tags.client.ui;

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


import org.gwtbootstrap3.client.ui.base.HasPlaceholder;
import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Div;
import org.gwtbootstrap3.extras.tags.client.ui.base.constants.TagSize;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;

/**
 * A light-weight GWT widget for <a href="http://maxwells.github.io/bootstrap-tags.html">bootstrap-tags</a>.
 * <p>
 * Note: The original javascript hard-coded the icon so it was modified to allow for the same icons used by
 * the rest of gwtboostrap3.
 * </p>
 * @author John Hill
 *
 */
public class Tags extends Div implements HasPlaceholder, HasType<IconType>{
    
    private boolean isEnabled = false;
    private TagSize tagSize = TagSize.MEDIUM; 
    private IconType iconType = IconType.TIMES_CIRCLE;
    private String placeHolder = "Enter tags...";
    private JsArrayString initializeTags = null;
    private String readOnlyEmptyMessage = "";
    
    @Override
    protected void onLoad() {
        super.onLoad();
        rebuild();
    }
    
    /**
     * Called after loading.
     */
    public void rebuild() {
        clear();
        initialize(getElement(), tagSize.getCssName(), !isEnabled, placeHolder, iconType.getCssName(), initializeTags);
    }


    /**
     * Get the current tags.
     * @return
     */
    public String[] getTags() {
        JsArrayString array = getTags(getElement());
        int count = array.length();
        String[] results = new String[count];
        for(int i=0; i<count; i++){
            results[i] = array.get(i);
        }
        return results;
    }
    
    /**
     * Set the tags.  This will reset the widget.
     * @param tags
     */
    public void setTags(String...tags){
        this.clear();
        this.initializeTags = JavaScriptObject.createArray().cast();

        for (String value : tags) {
            value = SafeHtmlUtils.htmlEscape(value);
            initializeTags.push(value);
        }
        // Need to defer the rebuild to make sure the element is actually in the DOM to manipulate
        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                rebuild();
            }
        });
    }

    /**
     * Add a new tag.
     * @param tag
     */
    public void addTag(final String tag){
        final String cleanTag = SafeHtmlUtils.htmlEscape(tag);
        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                addTag(getElement(), cleanTag);
            }
        });
    }
    
    /**
     * Remove a tag.
     * @param tag
     */
    public void removeTag(final String tag){
        final String cleanTag = SafeHtmlUtils.htmlEscape(tag);
        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                removeTag(getElement(), cleanTag);
            }
        });
    }
    
    /**
     * Set the place holder text in the main input field.
     * <p>
     * Note: If this is called after the widget has been built, then {@link #rebuild()} must be called to apply the change.
     * </p>
     */
    @Override
    public void setPlaceholder(final String placeholder) {
        this.placeHolder = placeholder;
    }

    @Override
    public String getPlaceholder() {
        return this.placeHolder;
    }

    /**
     * The icon used to render the 'remove' of each tag.
     * 
     * <p>
     * Note: If this is called after the widget has been built, then {@link #rebuild()} must be called to apply the change.
     * </p>
     */
    @Override
    public void setType(final IconType type) {
        this.iconType = type;
    }

    /**
     * The icon used to render the 'remove' of each tag.
     */
    @Override
    public IconType getType() {
        return this.iconType;
    }
    
    /**
     * Determines the size of both the tags and the input text box.
     * <p>
     * Note: If this is called after the widget has been built, then {@link #rebuild()} must be called to apply the change.
     * </p>
     * @param size
     */
    public void setSize(final TagSize size){
        this.tagSize = size;
    }
    
    /**
     * Determines the size of both the tags and the input text box.
     * @return
     */
    public TagSize getTagSize(){
        return this.tagSize;
    }
    /**
     * Can the user add and remove tags from this collection?
     * <p>
     * Note: If this is called after the widget has been built, then {@link #rebuild()} must be called to apply the change.
     * </p>
     * @param readOnly
     */
    public void setEnabled(final boolean enabled){
        this.isEnabled = enabled;
    }
    /**
     * Can the user add and remove tags from this collection?
     * @return
     */
    public boolean isEnabled(){
        return this.isEnabled;
    }
    
    
    /**
     * Message shown when there are no tags in read-only mode.
     * @return
     */
    public String getReadOnlyEmptyMessage() {
        return readOnlyEmptyMessage;
    }

    /**
     * Message shown when there are no tags in read-only mode.
     * <p>
     * Note: If this is called after the widget has been built, then {@link #rebuild()} must be called to apply the change.
     * </p>
     * @param readOnlyEmptyMessage
     */
    public void setReadOnlyEmptyMessage(String readOnlyEmptyMessage) {
        this.readOnlyEmptyMessage = readOnlyEmptyMessage;
    }

    /**
     * The main javascript that builds up the components of this widget.
     * @param e
     * @param tagSizeIn
     * @param readOnlyIn
     * @param placeHolder
     * @param removeIconIn
     * @param tagDataIn
     */
    private static native void initialize(Element e, String tagSizeIn, boolean readOnlyIn, String placeHolder, String removeIconIn, JsArrayString tagDataIn)/*-{
    $wnd.jQuery(e).tags({
          readOnly: readOnlyIn,
          tagSize: tagSizeIn,
          promptText: placeHolder,
          iconBase: 'fa',
          removeIcon: removeIconIn,
          tagData: tagDataIn
       });
    }-*/;

    /**
     * Get the current tags for this widget
     * @param e
     * @return
     */
    private static native JsArrayString getTags(Element e)/*-{
        return $wnd.jQuery(e).getTags();
    }-*/;

    /**
     * Dynamically add a tag to this widget.
     * 
     * @param e
     * @param tag
     */
    private static native void addTag(Element e, String tag)/*-{
         $wnd.jQuery(e).addTag(tag);
    }-*/;

    /**
     * Dynamically remove a tag from this widget.
     * @param e
     * @param tag
     */
    private static native void removeTag(Element e, String tag)/*-{
        $wnd.jQuery(e).removeTag(tag);
    }-*/;
}
