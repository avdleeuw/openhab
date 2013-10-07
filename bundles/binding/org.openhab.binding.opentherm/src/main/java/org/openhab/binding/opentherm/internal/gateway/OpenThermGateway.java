/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2013, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */
package org.openhab.binding.opentherm.internal.gateway;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.opentherm.internal.protocol.OpenThermConnectionException;
import org.openhab.binding.opentherm.internal.protocol.OpenThermGatewayConnector;
import org.openhab.binding.opentherm.internal.protocol.OpenThermSerialGatewayConnector;
import org.openhab.binding.opentherm.internal.protocol.OpenThermTCPGatewayConnector;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageException;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageReceiver;

/**
 * OpenThermGateway class. Provides methods for interacting with the OpenTherm
 * Gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermGateway implements SerialMessageReceiver {

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
	}

	@Override
	public void receiveSerialMessage(SerialMessage serialMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveSerialError(SerialMessageException exception) {
		// TODO Auto-generated method stub

	}

}
