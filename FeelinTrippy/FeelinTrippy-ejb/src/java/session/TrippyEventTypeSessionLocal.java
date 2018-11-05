package session;

import entity.TrippyEventType;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;

@Local
public interface TrippyEventTypeSessionLocal {
    public void createTrippyType(TrippyEventType type);
    public void updateTrippyType(TrippyEventType type) throws NoResultException;
    public void removeTrippyType(Long trippyTypeID) throws NoResultException;
    public List<TrippyEventType> getAllTripType();
    public TrippyEventType searchTrippyEventType(String name);
    
}
