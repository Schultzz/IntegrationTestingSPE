package mappers;

import entities.Customer;
import interfaces.IConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ms on 03-05-17.
 */
public class CustomerMapper {

    private IConnector connector;

    public CustomerMapper(IConnector connector) {
        this.connector = connector;
    }

    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList();

        try {
            connector.open();

            String sql = "select * from customers";
            PreparedStatement pstmt = connector.getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customerNumber"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }

            rs.close();
            pstmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close();
        }

        return customers;
    }


    public Customer getCustomerById(int id) {

        Customer customer = new Customer();

        try {
            connector.open();

            String sql = "select * from customer WHERE id = ?";
            PreparedStatement pstmt = connector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
            }

            rs.close();
            pstmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close();
        }

        return customer;
    }

    public boolean deleteCustomerById(int id) {

        boolean deleted = false;

        try {
            connector.open();

            String sql = "delete from customer WHERE id = ?";
            PreparedStatement pstmt = connector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();

            deleted = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close();
        }
        return deleted;
    }

    public Customer createCustomer(Customer customer) {


        try {
            connector.open();

            String sql = "INSERT INTO customer (id, customerName, phone, email) VALUES (?,?,?,?)";
            PreparedStatement pstmt = connector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getCustomerName());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            customer = null;
            ex.printStackTrace();
        } finally {
            connector.close();
        }
        return customer;
    }
}
