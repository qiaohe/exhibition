package cn.mobiledaily.exception;

public class UsernameExistsException extends Exception {
    private String username;

    public UsernameExistsException(String username) {
        super(username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
