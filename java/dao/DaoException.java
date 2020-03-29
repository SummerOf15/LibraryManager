package dao;

import java.sql.SQLException;

public class DaoException extends SQLException {
    public DaoException() {
        super();
    }

    public DaoException(String reason) {
        super(reason);
        System.out.println(reason);
    }
}
