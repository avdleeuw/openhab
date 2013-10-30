/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;

/**
 * OpenThermFrame base class. Defines a single frame that is communicated
 * between the boiler or thermostat and the OpenTherm gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public abstract class OpenThermFrame {

	private final FrameType frameType;
	private final MessageType messageType;
	private final DataId dataId;

	/**
	 * Constructor. Creates a new instance of the OpenThermFrame class.
	 * 
	 * @param frameType the Frame type from the OpenTherm frame
	 * @param messageType the message type for the OpenTherm frame
	 * @param dataId the data id for the OpenTherm frame
	 */
	protected OpenThermFrame(FrameType frameType, MessageType messageType, DataId dataId) {
		this.frameType = frameType;
		this.messageType = messageType;
		this.dataId = dataId;
	}

	/**
	 * Returns the {@link FrameType}.
	 * 
	 * @return the frameType
	 */
	protected FrameType getFrameType() {
		return frameType;
	}

	/**
	 * Returns the {@link MessageType}
	 * 
	 * @return the messageType
	 */
	protected MessageType getMessageType() {
		return messageType;
	}

	/**
	 * Returns the {@link DataId}
	 * 
	 * @return the dataId
	 */
	protected DataId getDataId() {
		return dataId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Frame Type: %s%nMessage Type: %s%nData ID: %s", this.getFrameType().getLabel(), this
				.getMessageType().getLabel(), this.getDataId().getLabel());
	}
	
	/**
	 * Returns an OpenThermFrame in case the Serial message was an OpenTherm
	 * frame. Returns null otherwise.
	 * 
	 * @param serialMessage
	 *            the serialMessage to process.
	 * @return an Opentherm Frame in case it is a frame null if the frame is not
	 *         a serialMessage.
	 */
	public static OpenThermFrame fromSerialMessage(SerialMessage serialMessage) {
		String messageContent = serialMessage.getMessage();
		byte[] payload;

		if (messageContent.length() != 9) {
			return null;
		}

		FrameType frameType = FrameType.get(messageContent.substring(0, 1));

		if (frameType == null) {
			return null;
		}

		try {
			payload = Hex.decodeHex(messageContent.substring(1, 9).toCharArray());
		} catch (DecoderException e) {
			return null;
		}

		boolean parity = true;
		
		for (byte b : payload ) {
		  for ( int mask = 0x01; mask != 0x100; mask <<= 1 ) {
		      parity ^= ( b & mask ) != 0;
		  }
		}
		
		if (!parity) {
			return null;
		}
		
		MessageType messageType = MessageType.get((payload[0] >> 4) & 0x7);

		if (messageType == null) {
			return null;
		}

		DataId dataId = DataId.get(payload[1] & 0xFF);

		if (dataId == null || dataId.getFrameClass() == null) {
			return null;
		}
		
		try {
			Constructor<? extends OpenThermFrame> constructor = dataId.getFrameClass().getConstructor(FrameType.class, MessageType.class, byte[].class);
			return constructor.newInstance(new Object[] {frameType, messageType, payload });
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Extracts an unsigned 16 bit value from the payload.
	 * @param the payload to use.
	 * @return an integer containing the 16 bit unsigned value;
	 */
	protected int extractUnsignedWord(byte[] payload) {
		return ((payload[2] & 0xFF) << 8) + (payload[3] & 0xFF);
	}
	
	/**
	 * Extracts an unsigned 16 bit value from the payload.
	 * @param the payload to use.
	 * @return an integer containing the 16 bit unsigned value;
	 */
	protected int extractSignedWord(byte[] payload) {
		int result = ((payload[2] & 0x7F) << 8) + (payload[3] & 0xFF);
		return (payload[2] & 0x80) != 0 ? -result : result;
	}

	/**
	 * Extracts an signed 16 bit fixed point value from the payload.
	 * @param payload the payload to use.
	 * @return a {@link BigDecimal} with the decimal value.
	 */
	protected BigDecimal extractFixedPoint(byte[] payload) {
		return new BigDecimal(extractSignedWord(payload)).divide(new BigDecimal(256), 2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Extracts an unsigned 8 bit value from the most significant byte of the payload.
	 * @param the payload to use.
	 * @return an integer containing the 8 bit unsigned value;
	 */
	protected int extractUnsignedMSB(byte[] payload) {
		return (payload[2] & 0xFF);
	}
	
	/**
	 * Extracts an unsigned 8 bit value from the least significant byte of the payload.
	 * @param the payload to use.
	 * @return an integer containing the 8 bit unsigned value;
	 */
	protected int extractUnsignedLSB(byte[] payload) {
		return (payload[3] & 0xFF);
	}
}
