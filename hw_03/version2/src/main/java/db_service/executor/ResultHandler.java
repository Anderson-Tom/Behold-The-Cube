package db_service.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */

// functional interface
public interface ResultHandler<T> {
    T handle(ResultSet set) throws SQLException;

}
