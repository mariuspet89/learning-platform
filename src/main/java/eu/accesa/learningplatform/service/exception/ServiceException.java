package eu.accesa.learningplatform.service.exception;

public class ServiceException extends RuntimeException {

    private final String failureDescription;
    private final Exception failureException;
    
    public ServiceException(final String failureDescription) {
        this.failureDescription = failureDescription;
        this.failureException = null;
    }
    
    public ServiceException(final String failureDescription, final Exception failureException) {
        this.failureDescription = failureDescription;
        this.failureException = failureException;
    }
    
}
