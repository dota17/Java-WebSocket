/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java_websocket.client;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebSocketClientTest {

	@Test
	public void testMyWebSocketClientConstructor() throws URISyntaxException {
		Draft draft = new Draft_6455();
		URI uri = new URI("ws://localhost");
		Map<String, String> httpHeaders = new HashMap<String, String>();
		httpHeaders.put("Cache-Control", "only-if-cached");
		httpHeaders.put("Keep-Alive", "1000");

		try {
			new MyWebSocketClient(null);
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {

		}

		try {
			new MyWebSocketClient(uri);
		} catch (IllegalArgumentException e) {
			fail("IllegalArgumentException should be thrown");
		}

		try {
			new MyWebSocketClient(uri, null);
		} catch (IllegalArgumentException e) {
			fail("IllegalArgumentException should be thrown");
		}

		try {
			new MyWebSocketClient(null, httpHeaders);
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {

		}

		try {
			new MyWebSocketClient(uri, httpHeaders);
		} catch (IllegalArgumentException e) {
			fail("IllegalArgumentException should be thrown");
		}

		try {
			new MyWebSocketClient(null, httpHeaders);
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {

		}

		try {
			new MyWebSocketClient(null, null, null);
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {

		}

		try {
			new MyWebSocketClient(null, draft, httpHeaders);
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {

		}

		try {
			new MyWebSocketClient(uri, null, httpHeaders);
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {

		}

		try {
			new MyWebSocketClient(uri, draft, null);
		} catch (IllegalArgumentException e) {
			fail("IllegalArgumentException should be thrown");
		}

		try {
		         MyWebSocketClient client = new MyWebSocketClient(uri, draft, httpHeaders);
			 assertEquals(draft, client.getDraft());
		} catch (IllegalArgumentException e) {
			fail("IllegalArgumentException should be thrown");
		}
	}

	@Test
	public void testGetLocalSocketAddress() throws URISyntaxException {
		Draft draft = new Draft_6455();
		URI uri = new URI("ws://localhost");
		Map<String, String> httpHeaders = new HashMap<String, String>();
		httpHeaders.put("Cache-Control", "only-if-cached");
		httpHeaders.put("Keep-Alive", "1000");

		MyWebSocketClient client = new MyWebSocketClient(uri, draft, httpHeaders);
		assertEquals(draft, client.getDraft());
		assertNotNull(client.getConnection());
		assertNull(client.getLocalSocketAddress());
		assertNull(client.getLocalSocketAddress(client.getConnection()));
		assertNull(client.getRemoteSocketAddress());
		assertNull(client.getRemoteSocketAddress(client.getConnection()));
	}

	private static class MyWebSocketClient extends WebSocketClient {

		public MyWebSocketClient(URI serverUri) {
			super(serverUri);
		}

		public MyWebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
			super(serverUri, httpHeaders);
		}

		public MyWebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
			super(serverUri, protocolDraft, httpHeaders);
		}

		@Override
		public void onOpen(ServerHandshake handshakedata) {

		}

		@Override
		public void onMessage(String message) {
		
		}

		@Override
		public void onClose(int code, String reason, boolean remote) {
		
		}

		@Override
		public void onError(Exception ex) {
		
		}
	}
}
