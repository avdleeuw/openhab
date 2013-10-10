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

import java.util.HashMap;
import java.util.Map;


/**
 * DataId enumeration. Enumerates the different data IDs of an OpenTherm frame.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public enum DataId {
	STATUS(0, "Master and Slave Status flags.", OpenThermStatusFrame.class),
	T_SET(1, "Control setpoint ie CH water temperature setpoint (°C)", OpenThermControlSetpointFrame.class),
	M_CONFIG(2, "Master Configuration Flags / Master MemberID Code", OpenThermMasterConfigurationFrame.class),
	S_CONFIG(3, "Slave Configuration Flags / Slave MemberID Code", OpenThermSlaveConfigurationFrame.class),
	COMMAND(4, "Remote Command", null),
	ASF_OEM_FAULT(5, "Application-specific fault flags and OEM fault code", OpenThermApplicationSpecificFaultFlagsFrame.class),
	RBP_FLAGS(6, "Remote boiler parameter transfer-enable & read/write flags", null),
	COOLING_CONTROL(7, "Cooling control signal (%)", null),
	T_SET_CH2(8, "Control setpoint for 2e CH circuit (°C)", OpenThermControl2ndSetpointFrame.class),
	TR_OVERRIDE(9, "Remote override room setpoint", null),
	TSP(10, "Number of Transparent-Slave-Parameters supported by slave", null),
	TSP_INDEX_VALUE(11, "Index number / Value of referred-to transparent slave parameter.", null),
	FHB_SIZE(12, "Size of Fault-History-Buffer supported by slave", null),
	FHB_INDEX_VALUE(13, "Index number / Value of referred-to fault-history buffer entry.", null),
	MAX_REL_MOD_LEVEL_SETTING(14, "Maximum relative modulation level setting (%)", null),
	MAX_CAPACITY_MIN_MOD_LEVEL(15, "Maximum boiler capacity (kW) / Minimum boiler modulation level(%)", null),
	TR_SET(16, "Room Setpoint (°C)", null),
	REL_MOD_LEVEL(17, "Relative Modulation Level (%)", null),
	CH_PRESSURE(18, "Water pressure in CH circuit (bar)", null),
	DHW_FLOW_RATE(19, "Water flow rate in DHW circuit. (litres/minute)", null),
	DAY_TIME(20, "Day of Week and Time of Day", null),
	DATE(21, "Calendar date", null),
	YEAR(22, "Calendar year", null),
	TR_SET_CH2(23, "Room Setpoint for 2nd CH circuit (°C)", null),
	TR(24, "Room temperature (°C)", null),
	T_BOILER(25, "Boiler flow water temperature (°C)", null),
	T_DHW(26, "DHW temperature (°C)", null),
	T_OUTSIDE(27, "Outside temperature (°C)", null),
	T_RET(28, "Return water temperature (°C)", null),
	T_STORAGE(29, "Solar storage temperature (°C)", null),
	T_COLLECTOR(30, "Solar collector temperature (°C)", null),
	T_FLOW_CH2(31, "Flow water temperature CH2 circuit (°C)", null),
	T_DHW2(32, "Domestic hot water temperature 2 (°C)", null),
	T_EXHAUST(33, "Boiler exhaust temperature (°C)", null),
	T_DHW_SET_BOUNDS(48, "DHW setpoint upper & lower bounds for adjustment (°C)", null),
	MAX_T_SET_BOUNDS(49, "Max CH water setpoint upper & lower bounds for adjustment (°C)", null),
	HC_RATIO_BOUNDS(50, "OTC heat curve ratio upper & lower bounds for adjustment", null),
	T_DHW_SET(56, "DHW setpoint (°C) (Remote parameter 1)", null),
	MAX_T_SET(57, "Max CH water setpoint (°C) (Remote parameters 2)", null),
	HC_RATIO(58, "OTC heat curve ratio (°C) (Remote parameter 3)", null),
	REMOTE_OVERRIDE(100, "Function of manual and program changes in master and remote room setpoint.", null),
	OEM_DIAGNOSTIC(115, "OEM-specific diagnostic/service code", OpenThermOEMDiagnosticCodeFrame.class),
	BURNER_STARTS(116, "Number of starts burner", null),
	CH_PUMP_STARTS(117, "Number of starts CH pump", null),
	DHW_PUMP_STARTS(118, "Number of starts DHW pump/valve", null),
	DHW_BURNER_STARTS(119, "Number of starts burner during DHW mode", null),
	BURNER_OPERATION_HOURS(120, "Number of hours that burner is in operation (i.e. flame on)", null),
	CH_PUMP_OPERATION_HOURS(121, "Number of hours that CH pump has been running", null),
	DHW_PUMP_OPERATION_HOURS(122, "Number of hours that DHW pump has been running or DHW valve has been opened", null),
	DHW_BURNER_OPERATION_HOURS(123, "Number of hours that burner is in operation during DHW mode", null),
	OPENTHERM_VERSION_MASTER(124, "The implemented version of the OpenTherm Protocol Specification in the master.", null),
	OPENTHERM_VERSION_SLAVE(125, "The implemented version of the OpenTherm Protocol Specification in the slave.", null),
	MASTER_VERSION(126, "Master product version number and type", null),
	SLAVE_VERSION(127, "Slave product version number and type", null);
	
	private static Map<Integer, DataId> keyToDataIdMapping = null;
	private final int key;
	private final String label;
	private final Class<? extends OpenThermFrame> frameClass;

	/**
	 * Returns the key of the data id.
	 * 
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Returns the label of the data id.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the frame class to instantiate.
	 * @return the frame class.
	 */
	public Class<? extends OpenThermFrame> getFrameClass() {
		return frameClass;
	}

	private DataId(int key, String label, Class<? extends OpenThermFrame> frameClass) {
		this.key = key;
		this.label = label;
		this.frameClass = frameClass;
	}

	private static void addMappings() {
		for (DataId frameType : values()) {
			keyToDataIdMapping.put(frameType.getKey(), frameType);
		}
	}

	/**
	 * Get the {@link DataId} by key.
	 * 
	 * @param key
	 *            the key to use.
	 * @return the {@link DataId} or null if not found;
	 */
	public static DataId get(int key) {
		if (keyToDataIdMapping == null) {
			keyToDataIdMapping = new HashMap<Integer, DataId>();
			addMappings();
		}
		return keyToDataIdMapping.get(key);
	}
}
