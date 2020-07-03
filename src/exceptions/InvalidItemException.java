package exceptions;

public class InvalidItemException extends Exception 
{
    public InvalidItemException(String message)
    {
        super(message);
    }

    public InvalidItemException(String message, Throwable cause)
    {
        super(message,cause);
    } 
}