package hr.podsjetnik.zeromq.ipbx;

import java.io.IOException;

import hr.podsjetnik.zeromq.common.data.CallInput;
import hr.podsjetnik.zeromq.common.data.CallOutput;
import hr.podsjetnik.zeromq.common.service.CallInputService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zeromq.ZMQ;

@Controller
public class IpbxRestService {

	@RequestMapping(value = "v1/calls", method = RequestMethod.POST)
	public @ResponseBody
	CallOutput callPbx(@RequestBody CallInput call) {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.connect("tcp://localhost:5555");

		CallOutput ret = new CallOutput();
		ret.setStatus("pending");
		ret.setTimestamp("2013-01-21T11:22:34.238424");
		ret.setDnid(call.getDestination());
		ret.setCallerid(call.getSource());
		ret.setCall_ref("cmesehvaraaaa0qxf6b9fkuj7");
		
		String request;
		try {
			request = CallInputService.convertJavaToJson(call);
			requester.send(request.getBytes(), 0);
			
			byte[] reply = requester.recv(0);
			System.out.println("IpbxRestService Received " + new String(reply));
		} catch (IOException e) {
			System.out.println("Greška pri slanju");
		}	
		requester.close();
        context.term();

		return ret;
	}

	@RequestMapping(value = "v1/calls/dummy", method = RequestMethod.GET)
	public @ResponseBody
	CallOutput getShopInJSON() {
		CallOutput ret = new CallOutput();
		ret.setStatus("pending");
		ret.setTimestamp("2013-01-21T11:22:34.238424");
		ret.setDnid("38511111311");
		ret.setCallerid("3112");

		return ret;

	}
}
