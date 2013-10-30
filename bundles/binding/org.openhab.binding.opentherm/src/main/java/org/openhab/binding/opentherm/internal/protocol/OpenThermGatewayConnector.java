/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol;

import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageReceiver;

/**
 * OpenThermGatewayConnector interface. Defines the methods for connecting to an
 * opentherm gateway. Two implementations are provided: A TCP/IP gateway
 * connector class and a serial gateway connector class.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public interface OpenThermGatewayConnector {

	/**
	 * Connects to the OpenTherm Gateway.
	 * 
	 * @param port
	 *            The port to connect the gateway to.
	 * @throws OpenThermConnectionException
	 */
	public void connect(String port) throws OpenThermConnectionException;

	/**
	 * Disconnects from the OpenTherm Gateway.
	 */
	public void disconnect();

	/**
	 * Enqueue a {@link SerialMessage} for sending to the gateway.
	 * 
	 * @param serialMessage
	 *            the {@link SerialMessage} to send.
	 */
	public void enqueue(SerialMessage serialMessage);

	/**
	 * Adds a {@link OpenThermSerialMessageReceiver} to the list of receivers.
	 * 
	 * @param serialMessageReceiver
	 *            the serialMessage receiver to add.
	 */
	public void addSerialMessageReceiver(SerialMessageReceiver serialMessageReceiver);

	/**
	 * Removes the {@link SerialMessageReceiver} from the list of receivers.
	 * 
	 * @param serialMessageReceiver
	 *            the serial message receiver to remove.
	 */
	public void removeSerialMessageReceiver(SerialMessageReceiver serialMessageReceiver);
}
