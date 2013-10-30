/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.serial;

import org.openhab.binding.opentherm.internal.OpenThermException;

/**
 * Exception that occurs when an invalid serial message is received from the
 * Gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class SerialMessageException extends OpenThermException {

	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID = -5254633632973540449L;

	private final String serialMessageContent;

	/**
	 * Returns the invalid serial Message content.
	 * 
	 * @return the invalid serial Message content;
	 */
	public String getSerialMessageContent() {
		return serialMessageContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param serialMessageContent
	 *            the invalid serial message content
	 */
	public SerialMessageException(String serialMessageContent) {
		this.serialMessageContent = serialMessageContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param serialMessageContent
	 *            the invalid serial message content
	 * @param message
	 *            the message indicating the Exception that occurred.
	 */
	public SerialMessageException(String serialMessageContent, String message) {
		super(message);
		this.serialMessageContent = serialMessageContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param serialMessageContent
	 *            the invalid serial message content
	 * @param cause
	 *            the cause that led to this Exception.
	 */
	public SerialMessageException(String serialMessageContent, Throwable cause) {
		super(cause);
		this.serialMessageContent = serialMessageContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param serialMessageContent
	 *            the invalid serial message content
	 * @param message
	 *            the message indicating the Exception that occurred.
	 * @param cause
	 *            the cause that led to this Exception.
	 */
	public SerialMessageException(String serialMessageContent, String message, Throwable cause) {
		super(message, cause);
		this.serialMessageContent = serialMessageContent;
	}
}
