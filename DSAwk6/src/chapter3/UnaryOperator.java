package chapter3;

/**
   A class which represents a unary operator
   @see Operator.java
*/

public abstract class UnaryOperator extends Operator
{
   public UnaryOperator(String text)
   {  super(text);
   }
   
   public abstract double evaluate(double op);
}
