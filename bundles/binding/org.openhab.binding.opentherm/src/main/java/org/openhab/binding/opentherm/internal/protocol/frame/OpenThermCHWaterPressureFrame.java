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
 * OpenTherm Cental Heating Water pressure Frame. Gets the central heating water
 * pressure from the boiler.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermCHWaterPressureFrame extends OpenThermReadFrame {

	private final BigDecimal chWaterPressure;

	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermCHWaterPressureFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermCHWaterPressureFrame(FrameType frameType, MessageType messageType, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, DataId.CH_PRESSURE, payload);

		chWaterPressure = extractFixedPoint(payload);
	}

	/**
	 * Returns the Cental Heating Water pressure;
	 * 
	 * @return the chWaterPressure
	 */
	public BigDecimal getCHWaterPressure() {
		return chWaterPressure;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Central Heating Water Pressure: %s (bar)", this.chWaterPressure.toPlainString()));

		return result.toString();
	}
}
