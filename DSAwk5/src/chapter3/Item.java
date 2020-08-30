package chapter3;

/**
   A class which represents an item which can be produced,
   placed in an item queue, and consumed
   @see Producer.java, ItemQueue.java, Consumer.java
*/

public class Item
{
   private String name;
   
   public Item(String name)
   {  this.name = name;
   }
   
   public String toString()
   {  return name;
   }
}
