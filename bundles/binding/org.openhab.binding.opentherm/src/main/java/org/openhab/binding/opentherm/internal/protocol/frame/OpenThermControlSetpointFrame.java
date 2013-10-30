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
 * OpenTherm Control Setpoint Frame. Represents directly a temperature setpoint
 * for the supply from the boiler.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermControlSetpointFrame extends OpenThermWriteFrame {

	private final BigDecimal controlSetpoint;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermControlSetpointFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermControlSetpointFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.T_SET, payload);

		controlSetpoint = extractFixedPoint(payload);
	}

	/**
	 * Returns the control setpoint;
	 * @return the controlSetpoint
	 */
	public BigDecimal getControlSetpoint() {
		return controlSetpoint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Control Setpoint: %s °C", this.controlSetpoint.toPlainString()));
		
		return result.toString();
	}
}
