package exceptions;

import java.lang.Exception;

public class InvalidShopException extends Exception
{
    public InvalidShopException(String message)
    {
        super(message);
    }

    public InvalidShopException(String message, Throwable cause)
    {
        super(message,cause);
    } 
}