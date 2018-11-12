/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BookedActivity;
import static entity.BookedActivity_.bookedDate;
import entity.TrippyEventItem;
import entity.Customer;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;
;
/**
 *
 * @author fengl
 */
@Local
public interface BookedActivitySessionLocal {
    
    public void createBookedActivity (BookedActivity b);
    public BookedActivity getBookedActivityByID(Long bookedActivityID) throws NoResultException;
    public List<BookedActivity> getBookedActivitesByCustomer (Customer c);
    public List<BookedActivity> getAllBookedActivities();
    public void updateBookedActivity(BookedActivity b) throws NoResultException;
    public void deleteBookedActivity(Long bookedActivityID) throws NoResultException;
    public BookedActivity getNewlyAddBookedActivity();
    
    //Handle Booking trip function
    public BookedActivity getNewlyAddSavedTrip();
    
    
}
