/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.CustomerSessionLocal;
import session.TrippyEventSessionLocal;
import session.TrippyEventTypeSessionLocal;

/**
 *
 * @author dk349
 */
@ManagedBean
@RequestScoped
public class adminManagedBean {

    /**
     * @return the softDeleteString
     */
    public String getSoftDeleteString() {
        return softDeleteString;
    }

    /**
     * @param softDeleteString the softDeleteString to set
     */
    public void setSoftDeleteString(String softDeleteString) {
        this.softDeleteString = softDeleteString;
    }

    /**
     * @return the selectedType
     */
    public TrippyEventType getSelectedType() {
        return selectedType;
    }

    /**
     * @param selectedType the selectedType to set
     */
    public void setSelectedType(TrippyEventType selectedType) {
        this.selectedType = selectedType;
    }

    /**
     * @return the eventImageStringArray
     */
    public List<String> getEventImageStringArray() {
        return eventImageStringArray;
    }

    /**
     * @param eventImageStringArray the eventImageStringArray to set
     */
    public void setEventImageStringArray(List<String> eventImageStringArray) {
        this.eventImageStringArray = eventImageStringArray;
    }

    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;

    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;

    @EJB
    CustomerSessionLocal customerSessionLocal;

    private Long eventID;
    private Long typeID;
    private Long custID;
    private String eventName;
    private Date startDate;
    private Date endDate;
    private String eventDescription;
    private Long point;
    private Double price;
    private Boolean softDelete;
    private Boolean accStatus;
    private String eventImage;
    private String eventTypeString;
    private String eventImageString;
    private String address;
    private List<String> eventTypeStringArray;
    private List<String> eventImageStringArray;
    private String eventTypeName;
    private TrippyEventType selectedType;
    private String softDeleteString;

    private List<TrippyEventItem> listOfTrippyEvent;
    private List<TrippyEventType> eventType;
    private List<Customer> listOfCustomer;

    public adminManagedBean() {
    }

    @PostConstruct
    public void init() {
        listOfTrippyEvent = trippyEventSessionLocal.retrieveAllEvents();
        eventType = trippyEventTypeSessionLocal.getAllTripType();
        listOfCustomer = customerSessionLocal.retrieveAllCustomer();
    }
    
    public void activateTrip(TrippyEventItem toUpdate) {
        System.out.println("Activating trip");
        toUpdate.setSoftDelete(false);
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
    }
    
    public void deactivateTrip(TrippyEventItem toUpdate) {
        System.out.println("Deactivating trip");
        toUpdate.setSoftDelete(true);
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
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
//        eventImage = Arrays.asList(eventImageString.split(","));
//        System.out.println("HTML Size: " + eventImage.size());
        System.out.println("After cutting: " + eventImage);
        eventTypeStringArray = Arrays.asList(eventTypeString.split(","));
//        System.out.println("Event type array: " + eventTypeStringArray);
        int count = 0;
        TrippyEventType toBeAdded;
        eventType.clear();
        eventTypeString="";
        while (count <= eventTypeStringArray.size() - 1) {
            try {
                toBeAdded = trippyEventTypeSessionLocal.searchTrippyEventType(eventTypeStringArray.get(count));
                System.out.println("Tobeadded: " + toBeAdded);
                if (toBeAdded != null) {
                    eventType.add(toBeAdded);
                    eventTypeString += eventTypeStringArray.get(count) + ",";
                }
            } catch (Exception e) {

            }
            count++;
        }
        if (eventTypeString.length() != 0) {
            if (eventTypeString.charAt(eventTypeString.length()-1) == ',') {
            eventTypeString = eventTypeString.substring(0,eventTypeString.length()-1);
        }
        }
        
        
        
        count = 0;
//        if (eventImage.size() > 0) {
//            while (count <= eventImage.size() - 1) {
////                if(eventImage.get(count).charAt(0) == '[') {
////                    eventImage.set(count, eventImage.get(count).substring(1,eventImage.get(count).length()-1));
////                }
////                System.out.println("After trimming1: " + eventImage.get(count));
////                if(eventImage.get(count).charAt(eventImage.get(count).length()) == ']') {
////                    eventImage.set(count, eventImage.get(count).substring(0,eventImage.get(count).length()-2));
////                }
////                System.out.println("After trimming2: " + eventImage.get(count));
//                eventImageStringArray.add(eventImage.get(count));
//                count++;
//            }
//        }

        if (eventType.size() == 0) {
            System.out.println("found nothing for type!");
            eventTypeString = "";
        }
        System.out.println("Event type: " + eventType);
        toUpdate.setEventImage(eventImage);
        toUpdate.setEventTypeString(eventTypeString);
        toUpdate.setEventImage(eventImage);
        toUpdate.setEventType(eventType);
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
//        eventImageStringArray.clear();
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
//        eventImage = Arrays.asList(eventImageString.split(","));
        eventTypeStringArray = Arrays.asList(eventTypeString.split(","));
        int count = 0;
        TrippyEventType toBeAdded;
        eventType.clear();
        while (count <= eventTypeStringArray.size() - 1) {
            try {
                toBeAdded = trippyEventTypeSessionLocal.searchTrippyEventType(eventTypeStringArray.get(count));
                if (toBeAdded != null) {
                    eventType.add(toBeAdded);
                }
            } catch (Exception e) {

            }
            count++;
        }
//        count = 0;
//        eventImageStringArray.clear();
//        while (count <= eventImage.size() - 1) {
//            eventImageStringArray.add(eventImage.get(count));
//            count++;
//        }
        if (eventType.size() == 0) {
            System.out.println("found nothing for type!");
            eventTypeString = "";
        }
        toCreate.setEventImage(eventImage);
        toCreate.setEventTypeString(eventTypeString);
        toCreate.setEventImage(eventImage);
        toCreate.setEventType(eventType);
        trippyEventSessionLocal.createTrippyEvent(toCreate);
    }

