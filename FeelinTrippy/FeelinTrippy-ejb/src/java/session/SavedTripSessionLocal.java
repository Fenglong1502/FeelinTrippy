/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SavedTrip;
import entity.Customer;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface SavedTripSessionLocal {

    public void createdSavedTrip(SavedTrip s);

    public void createdSavedTrip(SavedTrip s, Customer c);

    public List<SavedTrip> getAllSavedTrip();

    public void updateSavedTrip(SavedTrip s) throws NoResultException;

    public void deleteSavedTrip(Long savedTripID) throws NoResultException;

    public SavedTrip getSavedTripByID(Long savedTripID) throws NoResultException;

    public List<SavedTrip> getSavedActivityByCustomer(Customer u);

    public SavedTrip getNewlyAddSavedTrip();

}
