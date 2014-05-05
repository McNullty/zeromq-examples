package hr.podsjetnik.zeromq.common.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import hr.podsjetnik.zeromq.common.data.CallInput;
import org.junit.Test;

public class CallInputServiceTest {

	private CallInput ci;

	@Test
	public void testJsonToJava() {

		try {
			ci = CallInputService
					.convertJsonToJava("{ \"service_id\" : 1, \"source\" : \"311\", \"destination\" : \"38591222333\", \"external_ref\" : \"uuqu7TohH2m\" }");
			assertEquals(new Integer(1), ci.getService_id());
			assertEquals("311", ci.getSource());
			assertEquals("38591222333", ci.getDestination());
			assertEquals("uuqu7TohH2m", ci.getExternal_ref());
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testJsonToJavaFromFile() {
		try {
			ci = CallInputService
					.convertJsonToJava(new File(
							"/home/mladenc/Documents/workspaces/zero-mq/zeromq-ipbx-example/zeromq-common/src/test/resources/callInput.json"));
			assertEquals(new Integer(1), ci.getService_id());
			assertEquals("311", ci.getSource());
			assertEquals("38591222333", ci.getDestination());
			assertEquals("uuqu7TohH2m", ci.getExternal_ref());
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	public void testJavaToJson() {
		ci = new CallInput();
		ci.setService_id(1);
		ci.setSource("311");
		ci.setDestination("38591222333");
		ci.setExternal_ref("uuqu7TohH2m");

		try {
			String json = CallInputService.convertJavaToJson(ci);
			assertEquals(
					"{ \"service_id\" : 1, \"source\" : \"311\", \"destination\" : \"38591222333\", \"external_ref\" : \"uuqu7TohH2m\" }",
					json);
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

}
