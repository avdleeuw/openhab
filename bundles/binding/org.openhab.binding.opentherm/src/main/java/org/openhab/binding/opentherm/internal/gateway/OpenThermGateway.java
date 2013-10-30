/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.gateway;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.opentherm.internal.protocol.OpenThermConnectionException;
import org.openhab.binding.opentherm.internal.protocol.OpenThermGatewayConnector;
import org.openhab.binding.opentherm.internal.protocol.OpenThermSerialGatewayConnector;
import org.openhab.binding.opentherm.internal.protocol.OpenThermTCPGatewayConnector;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermFrame;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageException;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThermGateway class. Provides methods for interacting with the OpenTherm
 * Gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermGateway implements SerialMessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(OpenThermGateway.class);
	private OpenThermGatewayConnector gatewayConnector;

	/**
	 * Connects to the gateway using the specified port. The port can be a tcp
	 * uri or a serial port.
	 * 
	 * @param port
	 *            the port to connect to.
	 * @throws OpenThermConnectionException
	 *             a connection exception in case the connection fails.
	 */
	public void connect(String port) throws OpenThermConnectionException {
		logger.info("Gateway connecting to port {}", port);
		if (port.toLowerCase().contains("tcp://")) {
			gatewayConnector = new OpenThermTCPGatewayConnector();
		} else if (StringUtils.isNotBlank(port)) {
			gatewayConnector = new OpenThermSerialGatewayConnector();
		}
		gatewayConnector.addSerialMessageReceiver(this);
		gatewayConnector.connect(port);
	}

	/**
	 * Disconnects the connection to the gateway.
	 */
	public void disconnect() {
		if (gatewayConnector != null) {
			gatewayConnector.disconnect();
			gatewayConnector.removeSerialMessageReceiver(this);
			gatewayConnector = null;
		}
		logger.info("Gateway disconnected");
	}

	@Override
	public void receiveSerialMessage(SerialMessage serialMessage) {
		logger.trace("Incoming Serial Message from connector.");
		
		OpenThermFrame frame = OpenThermFrame.fromSerialMessage(serialMessage);
		
		if (frame != null) {
			logger.debug(String.format("Frame received: %n%s", frame.toString()));
		}

	}

	@Override
	public void receiveSerialError(SerialMessageException exception) {
		logger.trace("Incoming Serial Error from connector.");


	}

}
