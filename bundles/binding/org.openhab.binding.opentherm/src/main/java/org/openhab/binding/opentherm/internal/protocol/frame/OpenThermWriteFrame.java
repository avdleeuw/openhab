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

/**
 * OpenThermWriteFrame class. Base class for frames that support writing values
 * to the boiler.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 * 
 */
public abstract class OpenThermWriteFrame extends OpenThermFrame {

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermWriteFrame}
	 * class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame
	 * @param messageType
	 *            the message type for the OpenTherm frame
	 * @param dataId
	 *            the data id for the OpenTherm frame
	 * @param payload
	 *            The frame payload.
	 */
	public OpenThermWriteFrame(FrameType frameType, MessageType messageType, DataId dataId, byte[] payload)
			throws OpenThermFrameException {
		super(frameType, messageType, dataId);

		switch (messageType) {
		case READ_DATA:
		case READ_ACK:
			throw new OpenThermFrameException(payload, String.format("Incorrect message type for a Write Frame: %s",
					messageType.getLabel()));
		case INVALID_DATA:
		case WRITE_DATA:
			if (frameType.equals(FrameType.BOILER) || frameType.equals(FrameType.ANSWER_TO_THERMOSTAT)) {
				throw new OpenThermFrameException(payload, String.format(
						"Incorrect frame type for a Write Frame: %s with message type: %s", frameType.getLabel(),
						messageType.getLabel()));
			}
			break;
		case WRITE_ACK:
		case DATA_INVALID:
		case UNKNOWN_DATAID:
			if (frameType.equals(FrameType.THERMOSTAT) || frameType.equals(FrameType.REQUEST_TO_BOILER)) {
				throw new OpenThermFrameException(payload, String.format(
						"Incorrect frame type for a Write Frame: %s with message type: %s", frameType.getLabel(),
						messageType.getLabel()));
			}
			break;
		}
	}
}
