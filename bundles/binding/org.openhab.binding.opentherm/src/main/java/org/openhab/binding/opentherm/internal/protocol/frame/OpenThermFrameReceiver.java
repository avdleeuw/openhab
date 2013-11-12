/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol.frame;

/**
 * OpenThermFrameReceiver interface. Defines the interface for receiving
 * Opentherm Frames.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public interface OpenThermFrameReceiver {

	/**
	 * Receives an OpenTherm frame.
	 * 
	 * @param frame
	 *            The frame to receive.
	 */
	public void receiveFrame(OpenThermFrame frame);
}
