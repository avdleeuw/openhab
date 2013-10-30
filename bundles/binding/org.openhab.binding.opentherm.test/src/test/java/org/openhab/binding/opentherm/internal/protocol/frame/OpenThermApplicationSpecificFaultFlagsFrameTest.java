/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;

/**
 * Tests the {@link OpenThermApplicationSpecificFaultFlagsFrame} class.
 *
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermApplicationSpecificFaultFlagsFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#isServiceRequest()}.
	 */
	@Test
	public void testIsServiceRequest() {
		SerialMessage serialMessage = new SerialMessage("B40050100");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertTrue(frame.isServiceRequest());
		assertFalse(frame.isLockoutReset());
		assertFalse(frame.isLowWaterPressure());
		assertFalse(frame.isGasFlameFault());
		assertFalse(frame.isAirPressureFault());
		assertFalse(frame.isWaterOverTemperature());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#isLockoutReset()}.
	 */
	@Test
	public void testIsLockoutReset() {
		SerialMessage serialMessage = new SerialMessage("B40050200");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isServiceRequest());
		assertTrue(frame.isLockoutReset());
		assertFalse(frame.isLowWaterPressure());
		assertFalse(frame.isGasFlameFault());
		assertFalse(frame.isAirPressureFault());
		assertFalse(frame.isWaterOverTemperature());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#isLowWaterPressure()}.
	 */
	@Test
	public void testIsLowWaterPressure() {
		SerialMessage serialMessage = new SerialMessage("B40050400");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isServiceRequest());
		assertFalse(frame.isLockoutReset());
		assertTrue(frame.isLowWaterPressure());
		assertFalse(frame.isGasFlameFault());
		assertFalse(frame.isAirPressureFault());
		assertFalse(frame.isWaterOverTemperature());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#isGasFlameFault()}.
	 */
	@Test
	public void testIsGasFlameFault() {
		SerialMessage serialMessage = new SerialMessage("B40050800");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isServiceRequest());
		assertFalse(frame.isLockoutReset());
		assertFalse(frame.isLowWaterPressure());
		assertTrue(frame.isGasFlameFault());
		assertFalse(frame.isAirPressureFault());
		assertFalse(frame.isWaterOverTemperature());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#isAirPressureFault()}.
	 */
	@Test
	public void testIsAirPressureFault() {
		SerialMessage serialMessage = new SerialMessage("B40051000");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isServiceRequest());
		assertFalse(frame.isLockoutReset());
		assertFalse(frame.isLowWaterPressure());
		assertFalse(frame.isGasFlameFault());
		assertTrue(frame.isAirPressureFault());
		assertFalse(frame.isWaterOverTemperature());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#isWaterOverTemperature()}.
	 */
	@Test
	public void testIsWaterOverTemperature() {
		SerialMessage serialMessage = new SerialMessage("B40052000");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isServiceRequest());
		assertFalse(frame.isLockoutReset());
		assertFalse(frame.isLowWaterPressure());
		assertFalse(frame.isGasFlameFault());
		assertFalse(frame.isAirPressureFault());
		assertTrue(frame.isWaterOverTemperature());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermApplicationSpecificFaultFlagsFrame#getOemFaultCode()}.
	 */
	@Test
	public void testGetOemFaultCode() {
		SerialMessage serialMessage = new SerialMessage("BC00500DB");
		OpenThermApplicationSpecificFaultFlagsFrame frame = (OpenThermApplicationSpecificFaultFlagsFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(0xDB, frame.getOemFaultCode());
	}

}
