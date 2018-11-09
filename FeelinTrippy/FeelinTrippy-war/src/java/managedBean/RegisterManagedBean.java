/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import session.CustomerSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@RequestScoped
public class RegisterManagedBean {

    @EJB
    private CustomerSessionLocal customerSessionLocal;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private byte gender;
    private String mobileNumber;
    private String email;

    public String register() {
        Customer c = new Customer();
        c.setUsername(username);
        c.setPassword(password);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setGender(gender);
        c.setMobileNumber(mobileNumber);
        c.setEmail(email);
        c.setAccountStatus(true);
        c.setIsAdmin(false);
        c.setDateCreated(new Date());
        c.setPoints(0);
   
        
        customerSessionLocal.createCustomer(c);
        return "/login.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Creates a new instance of RegisterManagedBean
     */
    public RegisterManagedBean() {
    }

}
