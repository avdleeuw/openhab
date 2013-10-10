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
 * OpenThermCommandFrame. Executes a remote command.
 *
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermCommandFrame extends OpenThermWriteFrame {

	
	private final CommandCode commandCode;
	private final CommandResponseCode responseCode;
	
	/**
	 * Constructor. Creates a new instance of the {@link OpenThermCommandFrame} class
	 * @param frameType the Frame type from the OpenTherm frame.
	 * @param messageType the message type for the OpenTherm frame.
	 * @param payload. The frame payload.
	 */
	public OpenThermCommandFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.COMMAND, payload);

		switch (messageType) {
		case WRITE_DATA:
			responseCode = null;
			break;
		case WRITE_ACK:
		case DATA_INVALID:
		case UNKNOWN_DATAID:
			if (extractUnsignedLSB(payload) >= 0x80) {
				responseCode = CommandResponseCode.COMPLETED;
			} else {
				responseCode = CommandResponseCode.FAILED;
			}
			break;
		default:
			throw new OpenThermFrameException(payload, String.format("Invalid message type for command message: %s", messageType.getLabel()));
		}
		
		commandCode = CommandCode.get(payload[2] & 0xFF);
		
		if (commandCode == null) {
			throw new OpenThermFrameException(payload, String.format("Invalid command code for command message: %d", payload[2] & 0xFF));
		}
	}
	
	/**
	 * Returns the command code.
	 * @return the commandCode
	 */
	public CommandCode getCommandCode() {
		return commandCode;
	}

	/**
	 * Returns the response code.
	 * @return the responseCode
	 */
	public CommandResponseCode getResponseCode() {
		return responseCode;
	}

	/**
	 * Command Code enumeration. Enumerates the possible
	 * commands.
	 *
	 * @author Jan-Willem Spuij <jwspuij@gmail.com>
	 * @since 1.4.0
	 *
	 */
	public enum CommandCode {
		BLOR(1,"Boiler Lock-out Reset command"), 
		CHWF(2,"CH water filling command"); 
		
		private static Map<Integer, CommandCode> keyToCommandCodeMapping = null;
		private final int key;
		private final String label;

		/**
		 * Returns the key of the Command Code.
		 * 
		 * @return the key
		 */
		public int getKey() {
			return key;
		}

		/**
		 * Returns the label of the Command Code.
		 * 
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}
		
		private CommandCode(int key, String label) {
			this.key = key;
			this.label = label;
		}

		private static void addMappings() {
			for (CommandCode commandCode : values()) {
				keyToCommandCodeMapping.put(commandCode.getKey(), commandCode);
			}
		}

		/**
		 * Get the {@link CommandCode} by key.
		 * 
		 * @param key
		 *            the key to use.
		 * @return the {@link CommandCode} or null if not found;
		 */
		public static CommandCode get(int key) {
			if (keyToCommandCodeMapping == null) {
				keyToCommandCodeMapping = new HashMap<Integer, CommandCode>();
				addMappings();
			}
			return keyToCommandCodeMapping.get(key);
		}
	}

	/**
	 * Response code enumeration. Indicates a response.
	 *
	 * @author Jan-Willem Spuij <jwspuij@gmail.com>
	 * @since 1.4.0
	 *
	 */
	public enum CommandResponseCode {
		FAILED,
		COMPLETED
	}
}
