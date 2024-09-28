/**
 * Implementing a class called "BadYearException"
 * which will be called to throw an exception when bad year occurs*/
public class BadYearException extends Exception{

    /**
     * Implementing a parametrized constructor
     *
     * @param message       the message that will be thrown when there is an exception*/
    public BadYearException(String message){
        super("Semantic error: " + message);
    }
}
