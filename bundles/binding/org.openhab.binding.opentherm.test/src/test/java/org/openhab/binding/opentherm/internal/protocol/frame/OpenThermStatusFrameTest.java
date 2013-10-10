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
 * Test for {@link OpenThermStatusFrame} class.
 *
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermStatusFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isMasterCHEnable()}.
	 */
	@Test
	public void testIsMasterCHEnable() {
		SerialMessage serialMessage = new SerialMessage("T80000100");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertTrue(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isMasterDHWEnable()}.
	 */
	@Test
	public void testIsMasterDHWEnable() {
		SerialMessage serialMessage = new SerialMessage("T80000200");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertTrue(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isMasterCoolingEnable()}.
	 */
	@Test
	public void testIsMasterCoolingEnable() {
		SerialMessage serialMessage = new SerialMessage("T80000400");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertTrue(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isMasterOTCEnable()}.
	 */
	@Test
	public void testIsMasterOTCEnable() {
		SerialMessage serialMessage = new SerialMessage("T80000800");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertTrue(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isMasterCH2Enable()}.
	 */
	@Test
	public void testIsMasterCH2Enable() {
		SerialMessage serialMessage = new SerialMessage("T80001000");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertTrue(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveFault()}.
	 */
	@Test
	public void testIsSlaveFault() {
		SerialMessage serialMessage = new SerialMessage("B40000001");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertTrue(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveCHMode()}.
	 */
	@Test
	public void testIsSlaveCHMode() {
		SerialMessage serialMessage = new SerialMessage("B40000002");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertTrue(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveDHWMode()}.
	 */
	@Test
	public void testIsSlaveDHWMode() {
		SerialMessage serialMessage = new SerialMessage("B40000004");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertTrue(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveFlameStatus()}.
	 */
	@Test
	public void testIsSlaveFlameStatus() {
		SerialMessage serialMessage = new SerialMessage("B40000008");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertTrue(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveCoolingStatus()}.
	 */
	@Test
	public void testIsSlaveCoolingStatus() {
		SerialMessage serialMessage = new SerialMessage("B40000010");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertTrue(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveCH2Mode()}.
	 */
	@Test
	public void testIsSlaveCH2Mode() {
		SerialMessage serialMessage = new SerialMessage("B40000020");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertTrue(frame.isSlaveCH2Mode());
		assertFalse(frame.isSlaveDiagnosticIndication());
	}
	
	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame#isSlaveDiagnosticIndication()}.
	 */
	@Test
	public void testIsSlaveDiagnosticIndication() {
		SerialMessage serialMessage = new SerialMessage("B40000040");
		OpenThermStatusFrame frame = (OpenThermStatusFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertFalse(frame.isMasterCHEnable());
		assertFalse(frame.isMasterDHWEnable());
		assertFalse(frame.isMasterCoolingEnable());
		assertFalse(frame.isMasterOTCEnable());
		assertFalse(frame.isMasterCH2Enable());
		
		assertFalse(frame.isSlaveFault());
		assertFalse(frame.isSlaveCHMode());
		assertFalse(frame.isSlaveDHWMode());
		assertFalse(frame.isSlaveFlameStatus());
		assertFalse(frame.isSlaveCoolingStatus());
		assertFalse(frame.isSlaveCH2Mode());
		assertTrue(frame.isSlaveDiagnosticIndication());
	}

}
