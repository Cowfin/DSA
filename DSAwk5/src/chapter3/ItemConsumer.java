package chapter3;

/**
   A class which represents a consumer of some items
   that it dequeues from an item queue
   @see CloseableQueue.java, Item.java
*/
import java.util.NoSuchElementException;
import java.util.Random;

public class ItemConsumer implements Runnable
{
   private CloseableQueue<Item> queue;
   private Random generator;
   private String consumerName; // letter starting at A
   private static int numConsumers = 0; // total number of consumers
   private final int MAX_CONSUMPTION_TIME = 1000;
   
   public ItemConsumer(CloseableQueue<Item> queue)
   {  this.queue = queue;
      generator = new Random();
      consumerName = ""+(char)('A'+numConsumers);
      numConsumers++;
   }
   
   // consume an item that takes a random length of time
   private void consumeItem(Item item)
   {  // determine how long this item takes to consume
      int duration = generator.nextInt(MAX_CONSUMPTION_TIME);
      try
      {  Thread.sleep(duration);
      }
      catch (InterruptedException e)
      {}
   }
   
   public void run()
   {  System.out.println("Consumer " + consumerName + " starting");
      // continue while either the queue is still accepting items
      // or there are still items in the queue to consume
      while (queue.isAccepting() || queue.size()>0)
      {  if (queue.size()>0)
         {  // attempt to get the item at front of queue
            try
            {  Item item = queue.dequeue();
               System.out.println("Item " + item
                  + " consumed by consumer " + consumerName);
               consumeItem(item);
            }
            catch (NoSuchElementException e)
            {  // just in case the item is no longer in the queue
               System.out.println
                  ("Element dequeued by another consumer");
            } 
         }
         else
         {  // sleep for 100ms so  another item has chance to appear
            try
            {  Thread.sleep(100); // sleep 100ms
            }
            catch (InterruptedException e)
            {}
         }
      }
      System.out.println("Consumer " + consumerName + " stopping");
   }
}
