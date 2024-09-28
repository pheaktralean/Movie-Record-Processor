/**
 * Implementing a class called "BadGenreException"
 * which will be called to throw an exception when bad genre occurs*/
public class BadGenreException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadGenreException(String message){
        super("Semantic error: " + message);
    }
}
