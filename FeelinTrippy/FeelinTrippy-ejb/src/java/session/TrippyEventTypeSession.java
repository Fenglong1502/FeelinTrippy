package session;

import entity.TrippyEventType;
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
public class TrippyEventTypeSession implements TrippyEventTypeSessionLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createTrippyType(TrippyEventType type) {
        if (type != null) {
            try {
                em.persist(type);
            } catch (EntityExistsException e) {
                System.out.println("Trippy type exists.");
            }
        }
    }

    @Override
    public void updateTrippyType(TrippyEventType type) {
        TrippyEventType existingTrippyType = em.find(TrippyEventType.class, type.getTypeID());
        if (existingTrippyType != null) {
            existingTrippyType.setTypeName(existingTrippyType.getTypeName());
        }
        else {
            throw new NoResultException("Trippy Event Type not found.");
        }
    }

    @Override
    public void removeTrippyType(Long trippyTypeID) {
        TrippyEventType toBeRemoved = em.find(TrippyEventType.class, trippyTypeID);
        if (toBeRemoved != null) {
            em.remove(toBeRemoved);
        }
        else {
            throw new NoResultException("Trippy Event Type not found");
        }
    }
        
    @Override
    public List<TrippyEventType> getAllTripType() {
        Query q;
        q = em.createQuery("SELECT t FROM TrippyEventType t");
        return q.getResultList();
    }

    
}
