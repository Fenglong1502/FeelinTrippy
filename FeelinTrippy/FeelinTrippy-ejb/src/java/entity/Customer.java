/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean accountStatus;
    private byte gender;
    private String mobileNumber;
    private String email;
    private int points;
    private boolean isAdmin;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    private ArrayList<SavedTrip> savedTrips;
    private ArrayList<BookedActivity> bookedActivities;

    /**
     * @return the firstName
     */
    public Customer() {

    }

    public Customer(String username, String password, String firstName, String lastName, boolean accountStatus, byte gender, String mobileNumber, String email, int points, boolean isAdmin) {
        this();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountStatus = accountStatus;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.points = points;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;   
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the savedTrips
     */
    public ArrayList<SavedTrip> getSavedTrips() {
        return savedTrips;
    }

    /**
     * @param savedTrips the savedTrips to set
     */
    public void setSavedTrips(ArrayList<SavedTrip> savedTrips) {
        this.savedTrips = savedTrips;
    }

    /**
     * @return the bookedActivities
     */
    public ArrayList<BookedActivity> getBookedActivities() {
        return bookedActivities;
    }

    /**
     * @param bookedActivities the bookedActivities to set
     */
    public void setBookedActivities(ArrayList<BookedActivity> bookedActivities) {
        this.bookedActivities = bookedActivities;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserId(Long userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + userID + " ]";
    }

    /**
     * @return the gender
     */
    public byte getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(byte gender) {
        this.gender = gender;
    }

    /**
     * @return the accountStatus
     */
    public boolean isAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus the accountStatus to set
     */
    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

}
