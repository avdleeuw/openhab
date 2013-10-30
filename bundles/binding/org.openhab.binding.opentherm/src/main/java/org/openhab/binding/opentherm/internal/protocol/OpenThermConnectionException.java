/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol;

import org.openhab.binding.opentherm.internal.OpenThermException;

/**
 * Exception that occurs while connecting to a gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermConnectionException extends OpenThermException {

	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID = -4088025100591695796L;

	private final String port;

	/**
	 * Returns the port that the connection failed to connect to.
	 * 
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Creates a new instance of the {@link OpenThermConnectionException} class.
	 * 
	 * @param port the port that the connection failed to connect to.
	 */
	public OpenThermConnectionException(String port) {
		this.port = port;
	}

	/**
	 * Creates a new instance of the {@link OpenThermConnectionException} class.
	 * 
	 * @param port the port that the connection failed to connect to.
	 * @param message the message indicating the Exception that occurred.
	 */
	public OpenThermConnectionException(String port, String message) {
		super(message);
		this.port = port;
	}

	/**
	 * Creates a new instance of the {@link OpenThermConnectionException} class.
	 * 
	 * @param port the port that the connection failed to connect to.
	 * @param cause the cause that led to this Exception.
	 */
	public OpenThermConnectionException(String port, Throwable cause) {
		super(cause);
		this.port = port;
	}

	/**
	 * Creates a new instance of the {@link OpenThermConnectionException} class.
	 * 
	 * @param port the port that the connection failed to connect to.
	 * @param message the message indicating the Exception that occurred.
	 * @param cause the cause that led to this Exception.
	 */
	public OpenThermConnectionException(String port, String message, Throwable cause) {
		super(message, cause);
		this.port = port;
	}
}
