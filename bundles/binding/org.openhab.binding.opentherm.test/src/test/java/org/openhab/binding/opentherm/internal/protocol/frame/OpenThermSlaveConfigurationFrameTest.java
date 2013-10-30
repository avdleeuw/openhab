/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
