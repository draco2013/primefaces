/*
 * Copyright 2009-2013 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.context;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;

import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.StringEncrypter;
import org.primefaces.util.WidgetBuilder;

/**
 * A RequestContext is a helper class consisting of several utilities.
 * RequestContext is thread-safe and scope is same as FacesContext.
 * Current instance can be retrieved as;
 * <blockquote>
 *  RequestContext.getCurrentInstance();
 * </blockquote>
 */
public abstract class RequestContext {

	private static final ThreadLocal<RequestContext> INSTANCE = new ThreadLocal<RequestContext>();

    public static RequestContext getCurrentInstance() {
        return INSTANCE.get();
    }

    public static void setCurrentInstance(final RequestContext context) {
        if (context == null) {
        	INSTANCE.remove();
        } else {
        	INSTANCE.set(context);
        }
    }

    /**
     * @return true if request is an ajax request, otherwise return false.
     */
    public abstract boolean isAjaxRequest();

    /**
     * Add a parameter for ajax oncomplete client side callbacks. Value would be serialized to json.
     * @param name name of the parameter.
     * @param object value of the parameter.
     */
    public abstract void addCallbackParam(String name, Object value);

    /**
     * @return all callback parameters added in the current request.
     */
    public abstract Map<String, Object> getCallbackParams();

    /**
     * @return all scripts added in the current request.
     */
    public abstract List<String> getScriptsToExecute();

    /**
     * Execute a javascript after current ajax request is completed.
     * @param script Javascript statement to execute.
     */
    public abstract void execute(String script);

    /**
     * Scroll to a component after ajax request is completed.
     * @param clientId Client side identifier of the component.
     */
    public abstract void scrollTo(String clientId);

    /**
     * Update a component with ajax.
     * @param name Client side identifier of the component.
     */
    public abstract void update(String name);

    /**
     * Update components with ajax.
     * @param collection Client side identifiers of the components.
     */
    public abstract void update(Collection<String> collection);

    /**
     * Reset an editableValueHolder.
     * @param expressions A string with one or multiple search expression to resolve the components.
     */
    public abstract void reset(String expressions);

    /**
     * Reset a collection of editableValueHolders.
     * @param expressions A list with with one or multiple search expression to resolve the components.
     */
    public abstract void reset(Collection<String> expressions);

    /**
     * @return Shared WidgetBuilder instance of the current request
     */
    public abstract WidgetBuilder getWidgetBuilder();

    /**
     * @return Shared AjaxRequestBuilder instance of the current request
     */
    public abstract AjaxRequestBuilder getAjaxRequestBuilder();
    
    /**
     * @return Attributes map in RequestContext scope
     */
    public abstract Map<Object,Object> getAttributes();

    /**
     * Open a view in dialog.
     * @param outcome The logical outcome used to resolve a navigation case.
     */
    public abstract void openDialog(String outcome);
    
    /**
     * Open a view in dialog.
     * @param outcome The logical outcome used to resolve a navigation case.
     * @param options Configuration options for the dialog.
     * @param params Parameters to send to the view displayed in a dialog.
     */
    public abstract void openDialog(String outcome, Map<String,Object> options, Map<String,List<String>> params);
    
    /**
     * Close a dialog.
     * @param data Optional data to pass back to a dialogReturn event.
     */
    public abstract void closeDialog(Object data);
    
    /**
     * Displays a message in a dialog.
     * @param message FacesMessage to be displayed.
     */
    public abstract void showMessageInDialog(FacesMessage message);

    /**
     * @return ApplicationContext instance.
     */
    public abstract ApplicationContext getApplicationContext();

    /**
     * @return StringEncrypter used to encode and decode a string.
     */
	public abstract StringEncrypter getEncrypter();
    
    /**
     * Clear resources.
     */
    public abstract void release();
}
