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

package org.java_websocket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;

import org.junit.Test;
import static org.junit.Assert.fail;

public class SSLSocketChannel2Test {
	
    @Test
    public void testConstructor() throws IOException, NoSuchAlgorithmException {

        SocketChannel inputSocketChannel = SocketChannel.open();
	SSLContext sslContext = SSLContext.getDefault();
	SSLEngine engine = sslContext.createSSLEngine();

	try {
	    new SSLSocketChannel2(null, null, null, null);
	    fail("IllegalArgumentException should be thrown");
	} catch (IllegalArgumentException e) {
	
	}

	try {
	    new SSLSocketChannel2(inputSocketChannel, null, null, null);
	    fail("IllegalArgumentException should be thrown");
	} catch (IllegalArgumentException e) {
            inputSocketChannel.close();	
	}

	try {
	    inputSocketChannel = SocketChannel.open();
	    new SSLSocketChannel2(inputSocketChannel, engine, null, null);
	    fail("IllegalArgumentException should be thrown");
	} catch (IllegalArgumentException e) {
	    inputSocketChannel.close();
	}

	try {
	    inputSocketChannel = SocketChannel.open();
	    new SSLSocketChannel2(inputSocketChannel, null, Executors.newSingleThreadScheduledExecutor(), null);
	    fail("IllegalArgumentException should be thrown");
	} catch (IllegalArgumentException e) {
	    inputSocketChannel.close();
	}

	try {
	    new SSLSocketChannel2(null, engine, Executors.newSingleThreadScheduledExecutor(), null);
	    fail("IllegalArgumentException should be thrown");
	} catch (IllegalArgumentException e) {

	}

	
	try {
	    inputSocketChannel = SocketChannel.open();
	    engine.setUseClientMode(true);
	    engine.setSSLParameters(new SSLParameters());
	    new SSLSocketChannel2(inputSocketChannel, engine, Executors.newSingleThreadScheduledExecutor(), null);
	    fail("NotYetConnectedException should be thrown");
	} catch (NotYetConnectedException e) {
	    inputSocketChannel.close();
	}
    }
}
