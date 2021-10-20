import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ConsumerApplication {
    static ArrayPart arrayPartToSend;
    public static void main(String[] args) {
        ActiveMQConsumer consumer = new ActiveMQConsumer(Config.QUEUE_NAME_TO_CONSUMER);
        MessageListener listner = new MessageListener() {
            public void onMessage(Message message) {
                handleMessage(message);
                ActiveMQProducer producer = new ActiveMQProducer(Config.QUEUE_NAME_TO_PRODUCER);
                producer.sendArrayPart(arrayPartToSend);
            }
        };
        consumer.setMessageListener(listner);
    }

    private static void handleMessage(Message message) {
        Object object;
        try {
            object = ((ObjectMessage) message).getObject();
            ArrayPart request = (ArrayPart) object;

            Integer[] readArray = request.getReadArray();
            int firstIndex = request.getFirstIndex();
            int lastIndex = request.getLastIndex();


            for (int j = firstIndex; j < lastIndex; j++) {
                int currentItem = readArray[j];
                int currentPosition = 0;
                for (int i = 0; i < readArray.length; i++) {
                    if (currentItem > readArray[i]) {
                        currentPosition++;
                    }
                    if ((currentItem == readArray[i]) && (j < i)) {
                        currentPosition++;
                    }
                }
                request.addToWriteMap(currentPosition, currentItem);
            }
            arrayPartToSend = request;
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
