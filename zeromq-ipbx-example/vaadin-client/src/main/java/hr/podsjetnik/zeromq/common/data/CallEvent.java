package hr.podsjetnik.zeromq.common.data;

public class CallEvent {

	private String event;
	private String call_ref;
	private String external_ref;
	private String call_state;
	private String originate_response;
	private String originate_reason;
	private String user_id;
	private String source;
	private String destination;
	private String channel1_state;
	private String channel2_state;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCall_ref() {
		return call_ref;
	}

	public void setCall_ref(String call_ref) {
		this.call_ref = call_ref;
	}

	public String getExternal_ref() {
		return external_ref;
	}

	public void setExternal_ref(String external_ref) {
		this.external_ref = external_ref;
	}

	public String getCall_state() {
		return call_state;
	}

	public void setCall_state(String call_state) {
		this.call_state = call_state;
	}

	public String getOriginate_response() {
		return originate_response;
	}

	public void setOriginate_response(String originate_response) {
		this.originate_response = originate_response;
	}

	public String getOriginate_reason() {
		return originate_reason;
	}

	public void setOriginate_reason(String originate_reason) {
		this.originate_reason = originate_reason;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getChannel1_state() {
		return channel1_state;
	}

	public void setChannel1_state(String channel1_state) {
		this.channel1_state = channel1_state;
	}

	public String getChannel2_state() {
		return channel2_state;
	}

	public void setChannel2_state(String channel2_state) {
		this.channel2_state = channel2_state;
	}
}
