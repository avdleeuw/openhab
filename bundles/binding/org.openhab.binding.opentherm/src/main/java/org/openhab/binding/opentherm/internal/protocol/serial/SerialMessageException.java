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
	 * Creates a new instance of the {@link SerialMessageException} class.
	 * 
	 * @param serialMessageContent
	 *            the invalid serial message content
	 */
	public SerialMessageException(String serialMessageContent) {
		this.serialMessageContent = serialMessageContent;
	}

	/**
	 * Creates a new instance of the {@link SerialMessageException} class.
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
	 * Creates a new instance of the {@link SerialMessageException} class.
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
	 * Creates a new instance of the {@link SerialMessageException} class.
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
