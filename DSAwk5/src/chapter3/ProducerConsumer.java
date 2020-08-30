package chapter3;

/**
   A class which starts a producer and some consumers of items
   @author Andrew Ensor
*/

public class ProducerConsumer
{
   private static final int NUM_CONSUMERS = 3;
   
   public static void main(String[] args)
   {  CloseableQueue<Item> queue = new CloseableQueue<Item>();
      // create the producer and consumers
      ItemProducer producer = new ItemProducer(queue);
      ItemConsumer[] consumers = new ItemConsumer[NUM_CONSUMERS];
      for (int i=0; i<NUM_CONSUMERS; i++)
         consumers[i] = new ItemConsumer(queue);
      // create the threads for producer and each consumer
      Thread producerThread = new Thread(producer);
      Thread[] consumerThreads = new Thread[NUM_CONSUMERS];
      for (int i=0; i<NUM_CONSUMERS; i++)
         consumerThreads[i] = new Thread(consumers[i]);
      // start all the threads
      producerThread.start();
      for (int i=0; i<NUM_CONSUMERS; i++)
         consumerThreads[i].start();
   }
}
