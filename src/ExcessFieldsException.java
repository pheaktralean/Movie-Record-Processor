/**
 * Implementing a class called "ExcessFieldsException"
 * which will be called to throw an exception when excess fields occurs*/
public class ExcessFieldsException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public ExcessFieldsException(String message){
        super("Syntax error: " +message);
    }
}
