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
