/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.opentherm.internal.protocol;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessage;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageException;
import org.openhab.binding.opentherm.internal.protocol.serial.SerialMessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThermAbstractGatewayConnector class. Defines a base class for Classes
 * implementing the connection to the OpenTherm Gateway.
 * 
 * @author Jan-Willem Spuij <jwspuij@gmail.com>
 * @since 1.4.0
 */
public abstract class OpenThermAbstractGatewayConnector {

	private static final char CARRIAGE_RETURN = 0x0D;
	private static final char LINE_FEED = 0x0A;
	private static final int SEND_QUEUE_SIZE = 16;
	private static final Logger logger = LoggerFactory.getLogger(OpenThermTCPGatewayConnector.class);

	private final BlockingQueue<SerialMessage> queue = new ArrayBlockingQueue<SerialMessage>(SEND_QUEUE_SIZE);
	private final Set<SerialMessageReceiver> serialMessageReceivers = new HashSet<SerialMessageReceiver>();

	/**
	 * Enqueue a {@link SerialMessage} for sending to the gateway.
	 * 
	 * @param serialMessage
	 *            the {@link SerialMessage} to send.
	 */
	public void enqueue(SerialMessage serialMessage) {
		try {
			queue.put(serialMessage);
			logger.debug("Enqueued message: {}", serialMessage.getMessage());

		} catch (InterruptedException e) {
			logger.warn("Got interrupted while enqueuing. Serial message not queued.");
		}
	}

	/**
	 * Takes a SerialMessage to send from the queue. Returns null in case the
	 * thread gets interrupted while waiting for a new command.
	 * 
	 * @return the SerialMessage or null in case the thread gets interrupted.
	 */
	protected SerialMessage take() {
		try {
			SerialMessage serialMessage = queue.take();
			logger.debug("Dequeued message: {}", serialMessage.getMessage());
			return serialMessage;
		} catch (InterruptedException e) {
			return null;
		}
	}

	/**
	 * Adds a {@link OpenThermSerialMessageReceiver} to the list of receivers.
	 * 
	 * @param serialMessageReceiver
	 *            the serialMessage receiver to add.
	 */
	public void addSerialMessageReceiver(SerialMessageReceiver serialMessageReceiver) {
		this.serialMessageReceivers.add(serialMessageReceiver);
		logger.trace("Added Serial Message Receiver {} to the list of Serial Message Receivers.");
	}

	/**
	 * Removes the {@link SerialMessageReceiver} from the list of receivers.
	 * 
	 * @param serialMessageReceiver
	 *            the serial message receiver to remove.
	 */
	public void removeSerialMessageReceiver(SerialMessageReceiver serialMessageReceiver) {
		this.serialMessageReceivers.remove(serialMessageReceiver);
		logger.trace("Removed Serial Message Receiver {} from the list of Serial Message Receivers.");
	}

	/**
	 * Processes 0 or more serial messages for dispatch.
	 * @param buffer. The byte buffer to check for messages.
	 */
	protected void processSerialMessages(ByteBuffer buffer) {

		StringBuilder stringBuilder = new StringBuilder();

		buffer.flip();
		int position = 0;

		try {
			while (buffer.hasRemaining()) {
				byte c = buffer.get();
				switch (c) {
				case CARRIAGE_RETURN:
					if (!buffer.hasRemaining())
						return;

					c = buffer.get();
					if (c == LINE_FEED) {
						logger.debug("Message received: {}", stringBuilder.toString());
						handleReceivedSerialMessage(stringBuilder.toString());
					} else {
						logger.error("Incorrect message received: {}", stringBuilder.toString());
						handleSerialMessageError(stringBuilder.toString());
					}
					position = buffer.position();
					stringBuilder = new StringBuilder();
					break;
				default:
					stringBuilder.append((char) c);
					break;
				}
			}
		} finally {
			buffer.position(position);
			buffer.compact();
		}
	}

	/**
	 * Handle a received serial message by notifying all serial message receivers.
	 * 
	 * @param messageContent
	 *            the serial Message content;
	 */
	private void handleReceivedSerialMessage(String messageContent) {
		logger.trace("Handling received message by notifying message Receivers.");
		SerialMessage serialMessage = new SerialMessage(messageContent);
		for (SerialMessageReceiver serialMessageReceiver : this.serialMessageReceivers) {
			serialMessageReceiver.receiveSerialMessage(serialMessage);
		}
	}

	/**
	 * Handle a serial message error by notifying all serial message receivers.
	 * 
	 * @param messageContent
	 *            the invalid serial Message content;
	 */
	private void handleSerialMessageError(String messageContent) {
		logger.trace("Handling message error by notifying message Receivers.");
		for (SerialMessageReceiver serialMessageReceiver : this.serialMessageReceivers) {
			serialMessageReceiver.receiveSerialError(new SerialMessageException(messageContent, "Incorrect message received."));
		}
	}
}
