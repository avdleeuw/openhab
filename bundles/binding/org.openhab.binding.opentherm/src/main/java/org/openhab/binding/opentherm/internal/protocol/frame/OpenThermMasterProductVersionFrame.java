/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

/**
 * OpenTherm Control Master product version Frame. Indicates the Product type
 * and version of the master.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermMasterProductVersionFrame extends OpenThermWriteFrame {

	private final int productType;
	private final int productVersion;

	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermMasterProductVersionFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermMasterProductVersionFrame(FrameType frameType, MessageType messageType, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, DataId.MASTER_VERSION, payload);

		productType = extractUnsignedMSB(payload);
		productVersion = extractUnsignedLSB(payload);
	}

	/**
	 * Returns the product type;
	 * 
	 * @return the productType
	 */
	public int getProductType() {
		return productType;
	}

	/**
	 * Returns the product version;
	 * 
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
