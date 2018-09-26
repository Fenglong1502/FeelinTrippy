package session;

import entity.TrippyEventItem;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;

@Local
public interface TrippyEventSessionLocal {
    public void createTrippyEvent(TrippyEventItem trippyEventItem);
    public void updateTrippyEvent(TrippyEventItem trippyEventItem) throws NoResultException;
    public void removeTrippyEvent(TrippyEventItem trippyEventItem) throws NoResultException;
    public TrippyEventItem retrieveEventByEventId(Long eventID);
    public List<TrippyEventItem> retrieveAllEvents();
}
