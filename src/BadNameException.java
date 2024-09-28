/**
 * Implementing a class called "BadNameException"
 * which will be called to throw an exception when bad name occurs*/
public class BadNameException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadNameException(String message){
        super("Semantic error: " + message);
    }
}
