package interfaces;

import java.sql.Connection;

/**
 * Created by ms on 03.05.17.
 */
public interface IConnector {
    Connection getConnection();
    void open();
    void close();
}
