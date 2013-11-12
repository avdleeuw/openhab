/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.opentherm.OpenThermBindingConfig;
import org.openhab.binding.opentherm.OpenThermBindingProvider;
import org.openhab.binding.opentherm.OpenThermDataPoint;
import org.openhab.binding.opentherm.internal.gateway.OpenThermGateway;
import org.openhab.binding.opentherm.internal.protocol.OpenThermConnectionException;
import org.openhab.binding.opentherm.internal.protocol.frame.MessageType;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermFrame;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermFrameReceiver;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermRoomSetpointFrame;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermRoomTemperatureFrame;
import org.openhab.binding.opentherm.internal.protocol.frame.OpenThermStatusFrame;
import org.openhab.core.binding.AbstractBinding;
import org.openhab.core.binding.BindingProvider;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThermBinding class. Main class that runs the binding.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermBinding extends AbstractBinding<OpenThermBindingProvider> implements ManagedService, OpenThermFrameReceiver {

	private static final Logger logger = LoggerFactory.getLogger(OpenThermBinding.class);
	private final Map<OpenThermDataPoint, String> itemLookup = new HashMap<OpenThermDataPoint, String>();
	private OpenThermGateway gateway;
	
	/**
	 * Constructor. Creates a new instance of the {@link OpenThermBinding} class.
	 */
	public OpenThermBinding() {
	}

	/**
	 * Called when the binding is activated.
	 */
	@Override
	public void activate() {
	}

	/**
	 * Called when the binding is deactivated.
	 */
	@Override
	public void deactivate() {
		if (gateway != null) {
			gateway.disconnect();
			gateway.removeOpenThermFrameReceiver(this);
			gateway = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindingChanged(BindingProvider provider, String itemName) {
		super.bindingChanged(provider, itemName);
		
		if (!(provider instanceof OpenThermBindingProvider))
			return;
		
		OpenThermBindingProvider openThermBindingProvider = (OpenThermBindingProvider) provider;
		OpenThermBindingConfig config = openThermBindingProvider.getBindingConfig(itemName);
		
		if (config == null)
			return;
		
		itemLookup.put(config.getDataPoint(), itemName);
	}
	
	/**
	 * @{inheritDoc
	 */
	@Override
	protected void internalReceiveCommand(String itemName, Command command) {
		// the code being executed when a command was sent on the openHAB
		// event bus goes here. This method is only called if one of the
		// BindingProviders provide a binding for the given 'itemName'.
		logger.debug("internalReceiveCommand() is called!");
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	protected void internalReceiveUpdate(String itemName, State newState) {
		// the code being executed when a state was sent on the openHAB
		// event bus goes here. This method is only called if one of the
		// BindingProviders provide a binding for the given 'itemName'.
		logger.debug("internalReceiveUpdate() is called!");
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void updated(Dictionary<String, ?> config) throws ConfigurationException {
		
		if (config == null)
			return;
		
		String port = (String) config.get("port");

		if (StringUtils.isBlank(port))
			throw new ConfigurationException("port", "OpenTherm Gateway port not specified.");
			
		gateway = new OpenThermGateway();
		gateway.addOpenThermFrameReceiver(this);
		try {
			gateway.connect(port);
			return;
		} catch (OpenThermConnectionException e) {
			throw new ConfigurationException("port", e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void receiveFrame(OpenThermFrame frame) {
		
		if (frame instanceof OpenThermRoomTemperatureFrame && frame.getMessageType() == MessageType.WRITE_DATA) {
			OpenThermRoomTemperatureFrame roomTempFrame = (OpenThermRoomTemperatureFrame) frame;
			logger.debug(String.format("Received Room temperature (%f °C)", roomTempFrame.getRoomTemperature()));
			
			postDataPointUpdate(OpenThermDataPoint.ROOM_TEMPERATURE, new DecimalType(roomTempFrame.getRoomTemperature()));
		}
		else if (frame instanceof OpenThermRoomSetpointFrame && frame.getMessageType() == MessageType.WRITE_DATA) {
			OpenThermRoomSetpointFrame roomSetpointFrame = (OpenThermRoomSetpointFrame) frame;
			logger.debug(String.format("Received Room setpoint (%f °C)", roomSetpointFrame.getRoomSetpoint()));
			
			postDataPointUpdate(OpenThermDataPoint.ROOM_SETPOINT, new DecimalType(roomSetpointFrame.getRoomSetpoint()));
		}
		else if (frame instanceof OpenThermStatusFrame) {
			OpenThermStatusFrame statusFrame = (OpenThermStatusFrame) frame;
			if (frame.getMessageType() == MessageType.READ_DATA) {
				boolean masterCHEnable = statusFrame.isMasterCHEnable();
				logger.debug("Thermostat central heating enabled: {}", masterCHEnable);
				postDataPointUpdate(OpenThermDataPoint.THERMOSTAT_CENTRAL_HEATING_ENABLED, masterCHEnable ? OnOffType.ON : OnOffType.OFF);
				boolean masterDHWEnable = statusFrame.isMasterDHWEnable();
				logger.debug("Thermostat domestic hot water enabled: {}", masterDHWEnable);
				postDataPointUpdate(OpenThermDataPoint.THERMOSTAT_DOMESTIC_HOT_WATER_ENABLED, masterDHWEnable ? OnOffType.ON : OnOffType.OFF);
				boolean masterCoolingEnable = statusFrame.isMasterCoolingEnable();
				logger.debug("Thermostat cooling enabled: {}", masterCoolingEnable);
				postDataPointUpdate(OpenThermDataPoint.THERMOSTAT_COOLING_ENABLED, masterCoolingEnable ? OnOffType.ON : OnOffType.OFF);
				boolean masterOTCEnable = statusFrame.isMasterOTCEnable();
				logger.debug("Outdoor temperature control enabled: {}", masterOTCEnable);
				postDataPointUpdate(OpenThermDataPoint.THERMOSTAT_OUTDOOR_TEMPERATURE_CONTROL_ENABLED, masterOTCEnable ? OnOffType.ON : OnOffType.OFF);
				boolean masterCH2Enable = statusFrame.isMasterCH2Enable();
				logger.debug( "Thermostat central heating (2nd circuit) enabled: {}", masterCH2Enable);
				postDataPointUpdate(OpenThermDataPoint.THERMOSTAT_CENTRAL_HEATING2_ENABLED, masterCHEnable ? OnOffType.ON : OnOffType.OFF);
			} else if (frame.getMessageType() == MessageType.READ_ACK) {
				
			}
		}
	}
	
	/**
	 * Posts an update for the specified data point.
	 * @param dataPoint the data point to update.
	 * @param newState the new state.
	 */
	private void postDataPointUpdate(OpenThermDataPoint dataPoint, State newState) {
		String itemName = this.itemLookup.get(dataPoint);
		if (StringUtils.isEmpty(itemName)) {
			logger.debug("No item defined for datapoint {}", dataPoint);
			return;
		}
		this.eventPublisher.postUpdate(itemName, newState);
	}
}
