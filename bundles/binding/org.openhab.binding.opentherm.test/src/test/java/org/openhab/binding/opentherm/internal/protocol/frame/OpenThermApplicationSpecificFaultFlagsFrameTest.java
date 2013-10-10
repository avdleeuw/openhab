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
