import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.HashMap;
import java.util.Scanner;

public class ProducerApplication {
    private static Integer[] mergedArray;

    public static void main(String[] args) throws JMSException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter amount of elements: ");
        int elementCount = scan.nextInt();
        System.out.print("Enter amount of consumers: ");
        int blocks = scan.nextInt();
        scan.close();

        Integer[] totalValues = createUnsortedArray(elementCount);
        mergedArray = new Integer[elementCount];

        final long start = System.currentTimeMillis();

        final ActiveMQProducer producer = new ActiveMQProducer(Config.QUEUE_NAME_TO_CONSUMER);
        int blockSize = (int) Math.ceil(totalValues.length / blocks);
        int index;
        for (index = 0; index < totalValues.length; index += blockSize) {
            int lastIndex = index+blockSize;
            HashMap<Integer, Integer> writeMap = new HashMap<Integer, Integer>();
            ArrayPart arrayPart = new ArrayPart(totalValues, writeMap, index, lastIndex);
            producer.sendArrayPart(arrayPart);
        }
        producer.close();

        final ActiveMQConsumer consumer = new ActiveMQConsumer(Config.QUEUE_NAME_TO_PRODUCER);
        MessageListener listner = new MessageListener() {
            public void onMessage(Message message) {
                handleMessage(message);
                if(isSorted()) {
                    long elapsedTimeMillis = System.currentTimeMillis() - start;
                    System.out.println("Time to peform algorithm: " + elapsedTimeMillis);
                    consumer.close();
                }
            }
        };
        consumer.setMessageListener(listner);


    }

    private static boolean isSorted() {
        for (int i = 0; i < mergedArray.length - 1; i++) {
            if ( mergedArray[i] == null | mergedArray[i + 1] == null ){
                return false;
            }
            else if ( mergedArray[i] > mergedArray[i + 1]) {
                return false;
            }
        }

        return true;
    }

    private static void handleMessage(Message message) {
        Object object;
        try {
            object = ((ObjectMessage) message).getObject();
            ArrayPart request = (ArrayPart) object;
            mergeArray(request.getWriteMap());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static Integer[] createUnsortedArray(int elementCount)  {
        Integer[] resultArray = new Integer[elementCount];
        for (int i = 0; i < elementCount; i++) {
            resultArray[i] = elementCount % (i + 3);
        }
        return resultArray;
    }

    public static void mergeArray(HashMap<Integer, Integer> map) {
        for (java.util.Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            boolean hasMerged = false;
            do {
                if (mergedArray[key] == null){
                    mergedArray[key] = value;
                    hasMerged = true;
                } else {
                    key++;
                }
            } while (!hasMerged);
        }
    }
}
