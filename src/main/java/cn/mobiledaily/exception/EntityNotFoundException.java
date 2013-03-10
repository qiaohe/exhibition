package cn.mobiledaily.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/9/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1029992754666580407L;
    private static final String MESSAGE_FORMAT = "entity with id:%s can not be found in %s";
    private final String entityId;
    private final Class entityClass;

    public EntityNotFoundException(String entityId, Class entityClass) {
        this.entityId = entityId;
        this.entityClass = entityClass;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, entityId, entityClass.getSimpleName());
    }
}
