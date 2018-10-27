/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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

}
