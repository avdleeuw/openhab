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
package org.openhab.binding.dmx.internal;

import org.openhab.binding.dmx.DmxBindingProvider;
import org.openhab.binding.dmx.DmxService;
import org.openhab.binding.dmx.internal.config.DmxItem;
import org.openhab.core.binding.AbstractBinding;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DMX event binding class. Processes all the command events and forwards them
 * if required.
 *
 * @author Davy Vanherbergen
 * @since 1.2.0
 */
public class DmxBinding extends AbstractBinding<DmxBindingProvider> {

    private static final Logger logger = LoggerFactory.getLogger(DmxBinding.class);

    private DmxService dmxService;

    protected void addBindingProvider(DmxBindingProvider bindingProvider) {
        super.addBindingProvider(bindingProvider);
    }

    protected void removeBindingProvider(DmxBindingProvider bindingProvider) {
        super.removeBindingProvider(bindingProvider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveCommand(String itemName, Command command) {

        if (dmxService == null) {
            logger.warn("No DMX Service available.");
            return;
        }

        // get the item's corresponding dmx binding
        DmxItem itemBinding = null;
        for (DmxBindingProvider provider : providers) {
            if (provider.providesBindingFor(itemName)) {
                itemBinding = provider.getBindingConfig(itemName);
                break;
            }
        }

        if (itemBinding == null) {
            return;
        }

        dmxService.suspend(true);
        itemBinding.processCommand(dmxService, command);
        dmxService.suspend(false);

    }

    /**
     * DmxService loaded via DS.
     */
    public void setDmxService(DmxService dmxService) {
        this.dmxService = dmxService;
    }

    /**
     * DmxService unloaded via DS.
     */
    public void unsetDmxService(DmxService dmxService) {
        this.dmxService = null;
    }

}
