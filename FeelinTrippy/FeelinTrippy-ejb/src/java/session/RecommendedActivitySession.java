/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.RecommendedActivity;
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
public class RecommendedActivitySession implements RecommendedActivitySessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createRecommendedActivity(RecommendedActivity ra) {
        em.persist(ra);
    }

    @Override
    public List<RecommendedActivity> getAllRecommendedActivity() {
        Query q = em.createQuery("SELECT ra FROM RecommendedActivity ra");
        return q.getResultList();
    }

    @Override
    public List<RecommendedActivity> getRecommendedActivityByStatus(String status) {
        Query q;
        if (status != null) {
            q = em.createQuery("SELECT ra FROM RecommendedActivity ra WHERE "
                    + "LOWER(ra.status) LIKE :status");
            q.setParameter("status", "%" + status.toLowerCase() + "%");
        } else {
            return null;
        }

        return q.getResultList();
    }

    @Override
    public void updateRecommendedActivity(RecommendedActivity ra) throws NoResultException {
        RecommendedActivity oldRA = em.find(RecommendedActivity.class, ra.getRecID());
        if (oldRA != null) {
            oldRA.setRecDescription(ra.getRecDescription());
            oldRA.setRecName(ra.getRecName());
            oldRA.setRecImages(ra.getRecImages());
            oldRA.setRecPrice(ra.getRecPrice());
        } else {
            throw new NoResultException("Not Found");
        }

    }

    @Override
    public void deleteRecommendedActivity(Long recID) throws NoResultException {
        RecommendedActivity ra = em.find(RecommendedActivity.class, recID);

        if (ra != null) {
            em.remove(ra);
        } else {
            throw new NoResultException("Not found");
        }
    }

}
