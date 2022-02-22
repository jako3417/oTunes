package dk.qitsuk.otunes.dataaccess.dataaccessobjects;

import dk.qitsuk.otunes.dataaccess.connector.SQLiteDBConnector;
import dk.qitsuk.otunes.dataaccess.models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO<AddCustomer> {
    private ArrayList<Customer> customers;

    public ArrayList<Customer> getAllCustomers() {
        customers = new ArrayList<>();
        String sql = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer";
        try {
            Connection conn = SQLiteDBConnector.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CustomerId"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")
                ));
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            System.exit(-1);
        }
        return customers;
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE " +
                "CustomerId=?";
        try {
            Connection conn = SQLiteDBConnector.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerId"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")
                );
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            System.exit(-1);
        }
        return customer;
    }

   /* public Customer addCustomer() {
        Customer customer = null;
        String sql = "INSERT INTO Customer(FirstName, LastName, Country, PostalCode, Phone, Email) VALUES ('Kim', 'Tim', 'Denmark', '5600', '59547821', 'KimTim@live.dk')";

        try {
            Connection conn = SQLiteDBConnector.getInstance().getConnection();
            PreparedStatement stmt = conn.createStatement(sql);
            System.out.println("Inserting new customer into the customer table");
            stmt.executeUpdate(sql);


        } catch (SQLException sqe) {
            sqe.printStackTrace();
            System.exit(-1);
        }
    */
   public Customer updateCustomerById(Customer customer, int id) {
       String sql = "UPDATE Customer set firstName=?, lastName=?, country=?, postalcode=?, phoneNumber=?, email=? WHERE" + "CustomerId=? ";
       try {
           Connection conn = SQLiteDBConnector.getInstance().getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, customer.getFirstName());
           ps.setString(2, customer.getLastName());
           ps.setString(3, customer.getCountry());
           ps.setString(4, customer.getPostalCode());
           ps.setString(5, customer.getPhoneNumber());
           ps.setString(6, customer.getEmail());
           ps.setInt(7, id);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
               customer = new Customer(
                       rs.getInt("CustomerId"),
                       rs.getString("FirstName"),
                       rs.getString("LastName"),
                       rs.getString("Country"),
                       rs.getString("PostalCode"),
                       rs.getString("Phone"),
                       rs.getString("Email")
               );
           }
       } catch (SQLException sqe) {
           sqe.printStackTrace();
           System.exit(-1);
       }
       return customer;
   }

    public Customer numCustomerCountry(int id) {
        Customer customer = null;
        String sql = "SELECT COUNT(CustomerId), Country\n" +
                "FROM Customer\n" +
                "GROUP by Country\n" +
                "ORDER BY COUNT(?) DESC";
        try {
            Connection conn = SQLiteDBConnector.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerId"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")
                );
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            System.exit(-1);
        }
        return customer;
    }
}
