package chapter3;

/**
  An abstract class which represents an Operator
  @see PostfixEvaluator.java
*/

public abstract class Operator
{
   private String text; // text representation of operator
   
   public Operator(String text)
   {  if (text == null)
         this.text = "unknown";
      else
         this.text = text;
   }
   
   public final String getText()
   {  return text;
   }
}
