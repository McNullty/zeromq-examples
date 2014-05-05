package hr.podsjetnik.zeromq.common.service;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import hr.podsjetnik.zeromq.common.data.CallInput;

public class CallInputService {

	private static ObjectMapper mapper = new ObjectMapper();

	public static CallInput convertJsonToJava(String input)
			throws JsonParseException, JsonMappingException, IOException {
		CallInput ci = mapper.readValue(input, CallInput.class);

		return ci;
	}

	public static CallInput convertJsonToJava(File file) throws JsonParseException, JsonMappingException, IOException {
		CallInput ci = mapper.readValue(file, CallInput.class);

		return ci;
	}

	public static String convertJavaToJson(CallInput ci)
			throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(ci);
	}

}
