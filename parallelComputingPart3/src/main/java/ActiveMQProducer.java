import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQProducer {
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Queue queue;
    private MessageProducer messageProducer;

    public ActiveMQProducer(String queueName) {
        try {
            connectionFactory = new ActiveMQConnectionFactory(Config.SERVER_CONNECTION);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            queue = session.createQueue(queueName);
            messageProducer = session.createProducer(queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void sendArrayPart(ArrayPart arrayPart){
        try {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(arrayPart);
            messageProducer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
