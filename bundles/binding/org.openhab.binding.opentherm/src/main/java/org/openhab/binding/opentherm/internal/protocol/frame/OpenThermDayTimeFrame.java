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
