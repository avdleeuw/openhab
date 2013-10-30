/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

import org.openhab.binding.opentherm.internal.OpenThermException;

/**
 * Exception that occurs when invalid frame content is received from the
 * Gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermFrameException extends OpenThermException {

	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID = 6900455045649260321L;

	private final byte[] frameContent;

	/**
	 * Returns the invalid frame content.
	 * 
	 * @return the invalid frame content;
	 */
	public byte[] getFrameContent() {
		return frameContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param frameContent
	 *            the invalid frame content
	 */
	public OpenThermFrameException(byte[] frameContent) {
		this.frameContent = frameContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param frameContent
	 *            the invalid frame content
	 * @param message
	 *            the message indicating the Exception that occurred.
	 */
	public OpenThermFrameException(byte[] frameContent, String message) {
		super(message);
		this.frameContent = frameContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param frameContent
	 *            the invalid frame content
	 * @param cause
	 *            the cause that led to this Exception.
	 */
	public OpenThermFrameException(byte[] frameContent, Throwable cause) {
		super(cause);
		this.frameContent = frameContent;
	}

	/**
	 * Creates a new instance of the {@link OpenThermFrameException} class.
	 * 
	 * @param frameContent
	 *            the invalid frame content
	 * @param message
	 *            the message indicating the Exception that occurred.
	 * @param cause
	 *            the cause that led to this Exception.
	 */
	public OpenThermFrameException(byte[] frameContent, String message, Throwable cause) {
		super(message, cause);
		this.frameContent = frameContent;
	}
}
