/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.serial;

import java.io.UnsupportedEncodingException;

/**
 * SerialMesage class. Defines a serial message that can be communicated with
 * the OpenTherm Gateway. The serial message is terminated by a carriage return
 * and a line feed.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class SerialMessage {

	private final String message;

	/**
	 * Returns the Serial Message received.
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Constructor. Creates a new instance of a {@link SerialMessage} class.
	 * 
	 * @param message
	 */
	public SerialMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Returns this serial message as an array of ASCII bytes.
	 * @return the serial message as an array of ASCII bytes..
	 */
	public byte[] toArray() {
		try {
			return String.format("%s\r\n", this.message).getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
		}
		return new byte[0];
	}
}
