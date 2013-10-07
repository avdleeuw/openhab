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