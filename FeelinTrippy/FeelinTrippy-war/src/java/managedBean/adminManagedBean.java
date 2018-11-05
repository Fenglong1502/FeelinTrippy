/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.TrippyEventSessionLocal;
import session.TrippyEventTypeSessionLocal;

/**
 *
 * @author dk349
 */
@ManagedBean
@RequestScoped
public class adminManagedBean {
    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;
    
    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;

    private Long eventID;
    private String eventName;
    private Date startDate;
    private Date endDate;
    private String eventDescription;
    private Long point;
    private Double price;
    private Boolean softDelete;
    private List<TrippyEventType> eventType;
    private List<String> eventImage;
    private String eventTypeString;
    private String eventImageString;
    private String address;
    private List<String> eventTypeStringArray;

    private List<TrippyEventItem> listOfTrippyEvent;

    public adminManagedBean() {
    }

    @PostConstruct
    public void init() {
        listOfTrippyEvent = trippyEventSessionLocal.retrieveAllEvents();
    }
    
    public String updateTrippyEventItem() throws ParseException {
        System.out.println("Entering updating trippy event item");
        System.out.println("Event name: " + eventName);
        TrippyEventItem toUpdate = new TrippyEventItem();
        toUpdate.setEventID(eventID);
        toUpdate.setEventName(eventName);
        toUpdate.setPoint(point);
        toUpdate.setPrice(price);
        toUpdate.setAddress(address);
        toUpdate.setEventDescription(eventDescription);
        toUpdate.setSoftDelete(false);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate = dt.format(startDate);
        toUpdate.setStartDate(dt.parse(stringDate));
        stringDate = dt.format(endDate);
        toUpdate.setEndDate(dt.parse(stringDate));
//        toUpdate.setStartDate(startDate);
//        toUpdate.setEndDate(endDate);
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
        
        return "manageTrippyEventItem.xhtml?faces-redirect=true";
        
    }

    public void createTrippyEventItem() throws ParseException {
        System.out.println("Entering creating trippy event item");
        TrippyEventItem toCreate = new TrippyEventItem();
        toCreate.setEventName(eventName);
        toCreate.setPoint(point);
        toCreate.setPrice(price);
        toCreate.setAddress(address);
        toCreate.setEventDescription(eventDescription);
        toCreate.setSoftDelete(false);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate = dt.format(startDate);
        toCreate.setStartDate(dt.parse(stringDate));
        stringDate = dt.format(endDate);
        toCreate.setEndDate(dt.parse(stringDate));
        eventImage = Arrays.asList(eventImageString.split(","));
        eventTypeStringArray = Arrays.asList(eventTypeString.split(","));
        int count = 0;
        TrippyEventType toBeAdded;
        while (count <= eventTypeStringArray.size()-1) {
            try {
                toBeAdded = trippyEventTypeSessionLocal.searchTrippyEventType(eventTypeStringArray.get(count));
                if (toBeAdded != null) {
                    eventType.add(toBeAdded);
                }
            } catch(Exception e) {
                
            }
            count++;
        }
        toCreate.setEventImage(eventImage);
        toCreate.setEventType(eventType);
        trippyEventSessionLocal.createTrippyEvent(toCreate);
    }

    /**
     * @return the eventID
     */
    public Long getEventID() {
        return eventID;
    }

    /**
     * @param eventID the eventID to set
     */
    public void setEventID(Long eventID) {
        this.eventID = eventID;
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

    /**
     * @return the listOfTrippyEvent
     */
    public List<TrippyEventItem> getListOfTrippyEvent() {
        return listOfTrippyEvent;
    }

    /**
     * @param listOfTrippyEvent the listOfTrippyEvent to set
     */
    public void setListOfTrippyEvent(List<TrippyEventItem> listOfTrippyEvent) {
        this.listOfTrippyEvent = listOfTrippyEvent;
    }

    
    /**
     * @return the eventTypeStringArray
     */
    public List<String> getEventTypeStringArray() {
        return eventTypeStringArray;
    }

    /**
     * @param eventTypeStringArray the eventTypeStringArray to set
     */
    public void setEventTypeStringArray(List<String> eventTypeStringArray) {
        this.eventTypeStringArray = eventTypeStringArray;
    }

    /**
     * @return the eventTypeString
     */
    public String getEventTypeString() {
        return eventTypeString;
    }

    /**
     * @param eventTypeString the eventTypeString to set
     */
    public void setEventTypeString(String eventTypeString) {
        this.eventTypeString = eventTypeString;
    }

    /**
     * @return the eventImageString
     */
    public String getEventImageString() {
        return eventImageString;
    }

    /**
     * @param eventImageString the eventImageString to set
     */
    public void setEventImageString(String eventImageString) {
        this.eventImageString = eventImageString;
    }
}
