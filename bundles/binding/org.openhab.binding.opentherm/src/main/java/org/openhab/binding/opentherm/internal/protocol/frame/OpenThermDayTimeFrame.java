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
 * OpenThermDayTimeFrame class. Indicates the day of the week and the time.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 * 
 */
public class OpenThermDayTimeFrame extends OpenThermFrame {

	private final DayOfWeek dayOfWeek;
	private final int hours;
	private final int minutes;

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermDayTimeFrame}
	 * class.
	 * 
	 * @param frameType
	 *            the Frame type from the OpenTherm frame.
	 * @param messageType
	 *            the message type for the OpenTherm frame.
	 * @param payload
	 *            . The frame payload.
	 * @throws OpenThermFrameException
	 */
	public OpenThermDayTimeFrame(FrameType frameType, MessageType messageType, byte[] payload) {
		super(frameType, messageType, DataId.DAY_TIME);

		hours = (payload[2] & 0x3F);
		dayOfWeek = DayOfWeek.values()[(payload[2] & 0xFF) >> 5];
		minutes = (payload[3] & 0xFF);
	}

	/**
	 * Returns the day of the week.
	 * 
	 * @return the dayOfWeek
	 */
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Returns the hours of the day.
	 * 
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Returns the minutes of the hour.
	 * 
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * DayOfWeek enumeration. Indicates the days of the week.
	 * 
	 * @author Jan-Willem Spuij <jwspuij@gmail.com>
	 * @since 1.4.0
	 * 
	 */
	public enum DayOfWeek {
		UNAVAILABLE, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}
}
