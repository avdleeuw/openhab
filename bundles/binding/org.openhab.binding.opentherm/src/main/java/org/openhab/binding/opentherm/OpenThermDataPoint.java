/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm;

/**
 * OpenThermDataPoint enumeration. Defines the data points (variables) that can
 * be monitored using the binding and the gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public enum OpenThermDataPoint {
	ROOM_TEMPERATURE,
	THERMOSTAT_CENTRAL_HEATING_ENABLED,
	THERMOSTAT_DOMESTIC_HOT_WATER_ENABLED,
	THERMOSTAT_COOLING_ENABLED,
	THERMOSTAT_OUTDOOR_TEMPERATURE_CONTROL_ENABLED,
	THERMOSTAT_CENTRAL_HEATING2_ENABLED,
	ROOM_SETPOINT
}
