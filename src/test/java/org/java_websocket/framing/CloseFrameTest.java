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

package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.junit.Test;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * JUnit Test for the CloseFrame class
 */
public class CloseFrameTest {

    @Test
    public void testConstructor() {
        CloseFrame frame = new CloseFrame();
        assertEquals("Opcode must be equal", Opcode.CLOSING, frame.getOpcode());
        assertEquals("Fin must be set", true, frame.isFin());
        assertEquals("TransferedMask must not be set", false, frame.getTransfereMasked());
        assertEquals("Payload must be 2 (close code)", 2, frame.getPayloadData().capacity());
        assertEquals("RSV1 must be false", false, frame.isRSV1());
        assertEquals("RSV2 must be false", false, frame.isRSV2());
        assertEquals("RSV3 must be false", false, frame.isRSV3());
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
    }

   @Test
    public void testExtends() {
        CloseFrame frame = new CloseFrame();
        assertEquals("Frame must extend dataframe", true, frame instanceof ControlFrame);
    }

    @Test
    public void testToString() {
        CloseFrame frame = new CloseFrame();
        String frameString = frame.toString();
        frameString = frameString.replaceAll("payload:(.*)}", "payload: *}");
        assertEquals("Frame toString must include a close code", "Framedata{ opcode:CLOSING, fin:true, rsv1:false, rsv2:false, rsv3:false, payload length:[pos:0, len:2], payload: *}code: 1000", frameString);
    }


    @Test
    public void testIsValid() {
        CloseFrame frame = new CloseFrame();
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setFin(false);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setFin(true);
        frame.setRSV1(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV1(false);
        frame.setRSV2(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV2(false);
        frame.setRSV3(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV3(false);
        frame.setCode(CloseFrame.NORMAL);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.GOING_AWAY);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.PROTOCOL_ERROR);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.REFUSE);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.NOCODE);
        assertEquals(0,frame.getPayloadData().capacity());
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.ABNORMAL_CLOSE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.POLICY_VALIDATION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.TOOBIG);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.EXTENSION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.UNEXPECTED_CONDITION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.SERVICE_RESTART);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.TRY_AGAIN_LATER);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.BAD_GATEWAY);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.TLS_ERROR);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NEVER_CONNECTED);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.BUGGYCLOSE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.FLASHPOLICY);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NOCODE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NO_UTF8);
        frame.setReason(null);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NOCODE);
        frame.setReason("Close");
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }

	int test_undefine_code = 2500;
	frame.setCode(test_undefine_code);
	frame.setReason("Trying to send an illegal close code");
	try {
            frame.isValid();
	    fail("InvalidDataException should be thrown");
	} catch (InvalidDataException e) {
	    //fine
	}

	test_undefine_code = 5000;
	frame.setCode(test_undefine_code);
	frame.setReason("Close code must not be send over the wire");
	try {
            frame.isValid();
	    fail("InvalidDataException should be thrown");
	} catch (InvalidDataException e) {
	    //fine
	}
        
	test_undefine_code = 1004;
	frame.setCode(test_undefine_code);
	frame.setReason("Close code must not be send over the wire");
	try {
            frame.isValid();
	    fail("InvalidDataException should be thrown");
	} catch (InvalidDataException e) {
	    //fine
	}
    }

    @Test
    public void testEquals() {
	CloseFrame frame = new CloseFrame();

	assertFalse(frame.equals(null));
	assertFalse(frame.equals(new Object()));

	CloseFrame frame1 = frame;
	assertTrue(frame.equals(frame1));

        frame1 = new CloseFrame();
	frame1.setCode(CloseFrame.GOING_AWAY);
	assertFalse(frame.equals(frame1));

	frame1.setCode(CloseFrame.NORMAL);
	assertTrue(frame.equals(frame1));

	frame1.setReason("test");
	assertFalse(frame.equals(frame1));

	frame1.setReason("");
	assertTrue(frame.equals(frame1));

	frame1.setFin(false);
	assertFalse(frame.equals(frame1));

	frame1.setFin(true);
	frame1.setRSV1(true);
	assertFalse(frame.equals(frame1));

	frame1.setRSV1(false);
	frame1.setRSV2(true);
	assertFalse(frame.equals(frame1));

	frame1.setRSV2(false);
	frame1.setRSV3(true);
	assertFalse(frame.equals(frame1));

	frame1.setRSV3(false);
	assertTrue(frame.equals(frame1));	
    }

    @Test
    public void testSetPayload() {
	CloseFrame frame = new CloseFrame();
	frame.setPayload(ByteBuffer.wrap("".getBytes()));
	assertEquals("Must be CloseFrame.NORMAL", CloseFrame.NORMAL, frame.getCloseCode());

	frame.setPayload(ByteBuffer.wrap("i".getBytes()));
	assertEquals("Must be CloseFrame.PROTOCOL_ERROR", CloseFrame.PROTOCOL_ERROR, frame.getCloseCode());

	frame.setPayload(ByteBuffer.wrap("io".getBytes()));
	assertEquals("Uncertain values, if more than two character strings are transferred", 26991, frame.getCloseCode());
    }
}
