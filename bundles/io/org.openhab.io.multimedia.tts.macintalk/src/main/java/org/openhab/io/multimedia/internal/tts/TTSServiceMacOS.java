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
package org.openhab.io.multimedia.internal.tts;

import java.io.IOException;
import java.util.ArrayList;

import org.openhab.io.multimedia.tts.TTSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a TTS service implementation for MacOS, which simply uses the "say" command from MacOS.
 *
 * @author Karel Goderis
 * @since 1.5.0
 */
public class TTSServiceMacOS implements TTSService {

    private static final Logger logger = LoggerFactory.getLogger(TTSServiceMacOS.class);

    public void activate() {
    }

    public void deactive() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void say(String text, String voiceName, String outputDevice) {

        ArrayList<String> list = new ArrayList<String>();
        list.add("say");

        if (outputDevice != null) {
            list.add("-a");
            list.add(outputDevice);
        }

        if (voiceName != null) {
            list.add("-v");
            list.add(voiceName);
        }

        list.add(text.replace("-", " minus "));

        try {
            Process process = Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
            process.waitFor();
        } catch (IOException e) {
            logger.error("Error while executing the 'say' command: " + e.getMessage());
        } catch (InterruptedException e) {
            logger.error("The 'say' command has been interrupted: " + e.getMessage());
        }
    }

}
