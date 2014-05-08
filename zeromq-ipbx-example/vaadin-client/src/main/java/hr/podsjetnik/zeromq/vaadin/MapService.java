package hr.podsjetnik.zeromq.vaadin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapService {

	private Map<String, String> map;

	public MapService() {
		map = new HashMap<>();
	}

	public String getMessage(String caller) {
		return map.get(caller);
	}

	public void setMessage(String caller, String message) {
		map.put(caller, message);
	}

	@Override
	public String toString() {
		return "MapService [map=" + map + "]";
	}
}
