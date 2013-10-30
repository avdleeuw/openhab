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
 * Tests the {@link OpenThermOEMDiagnosticCodeFrame} class.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 * 
 */
public class OpenThermOEMDiagnosticCodeFrameTest {

	/**
	 * Test method for {@link org.openhab.binding.opentherm.internal.protocol.frame.OpenThermOEMDiagnosticCodeFrame#getOEMDiagnosticCode()}.
	 */
	@Test
	public void testGetOEMDiagnosticCode() {
		SerialMessage serialMessage = new SerialMessage("BC073FABC");
		OpenThermOEMDiagnosticCodeFrame frame = (OpenThermOEMDiagnosticCodeFrame) OpenThermFrame.fromSerialMessage(serialMessage);
		assertEquals(0xFABC, frame.getOEMDiagnosticCode());
	}

}
