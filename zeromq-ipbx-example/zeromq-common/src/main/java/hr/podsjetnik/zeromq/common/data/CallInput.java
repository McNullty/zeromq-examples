package hr.podsjetnik.zeromq.common.data;

public class CallInput {

	private Integer service_id;
	private String source;
	private String destination;
	private String external_ref;

	public Integer getService_id() {
		return service_id;
	}

	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getExternal_ref() {
		return external_ref;
	}

	public void setExternal_ref(String external_ref) {
		this.external_ref = external_ref;
	}
}
