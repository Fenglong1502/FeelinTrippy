/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface CustomerSessionLocal {
    
    public Customer getCustomer(Long cId) throws NoResultException;
    public void createCustomer(Customer c);
    public List<Customer> searchCustomers(String name);
    public void updateCustomer(Customer c) throws NoResultException;
    public void deleteCustomer(Long userID) throws NoResultException;
    
    
}
