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
package org.openhab.binding.opentherm.internal.protocol.frame;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the type of message that was received from the gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public enum MessageType {
	READ_DATA(0, "Read Data from Master to Slave"),
	WRITE_DATA(1, "Write Data from Master to Slave"),
	INVALID_DATA(2, "Invalid Data from Master to Slave"),
	READ_ACK(4, "Read Acknowledge from Slave to Master"),
	WRITE_ACK(5, "Write Acknowledge from Slave to Master"),
	DATA_INVALID(6, "Invalid Data from Slave to Master"),
	UNKNOWN_DATAID(7, "Unknown Data ID from Slave to Master");

	private static Map<Integer, MessageType> keyToMessageTypeMapping = null;
	private final int key;
	private final String label;

	/**
	 * Returns the key of the message type.
	 * 
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Returns the label of the message type.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	private MessageType(int key, String label) {
		this.key = key;
		this.label = label;
	}

	private static void addMappings() {
		for (MessageType frameType : values()) {
			keyToMessageTypeMapping.put(frameType.getKey(), frameType);
		}
	}

	/**
	 * Get the {@link MessageType} by key.
	 * 
	 * @param key
	 *            the key to use.
	 * @return the {@link MessageType} or null if not found;
	 */
	public static MessageType get(int key) {
		if (keyToMessageTypeMapping == null) {
			keyToMessageTypeMapping = new HashMap<Integer, MessageType>();
			addMappings();
		}
		return keyToMessageTypeMapping.get(key);
	}
}