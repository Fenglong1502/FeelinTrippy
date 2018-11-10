/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import error.NoResultException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import session.CustomerSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class AuthenticationManagedBean implements Serializable {

    private String email = null;
    private String password = null;
    private String firstName;
    private String lastName;
    private byte gender;
    private String mobileNumber;
    private int points;

    private Long id = -1L;
    @EJB
    private CustomerSessionLocal customerSessionLocal;
    private Customer loggedInCustomer;

    public AuthenticationManagedBean() {
    }

    public String login() throws NoResultException {
        if (email.equals("admin") && password.equals("admin")) {
            id = 0L;
            return "/admin/adminIndex.xhtml?faces-redirect=true";
        } else {
            Customer u = new Customer(email, encryptPassword(password));
            if (customerSessionLocal.Login(u) == true) {
                if (customerSessionLocal.getCustomerByEmail(email).isAccountStatus() == false) {
                    return "/login.xhtml";
                } else {
                    loggedInCustomer = customerSessionLocal.getCustomerByEmail(email);
                    id = loggedInCustomer.getUserID();
                    setFirstName(loggedInCustomer.getFirstName());
                    setLastName(loggedInCustomer.getLastName());
                    setGender(loggedInCustomer.getGender());
                    setMobileNumber(loggedInCustomer.getMobileNumber());
                    setPoints(loggedInCustomer.getPoints());

                    return "/user/filterTrip.xhtml?faces-redirect=true";
                }
            } else {

                email = null;
                password = null;
                id = -1L;

                return "/login.xhtml";
            }
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String update() {
        loggedInCustomer.setFirstName(firstName);
        loggedInCustomer.setLastName(lastName);
        loggedInCustomer.setPassword(password);
        loggedInCustomer.setMobileNumber(mobileNumber);
        loggedInCustomer.setGender(gender);
        try {
            customerSessionLocal.updateCustomer(loggedInCustomer);
        } catch (Exception e) {
            return "503.jsp";
        }
        return "profile.xhtml";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerSessionLocal getCustomerSessionLocal() {
        return customerSessionLocal;
    }

    public void setCustomerSessionLocal(CustomerSessionLocal customerSessionLocal) {
        this.customerSessionLocal = customerSessionLocal;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
