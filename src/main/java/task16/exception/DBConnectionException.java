package task16.exception;

import java.sql.SQLException;

public class DBConnectionException extends SQLException {
    public static final long serialVersionUID = 1L;
    public DBConnectionException(String message) {
        super(message);
    }
    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
