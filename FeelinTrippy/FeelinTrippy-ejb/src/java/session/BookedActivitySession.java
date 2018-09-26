/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BookedActivity;
import entity.User;
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
public class BookedActivitySession implements BookedActivitySessionLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createBookedActivity(BookedActivity b) {
        em.persist(b);
    }

    @Override
    public BookedActivity getBookedActivityByID(Long bookedActivityID) throws NoResultException {
        BookedActivity b = em.find(BookedActivity.class, bookedActivityID);
        if (b != null){
            return b;
        }
        
        else {
            throw new NoResultException("Booked Activity Not Found!");
        }
    }

    @Override
    public List<BookedActivity> getBookedActivitesByUser(User u) {
        Query q;
        if (u != null) {
            q = em.createQuery("SELECT b FROM BookedActivity b WHERE "
                    + "u.bookedBy IN :user");
            q.setParameter("user", u);
        } else {
            return new ArrayList<BookedActivity>();
        }

        return q.getResultList();
    }

    @Override
    public List<BookedActivity> getAllBookedActities() {
        Query q = em.createQuery("SELECT c FROM Customer c");
        return q.getResultList();
    }

    @Override
    public void updateBookedActivity(BookedActivity b) throws NoResultException {
         BookedActivity oldB = em.find(BookedActivity.class, b.getbookedID());
        if (oldB != null){
            oldB.setStatus(b.getStatus());
            oldB.setQty(b.getQty());
            oldB.setPrice(b.getPrice());
        }
        else {
            throw new NoResultException("Booked Activity Not Found!");
        }
    }

    @Override
    public void deleteBookedActivity(Long bookedActivityID) throws NoResultException {
        BookedActivity b= em.find(BookedActivity.class, bookedActivityID);
        if (b == null){
            throw new NoResultException("Booked Activity Not Found!");
        }
        em.remove(b);
    }


}
