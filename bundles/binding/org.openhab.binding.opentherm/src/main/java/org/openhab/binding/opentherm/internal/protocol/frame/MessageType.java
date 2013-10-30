/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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