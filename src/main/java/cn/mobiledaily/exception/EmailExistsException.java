package cn.mobiledaily.exception;

public class EmailExistsException extends Exception {
    private String email;

    public EmailExistsException(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
