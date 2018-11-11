/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SavedTrip;
import entity.Customer;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fengl
 */
@Stateless
public class SavedTripSession implements SavedTripSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createdSavedTrip(SavedTrip s) {
        em.persist(s);
        em.flush();
    }

    @Override
    public List<SavedTrip> getAllSavedTrip() {
        Query q = em.createQuery("SELECT s FROM SavedTrip s");
        return q.getResultList();
    }

    @Override
    public void updateSavedTrip(SavedTrip s) throws NoResultException {
        SavedTrip oldS = em.find(SavedTrip.class, s.getSavedTripID());
        if (oldS != null) {
            oldS.setPrice(s.getPrice());
            oldS.setSavedDate(s.getSavedDate());
            oldS.setEventItem(s.getEventItem());
        } else {
            throw new NoResultException("Saved Trip not found!");
        }
    }

    @Override
    public void deleteSavedTrip(Long savedTripID) throws NoResultException {
        SavedTrip s = em.find(SavedTrip.class, savedTripID);
        if (s != null) {
            em.remove(s);
        } else {
            throw new NoResultException("Saved Trip not found!");
        }
    }

    @Override
    public SavedTrip getSavedTripByID(Long savedTripID) throws NoResultException {
        SavedTrip s = em.find(SavedTrip.class, savedTripID);
        if (s != null) {
            return s;
        } else {
            throw new NoResultException("Saved Trip not found!");
        }
    }

    @Override
    public List<SavedTrip> getSavedActivityByCustomer(Customer c) {
        Query q;
        if (c != null) {
            q = em.createQuery("SELECT s FROM SavedTrip s WHERE "
                    + "s.customer IN :user");
            q.setParameter("customer", c);
        } else {
            return new ArrayList<SavedTrip>();
        }
        return q.getResultList();
    }

    @Override
    public SavedTrip getNewlyAddSavedTrip() {
        Query q = em.createQuery("SELECT s FROM SavedTrip s ORDER BY s.savedTripID DESC");
        return (SavedTrip) q.getResultList().get(0);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createdSavedTrip(SavedTrip s, Customer c) {
        s.setCustomer(c);
        em.persist(s);
        em.flush();

    }
}
