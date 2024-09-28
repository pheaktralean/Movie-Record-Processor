/**
 * Implementing a class called "BadScoreException"
 * which will be called to throw an exception when bad score occurs
 * */
public class BadScoreException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadScoreException(String message){
        super("Semantic error: " + message);
    }
}
