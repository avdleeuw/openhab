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
 * OpenTherm OEM Diagnostic Code Frame. Extended diagnostic code frame for OEM's
 * to implement.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermOEMDiagnosticCodeFrame extends OpenThermReadFrame {

	private final int oemDiagnosticCode;

	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermOEMDiagnosticCodeFrame} class
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermOEMDiagnosticCodeFrame(FrameType frameType, MessageType messageType, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, DataId.OEM_DIAGNOSTIC, payload);

		oemDiagnosticCode = extractUnsignedWord(payload);
	}

	/**
	 * Returns the OEM Diagnostic Code
	 * 
	 * @return the OEMDiagnosticCode
	 */
	public int getOEMDiagnosticCode() {
		return oemDiagnosticCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("OEM Diagnostic Code: %d (0x%04x)", this.oemDiagnosticCode, this.oemDiagnosticCode));

		return result.toString();
	}
}
