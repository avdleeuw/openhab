/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.serial;

/**
 * SerialMessageReceiver interface. Defines the interface for receiving
 * SerialMessage frames and handling errors.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public interface SerialMessageReceiver {

	/**
	 * Receives a single serial Message from the source.
	 * 
	 * @param serialMessage
	 *            the serial Message to receive.
	 */
	public void receiveSerialMessage(SerialMessage serialMessage);

	/**
	 * Handles a serial message error from the source.
	 * 
	 * @param exception
	 *            the exception to handle.
	 */
	public void receiveSerialError(SerialMessageException exception);
}
