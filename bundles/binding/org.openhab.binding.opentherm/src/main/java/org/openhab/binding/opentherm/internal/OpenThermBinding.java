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

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.opentherm.OpenThermBindingProvider;
import org.openhab.binding.opentherm.internal.gateway.OpenThermGateway;
import org.openhab.binding.opentherm.internal.protocol.OpenThermConnectionException;
import org.openhab.core.binding.AbstractBinding;
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
public class OpenThermBinding extends AbstractBinding<OpenThermBindingProvider> implements ManagedService {

	private static final Logger logger = LoggerFactory.getLogger(OpenThermBinding.class);
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
			gateway = null;
		}
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
		try {
			gateway.connect(port);
			return;
		} catch (OpenThermConnectionException e) {
			throw new ConfigurationException("port", e.getMessage(), e);
		}
	}
}
