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
 * OpenTherm Room Temperature Frame. Represents the room temperature
 * the thermostat is currently measuring.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermRoomTemperatureFrame extends OpenThermWriteFrame {

	private final BigDecimal roomTemperature;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermRoomTemperatureFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermRoomTemperatureFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.TR, payload);

		roomTemperature = extractFixedPoint(payload);
	}

	/**
	 * Returns the room temperature;
	 * @return the roomTemperature
	 */
	public BigDecimal getRoomTemperature() {
		return roomTemperature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Room Temperature: %s °C", this.roomTemperature.toPlainString()));
		
		return result.toString();
	}
}
