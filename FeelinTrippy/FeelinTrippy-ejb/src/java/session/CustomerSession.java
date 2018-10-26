/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MC
 */
@Stateless
public class CustomerSession implements CustomerSessionLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Customer getCustomer(Long cId) throws NoResultException {
        Customer c = em.find(Customer.class, cId);

        if (c != null) {
            return c;
        } else {
            throw new NoResultException("Not found");
        }
    } //end getCustomer

    @Override
    public void createCustomer(Customer c) {
        em.persist(c);
    } //end createCustomer
    
    @Override
    public List<Customer> searchCustomers(String name) {
        Query q;
        if (name != null) {
            q = em.createQuery("SELECT c FROM Customer c WHERE "
                    + "LOWER(c.name) LIKE :name");
            q.setParameter("name", "%" + name.toLowerCase() + "%");
        } else {
            q = em.createQuery("SELECT c FROM Customer c");
        }

        return q.getResultList();
    } //end searchCustomers
    
        public void updateCustomer(Customer c) throws NoResultException {
        Customer oldC = em.find(Customer.class, c.getUserID());

        if (oldC != null) {
            oldC.setFirstName(c.getFirstName());
            oldC.setLastName(c.getLastName());
            oldC.setEmail(c.getEmail());
            oldC.setUsername(c.getUsername());
            oldC.setGender(c.getGender());
            oldC.setMobileNumber(c.getMobileNumber());
            
        } else {
            throw new NoResultException("Not found");
        }
    } //end updateCustomer
        
        
    
}
