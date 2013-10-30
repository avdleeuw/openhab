/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

import java.math.BigDecimal;

/**
 * OpenTherm Room Setpoint Frame. Represents the room setpoint
 * the thermostat is currently set to.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermRoomSetpointFrame extends OpenThermWriteFrame {

	private final BigDecimal roomSetpoint;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermRoomSetpointFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermRoomSetpointFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.TR_SET, payload);

		roomSetpoint = extractFixedPoint(payload);
	}

	/**
	 * Returns the room setpoint;
	 * @return the roomSetpoint
	 */
	public BigDecimal getRoomSetpoint() {
		return roomSetpoint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Room Setpoint: %s °C", this.roomSetpoint.toPlainString()));
		
		return result.toString();
	}
}
