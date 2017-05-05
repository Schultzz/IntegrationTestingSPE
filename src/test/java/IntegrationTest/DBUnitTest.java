package IntegrationTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import connector.MySQLConnector;
import entities.Customer;
import interfaces.IConnector;
import mappers.CustomerMapper;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;

/**
 * Created by ms on 04-05-17.
 */
public class DBUnitTest {

    private IConnector connector;
    private CustomerMapper customerMapper;
    private QueryDataSet databaseDataSet;
    private Connection connection;
    private MysqlDataSource dataSource;
    private IDatabaseConnection dbConnection;
    private ITable xmlTable, databaseTable;
    private IDataSet xmlDataSet;

    public DBUnitTest() {

    }

    @Before
    public void setUp() throws Exception {

        dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/shoptest");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("pwd");
        dataSource.setDatabaseName("shoptest");

        connector = new MySQLConnector(dataSource);
        customerMapper = new CustomerMapper(connector);

        try {
            connection = dataSource.getConnection();
            dbConnection = new DatabaseConnection(connection, "shoptest");
            xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("partial.xml"));
            DatabaseOperation.REFRESH.execute(dbConnection, xmlDataSet);
        } catch (Exception e) {
            System.out.println("exception " + e);
        } finally {

            //cause the connection to close to early.
            //dbConnection.close();
        }
    }

    @Test
    public void getAllUsers_allValuesGiven_returns20Rows() {

        databaseDataSet = new QueryDataSet(dbConnection);
        try {
            databaseDataSet.addTable("customer");
            databaseTable = databaseDataSet.getTable("customer");

            xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("partial.xml"));
            xmlTable = xmlDataSet.getTable("customer");

            Assertion.assertEquals(xmlTable, databaseTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewCustomer_allValuesGiven_returns21Rows() {

        customerMapper.createCustomer(new Customer("test", "222", "email@emila.dk"));

        databaseDataSet = new QueryDataSet(dbConnection);
        try {
            databaseDataSet.addTable("customer");
            databaseTable = databaseDataSet.getTable("customer");

            xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("partial.xml"));
            xmlTable = xmlDataSet.getTable("customer");

            Assertion.assertEquals(xmlTable, databaseTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCustomerById_allValuesGiven_returnsTagPad() {

        databaseDataSet = new QueryDataSet(dbConnection);
        try {

            String actualCustomerName = customerMapper.getCustomerById(1).getCustomerName();

            xmlDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("partial.xml"));
            xmlTable = xmlDataSet.getTable("customer");

            //Should be on a unique key.
            String expectedCustomerName = (String) xmlTable.getValue(0, "customerName");

            Assert.assertEquals(actualCustomerName, expectedCustomerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
