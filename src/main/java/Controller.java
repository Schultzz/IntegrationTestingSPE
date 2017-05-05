import com.mysql.cj.jdbc.MysqlDataSource;
import connector.MySQLConnector;
import entities.Customer;
import interfaces.IConnector;
import mappers.CustomerMapper;

import java.util.List;

/**
 * Created by ms on 03-05-17.
 */
public class Controller {

    private IConnector connector;
    private CustomerMapper customerMapper;

    public Controller() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setURL("jdbc:mysql://localhost:3306/classicmodels");
        datasource.setUser("root");
        datasource.setPassword("pwd");

        connector = new MySQLConnector(datasource);
        customerMapper = new CustomerMapper(connector);
    }

    public List<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers();
    }

    public Customer createCustomer(Customer customer){return customerMapper.createCustomer(customer);}

    public Customer getCustomerById(int id) {
        return customerMapper.getCustomerById(id);
    }

    public boolean deleteCustomerById(int id){
        return customerMapper.deleteCustomerById(id);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

        //System.out.println(controller.deleteCustomerById(103));

       System.out.println(controller.getCustomerById(103));

    }
}
