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
