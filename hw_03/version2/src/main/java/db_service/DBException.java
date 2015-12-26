package db_service;

import java.sql.SQLException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */
public class DBException extends Exception {
    public DBException(Throwable cause) {
        super(cause);
    }
}
