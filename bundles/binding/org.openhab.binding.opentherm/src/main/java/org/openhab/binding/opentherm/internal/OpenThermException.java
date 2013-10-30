/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal;

/**
 * Base class for all OpenTherm Exceptions
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermException extends Exception {

	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID = 1156345704186704559L;

	/**
	 * {@inheritDoc}
	 */
	public OpenThermException() {
	}

	/**
	 * {@inheritDoc}
	 */
	public OpenThermException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public OpenThermException(Throwable cause) {
		super(cause);
	}

	/**
	 * {@inheritDoc}
	 */
	public OpenThermException(String message, Throwable cause) {
		super(message, cause);
	}

}
