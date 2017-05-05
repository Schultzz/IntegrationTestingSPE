package entities;

/**
 * Created by ms on 03-05-17.
 */
public class Customer {

    private int id;
    private String customerName;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(String customerName, String phone, String email) {
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
