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
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame.CommandCode;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame.CommandResponseCode;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;

/**
 * Tests the {@link OpenThermCommandFrame} class.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermCommandFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame#getCommandCode()}.
	 */
	@Test
	public void testGetCommandCodeBLOR() {
		SerialMessage serialMessage = new SerialMessage("T90040100");
		OpenThermCommandFrame frame = (OpenThermCommandFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(CommandCode.BLOR, frame.getCommandCode());
		assertNull(frame.getResponseCode());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame#getCommandCode()}.
	 */
	@Test
	public void testGetCommandCodeCHWF() {
		SerialMessage serialMessage = new SerialMessage("T90040200");
		OpenThermCommandFrame frame = (OpenThermCommandFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(CommandCode.CHWF, frame.getCommandCode());
		assertNull(frame.getResponseCode());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame#getCommandCode()}.
	 */
	@Test
	public void testGetInvalidCommandCode() {
		SerialMessage serialMessage = new SerialMessage("T90040800");
		OpenThermCommandFrame frame = (OpenThermCommandFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertNull(frame);
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame#getCommandCode()}.
	 */
	@Test
	public void testInvalidMessageType() {
		SerialMessage serialMessage = new SerialMessage("T00040200");
		OpenThermCommandFrame frame = (OpenThermCommandFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertNull(frame);
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame#getResponseCode()}.
	 */
	@Test
	public void testGetResponseCodeCompleted() {
		SerialMessage serialMessage = new SerialMessage("B500402FF");
		OpenThermCommandFrame frame = (OpenThermCommandFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(CommandCode.CHWF, frame.getCommandCode());
		assertEquals(CommandResponseCode.COMPLETED,frame.getResponseCode());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermCommandFrame#getResponseCode()}.
	 */
	@Test
	public void testGetResponseCodeFailed() {
		SerialMessage serialMessage = new SerialMessage("B50040100");
		OpenThermCommandFrame frame = (OpenThermCommandFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(CommandCode.BLOR, frame.getCommandCode());
		assertEquals(CommandResponseCode.FAILED,frame.getResponseCode());
	}

}
