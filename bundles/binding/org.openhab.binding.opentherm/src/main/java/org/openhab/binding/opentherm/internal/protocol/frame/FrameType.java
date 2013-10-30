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
 * Defines the type of frame that was received from the gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public enum FrameType {
	BOILER("B", "Received from Boiler"), 
	THERMOSTAT("T", "Received from Thermostat"), 
	REQUEST_TO_BOILER("R", "Altered Request sent to boiler."), 
	ANSWER_TO_THERMOSTAT("A", "Altered Answer sent to Thermostat.");

	private static Map<String, FrameType> keyToFrameTypeMapping = null;
	private final String key;
	private final String label;

	/**
	 * Returns the key of the frame type.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the label of the frame type.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	private FrameType(String key, String label) {
		this.key = key;
		this.label = label;
	}

	private static void addMappings() {
		for (FrameType frameType : values()) {
			keyToFrameTypeMapping.put(frameType.getKey(), frameType);
		}
	}

	/**
	 * Get the {@link FrameType} by key.
	 * 
	 * @param key
	 *            the key to use. Can be B,T,R,A
	 * @return the {@link FrameType} or null if not found;
	 */
	public static FrameType get(String key) {
		if (keyToFrameTypeMapping == null) {
			keyToFrameTypeMapping = new HashMap<String, FrameType>();
			addMappings();
		}
		return keyToFrameTypeMapping.get(key);
	}
}