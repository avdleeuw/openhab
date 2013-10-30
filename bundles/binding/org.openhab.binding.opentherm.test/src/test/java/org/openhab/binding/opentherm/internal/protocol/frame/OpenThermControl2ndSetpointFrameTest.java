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
 * Tests the {@link OpenThermControlSetpointFrame} class.
 *
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermControl2ndSetpointFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermControlSetpointFrame#getControlSetpoint()}.
	 */
	@Test
	public void testGetControlSetpoint() {
		SerialMessage serialMessage = new SerialMessage("T90081402");
		OpenThermControl2ndSetpointFrame frame = (OpenThermControl2ndSetpointFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(0, frame.getControlSetpoint().compareTo(new BigDecimal("20.01")));
	}

}
