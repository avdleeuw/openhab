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

/**
 * OpenTherm Status Frame. Signals Master (thermostat) and slave (boiler)
 * status.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermStatusFrame extends OpenThermReadFrame {

	private final boolean masterCHEnable;
	private final boolean masterDHWEnable;
	private final boolean masterCoolingEnable;
	private final boolean masterOTCEnable;
	private final boolean masterCH2Enable;

	private final boolean slaveFault;
	private final boolean slaveCHMode;
	private final boolean slaveDHWMode;
	private final boolean slaveFlameStatus;
	private final boolean slaveCoolingStatus;
	private final boolean slaveCH2Mode;
	private final boolean slaveDiagnosticIndication;

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermStatusFrame}
	 * class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermStatusFrame(FrameType frameType, MessageType messageType, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, DataId.STATUS, payload);

		this.masterCHEnable = (payload[2] & 0x01) != 0;
		this.masterDHWEnable = (payload[2] & 0x02) != 0;
		this.masterCoolingEnable = (payload[2] & 0x04) != 0;
		this.masterOTCEnable = (payload[2] & 0x08) != 0;
		this.masterCH2Enable = (payload[2] & 0x10) != 0;

		this.slaveFault = (payload[3] & 0x01) != 0;
		this.slaveCHMode = (payload[3] & 0x02) != 0;
		this.slaveDHWMode = (payload[3] & 0x04) != 0;
		this.slaveFlameStatus = (payload[3] & 0x08) != 0;
		this.slaveCoolingStatus = (payload[3] & 0x10) != 0;
		this.slaveCH2Mode = (payload[3] & 0x20) != 0;
		this.slaveDiagnosticIndication = (payload[3] & 0x40) != 0;
	}

	/**
	 * Central Heating enabled on master
	 * 
	 * @return the masterCHEnable
	 */
	public boolean isMasterCHEnable() {
		return masterCHEnable;
	}

	/**
	 * Domestic Hot Water enabled on master
	 * 
	 * @return the masterDHWEnable
	 */
	public boolean isMasterDHWEnable() {
		return masterDHWEnable;
	}

	/**
	 * Cooling enabled on master
	 * 
	 * @return the masterCoolingEnable
	 */
	public boolean isMasterCoolingEnable() {
		return masterCoolingEnable;
	}

	/**
	 * Override Temperature Control enabled on master
	 * 
	 * @return the masterOTCEnable
	 */
	public boolean isMasterOTCEnable() {
		return masterOTCEnable;
	}

	/**
	 * Central Heating 2nd circuit enabled on master
	 * 
	 * @return the masterCH2Enable
	 */
	public boolean isMasterCH2Enable() {
		return masterCH2Enable;
	}

	/**
	 * Fault occurred on slave
	 * 
	 * @return the slaveFault
	 */
	public boolean isSlaveFault() {
		return slaveFault;
	}

	/**
	 * Central Heating active on slave.
	 * 
	 * @return the slaveCHMode
	 */
	public boolean isSlaveCHMode() {
		return slaveCHMode;
	}

	/**
	 * DHW active on slave
	 * 
	 * @return the slaveDHWMode
	 */
	public boolean isSlaveDHWMode() {
		return slaveDHWMode;
	}

	/**
	 * Flame active on slave
	 * 
	 * @return the slaveFlameStatus
	 */
	public boolean isSlaveFlameStatus() {
		return slaveFlameStatus;
	}

	/**
	 * Cooling active on slave.
	 * 
	 * @return the slaveCoolingStatus
	 */
	public boolean isSlaveCoolingStatus() {
		return slaveCoolingStatus;
	}

	/**
	 * Central Heating 2nd circuit active on slave.
	 * 
	 * @return the slaveCH2Mode
	 */
	public boolean isSlaveCH2Mode() {
		return slaveCH2Mode;
	}

	/**
	 * Diagnostic event occurred on slave.
	 * 
	 * @return the slaveDiagnosticIndication
	 */
	public boolean isSlaveDiagnosticIndication() {
		return slaveDiagnosticIndication;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Master Central Heating enabled: %b%n", this.masterCHEnable));
		result.append(String.format("Master Domestic Hot Water enabled: %b%n", this.masterDHWEnable));
		result.append(String.format("Master Cooling enabled: %b%n", this.masterCoolingEnable));
		result.append(String.format("Master Override Temperature Control enabled: %b%n", this.masterOTCEnable));
		result.append(String.format("Master Central Heating 2nd circuit enabled: %b%n", this.masterCH2Enable));

		result.append(String.format("Slave Fault indication: %b%n", this.slaveFault));
		result.append(String.format("Slave Central Heating active: %b%n", this.slaveCHMode));
		result.append(String.format("Slave Domestic Hot Water active: %b%n", this.slaveDHWMode));
		result.append(String.format("Slave Flame active: %b%n", this.slaveFlameStatus));
		result.append(String.format("Slave Cooling active: %b%n", this.slaveCoolingStatus));
		result.append(String.format("Slave Central Heating 2nd circuit active: %b%n", this.slaveCH2Mode));
		result.append(String.format("Slave Diagnostic event occurred: %b", this.slaveDiagnosticIndication));

		return result.toString();
	}
}
