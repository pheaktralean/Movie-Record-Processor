/** Implementing a class called "MissingFieldsException"
which will be called to throw an exception when a field is missing*/
public class MissingFieldsException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public MissingFieldsException(String message){
        super("Syntax error: " +message);
    }
}
