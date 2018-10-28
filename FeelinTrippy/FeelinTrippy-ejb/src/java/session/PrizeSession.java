/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.Prize;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dk349
 */
@Stateless
public class PrizeSession implements PrizeSessionLocal {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void createPrize(Prize prize) {
        if (prize != null) {
            try {
                em.persist(prize);
            } catch (EntityExistsException e) {
                System.out.println("Prize exists.");
            }
        }
    }

    @Override
    public void updatePrize(Prize prize) {
        Prize existingPrize = em.find(Prize.class, prize.getPrizeID());
        if (existingPrize != null) {
            existingPrize.setPrizeName(prize.getPrizeName());
            existingPrize.setPrizePoint(prize.getPrizePoint());
            existingPrize.setPrizeQty(prize.getPrizeQty());
            existingPrize.setPrizeImage(prize.getPrizeImage());
            existingPrize.setPrizeDescription(prize.getPrizeDescription());
        }
        else {
            throw new NoResultException("Prize not found.");
        }
    }

    @Override
    public void removePrize(Long prizeID) {
        Prize prize = em.find(Prize.class, prizeID);
        if (prize != null) {
            em.remove(prize);
        }
        else {
            throw new NoResultException("Prize not found");
        }
    }

    @Override
    public List<Prize> getAllPrize() {
        Query q;
        q = em.createQuery("SELECT p FROM Prize p");
        return q.getResultList();
    }
    
    @Override
    public List<Prize> searchPrizeByName(String searchTerm) {
        Query q;
        q = em.createQuery("SELECT p FROM Prize p WHERE LOWER(p.prizeName) LIKE :searchTerm");
        q.setParameter("searchTerm", "%" + searchTerm.toLowerCase() + "%");
        return q.getResultList();
    }
    
    @Override
    public boolean redeemPrize(Long customerID, Long prizeID, int qty) {
        Customer c = em.find(Customer.class, customerID);
        Prize toRedeem = em.find(Prize.class, prizeID);
        //Check if customer has enough points to redeem prize and if qty customer wants to redeem is <= the amount of prize left.
        if (c.getPoints() >= qty*toRedeem.getPrizePoint() && qty <= toRedeem.getPrizeQty()) {
            c.setPoints(c.getPoints()-qty*toRedeem.getPrizePoint());
            toRedeem.setPrizeQty(toRedeem.getPrizeQty()-qty);
            return true;
        }
        else {
            return false;
        }
        
    }

}
