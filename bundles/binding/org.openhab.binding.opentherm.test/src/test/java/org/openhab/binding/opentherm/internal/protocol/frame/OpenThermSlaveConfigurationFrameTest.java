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

import static org.junit.Assert.*;

import org.junit.Test;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;

/**
 * Tests the {@link OpenThermSlaveConfigurationFrame} class
 *
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermSlaveConfigurationFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#isDHWPresent()}.
	 */
	@Test
	public void testIsDHWPresent() {
		SerialMessage serialMessage = new SerialMessage("B40030100");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertTrue(frame.isDHWPresent());
		assertTrue(frame.isModulating());
		assertFalse(frame.isCooling());
		assertFalse(frame.isDHWStorageTank());
		assertFalse(frame.isLowOffPumpControl());
		assertFalse(frame.isCH2Present());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#isModulating()}.
	 */
	@Test
	public void testIsModulating() {
		SerialMessage serialMessage = new SerialMessage("B40030200");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isDHWPresent());
		assertFalse(frame.isModulating());
		assertFalse(frame.isCooling());
		assertFalse(frame.isDHWStorageTank());
		assertFalse(frame.isLowOffPumpControl());
		assertFalse(frame.isCH2Present());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#isCooling()}.
	 */
	@Test
	public void testIsCooling() {
		SerialMessage serialMessage = new SerialMessage("B40030400");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isDHWPresent());
		assertTrue(frame.isModulating());
		assertTrue(frame.isCooling());
		assertFalse(frame.isDHWStorageTank());
		assertFalse(frame.isLowOffPumpControl());
		assertFalse(frame.isCH2Present());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#isDHWStorageTank()}.
	 */
	@Test
	public void testIsDHWStorageTank() {
		SerialMessage serialMessage = new SerialMessage("B40030800");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isDHWPresent());
		assertTrue(frame.isModulating());
		assertFalse(frame.isCooling());
		assertTrue(frame.isDHWStorageTank());
		assertFalse(frame.isLowOffPumpControl());
		assertFalse(frame.isCH2Present());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#isLowOffPumpControl()}.
	 */
	@Test
	public void testIsLowOffPumpControl() {
		SerialMessage serialMessage = new SerialMessage("B40031000");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isDHWPresent());
		assertTrue(frame.isModulating());
		assertFalse(frame.isCooling());
		assertFalse(frame.isDHWStorageTank());
		assertTrue(frame.isLowOffPumpControl());
		assertFalse(frame.isCH2Present());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#isCH2Present()}.
	 */
	@Test
	public void testIsCH2Present() {
		SerialMessage serialMessage = new SerialMessage("B40032000");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isDHWPresent());
		assertTrue(frame.isModulating());
		assertFalse(frame.isCooling());
		assertFalse(frame.isDHWStorageTank());
		assertFalse(frame.isLowOffPumpControl());
		assertTrue(frame.isCH2Present());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermSlaveConfigurationFrame#getMemberIdCode()}.
	 */
	@Test
	public void testGetMemberIdCode() {
		SerialMessage serialMessage = new SerialMessage("B400300F1");
		OpenThermSlaveConfigurationFrame frame = (OpenThermSlaveConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(0xF1, frame.getMemberIdCode());
	}

}
