/**
 * Implementing a class called "BadDurationException".
 * It will be called to throw an exception when bad duration occurs
 * */
public class BadDurationException extends Exception {

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadDurationException(String message){
        super("Semantic error: " + message);
    }
}
