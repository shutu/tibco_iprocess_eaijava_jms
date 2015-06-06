/**
 * Created By: Comwave Project Team Created Date: 2015年6月5日
 */
package com.tibco.iprocess.eaijava.jms;

import java.io.InputStream;
import java.util.Properties;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tibco.iprocess.eaijava.jms.domain.ServiceRequest;
import com.tibco.iprocess.eaijava.jms.domain.ServiceResponse;

/**
 * @author Geln Yang
 * @version 1.0
 */
public class JmsSender {

	private static final Log logger = LogFactory.getLog(JmsSender.class);

	private static String serverUrl;

	private static String userName;

	private static String password;

	static {
		try {
			InputStream is = JmsSender.class.getResourceAsStream("/iprocess_eaijava.properties");
			Properties properties = new Properties();
			properties.load(is);
			serverUrl = properties.getProperty("eaijava.jms.url");
			userName = properties.getProperty("eaijava.jms.user");
			password = properties.getProperty("eaijava.jms.password");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ServiceResponse sendJms(String queue, ServiceRequest request) throws Exception {
		String messageString = request.getFormatMessageString();
		logger.info(queue + " => " + messageString);
		String responseValue = sendJms(queue, messageString);
		logger.info(responseValue);
		ServiceResponse response = new ServiceResponse();
		response.initMessageFromFormatString(responseValue);
		return response;
	}

	private static String sendJms(String queue, String requestValue) throws JMSException, InterruptedException {
		EmsProducer producer = new EmsProducer(serverUrl, userName, password, queue);
		return producer.send(requestValue);
	}
}
