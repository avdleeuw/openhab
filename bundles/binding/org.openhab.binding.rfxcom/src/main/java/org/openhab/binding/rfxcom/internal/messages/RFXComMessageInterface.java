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
package org.openhab.binding.rfxcom.internal.messages;

import java.util.List;

import org.openhab.binding.rfxcom.RFXComValueSelector;
import org.openhab.binding.rfxcom.internal.RFXComException;
import org.openhab.core.types.State;
import org.openhab.core.types.Type;

/**
 * This interface defines interface which every message class should implement.
 *
 * @author Pauli Anttila
 * @since 1.2.0
 */
public interface RFXComMessageInterface {

    /**
     * Procedure for present class information in string format. Used for
     * logging purposes.
     * 
     */
    @Override
    String toString();

    /**
     * Procedure for encode raw data.
     * 
     * @param data
     *            Raw data.
     */
    void encodeMessage(byte[] data);

    /**
     * Procedure for decode object to raw data.
     * 
     * @return raw data.
     */
    byte[] decodeMessage();

    /**
     * Procedure for converting RFXCOM value to Openhab state.
     * 
     * @param valueSelector
     * 
     * @return Openhab state.
     */
    State convertToState(RFXComValueSelector valueSelector) throws RFXComException;

    /**
     * Procedure for converting Openhab state to RFXCOM object.
     * 
     */
    void convertFromState(RFXComValueSelector valueSelector, String id, Object subType, Type type, byte seqNumber)
            throws RFXComException;

    /**
     * Procedure for converting sub type as string to sub type object.
     * 
     * @return sub type object.
     */
    Object convertSubType(String subType) throws RFXComException;

    /**
     * Procedure for creating device id.
     * 
     * @return device Id.
     */
    String generateDeviceId() throws RFXComException;

    /**
     * Procedure for get supported value selector list.
     * 
     * @return List of supported value selectors.
     */
    List<RFXComValueSelector> getSupportedValueSelectors() throws RFXComException;

}
