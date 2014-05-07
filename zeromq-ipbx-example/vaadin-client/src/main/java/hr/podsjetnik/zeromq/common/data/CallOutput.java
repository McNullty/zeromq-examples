package hr.podsjetnik.zeromq.common.data;

public class CallOutput {

	private String status;
	private String timestamp;
	private String call_ref;
	private String dnid;
	private String callerid;

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCall_ref() {
		return call_ref;
	}

	public void setCall_ref(final String call_ref) {
		this.call_ref = call_ref;
	}

	public String getDnid() {
		return dnid;
	}

	public void setDnid(final String dnid) {
		this.dnid = dnid;
	}

	public String getCallerid() {
		return callerid;
	}

	public void setCallerid(final String callerid) {
		this.callerid = callerid;
	}

}
