/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.onewire.internal;

import org.openhab.binding.onewire.internal.control.OneWireClearCacheControlBindingConfig;
import org.openhab.binding.onewire.internal.deviceproperties.OneWireDevicePropertyContactBindingConfig;
import org.openhab.binding.onewire.internal.deviceproperties.OneWireDevicePropertyNumberBindingConfig;
import org.openhab.binding.onewire.internal.deviceproperties.OneWireDevicePropertyPushButtonBindingConfig;
import org.openhab.binding.onewire.internal.deviceproperties.OneWireDevicePropertyStringBindingConfig;
import org.openhab.binding.onewire.internal.deviceproperties.OneWireDevicePropertySwitchBindingConfig;
import org.openhab.binding.onewire.internal.deviceproperties.OneWireDevicePropertySwitchMinMaxNumberWarningBindingConfig;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.ContactItem;
import org.openhab.core.library.items.NumberItem;
import org.openhab.core.library.items.StringItem;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides a Factory, which creates OneWireBindingConfigs
 *
 * @author Dennis Riegelbauer
 * @since 1.7.0
 *
 */
public class OneWireBindingConfigFactory {

    private static final Logger logger = LoggerFactory.getLogger(OneWireBindingConfigFactory.class);

    /**
     * @param pvItem
     * @param pvBindingConfig
     * @return a new BindingConfig, corresponding to the given
     *         <code><pvItem/code> and <code><pvBindingConfig/code>
     * @throws BindingConfigParseException
     */
    public static OneWireBindingConfig createOneWireDeviceProperty(Item pvItem, String pvBindingConfig)
            throws BindingConfigParseException {
        logger.debug("createOneWireDeviceProperty: {} - bindingConfig:{}", pvItem.getName(), pvBindingConfig);

        OneWireBindingConfig lvNewBindingConfig = null;
        if (OneWireClearCacheControlBindingConfig.isBindingConfigToCreate(pvItem, pvBindingConfig)) {
            lvNewBindingConfig = new OneWireClearCacheControlBindingConfig(pvBindingConfig);
        } else if (OneWireDevicePropertyPushButtonBindingConfig.isBindingConfigToCreate(pvItem, pvBindingConfig)) {
            lvNewBindingConfig = new OneWireDevicePropertyPushButtonBindingConfig(pvBindingConfig);
        } else if (OneWireDevicePropertySwitchMinMaxNumberWarningBindingConfig.isBindingConfigToCreate(pvItem,
                pvBindingConfig)) {
            lvNewBindingConfig = new OneWireDevicePropertySwitchMinMaxNumberWarningBindingConfig(pvBindingConfig);
        } else if (pvItem instanceof NumberItem) {
            lvNewBindingConfig = new OneWireDevicePropertyNumberBindingConfig(pvBindingConfig);
        } else if (pvItem instanceof ContactItem) {
            lvNewBindingConfig = new OneWireDevicePropertyContactBindingConfig(pvBindingConfig);
        } else if (pvItem instanceof SwitchItem) {
            lvNewBindingConfig = new OneWireDevicePropertySwitchBindingConfig(pvBindingConfig);
        } else if (pvItem instanceof StringItem) {
            lvNewBindingConfig = new OneWireDevicePropertyStringBindingConfig(pvBindingConfig);
        } else {
            throw new UnsupportedOperationException(
                    "the item-type " + pvItem.getClass() + " cannot be a onewire device");
        }

        logger.debug("created newBindingConfig: {}", lvNewBindingConfig.toString());

        return lvNewBindingConfig;
    }

    /**
     *
     * @param pvItem
     * @param pvBindingConfig
     * @return is the given Item a valid one, which can be used for a
     *         OneWireBinding?
     * @throws BindingConfigParseException
     */
    public static boolean isValidItemType(Item pvItem, String pvBindingConfig) throws BindingConfigParseException {
        boolean lvIsValidItem = ((pvItem instanceof NumberItem) || (pvItem instanceof ContactItem)
                || (pvItem instanceof SwitchItem) || (pvItem instanceof StringItem));

        if (!lvIsValidItem) {
            logger.error("Item {} of type {} with configuration {} is not a valid onewire ItemType!", pvItem.getName(),
                    pvItem.getClass().getSimpleName(), pvBindingConfig);
        }

        return lvIsValidItem;
    }
}
