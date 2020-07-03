package exceptions;

public class FileErrorException extends Exception
{
    
    public FileErrorException(String message)
    {
        super(message);
    }

    public FileErrorException(String message, Throwable cause)
    {
        super(message,cause);
    } 
}
    