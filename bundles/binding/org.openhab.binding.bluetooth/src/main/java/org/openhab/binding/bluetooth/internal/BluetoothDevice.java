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
package org.openhab.binding.bluetooth.internal;

/**
 * This class is a data structure to hold all relevant information about a bluetooth device.
 *
 * @author Kai Kreuzer
 *
 * @since 0.3.0
 *
 */
public class BluetoothDevice {

    private String address;
    private String friendlyName;
    private boolean paired;

    /**
     * Default constructor which directly initializes the fields
     * 
     * @param address the address of the bluetooth device, e.g. "EC3B2BD562D2"
     * @param friendlyName the name the user has given the device, e.g. "iPhone"
     * @param paired true, if the device has been paired with the host and is therefore trusted
     */
    public BluetoothDevice(String address, String friendlyName, boolean paired) {
        this.address = address;
        this.friendlyName = friendlyName;
        this.paired = paired;
    }

    /**
     * returns the address of the bluetooth device
     * 
     * @return the address of the bluetooth device
     */
    public String getAddress() {
        return address;
    }

    /**
     * returns the friendly name of the bluetooth device
     * 
     * @return the friendly name of the bluetooth device
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * tells whether the device is paired with the host
     * 
     * @return true, if the device has been paired
     */
    public boolean isPaired() {
        return paired;
    }

    @Override
    public String toString() {
        return address + " (" + friendlyName + ")" + (paired ? "!" : "?");
    }

}
