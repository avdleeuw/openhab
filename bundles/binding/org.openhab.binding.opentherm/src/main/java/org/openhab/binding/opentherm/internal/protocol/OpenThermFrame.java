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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

/**
 * OpenThermFrame class. Defines a single frame that is communicated between the
 * boiler or thermostat and the OpenTherm gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermFrame {

	private final FrameType frameType;
	private final byte[] payload;

	/**
	 * Constructor creates a new instance of the {@link OpenThermFrame} class.
	 * 
	 * @param frameType
	 *            the Frame Type
	 * @param payload
	 *            the payload.
	 */
	private OpenThermFrame(FrameType frameType, byte[] payload) {
		this.payload = payload;
		this.frameType = frameType;
	}

	/**
	 * Gets the Frame Type for this frame.
	 * @return the frameType
	 */
	public FrameType getFrameType() {
		return frameType;
	}

	/**
	 * Gets the frame payload.
	 * 
	 * @return the received payload.
	 */
	public byte[] getPayload() {
		return payload;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s: %s", frameType.getKey(),Hex.encodeHexString(payload));
	}
	
	/**
	 * Creates an OpenTherm frame from a string with frame content;
	 * @param frameContent the frame content to load.
	 * @return an OpenTherm frame
	 */
	public static OpenThermFrame fromString(String frameContent) {
		byte[] payload = new byte[4];
		FrameType frameType = FrameType.get(frameContent.substring(0, 1));
		payload[0] = (byte)(int) Integer.decode("0x" + frameContent.substring(1, 3));
		payload[1] = (byte)(int) Integer.decode("0x" + frameContent.substring(3, 5));
		payload[2] = (byte)(int) Integer.decode("0x" + frameContent.substring(5, 7));
		payload[3] = (byte)(int) Integer.decode("0x" + frameContent.substring(7, 9));
		return new OpenThermFrame(frameType, payload);
	}
	

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
}
