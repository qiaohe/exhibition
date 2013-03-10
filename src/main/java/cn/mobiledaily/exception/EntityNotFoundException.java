package cn.mobiledaily.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/9/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public final class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1029992754666580407L;
    public static final String EXHIBITION_ERROR_FORMAT = "exhibitionCode: %scan not be found";
    public static final String ATTENDEE_ERROR_FORMAT = "attendeeId: %d can not be found";

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
