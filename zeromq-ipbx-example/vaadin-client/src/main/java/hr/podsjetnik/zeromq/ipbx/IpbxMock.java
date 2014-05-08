package hr.podsjetnik.zeromq.ipbx;

import java.text.SimpleDateFormat;
import java.util.Date;

import hr.podsjetnik.zeromq.common.data.CallEvent;
import hr.podsjetnik.zeromq.common.data.CallInput;
import hr.podsjetnik.zeromq.common.service.CallInputService;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class IpbxMock {

	public static void main(String[] args) throws Exception {
		// Prepare our context and publisher
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);
		Socket responder = context.socket(ZMQ.REP);
		
		SimpleDateFormat sfd = new SimpleDateFormat("kk:mm:ss dd.MM.yyyy");

		responder.bind("tcp://*:5555");
		publisher.bind("tcp://*:5563");
		System.out.println("Starting pub");
		while (!Thread.currentThread().isInterrupted()) {

			byte[] request = responder.recv(0);
			String message = new String(request);
			System.out.println("Got Message: " + message);
			String reply = "OK";
			responder.send(reply.getBytes(), 0);

			Thread.sleep(3000);
			CallInput ci = CallInputService.convertJsonToJava(message);
			CallEvent event = new CallEvent();
			event.setEvent("link");
			event.setCall_ref("76u0wdd6b4db36zuuxgtvs6n2");
			event.setExternal_ref(ci.getExternal_ref());
			event.setTimestamp(sfd.format(new Date()));
			event.setCall_state("PCS_TALKING");
			event.setOriginate_reason("4");
			event.setOriginate_response("Success");
			event.setUser_id("1");
			event.setSource(ci.getSource());
			event.setDestination(ci.getDestination());
			event.setChannel1_state("CS_UP");
			event.setChannel2_state("CS_UP");

			publisher.send(CallInputService.convertJavaToJson(event));

		}

		responder.close();
		context.term();

		System.out.println("Closing pub");
		publisher.close();
		context.term();
	}
}
