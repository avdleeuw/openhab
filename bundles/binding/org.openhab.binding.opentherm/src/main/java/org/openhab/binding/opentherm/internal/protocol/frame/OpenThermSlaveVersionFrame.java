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
 * OpenTherm Control Slave version Frame. Indicates the Opentherm protocol
 * version of the slave.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermSlaveVersionFrame extends OpenThermReadFrame {

	private final BigDecimal protocolVersion;
	
	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermSlaveVersionFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermSlaveVersionFrame(FrameType frameType, MessageType messageType, byte[] payload) throws OpenThermFrameException {
		super(frameType, messageType, DataId.OPENTHERM_VERSION_SLAVE, payload);

		protocolVersion = extractFixedPoint(payload);
	}

	/**
	 * Returns the protocol version;
	 * @return the protocolVersion
	 */
	public BigDecimal getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("OpenTherm Protocol Version: %s", this.protocolVersion.toPlainString()));
		return result.toString();
	}
}
