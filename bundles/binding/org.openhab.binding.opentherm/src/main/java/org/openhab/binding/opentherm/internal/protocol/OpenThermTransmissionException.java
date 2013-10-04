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
package org.openhab.binding.opentherm.internal.protocol;

import java.util.HashMap;
import java.util.Map;

import org.openhab.binding.opentherm.internal.OpenThermException;

/**
 * Exception that occurs when an error occurs during transmission.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermTransmissionException extends OpenThermException {

	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID = -9069525441513225447L;

	private final TransmissionError transmissionError;

	/**
	 * Returns the transmission Error.
	 * 
	 * @return the transmission Error.
	 */
	public TransmissionError getTransmissionError() {
		return transmissionError;
	}

	/**
	 * Creates a new instance of the {@link OpenThermTransmissionException}
	 * class.
	 * 
	 * @param transmissionError
	 *            the transmission Error.
	 */
	public OpenThermTransmissionException(TransmissionError transmissionError) {
		this.transmissionError = transmissionError;
	}

	/**
	 * Creates a new instance of the {@link OpenThermTransmissionException}
	 * class.
	 * 
	 * @param transmissionError
	 *            the transmission Error.
	 * @param message
	 *            the message indicating the Exception that occurred.
	 */
	public OpenThermTransmissionException(TransmissionError transmissionError, String message) {
		super(message);
		this.transmissionError = transmissionError;
	}

	/**
	 * Creates a new instance of the {@link OpenThermTransmissionException}
	 * class.
	 * 
	 * @param transmissionError
	 *            the transmission Error.
	 * @param cause
	 *            the cause that led to this Exception.
	 */
	public OpenThermTransmissionException(TransmissionError transmissionError, Throwable cause) {
		super(cause);
		this.transmissionError = transmissionError;
	}

	/**
	 * Creates a new instance of the {@link OpenThermTransmissionException}
	 * class.
	 * 
	 * @param transmissionError
	 *            the transmission Error.
	 * @param message
	 *            the message indicating the Exception that occurred.
	 * @param cause
	 *            the cause that led to this Exception.
	 */
	public OpenThermTransmissionException(TransmissionError transmissionError, String message, Throwable cause) {
		super(message, cause);
		this.transmissionError = transmissionError;
	}

	/**
	 * Transmission Error enumeration. Indicates the transmission error that
	 * occurred.
	 * 
	 * @author Jan-Willem Spuij <jwspuij@gmail.com>
	 * @since 1.4.0
	 */
	public enum TransmissionError {
		BIT_TRANSITION(1, "A bit transition happened at an unexpected time."), 
		STOP_BIT(2, "The stop bit was 0 while it should be 1."), 
		NOT_RECEIVED(3, "A bit was not received when it was expected."),
		PARITY(4, "A parity error was detected on a received opentherm message.");

		private static Map<Integer, TransmissionError> keyToTransmissionErrorMapping = null;
		private final int key;
		private final String label;

		/**
		 * Returns the key of the transmission error.
		 * 
		 * @return the key
		 */
		public int getKey() {
			return key;
		}

		/**
		 * Returns the label of the frame type.
		 * 
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		private TransmissionError(int key, String label) {
			this.key = key;
			this.label = label;
		}

		private static void addMappings() {
			for (TransmissionError transmissionError : values()) {
				keyToTransmissionErrorMapping = new HashMap<Integer, TransmissionError>();
				keyToTransmissionErrorMapping.put(transmissionError.getKey(), transmissionError);
			}
		}

		/**
		 * Get the {@link TransmissionError} by key.
		 * 
		 * @param key
		 *            the key to use.
		 * @return the {@link TransmissionError} or null if not found;
		 */
		public static TransmissionError get(int key) {
			if (keyToTransmissionErrorMapping == null) {
				addMappings();
			}
			return keyToTransmissionErrorMapping.get(key);
		}
	}
}
