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
 * OpenTherm Application Specific Fault Flags Frame. Signals application
 * Specific Fault Flags.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermApplicationSpecificFaultFlagsFrame extends OpenThermReadFrame {

	private final boolean serviceRequest;
	private final boolean lockoutReset;
	private final boolean lowWaterPressure;
	private final boolean gasFlameFault;
	private final boolean airPressureFault;
	private final boolean waterOverTemperature;

	private final int oemFaultCode;

	/**
	 * Constructor. Creates a new instance of the
	 * {@link OpenThermApplicationSpecificFaultFlagsFrame} class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermApplicationSpecificFaultFlagsFrame(FrameType frameType, MessageType messageType, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, DataId.ASF_OEM_FAULT, payload);

		this.serviceRequest = (payload[2] & 0x01) != 0;
		this.lockoutReset = (payload[2] & 0x02) != 0;
		this.lowWaterPressure = (payload[2] & 0x04) != 0;
		this.gasFlameFault = (payload[2] & 0x08) != 0;
		this.airPressureFault = (payload[2] & 0x10) != 0;
		this.waterOverTemperature = (payload[2] & 0x20) != 0;
		this.oemFaultCode = this.extractUnsignedLSB(payload);
	}

	/**
	 * Returns a boolean indicating whether service is required.
	 * 
	 * @return the serviceRequest
	 */
	public boolean isServiceRequest() {
		return serviceRequest;
	}

	/**
	 * Returns a boolean indicating whether Remote lock-out reset is enabled
	 * 
	 * @return the lockoutReset
	 */
	public boolean isLockoutReset() {
		return lockoutReset;
	}

	/**
	 * Returns a boolean indicating low water pressure
	 * 
	 * @return the lowWaterPressure
	 */
	public boolean isLowWaterPressure() {
		return lowWaterPressure;
	}

	/**
	 * Returns a boolean indicating a gas or flame fault
	 * 
	 * @return the gasFlameFault
	 */
	public boolean isGasFlameFault() {
		return gasFlameFault;
	}

	/**
	 * Returns a boolean indicating an air pressure fault
	 * 
	 * @return the airPressureFault
	 */
	public boolean isAirPressureFault() {
		return airPressureFault;
	}

	/**
	 * Returns a boolean indicating the water temperature is over it's limit
	 * 
	 * @return the waterOverTemperature
	 */
	public boolean isWaterOverTemperature() {
		return waterOverTemperature;
	}

	/**
	 * Returns the OEM Fault Code.
	 * 
	 * @return the oemFaultCode
	 */
	public int getOemFaultCode() {
		return oemFaultCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		StringBuilder result = new StringBuilder(super.toString());
		result.append(System.getProperty("line.separator"));
		result.append(String.format("Service required: %b%n", this.serviceRequest));
		result.append(String.format("Remote lock-out reset enabled: %b%n", this.lockoutReset));
		result.append(String.format("Low water pressure: %b%n", this.lowWaterPressure));
		result.append(String.format("Gas or flame fault: %b%n", this.gasFlameFault));
		result.append(String.format("Air pressure fault: %b%n", this.airPressureFault));
		result.append(String.format("Water over temperature: %b%n", this.waterOverTemperature));
		result.append(String.format("OEM Fault Code: %d (0x%02x)", this.oemFaultCode, this.oemFaultCode));

		return result.toString();
	}
}
