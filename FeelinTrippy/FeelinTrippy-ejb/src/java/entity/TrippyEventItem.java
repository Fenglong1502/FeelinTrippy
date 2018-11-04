package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TrippyEventItem implements Serializable {

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventID;
    
    private String eventName;
    @Temporal(TemporalType.DATE)
    private Date startDate;
            
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name="eventDescription",columnDefinition="LONG VARCHAR")
    private String eventDescription;
    //point indicates the number of points users can earn from attending this event
    private Long point;
    private Double price;
    private Boolean softDelete;

//    @OneToMany
    private List<TrippyEventType> eventType;
    private List<String> eventImage;
    private String address;
    
    public TrippyEventItem(){
        
    
    }
    
    public TrippyEventItem(String eventName, String eventDescription, Long point, Date startDate, Date endDate, Double price, List<String> eventImage, List<TrippyEventType> eventType, Boolean softDelete) {
        this();
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.point = point;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.eventImage = eventImage;
        this.eventType = eventType;
        this.softDelete = softDelete;
    }
    
    
    //getter and setters
    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getEventID() != null ? getEventID().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrippyEventItem)) {
            return false;
        }
        TrippyEventItem other = (TrippyEventItem) object;
        if ((this.getEventID() == null && other.getEventID() != null) || (this.getEventID() != null && !this.eventID.equals(other.eventID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TrippyEventItem[ id=" + getEventID() + " ]";
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the eventDescription
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * @param eventDescription the eventDescription to set
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the softDelete
     */
    public Boolean getSoftDelete() {
        return softDelete;
    }

    /**
     * @param softDelete the softDelete to set
     */
    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the point
     */
    public Long getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(Long point) {
        this.point = point;
    }

    /**
     * @return the eventType
     */
    public List<TrippyEventType> getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(List<TrippyEventType> eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the eventImage
     */
    public List<String> getEventImage() {
        return eventImage;
    }

    /**
     * @param eventImage the eventImage to set
     */
    public void setEventImage(List<String> eventImage) {
        this.eventImage = eventImage;
    }
    
}
