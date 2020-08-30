package chapter3;

/**
   A class which represents a producer of some items
   that it enqueues in a queue
   @see CloseableQueue.java, Item.java
*/

public class ItemProducer implements Runnable
{
   private CloseableQueue<Item> queue;
   private int idNumber;
   private final int PRODUCTION_QUANTITY = 10; // num items to produce
   private final int PRODUCTION_TIME = 150; // time to produce an item
   
   public ItemProducer(CloseableQueue<Item> queue)
   {  this.queue = queue;
      idNumber = 0;
   }
   
   // produce an item that takes time PRODUCTION_TIME
   private Item produceItem()
   {  Item item = new Item("ID number: " + idNumber);
      try
      {  Thread.sleep(PRODUCTION_TIME);
      }
      catch (InterruptedException e)
      {}
      idNumber++;
      return item;
   }
   
   public void run()
   {  System.out.println("Producer starting");
      for (int i=0; i<PRODUCTION_QUANTITY; i++)
      {  Item item = produceItem();
         System.out.println("Item " + item + " produced");
         // put item in queue
         queue.enqueue(item);
      }
      queue.stopAccepting();
      System.out.println("Producer stopping");
   }
}
