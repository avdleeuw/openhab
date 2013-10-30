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
 * OpenTherm Domestic Hot Water Flow rate Frame. 
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermDHWFlowRateFrame extends OpenThermReadFrame {

	private final BigDecimal dhwFlowRate;

	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermDHWFlowRateFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermDHWFlowRateFrame(FrameType frameType, MessageType messageType, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, DataId.DHW_FLOW_RATE, payload);

		dhwFlowRate = extractFixedPoint(payload);
	}

	/**
	 * Returns the Domestic Hot Water Flow rate;
	 * 
	 * @return the dhwFlowRate
	 */
	public BigDecimal getDHWFlowRate() {
		return dhwFlowRate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Domestic Hot Water Flow Rate: %s (l/min)", this.dhwFlowRate.toPlainString()));

		return result.toString();
	}
}
