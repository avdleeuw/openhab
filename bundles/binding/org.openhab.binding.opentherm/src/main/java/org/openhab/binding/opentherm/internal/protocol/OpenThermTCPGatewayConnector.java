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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThermTCPGatewayConnector class. Implements communication with the OpenTherm
 * Gateway over TCP/IP.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public class OpenThermTCPGatewayConnector extends OpenThermAbstractGatewayConnector implements OpenThermGatewayConnector {

	private static final int READ_BUFFER_SIZE=1024;
	private static final int WRITE_BUFFER_SIZE=1024;
	
	private static final Logger logger = LoggerFactory.getLogger(OpenThermTCPGatewayConnector.class);
	private SocketChannel socketChannel;
	private ReadThread readThread;
	private WriteThread writeThread;

	/**
	 * Constructor. Creates a new instance of the {@link OpenThermTCPGatewayConnector}
	 * class.
	 */
	public OpenThermTCPGatewayConnector() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connect(String port) throws OpenThermConnectionException {
		logger.trace("Starting the TCP Gateway.");
		URI uri;
		try {
			uri = new URI(port);
		} catch (URISyntaxException e) {
			throw new OpenThermConnectionException(port, String.format("Malformed URL: %s", port), e);
		}

		try {
			socketChannel = SocketChannel.open(new InetSocketAddress(uri.getHost(), uri.getPort()));
		} catch (IOException e) {
			throw new OpenThermConnectionException(port, String.format("Connection to port failed: %s", port), e);
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
		
		if (socketChannel != null) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				logger.warn("Got an exception while closing the socket. Assuming that it is closed.");
			}
			socketChannel = null;
		}
		
		logger.trace("TCP Gateway stopped.");
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
			
			ByteBuffer buffer = ByteBuffer.allocate(READ_BUFFER_SIZE);
			
			while (!interrupted()) {
				try {
					int count = socketChannel.read(buffer);
					
					if (count < 0) {
						logger.error("Stream was closed from the other side, ending thread.");
						return;
					}
					
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
			
			ByteBuffer buffer = ByteBuffer.allocate(WRITE_BUFFER_SIZE);
			
			while (!interrupted()) {
				try {
					SerialMessage serialMessage = take();
					
					if (serialMessage == null) {
						logger.error("Thread was interrupted, ending thread.");
						return;
					}

					buffer.clear();
					buffer.put(serialMessage.toArray());
					buffer.flip();

					while (buffer.hasRemaining()) {
						socketChannel.write(buffer);
					}
				} catch (IOException e) {
					logger.error("Got an I/O error while writing data, ending thread. error = {}", e.getMessage());
					return;
				}
			}
			logger.trace("Write thread stopped.");
		}
	}
}
