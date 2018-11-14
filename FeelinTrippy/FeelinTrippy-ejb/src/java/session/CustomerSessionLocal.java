/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BookedActivity;
import entity.Customer;
import entity.SavedTrip;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import error.CustomerAddSavedTripException;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface CustomerSessionLocal {
    
    public List<Customer> retrieveAllCustomer();
    public Customer getCustomerById(Long cId) throws NoResultException;
    public Customer getCustomerByName(String name) throws NoResultException;
    public Customer getCustomerByEmail(String email) throws NoResultException;
    public void createCustomer(Customer c);
    public List<Customer> searchCustomers(String name);
    
    // Login and Registration
    public boolean Login(Customer c);
    public void changePasword(Customer c, String newPass);
    public boolean forgetPassword(Customer c);
  
    
    //Handle Profile
    public void updateCustomer(Customer c) throws NoResultException;
    public void deleteCustomer(Long userID) throws NoResultException;
    public void deactivateAccount(Long cId) throws NoResultException;
    public void activateAccount(Long cId) throws NoResultException;
    
    //Points Handling
    public int deductPoints(Customer c, int pointsDeducted);
    public int addPoints(Customer c, int pointsToAdd);
    
    //Hnadling CRUD for Customer savedTrip, pastTrip and bookedActivity
    public void addSavedTrip(Long id, SavedTrip s);
    public void removeSavedTrip(Long id, SavedTrip s);
    public void addBookedTrip(Long id, BookedActivity b);
       public void removeBookedTrip(Long id, TrippyEventItem t);
    public List<SavedTrip> getSavedTripByType(TrippyEventType type, Customer c);
    public boolean isEventExist(TrippyEventItem item, Long id);
    public List<BookedActivity> getPastTripByType(TrippyEventType type, Customer c);
    public List<BookedActivity> getBookedTripByType(TrippyEventType type, Customer c);
    
    //Sms service
    public void shareTripsViaPhoneNumber(String message, String phoneNumber);
    public boolean validPhoneNumber(String phoneNum);
    public String generateSharingID(TrippyEventItem trip);
    public TrippyEventItem eventShared(String sharingID);
    
    //For ActivityDetails Button Check
    
}
