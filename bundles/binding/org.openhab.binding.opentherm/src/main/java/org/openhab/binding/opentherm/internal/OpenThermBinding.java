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
package org.openhab.binding.opentherm.internal;

import java.util.Dictionary;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.opentherm.OpenThermBindingProvider;
import org.openhab.binding.opentherm.internal.protocol.OpenThermConnectionException;
import org.openhab.binding.opentherm.internal.protocol.OpenThermGateway;
import org.openhab.binding.opentherm.internal.protocol.OpenThermTCPGateway;

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
			gateway.Disconnect();
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
			
		if (port.toLowerCase().contains("tcp://")) {
			gateway = new OpenThermTCPGateway();
			try {
				gateway.Connect(port);
				return;
			} catch (OpenThermConnectionException e) {
				throw new ConfigurationException("port", e.getMessage(), e);
			}
		} else if (StringUtils.isNotBlank(port)) {
			
		}
	}
}
