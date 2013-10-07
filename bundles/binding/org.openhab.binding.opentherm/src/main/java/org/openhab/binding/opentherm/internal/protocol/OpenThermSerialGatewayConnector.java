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

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThermSerialGatewayConnector class. Implements communication with the OpenTherm
 * Gateway over the serial port.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermSerialGatewayConnector extends OpenThermAbstractGatewayConnector implements OpenThermGatewayConnector {

	private static final int SERIAL_PORT_BAUD_RATE = 9600;
	private static final int SERIAL_PORT_TIMEOUT = 2000;
	private static final int SERIAL_RECEIVE_TIMEOUT = 1000;
	private static final int READ_BUFFER_SIZE = 1024;
	
	private static final Logger logger = LoggerFactory.getLogger(OpenThermSerialGatewayConnector.class);
	private SerialPort serialPort;
	private ReadThread readThread;
	private WriteThread writeThread;

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermSerialGatewayConnector}
	 * class.
	 */
	public OpenThermSerialGatewayConnector() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connect(String port) throws OpenThermConnectionException {
		logger.trace("Starting the Serial Gateway.");
		try
		{
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(port);
			CommPort commPort = portIdentifier.open("org.openhab.binding.opentherm", SERIAL_PORT_TIMEOUT);
			this.serialPort = (SerialPort) commPort;
			this.serialPort.setSerialPortParams(SERIAL_PORT_BAUD_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			this.serialPort.enableReceiveThreshold(1);
			this.serialPort.enableReceiveTimeout(SERIAL_RECEIVE_TIMEOUT);
		} catch (NoSuchPortException e) {
			logger.error(String.format("Port does not exist: %s", port));
			throw new OpenThermConnectionException(String.format("Port does not exist: %s", port), e);
		} catch (PortInUseException e) {
			logger.error(String.format("Port in use: %s", port));
			throw new OpenThermConnectionException(String.format("Port in use: %s", port), e);
		} catch (UnsupportedCommOperationException e) {
			logger.error(String.format("Unsupported operation on port: %s", port));
			throw new OpenThermConnectionException(String.format("Unsupported operation on port: %s", port), e);
		}
		
		readThread = new ReadThread();
		readThread.start();
		writeThread = new WriteThread();
		writeThread.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disconnect() {
		if (writeThread != null) {
			writeThread.interrupt();
			try {
				writeThread.join();
			} catch (InterruptedException e) {
			}
			writeThread = null;
		}
		
		if (readThread != null) {
			readThread.interrupt();
			try {
				readThread.join();
			} catch (InterruptedException e) {
			}
			readThread = null;
		}
		
		if (this.serialPort != null) {
			this.serialPort.close();
			this.serialPort = null;
		}
		
		logger.trace("Serial Gateway stopped.");
	}

	/**
	 * ReadThread class. Implements reading from the gateway and passing the
	 * message on to the parser for further handling.
	 * 
	 * @author Jan-Willem Spuij <jwspuij@gmail.com>
	 * @since 1.4.0
	 */
	private class ReadThread extends Thread {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			logger.trace("Read thread started.");
			
		    InputStream stream;
			try {
				stream = serialPort.getInputStream();
			} catch (IOException e) {
				logger.error("Got an I/O error while reading data, ending thread. error = {}", e.getMessage());
				return;
			}
			
			ByteBuffer buffer = ByteBuffer.allocate(READ_BUFFER_SIZE);
			
			while (!interrupted()) {
				try {
					
					int count = stream.read(buffer.array(), buffer.position(), buffer.remaining());
					
					if (count <= 0) {
						continue;
					}
					buffer.position(buffer.position() + count);
					
					processSerialMessages(buffer);
				} catch (IOException e) {
					logger.error("Got an I/O error while reading data, ending thread. error = {}", e.getMessage());
					return;
				}
			}
			logger.trace("Read thread stopped.");
		}
	}
	
	/**
	 * WriteThread class. Implements writing to the gateway.
	 * 
	 * @author Jan-Willem Spuij <jwspuij@gmail.com>
	 * @since 1.4.0
	 */
	private class WriteThread extends Thread {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			logger.trace("Write thread started.");
			
			OutputStream stream;
			try {
				stream = serialPort.getOutputStream();
			} catch (IOException e) {
				logger.error("Got an I/O error while writing data, ending thread. error = {}", e.getMessage());
				return;
			}
			
			while (!interrupted()) {
				try {
						SerialMessage serialMessage = take();
						
						if (serialMessage == null) {
							logger.error("Thread was interrupted, ending thread.");
							return;
						}
						
						stream.write(serialMessage.toArray());
						stream.flush();
				} catch (IOException e) {
					logger.error("Got an I/O error while writing data, ending thread. error = {}", e.getMessage());
					return;
				}
			}
			logger.trace("Write thread stopped.");
		}
	}
}
