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
 * OpenTherm Master Configuration Frame. Indicates master configuration
 * options.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermMasterConfigurationFrame extends OpenThermWriteFrame {

	private final int memberIdCode;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermMasterConfigurationFrame} class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermMasterConfigurationFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.M_CONFIG, payload);

		this.memberIdCode = this.extractUnsignedLSB(payload);
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
		result.append(String.format("Member ID Code: %d (0x%02x)", this.memberIdCode, this.memberIdCode));

		return result.toString();
	}
}
