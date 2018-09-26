package session;

import entity.TrippyEventItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TrippyEventSession implements TrippyEventSessionLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createTrippyEvent(TrippyEventItem trippyEventItem) {
        em.persist(trippyEventItem);
    }

    @Override
    public void updateTrippyEvent(TrippyEventItem trippyEventItem) {
        TrippyEventItem existingTrippyEvent = em.find(TrippyEventItem.class, trippyEventItem.getEventID());
        if (existingTrippyEvent != null) {
            existingTrippyEvent.setEventName(trippyEventItem.getEventName());
            existingTrippyEvent.setEventDescription(trippyEventItem.getEventDescription());
            existingTrippyEvent.setEventType(trippyEventItem.getEventType());
            existingTrippyEvent.setPoint(trippyEventItem.getPoint());
            existingTrippyEvent.setPrice(trippyEventItem.getPrice());
            existingTrippyEvent.setSoftDelete(trippyEventItem.getSoftDelete());
            existingTrippyEvent.setStartDate(trippyEventItem.getStartDate());
            existingTrippyEvent.setEndDate(trippyEventItem.getEndDate());
        }
        else {
            throw new NoResultException("Trippy Event not found.");
        }
    }
    
    @Override
    public void removeTrippyEvent(TrippyEventItem trippyEventItem) {
        TrippyEventItem toBeRemoved = em.find(TrippyEventItem.class, trippyEventItem.getEventID());
        if (toBeRemoved != null) {
            em.remove(toBeRemoved);
        }
        else {
            throw new NoResultException("Trippy Event not found");
        }
    }

    @Override
    public TrippyEventItem retrieveEventByEventId(Long eventID) {
        TrippyEventItem result = em.find(TrippyEventItem.class, eventID);
        return result;
    }

    @Override
    public List<TrippyEventItem> retrieveAllEvents() {
        Query q;
        q = em.createQuery("SELECT t FROM TrippyEventItem t");
        return q.getResultList();
    }
      
}
