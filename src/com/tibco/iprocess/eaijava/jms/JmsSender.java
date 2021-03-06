/**
 * Created By: Comwave Project Team Created Date: 2015年6月5日
 */
package com.tibco.iprocess.eaijava.jms;

import java.io.InputStream;
import java.util.Properties;

import javax.jms.JMSException;

import org.apache.log4j.Logger;

import com.tibco.iprocess.eaijava.jms.domain.ServiceRequest;
import com.tibco.iprocess.eaijava.jms.domain.ServiceResponse;

/**
 * @author Geln Yang
 * @version 1.0
 */
public class JmsSender {

  private static final Logger logger = Logger.getLogger(JmsSender.class);

  private static String serverUrl = "tcp://ipejmsserver1:7222,tcp://ipejmsserver2:7222";

  private static String userName = "admin";

  private static String password = "";

  static {
    try {
      InputStream is = JmsSender.class.getResourceAsStream("/iprocess_eaijava.properties");
      Properties properties = new Properties();
      properties.load(is);
      String url = properties.getProperty("eaijava.jms.url");
      if (url != null) {
        serverUrl = url;
      }
      String user = properties.getProperty("eaijava.jms.user");
      if (user != null) {
        userName = user;
      }
      String pass = properties.getProperty("eaijava.jms.password");
      if (pass != null) {
        password = pass;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static ServiceResponse sendJms(String queue, ServiceRequest request) throws Exception {
    String messageString = request.getFormatMessageString();
    logger.info(queue + " => " + messageString);
    String responseValue = "S";
    ServiceResponse response = new ServiceResponse();
    try {
      responseValue = sendJms(queue, messageString);
    } catch (Exception e) {
      String message = e.getMessage();
      logger.error("request:" + messageString + "\r\n error:" + message, e);
      response.setReturnCode(ServiceResponse.RETURNCODE_FAILED);
      if (message.length() > 50) {
        message = message.substring(0, 50);
      }
      response.setReturnMsg(message);
      return response;
    }
    logger.info(responseValue);
    response.initMessageFromFormatString(responseValue);
    return response;
  }

  private static String sendJms(String queue, String requestValue) throws JMSException,
      InterruptedException {
    EmsProducer producer = new EmsProducer(serverUrl, userName, password, queue);
    return producer.send(requestValue);
  }
}
