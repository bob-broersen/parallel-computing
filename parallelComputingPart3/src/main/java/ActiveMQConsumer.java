import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQConsumer {
    private MessageConsumer consumer;
    private Session session;
    private Queue queue;
    private Connection connection;

    public ActiveMQConsumer(String queueName) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Config.SERVER_CONNECTION);
        connectionFactory.setTrustAllPackages(true);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            queue = session.createQueue(queueName);
            consumer = session.createConsumer(queue);


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setMessageListener(MessageListener listener) {
        try {
            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
