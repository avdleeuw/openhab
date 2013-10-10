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
 * OpenTherm Control Master product version Frame. Indicates the Product
 * type and version of the master.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermMasterProductVersionFrame extends OpenThermFrame {

	private final int productType;
	private final int productVersion;	
	/**
	 * Constructor. Creates a new instance of the {@link OpenThermMasterProductVersionFrame} class
	 * @param frameType the Frame type from the OpenTherm frame.
	 * @param messageType the message type for the OpenTherm frame.
	 * @param payload. The frame payload.
	 */
	public OpenThermMasterProductVersionFrame(FrameType frameType, MessageType messageType, byte[] payload) {
		super(frameType, messageType, DataId.MASTER_VERSION);

		productType = extractUnsignedMSB(payload);
		productVersion = extractUnsignedLSB(payload);
	}

	/**
	 * Returns the product type;
	 * @return the productType
	 */
	public int getProductType() {
		return productType;
	}

	/**
	 * Returns the product version;
	 * @return the productVersion
	 */
	public int getProductVersion() {
		return productVersion;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Product Type: %d (0x%02x)%n", this.productType));
		result.append(String.format("Product Version: %d (0x%02x)", this.productVersion));
		return result.toString();
	}
}
