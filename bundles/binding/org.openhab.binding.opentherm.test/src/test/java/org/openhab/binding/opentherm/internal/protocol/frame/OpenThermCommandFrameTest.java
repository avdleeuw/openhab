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

import java.math.BigDecimal;

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
