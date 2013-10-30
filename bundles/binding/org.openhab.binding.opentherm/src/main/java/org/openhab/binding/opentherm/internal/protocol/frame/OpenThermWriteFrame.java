/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

/**
 * OpenThermWriteFrame class. Base class for frames that support writing values
 * to the boiler.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 * 
 */
public abstract class OpenThermWriteFrame extends OpenThermFrame {

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermWriteFrame}
	 * class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame
	 * @param messageType
	 *            the message type for the OpenTherm frame
	 * @param dataId
	 *            the data id for the OpenTherm frame
	 * @param payload
	 *            The frame payload.
	 */
	public OpenThermWriteFrame(FrameType frameType, MessageType messageType, DataId dataId, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, dataId);

		switch (messageType) {
		case READ_DATA:
		case READ_ACK:
			throw new OpenThermFrameException(payload, String.format("Incorrect message type for a Write Frame: %s",
					messageType.getLabel()));
		case INVALID_DATA:
		case WRITE_DATA:
			if (frameType.equals(FrameType.BOILER) || frameType.equals(FrameType.ANSWER_TO_THERMOSTAT)) {
				throw new OpenThermFrameException(payload, String.format(
						"Incorrect frame type for a Write Frame: %s with message type: %s", frameType.getLabel(),
						messageType.getLabel()));
			}
			break;
		case WRITE_ACK:
		case DATA_INVALID:
		case UNKNOWN_DATAID:
			if (frameType.equals(FrameType.THERMOSTAT) || frameType.equals(FrameType.REQUEST_TO_BOILER)) {
				throw new OpenThermFrameException(payload, String.format(
						"Incorrect frame type for a Write Frame: %s with message type: %s", frameType.getLabel(),
						messageType.getLabel()));
			}
			break;
		}
	}
}
