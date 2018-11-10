package session;

import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;

@Local
public interface TrippyEventSessionLocal {
    public void createTrippyEvent(TrippyEventItem trippyEventItem);
    public void updateTrippyEvent(TrippyEventItem trippyEventItem) throws NoResultException;
    public void removeTrippyEvent(TrippyEventItem trippyEventItem) throws NoResultException;
    public TrippyEventItem retrieveEventByEventId(Long eventID);
    public TrippyEventItem retrieveEventByEventName(String eventName);
    public List<TrippyEventItem> retrieveAllEvents();
    
    
    
    public List<TrippyEventItem> searchEventListByConditions(TrippyEventType type, Double price);
    public List<TrippyEventItem> searchEventListByPrice(Double price);
    public TrippyEventItem randomEvent(TrippyEventType type, Double price);
}
