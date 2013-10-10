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

import java.math.BigDecimal;

/**
 * OpenTherm Control Setpoint of Second circuit Frame. Represents directly a
 * temperature setpoint for the supply from the boiler.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermControl2ndSetpointFrame extends OpenThermFrame {

	private final BigDecimal controlSetpoint;

	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermControl2ndSetpointFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 */
	public OpenThermControl2ndSetpointFrame(FrameType frameType, MessageType messageType, byte[] payload) {
		super(frameType, messageType, DataId.T_SET_CH2);

		controlSetpoint = extractFloat(payload);
	}

	/**
	 * Returns the control setpoint;
	 * 
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
