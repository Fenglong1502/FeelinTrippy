/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.CustomerAddBookedActivityException;
import error.CustomerAddSavedTripException;
import error.CustomerRemoveSavedTripException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "customer")
    private List<SavedTrip> savedTrips;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookedBy")
    private List<BookedActivity> bookedActivities;

    /**
     * @return the firstName
     */
    public Customer() {

    }

    public Customer(String email, String password) {
        this();
        this.email = email;
        this.password = password;
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
        dateCreated = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void addSavedTrip(SavedTrip savedTrip) throws CustomerAddSavedTripException {
        if (savedTrip != null && !this.getSavedTrips().contains(savedTrip)) {
            this.getSavedTrips().add(savedTrip);
        } else {
            throw new CustomerAddSavedTripException("SavedTrip already added to Customer");
        }
    }
    
    public void addBookedActivity(BookedActivity bookedActivity) throws CustomerAddBookedActivityException {
        if (bookedActivity != null && !this.getBookedActivities().contains(bookedActivity)) {
            this.getBookedActivities().add(bookedActivity);
        } else {
            throw new CustomerAddBookedActivityException("BookedActivity already added to Customer");
        }
    }

    public void removeAppointment(SavedTrip savedTrip) throws CustomerRemoveSavedTripException {
        if (savedTrip != null && this.getSavedTrips().contains(savedTrip)) {
            this.getSavedTrips().remove(savedTrip);
        } else {
            throw new CustomerRemoveSavedTripException("SavedTrip has not been added to Customer");
        }
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

    public Long getUserID() {
        return userID;
    }

    public void setUserId(Long userID) {
        this.userID = userID;
    }

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

    public List<SavedTrip> getSavedTrips() {
        return savedTrips;
    }

    public void setSavedTrips(List<SavedTrip> savedTrips) {
        this.savedTrips = savedTrips;
    }

    public List<BookedActivity> getBookedActivities() {
        return bookedActivities;
    }

    public void setBookedActivities(List<BookedActivity> bookedActivities) {
        this.bookedActivities = bookedActivities;
    }

    /**
     * @return the gender
     */
}
