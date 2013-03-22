/*
 *  Copyright (C) 2010 Pedro T. Oliveira <pedro.oliveira.nom.br>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.nom.pedro.oliveira.smartproject.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * Utility Class to hold the messages of the system.
 * 
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public final class Messages {

	private static final String baseName = "messages";
	private static final String UNKNOWN = "???";
	private static final ResourceBundle bundle;
	

	static {
		bundle = ResourceBundle.getBundle(baseName, getLocale(), getLoader());
	}

	/**
	 * Create a new Faces Message with the correspondent code from default
	 * resource Bundle.
	 * 
	 * @param code
	 * @param severity
	 * @return
	 */
	public static FacesMessage create(final String code,
			final FacesMessage.Severity severity) {		
		final String summary;
		final String detail;
		
		if (bundle == null) {
			summary = UNKNOWN;
			detail = UNKNOWN + ".detail";
		} else {
			summary = bundle.getString(code);
			detail = bundle.getString(code + ".detail");
		}
		
		return new FacesMessage(severity, summary, detail);
	}
	
	/**
	 * Create a new Faces Message with the correspondent code from default
	 * resource Bundle.
	 * 
	 * @param code
	 * @param severity
	 * @return
	 */
	public static FacesMessage create(final String code, final Object[] params,
			final FacesMessage.Severity severity) {
		final String summary;
		final String detail;
		
		//TODO: ARRUMAR IMPLEMENTAÇÃO!!!
		
		if (bundle == null) {
			summary = UNKNOWN;
			detail = UNKNOWN + ".detail";
		} else {
			
			//MessageFormat format = new MessageFormat(null, null);
			//format.			
			summary = bundle.getString(code);
			detail = bundle.getString(code + ".detail");
		}
		
		return new FacesMessage(severity, summary, detail);
	}

	/**
	 * Returns the Application Locale
	 * 
	 * @return a Locale
	 */
	public static Locale getLocale() {
		final FacesContext context = FacesContext.getCurrentInstance();
		final UIViewRoot viewRoot = context.getViewRoot();
		return (viewRoot == null) ? Locale.getDefault() : viewRoot.getLocale();
	}

	/**
	 * Gets the Application ClassLoader
	 * 
	 * @return
	 */
	private static ClassLoader getLoader() {
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
	}
}
