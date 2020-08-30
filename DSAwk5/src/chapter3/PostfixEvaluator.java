package chapter3;

/**
   A class which demonstrates how a stack is used to
   parse a postfix expression
   @author Andrew Ensor
*/
import java.util.Iterator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner; // Java 1.5 equivalent of cs1.Keyboard
import java.util.Set;
import java.util.StringTokenizer;

public class PostfixEvaluator
{
   private Set<Operator> operators;
   
   public PostfixEvaluator()
   {  // create the valid operators for this evaluator
      operators = new HashSet<Operator>();
      Operator addition = new BinaryOperator("+")
         {  public double evaluate(double op1, double op2)
            {  return op1+op2;
            }
         };
      Operator subtraction = new BinaryOperator("-")
         {  public double evaluate(double op1, double op2)
            {  return op1-op2;
            }
         };
      Operator multiplication = new BinaryOperator("*")
         {  public double evaluate(double op1, double op2)
            {  return op1*op2;
            }
         };
      Operator division = new BinaryOperator("/")
         {  public double evaluate(double op1, double op2)
            {  return op1/op2;
            }
         };
      Operator power = new BinaryOperator("^")
         {  public double evaluate(double op1, double op2)
            {  return Math.pow(op1, op2);
            }
         };
      Operator inverse = new UnaryOperator("inv")
         {  public double evaluate(double op)
            {  return 1.0/op;
            }
         };
      operators.add(addition);
      operators.add(subtraction);
      operators.add(multiplication);
      operators.add(division);
      operators.add(power);
      operators.add(inverse);
   }
   
   // parse the string expression which is presumed to 
   // be an expression in postfix notation with double value
   // operands and string operators all separated by spaces
   public double parse(String expression)
      throws NoSuchElementException, NumberFormatException
   {  StringTokenizer tokenizer = new StringTokenizer(expression);
      StackADT<Double> operandStack = new ArrayStack<Double>();
      while (tokenizer.hasMoreTokens())
      {  String token = tokenizer.nextToken();
         // check whether token is the text for an operator
         Operator operator = getOperator(token);
         if (operator instanceof UnaryOperator)
         {
            double op = operandStack.pop().doubleValue();
            double result = ((UnaryOperator)operator).evaluate(op);
            operandStack.push(new Double(result));
         }
         else if (operator instanceof BinaryOperator)
         {  double op2 = operandStack.pop().doubleValue();
            double op1 = operandStack.pop().doubleValue();
            double result
               = ((BinaryOperator)operator).evaluate(op1, op2);
            operandStack.push(new Double(result));
         }
         else if (operator == null)
         {  // try to push a double value onto stack
            operandStack.push(new Double(Double.parseDouble(token)));
         }
      }
      return operandStack.pop().doubleValue();
   }
   
   // helper method which searches through the operators set for an
   // operator with the specified text and returns null if none found
   private Operator getOperator(String text)
   {  Iterator<Operator> iterator = operators.iterator();
      while (iterator.hasNext())
      {  Operator nextOperator = iterator.next();
         if (nextOperator.getText().equalsIgnoreCase(text))
            return nextOperator;
      }
      return null; // no operator found for this text      
   }
   
   public static void main(String[] args)
   {  // obtain postfix expression from keyboard
      Scanner keyboardScanner = new Scanner(System.in);
      System.out.print("Please enter a postfix expression:");
      String expression = keyboardScanner.nextLine();
      PostfixEvaluator evaluator = new PostfixEvaluator();
      try
      {  double result = evaluator.parse(expression);
         System.out.println("The result is " + result);
      }
      catch (NoSuchElementException e)
      {  System.out.println("Insufficient operands: "+e.getMessage());
      }
      catch (NumberFormatException e)
      {  System.out.println("Illegal value: " + e.getMessage());
      }
   }
}
