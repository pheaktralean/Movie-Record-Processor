/**
 * Implementing a class called "MissingQuoteException"
 * which will be called to throw an exception when a missing quote occurs*/
public class MissingQuoteException extends Exception{

   /**
    * Implementing a parametrized constructor
    *
    * @param message       the message that will be thrown when there is an exception*/
   public MissingQuoteException(String message){
      super("Syntax error: " +message);
   }
}
