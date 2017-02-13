package loader;

/**
 * Thrown when there is an issue with an XML file
 */
public class XMLException extends RuntimeException {
	

	/**
	 * set ID to default value
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Create an exception based on a caught exception, with no additional message.
     */
	public XMLException (Throwable cause) {
        super(cause);
    }
	
	/**
     * Create an exception based on an issue in our code.
     */
    public XMLException (String message, Object ... values) {
        super(String.format(message, values));
    }

}