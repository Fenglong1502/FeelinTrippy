/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.Prize;
import entity.PrizeOrder;
import java.util.Date;
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
    public Prize getPrizeById(Long prizeID) throws NoResultException {
        Prize prize = em.find(Prize.class, prizeID);

        if (prize != null) {
            return prize;
        } else {
            throw new NoResultException("Not found");
        }
    }

    @Override
    public void updatePrize(Prize prize) {
        Prize existingPrize = em.find(Prize.class, prize.getPrizeID());
        if (existingPrize != null) {
            existingPrize.setPrizeName(prize.getPrizeName());
            existingPrize.setPrizePoint(prize.getPrizePoint());
            existingPrize.setPrizeQty(prize.getPrizeQty());
            existingPrize.setSoftDelete(prize.getSoftDelete());
            existingPrize.setPrizeImage(prize.getPrizeImage());
            existingPrize.setPrizeDescription(prize.getPrizeDescription());
        } else {
            throw new NoResultException("Prize not found.");
        }
    }

    @Override
    public void removePrize(Long prizeID) {
        Prize prize = em.find(Prize.class, prizeID);
        if (prize != null) {
            em.remove(prize);
        } else {
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
        PrizeOrder po = new PrizeOrder();
        //Check if customer has enough points to redeem prize and if qty customer wants to redeem is <= the amount of prize left.
        if (c.getPoints() >= qty * toRedeem.getPrizePoint() && qty <= toRedeem.getPrizeQty()) {
            c.setPoints(c.getPoints() - qty * toRedeem.getPrizePoint());
            toRedeem.setPrizeQty(toRedeem.getPrizeQty() - qty);
            po.setCustomerID(customerID);
            po.setPointsUsed(qty * toRedeem.getPrizePoint());
            po.setPrizeRedeemed(toRedeem);
            po.setQuantity(qty);
            po.setRedemptionDate(new Date());
            em.persist(po);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<PrizeOrder> getPrizeRedeemed(Long customerID) {
        Query q;
        q = em.createQuery("SELECT po FROM PrizeOrder po WHERE po.customerID = :customerID");
        q.setParameter("customerID", customerID);
        return q.getResultList();
    }

   

}