    public String createEventType() {
        TrippyEventType newType = new TrippyEventType();
        newType.setTypeName(getEventTypeName());
        if(softDeleteString.equals("true"))
            softDelete = true;
        else
            softDelete = false;
        newType.setSoftDelete(getSoftDelete());

        trippyEventTypeSessionLocal.createTrippyType(newType);
        
        return "searchTrippyEventType.xhtml?faces-redirect=true";
    }

    public String updateEventType() {
        if(softDeleteString.equals("true"))
            softDelete = true;
        else
            softDelete = false;
        TrippyEventType newType = new TrippyEventType();
        newType.setTypeID(getTypeID());
        newType.setTypeName(getEventTypeName());
        newType.setSoftDelete(getSoftDelete());

        trippyEventTypeSessionLocal.updateTrippyType(newType);
        
        return "searchTrippyEventType.xhtml?faces-redirect=true";
    }
    
    public void loadSelectedType(){
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.selectedType = trippyEventTypeSessionLocal.getTypeById(typeID);
            eventTypeName = selectedType.getTypeName();
            softDelete = selectedType.getSoftDelete();
            if(softDelete == true)
                this.softDeleteString = "true";
            else
                this.softDeleteString = "false";

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to event Type"));
        }
        
        setSelectedType(trippyEventTypeSessionLocal.getTypeById(typeID));
    }
    
    public void deleteType(){
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String tIdStr = params.get("typeID");
        Long tId = Long.parseLong(tIdStr);
        
        trippyEventTypeSessionLocal.removeTrippyType(tId);
        init();
    }
    
    public void recoverType(){
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String tIdStr = params.get("typeID");
        Long tId = Long.parseLong(tIdStr);

        try {
            trippyEventTypeSessionLocal.recoverType(tId);
        } catch (Exception e) {
            //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to activate member"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully activated member"));
        init();
    }
    public void removeType(){
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String tIdStr = params.get("typeID");
        Long tId = Long.parseLong(tIdStr);

        try {
            trippyEventTypeSessionLocal.removeType(tId);
        } catch (Exception e) {
            //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to remove type"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully removed type"));
        init();
    }
    

    public void activateAccount() {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String cIdStr = params.get("cId");
        Long cId = Long.parseLong(cIdStr);

        try {
            customerSessionLocal.activateAccount(cId);
        } catch (Exception e) {
            //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to activate member"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully activated member"));
        init();
    }

    public void deactivateAccount() {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String cIdStr = params.get("cId");
        Long cId = Long.parseLong(cIdStr);

        try {
            customerSessionLocal.deactivateAccount(cId);
        } catch (Exception e) {
            //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to deactivate member"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully deactivated member"));
        init();
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
    public String getEventImage() {
        return eventImage;
    }

    /**
     * @param eventImage the eventImage to set
     */
    public void setEventImage(String eventImage) {
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

    /**
     * @return the accStatus
     */
    public Boolean getAccStatus() {
        return accStatus;
    }

    /**
     * @param accStatus the accStatus to set
     */
    public void setAccStatus(Boolean accStatus) {
        this.accStatus = accStatus;
    }

    /**
     * @return the listOfCustomer
     */
    public List<Customer> getListOfCustomer() {
        return listOfCustomer;
    }

    /**
     * @param listOfCustomer the listOfCustomer to set
     */
    public void setListOfCustomer(List<Customer> listOfCustomer) {
        this.listOfCustomer = listOfCustomer;
    }

    /**
     * @return the eventTypeName
     */
    public String getEventTypeName() {
        return eventTypeName;
    }

    /**
     * @param eventTypeName the eventTypeName to set
     */
    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    /**
     * @return the typeID
     */
    public Long getTypeID() {
        return typeID;
    }

    /**
     * @param typeID the typeID to set
     */
    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    /**
     * @return the custID
     */
    public Long getCustID() {
        return custID;
    }

    /**
     * @param custID the custID to set
     */
    public void setCustID(Long custID) {
        this.custID = custID;
    }
}
