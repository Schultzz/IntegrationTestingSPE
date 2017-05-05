package connector;

import interfaces.IConnector;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ms on 03.05.17.
 */
public class MySQLConnector implements IConnector {

    private Connection connection;
    private DataSource dataSource;

    public MySQLConnector(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void open() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
