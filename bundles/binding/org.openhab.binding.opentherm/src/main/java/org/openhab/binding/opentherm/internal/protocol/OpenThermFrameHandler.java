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
package org.openhab.binding.opentherm.internal.protocol;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import org.openhab.binding.opentherm.internal.protocol.OpenThermTransmissionException.TransmissionError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThermFrameHandler class. Handles framing of incoming bytes into OpenTherm
 * frames. Triggers events on received frames.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermFrameHandler {

	private static final char CARRIAGE_RETURN = 0x0D;
	private static final char LINE_FEED = 0x0A;
	/**
	 * Buffer size.
	 */
	private static final Logger logger = LoggerFactory.getLogger(OpenThermFrameHandler.class);
	private final Set<OpenThermFrameReceiver> frameReceivers = new HashSet<OpenThermFrameReceiver>();

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermFrameHandler}
	 * class.
	 */
	public OpenThermFrameHandler() {
	}

	/**
	 * Adds a {@link OpenThermFrameReceiver} to the list of receivers.
	 * 
	 * @param frameReceiver
	 *            the frame receiver to add.
	 */
	public void addFrameReceiver(OpenThermFrameReceiver frameReceiver) {
		this.frameReceivers.add(frameReceiver);
		logger.trace("Added Frame Receiver {} to the list of Frame Receivers.");
	}
	
	/**
	 * Handles data in the buffer. When the buffer contains a valid frame,
	 * the frame receivers are called. When a new frame is started without
	 * a valid frame in the buffer, the frame receiver is called with an
	 * error.
	 * @param data. The buffer with the data.
	 */
	public void handleData(ByteBuffer buffer) {

		StringBuilder stringBuilder = new StringBuilder();
		
		buffer.flip();
		
		while (true) {
			byte c = buffer.get();
			switch (c) {
				case CARRIAGE_RETURN:
					c = buffer.get();
					if (c == LINE_FEED && stringBuilder.length() == 9) {
						logger.debug("Received: {}",  stringBuilder.toString());
						handleReceivedFrame(stringBuilder.toString());
					} else if (c == LINE_FEED && stringBuilder.length() == 8 && stringBuilder.toString().toLowerCase().startsWith("error")) {
						handleTransmissionError(stringBuilder.toString());
					} else {
						logger.error("Incorrect frame received: {}",  stringBuilder.toString());
						handleFrameError(stringBuilder.toString());
					}
					stringBuilder = new StringBuilder();
					if (buffer.remaining() < 11) {
						buffer.compact();
						return;
					}
					break;
				default:
					stringBuilder.append((char)c);
					break;
			}
		}
	}

	/**
	 * Removes the {@link OpenThermFrameReceiver} from the list of receivers.
	 * 
	 * @param frameReceiver
	 *            the frame receiver to remove.
	 */
	public void removeFrameReceiver(OpenThermFrameReceiver frameReceiver) {
		this.frameReceivers.remove(frameReceiver);
		logger.trace("Removed Frame Receiver {} from the list of Frame Receivers.");
	}
	
	/**
	 * Handle a received frame by notifying all frame receivers.
	 * @param frameContent the frame content;
	 */
	private void handleReceivedFrame(String frameContent) {
		logger.trace("Handling received frame by notifying frame Receivers.");
		OpenThermFrame frame = OpenThermFrame.fromString(frameContent);
		for (OpenThermFrameReceiver frameReceiver : this.frameReceivers) {
			frameReceiver.ReceiveFrame(frame);
		}
	}
	
	/**
	 * Handle a transmission error by notifying all frame receivers.
	 * @param errorMessage the errormessage to convert to an Exception.;
	 */
	private void handleTransmissionError(String errorMessage) {
		logger.trace("Handling transmission error by notifying frame Receivers.");
		
		TransmissionError transmissionError = TransmissionError.get(Integer.parseInt(errorMessage.substring(6, 8)));
		
		for (OpenThermFrameReceiver frameReceiver : this.frameReceivers) {
			frameReceiver.FrameError(new OpenThermTransmissionException(transmissionError, "Incorrect transmission of an opentherm message."));
		}
	}


	/**
	 * Handle a frame error by notifying all frame receivers.
	 * @param frameContent the invalid frame content;
	 */
	private void handleFrameError(String frameContent) {
		logger.trace("Handling frame error by notifying frame Receivers.");
		for (OpenThermFrameReceiver frameReceiver : this.frameReceivers) {
			frameReceiver.FrameError(new OpenThermInvalidFrameException(frameContent, "Incorrect frame received."));
		}
	}
}
