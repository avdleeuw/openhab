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
 * OpenTherm Slave Configuration Frame. Indicates slave configuration
 * options.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermSlaveConfigurationFrame extends OpenThermReadFrame {

	private final boolean dhwPresent;
	private final boolean modulating;
	private final boolean cooling;
	private final boolean dhwStorageTank;
	private final boolean lowOffPumpControl;
	private final boolean ch2Present;
	
	private final int memberIdCode;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermSlaveConfigurationFrame} class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermSlaveConfigurationFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.S_CONFIG, payload);
		
		this.dhwPresent = (payload[2] & 0x01) != 0;
		this.modulating = (payload[2] & 0x02) == 0;
		this.cooling = (payload[2] & 0x04) != 0;
		this.dhwStorageTank = (payload[2] & 0x08) != 0;
		this.lowOffPumpControl = (payload[2] & 0x10) != 0;
		this.ch2Present = (payload[2] & 0x20) != 0;

		this.memberIdCode = this.extractUnsignedLSB(payload);
	}

	/**
	 * Returns whether Domestic Hot Water function is present.
	 * @return the dhwPresent
	 */
	public boolean isDHWPresent() {
		return dhwPresent;
	}

	/**
	 * Returns whether the boiler is modulating (or on/off)
	 * @return the modulating
	 */
	public boolean isModulating() {
		return modulating;
	}

	/**
	 * Returns whether the boiler supports cooling
	 * @return the cooling
	 */
	public boolean isCooling() {
		return cooling;
	}

	/**
	 * Returns whether the boiler has a DHW storage tank 
	 * (as opposed to direct flow or not specified).
	 * 
	 * @return the dHWStorageTank
	 */
	public boolean isDHWStorageTank() {
		return dhwStorageTank;
	}

	/**
	 * Returns low-off & pump control function
	 * @return the lowOffPumpControl
	 */
	public boolean isLowOffPumpControl() {
		return lowOffPumpControl;
	}

	/**
	 * Returns whether a 2nd heating circuit is present.
	 * @return the ch2Present
	 */
	public boolean isCH2Present() {
		return ch2Present;
	}

	/**
	 * Returns the Member ID Code.
	 * @return the memberIdCode
	 */
	public int getMemberIdCode() {
		return memberIdCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Domestic Hot Water function present: %b%n", this.dhwPresent));
		result.append(String.format("Modulating supported: %b%n", this.modulating));
		result.append(String.format("Cooling supported: %b%n", this.cooling));
		result.append(String.format("DHW storage tank : %b%n", this.dhwStorageTank));
		result.append(String.format("Low-off & pump control function: %b%n", this.lowOffPumpControl));
		result.append(String.format("2nd heating circuit present: %b%n", this.ch2Present));
		result.append(String.format("Member ID Code: %d (0x%02x)", this.memberIdCode, this.memberIdCode));

		return result.toString();
	}
}
