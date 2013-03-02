package cn.mobiledaily.exception;

public class ExistedEmailException extends Exception {
    private String email;

    public ExistedEmailException(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
