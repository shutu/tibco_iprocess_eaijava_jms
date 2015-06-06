package com.tibco.iprocess.eaijava.jms;

import com.tibco.iprocess.eaijava.jms.domain.ServiceRequest;
import com.tibco.iprocess.eaijava.jms.domain.ServiceResponse;

public class TestJmsSender {

	public static void main(String[] args) throws Exception {
		ServiceRequest request = new ServiceRequest();
		request.setRequestType("TEST");
		request.setProcessCode("STEP");
		request.setNeedUpdateStep("0");
		request.setProdCode("111111");
		request.setAppNum("BO0000001");
		request.setExtValue1("test");

		ServiceResponse response = JmsSender.sendJms("bpm.iprocess.service", request);

		System.out.println(response.getFormatMessageString());

	}
}
