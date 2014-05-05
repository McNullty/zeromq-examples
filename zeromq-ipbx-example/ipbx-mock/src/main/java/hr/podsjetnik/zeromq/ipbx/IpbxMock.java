package hr.podsjetnik.zeromq.ipbx;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class IpbxMock {

	public static void main(String[] args) throws Exception {
		// Prepare our context and publisher
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);
		Socket responder = context.socket(ZMQ.REP);
		
        responder.bind("tcp://*:5555");
		publisher.bind("tcp://*:5563");
		System.out.println("Starting pub");		
		while (!Thread.currentThread().isInterrupted()) {

			byte[] request = responder.recv(0);
			String message = new String(request);
			System.out.println("Got Message: " + message);
			String reply = "OK";
            responder.send(reply.getBytes(), 0);
            			
			publisher.sendMore("A");
			publisher.send(message);

		}
		
		responder.close();
        context.term();
        
		System.out.println("Closing pub");		
		publisher.close();
		context.term();
	}
}
