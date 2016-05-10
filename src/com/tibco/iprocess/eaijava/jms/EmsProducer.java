/**
 * Created By: Comwave Project Team Created Date: 2015年4月20日
 */
package com.tibco.iprocess.eaijava.jms;

/**
 * @author Geln Yang
 * @version 1.0
 */
import java.net.InetAddress;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;

import com.tibco.tibjms.TibjmsQueueConnectionFactory;
import com.tibco.tibjms.TibjmsTopicConnectionFactory;

/**
 * 
 * @author gelnyang
 * 
 */
public class EmsProducer {

  private static final int TIMEOUT_DEADLINE = 300 * 1000; // 300 seconds

  private static final int DEFAULT_PRIORITY = 5;

  private String serverUrl;

  private String userName;

  private String password;

  private String destinationName;

  private boolean transacted = false;

  private int ackMode = Session.AUTO_ACKNOWLEDGE;

  private boolean isTopic = false;

  private Connection connection;

  private Session session;

  private MessageProducer producer;

  private static String hostName = "";
  static {
    try {
      hostName = InetAddress.getLocalHost().getHostName();
      hostName = hostName.replaceAll("\\W", "");
    } catch (Exception e) {
    }
  }

  /**
   * @param serverUrl
   * @param userName
   * @param password
   * @param destinationName
   */
  public EmsProducer(String serverUrl, String userName, String password, String destinationName) {
    super();
    this.serverUrl = serverUrl;
    this.userName = userName;
    this.password = password;
    this.destinationName = destinationName;
  }

  /**
   * @param serverUrl
   * @param userName
   * @param password
   * @param destinationName
   * @param transacted
   * @param ackMode
   * @param isTopic
   */
  public EmsProducer(String serverUrl, String userName, String password, String destinationName,
      boolean transacted, int ackMode, boolean isTopic) {
    this(serverUrl, userName, password, destinationName);
    this.transacted = transacted;
    this.ackMode = ackMode;
    this.isTopic = isTopic;
  }

  public synchronized String send(String content) throws JMSException, InterruptedException {
    return send(null, content, TIMEOUT_DEADLINE);
  }

  public synchronized String send(String correlationId, String content, int timeout)
      throws JMSException, InterruptedException {
    initalSession();

    // Now create the actual message you want to send
    TextMessage txtMessage = session.createTextMessage();
    txtMessage.setText(content);

    Message responseMessage = sendMessage(correlationId, txtMessage, timeout);

    String responseText = null;
    if (responseMessage instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) responseMessage;
      responseText = textMessage.getText();
    } else {
      responseText = responseMessage.toString();
    }
    return responseText;
  }

  public synchronized Message sendBytes(String correlationId, byte[] bytes,
      Map<String, Object> properties, int timeout) throws JMSException, InterruptedException {
    initalSession();
    // Now create the actual message you want to send
    BytesMessage bytesMessage = session.createBytesMessage();
    bytesMessage.writeBytes(bytes);

    if (properties != null) {
      Set<String> keySet = properties.keySet();
      for (String key : keySet) {
        Object value = properties.get(key);
        bytesMessage.setObjectProperty(key, value);
      }
    }

    return sendMessage(correlationId, bytesMessage, timeout);
  }

  private synchronized Message sendMessage(String correlationId, Message message, int timeout)
      throws JMSException {
    try {
      /*
       * Create a temporary queue that this client will listen for responses on then create a
       * consumer that consumes message from this temporary queue...for a real application a client
       * should reuse the same temp queue for each message to the server...one temp queue per client
       */
      TemporaryQueue tempDest = session.createTemporaryQueue();
      /*
       * Set the reply to field to the temp queue you created above, this is the queue the server
       * will respond to
       */
      message.setJMSReplyTo(tempDest);

      /*
       * Set a correlation ID so when you get a response you know which sent message the response is
       * for If there is never more than one outstanding message to the server then the same
       * correlation ID can be used for all the messages...if there is more than one outstanding
       * message to the server you would presumably want to associate the correlation ID with this
       * message somehow...a Map works good
       */
      if (correlationId == null) {
        correlationId = createRandomString();
      }
      message.setJMSCorrelationID(correlationId);
      long timeToLive = timeout / 5;
      producer.send(message, DeliveryMode.PERSISTENT, DEFAULT_PRIORITY, timeToLive);

      MessageConsumer responseConsumer = session.createConsumer(tempDest);
      Message responseMessage = responseConsumer.receive(timeout);
      if (responseMessage == null) {
        throw new JMSException("receive response timeout(" + timeout + ")!");
      }
      return responseMessage;
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
      }
    }
  }

  private void initalSession() throws JMSException {
    Destination dest;
    if (isTopic) {
      connection =
          new TibjmsTopicConnectionFactory(serverUrl).createTopicConnection(userName, password);
      session = ((TopicConnection) connection).createTopicSession(transacted, ackMode);
      dest = session.createTopic(destinationName);
    } else {
      connection =
          new TibjmsQueueConnectionFactory(serverUrl).createQueueConnection(userName, password);
      session = ((QueueConnection) connection).createQueueSession(transacted, ackMode);
      dest = session.createQueue(destinationName);
    }
    connection.start();

    /*
     * Setup a message producer to send message to the queue the server is consuming from
     */
    producer = session.createProducer(dest);
    producer.setDeliveryMode(DeliveryMode.PERSISTENT);
  }

  private synchronized String createRandomString() {
    long currentTimeMillis = System.currentTimeMillis();
    Random random = new Random(currentTimeMillis);
    long randomLong = random.nextLong();
    return hostName + Thread.currentThread().getId() + Long.toHexString(currentTimeMillis)
        + Long.toHexString(randomLong);
  }

}
