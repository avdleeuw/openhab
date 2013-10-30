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
 * OpenTherm Relative modulation level Frame. Gets the modulation
 * level from the boiler as a value between 0% and 100%.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermRelativeModulationLevelFrame extends OpenThermReadFrame {

	private final BigDecimal relativeModulationLevel;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermRelativeModulationLevelFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermRelativeModulationLevelFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.REL_MOD_LEVEL, payload);

		relativeModulationLevel = extractFixedPoint(payload);
	}

	/**
	 * Returns the relative modulation level;
	 * @return the relativeModulationLevel
	 */
	public BigDecimal getRelativeModulationLevel() {
		return relativeModulationLevel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Relative modulation level: %s%%", this.relativeModulationLevel.toPlainString()));
		
		return result.toString();
	}
}
