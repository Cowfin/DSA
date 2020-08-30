package chapter3;

/**
   A class which represents a binary operator
   @see Operator.java
*/

public abstract class BinaryOperator extends Operator
{
   public BinaryOperator(String text)
   {  super(text);
   }
   
   public abstract double evaluate(double op1, double op2);
}
