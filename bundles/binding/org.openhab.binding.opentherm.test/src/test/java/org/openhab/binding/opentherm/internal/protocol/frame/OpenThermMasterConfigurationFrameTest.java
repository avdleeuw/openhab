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

import org.junit.Test;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;

/**
 * Tests the {@link OpenThermMasterConfigurationFrame} class.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 *
 */
public class OpenThermMasterConfigurationFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermMasterConfigurationFrame#getMemberIdCode()}.
	 */
	@Test
	public void testGetMemberIdCode() {
		SerialMessage serialMessage = new SerialMessage("T900200F1");
		OpenThermMasterConfigurationFrame frame = (OpenThermMasterConfigurationFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(0xF1, frame.getMemberIdCode());
	}

}
