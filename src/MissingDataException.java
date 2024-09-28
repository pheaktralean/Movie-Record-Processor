/**
 * Implementing a class called "MissingDataException"
 * which will be called to throw an exception when a data is missing*/
public class MissingDataException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public MissingDataException(String message){
        super("Semantic error: " + message);
    }
}
