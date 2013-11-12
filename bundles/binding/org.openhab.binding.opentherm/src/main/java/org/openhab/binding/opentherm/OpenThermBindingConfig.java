/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm;

import org.openhab.core.binding.BindingConfig;

/**
 * OpenThermBindingConfig class. Represents a binding configuration for a single
 * data item in the items file.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermBindingConfig implements BindingConfig {

	private final OpenThermDataPoint dataPoint;

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermBindingConfig
	 * class}
	 */
	public OpenThermBindingConfig(OpenThermDataPoint dataPoint) {
		this.dataPoint = dataPoint;
	}

	/**
	 * Returns the data point used for this item;
	 * 
	 * @return the dataPoint
	 */
	public OpenThermDataPoint getDataPoint() {
		return dataPoint;
	}

}
