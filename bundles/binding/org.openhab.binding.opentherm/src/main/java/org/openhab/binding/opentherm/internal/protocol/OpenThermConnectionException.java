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
