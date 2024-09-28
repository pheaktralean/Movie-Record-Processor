/**
 * Implementing a class called "BadRatingException"
 * which will be called to throw an exception when bad rating occurs*/
public class BadRatingException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadRatingException(String message){
        super("Semantic error: " + message);
    }
}
