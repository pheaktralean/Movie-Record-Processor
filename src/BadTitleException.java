/**
 * Implementing a class called "BadTitleException".
 * which will be called to throw an exception when bad title occurs.
 * */
public class BadTitleException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadTitleException(String message){
        super("Semantic error: " + message);
    }
}
