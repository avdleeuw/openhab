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

import java.math.BigDecimal;

import org.junit.Test;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;

/**
 * Tests the {@link OpenThermRoomSetpointFrame} class.
 *
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermRoomTemperatureFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermRoomSetpointFrame#getRoomTemperature()}.
	 */
	@Test
	public void testGetRoomTemperature() {
		SerialMessage serialMessage = new SerialMessage("T90181319");
		OpenThermRoomTemperatureFrame frame = (OpenThermRoomTemperatureFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(0, frame.getRoomTemperature().compareTo(new BigDecimal("19.10")));
	}

}
