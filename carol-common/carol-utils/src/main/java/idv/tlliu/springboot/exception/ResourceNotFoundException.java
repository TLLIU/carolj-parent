package idv.tlliu.springboot.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1212439197068161287L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
