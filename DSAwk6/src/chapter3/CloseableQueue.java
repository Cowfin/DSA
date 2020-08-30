package chapter3;

/**
   A class which extends the LinkedQueue class so that its
   enqueue and dequeue methods are synchronized
   and which can be closed to prevent it from further 
   enqueues of elements
   @author Andrew Ensor
*/
import java.util.NoSuchElementException;
import java.nio.ReadOnlyBufferException;

public class CloseableQueue<E> extends LinkedQueue<E>
{
   private boolean accepting; //whether queue currently allows enqueues
   
   public CloseableQueue()
   {  super();
      accepting = true;
   }
   
   public synchronized void enqueue(E element)
      throws ReadOnlyBufferException
   {  if (accepting)
         super.enqueue(element);
      else
         throw new ReadOnlyBufferException();
   }
   
   public synchronized E dequeue() throws NoSuchElementException
   {  return super.dequeue();
   }
   
   public boolean isAccepting()
   {  return accepting;
   }
   
   public void stopAccepting()
   {  accepting = false;
   }
}
