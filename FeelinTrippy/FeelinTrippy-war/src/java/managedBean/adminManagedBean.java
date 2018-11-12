/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import entity.Prize;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.persistence.NoResultException;
import javax.servlet.http.Part;
import session.CustomerSessionLocal;
import session.PrizeSessionLocal;
import session.TrippyEventSessionLocal;
import session.TrippyEventTypeSessionLocal;
import sun.misc.IOUtils;

/**
 *
 * @author dk349
 */
@ManagedBean
@RequestScoped
public class adminManagedBean {

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }
       
    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;

    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;

    @EJB
    CustomerSessionLocal customerSessionLocal;

    @EJB
    PrizeSessionLocal prizeSessionLocal;

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
    private Prize selectedPrize;
    private String softDeleteString;
    private Long prizeID;
    private String prizeName;
    private String prizeDescription;
    private int prizePoint;
    private int prizeQty;
    private String prizeString;
    private String prizeImageString;
    private String prizeImage;
    private List<String> prizeStringArray;
    private List<String> prizeImageStringArray;
    private String imgFile = "Noimage.jpg";
    private Part prizeFile;
    private String prizeImageFile = "Noimage.jpg";
    private Part file;

    private List<TrippyEventItem> listOfTrippyEvent;
    private List<TrippyEventType> eventType;
    private List<Customer> listOfCustomer;
    private List<Prize> listOfPrize;

    public adminManagedBean() {
    }

    @PostConstruct
    public void init() {
        setListOfTrippyEvent(trippyEventSessionLocal.retrieveAllEvents());
        setEventType(trippyEventTypeSessionLocal.getAllTripType());
        setListOfCustomer(customerSessionLocal.retrieveAllCustomer());
        setListOfPrize(prizeSessionLocal.getAllPrize());
    }

    public void activateTrip(TrippyEventItem toUpdate) {
        System.out.println("Activating trip");
        toUpdate.setSoftDelete(false);
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
        init();
    }

    public void deactivateTrip(TrippyEventItem toUpdate) {
        System.out.println("Deactivating trip");
        toUpdate.setSoftDelete(true);
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
        init();
    }

    public String updateTrippyEventItem() throws ParseException {
//        System.out.println("Entering updating trippy event item");
//        System.out.println("Event name: " + eventName);
        TrippyEventItem toUpdate = new TrippyEventItem();
        toUpdate.setEventID(getEventID());
        toUpdate.setEventName(getEventName());
        toUpdate.setPoint(getPoint());
        toUpdate.setPrice(getPrice());
        toUpdate.setAddress(getAddress());
        toUpdate.setEventDescription(getEventDescription());
        toUpdate.setSoftDelete(null);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate = dt.format(getStartDate());
        toUpdate.setStartDate(dt.parse(stringDate));
        stringDate = dt.format(getEndDate());
        toUpdate.setEndDate(dt.parse(stringDate));
        setEventTypeStringArray(Arrays.asList(getEventTypeString().split(",")));
//        System.out.println("Event type array: " + eventTypeStringArray);
        int count = 0;
        TrippyEventType toBeAdded;
        getEventType().clear();
        setEventTypeString("");
        while (count <= getEventTypeStringArray().size() - 1) {
            try {
                toBeAdded = trippyEventTypeSessionLocal.searchTrippyEventType(getEventTypeStringArray().get(count).trim());
//                System.out.println("Tobeadded: " + toBeAdded);
//                System.out.println("Checking: " + eventTypeStringArray.get(count));
                if (toBeAdded != null) {
                    getEventType().add(toBeAdded);
                    setEventTypeString(getEventTypeString() + getEventTypeStringArray().get(count).trim() + ",");
                }
            } catch (Exception e) {

            }
            count++;
        }
        if (getEventTypeString().length() != 0) {
            if (getEventTypeString().charAt(getEventTypeString().length() - 1) == ',') {
                setEventTypeString(getEventTypeString().substring(0, getEventTypeString().length() - 1));
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

        if (getEventType().size() == 0) {
//            System.out.println("found nothing for type!");
            setEventTypeString("");
        }
//      get image uploaded
        if (file != null) {
            imgFile = file.getSubmittedFileName();
            try {

                InputStream bytes = file.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://houszqzb:leonleon95@houseofcasesg.website/public_html/image-upload/trippyImages/" + file.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        toUpdate.setEventImage(imgFile);
        toUpdate.setEventTypeString(getEventTypeString());
        toUpdate.setEventType(getEventType());
        trippyEventSessionLocal.updateTrippyEvent(toUpdate);
        setListOfTrippyEvent(trippyEventSessionLocal.retrieveAllEvents());
//        eventImageStringArray.clear();
        return "manageTrippyEventItem.xhtml?faces-redirect=true";

    }

    public void createTrippyEventItem() throws ParseException {
//        System.out.println("Entering creating trippy event item");
        try {
            TrippyEventItem toCreate = new TrippyEventItem();
            toCreate.setEventName(getEventName());
            toCreate.setPoint(getPoint());
            toCreate.setPrice(getPrice());
            toCreate.setAddress(getAddress());
            toCreate.setEventDescription(getEventDescription());
            toCreate.setSoftDelete(false);
            SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            String stringDate = dt.format(getStartDate());
            toCreate.setStartDate(dt.parse(stringDate));
            stringDate = dt.format(getEndDate());
            toCreate.setEndDate(dt.parse(stringDate));
//        eventImage = Arrays.asList(eventImageString.split(","));
            setEventTypeStringArray(Arrays.asList(getEventTypeString().split(",")));
            int count = 0;
            TrippyEventType toBeAdded;
            getEventType().clear();
            setEventTypeString("");
            while (count <= getEventTypeStringArray().size() - 1) {
                try {
                    toBeAdded = trippyEventTypeSessionLocal.searchTrippyEventType(getEventTypeStringArray().get(count).trim());
//                System.out.println("Tobeadded: " + toBeAdded);
//                System.out.println("Checking: " + eventTypeStringArray.get(count));
                    if (toBeAdded != null) {
                        getEventType().add(toBeAdded);
                        setEventTypeString(getEventTypeString() + getEventTypeStringArray().get(count).trim() + ",");
                    }
                } catch (Exception e) {

                }
                count++;
            }

            if (getEventTypeString().length() != 0) {
                if (getEventTypeString().charAt(getEventTypeString().length() - 1) == ',') {
                    setEventTypeString(getEventTypeString().substring(0, getEventTypeString().length() - 1));
                }
            }
//        count = 0;
//        eventImageStringArray.clear();
//        while (count <= eventImage.size() - 1) {
//            eventImageStringArray.add(eventImage.get(count));
//            count++;
//        }
            if (file != null) {
            imgFile = file.getSubmittedFileName();
            try {

                InputStream bytes = file.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://houszqzb:leonleon95@houseofcasesg.website/public_html/image-upload/trippyImages/" + file.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

            toCreate.setEventImage(imgFile);
            toCreate.setEventTypeString(getEventTypeString());
            toCreate.setEventType(getEventType());

            trippyEventSessionLocal.createTrippyEvent(toCreate);
        } catch (Exception e) {

        }

        init();
    }

    public String createEventType() {
        TrippyEventType newType = new TrippyEventType();
        newType.setTypeName(getEventTypeName());
        if (getSoftDeleteString().equals("true")) {
            setSoftDelete((Boolean) true);
        } else {
            setSoftDelete((Boolean) false);
        }
        newType.setSoftDelete(getSoftDelete());

        trippyEventTypeSessionLocal.createTrippyType(newType);

        return "searchTrippyEventType.xhtml?faces-redirect=true";
    }

    public String updateEventType() {
        if (getSoftDeleteString().equals("true")) {
            setSoftDelete((Boolean) true);
        } else {
            setSoftDelete((Boolean) false);
        }
        TrippyEventType newType = new TrippyEventType();
        newType.setTypeID(getTypeID());
        newType.setTypeName(getEventTypeName());
        newType.setSoftDelete(getSoftDelete());

        trippyEventTypeSessionLocal.updateTrippyType(newType);

        return "searchTrippyEventType.xhtml?faces-redirect=true";
    }

    public void loadSelectedType() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.setSelectedType(trippyEventTypeSessionLocal.getTypeById(getTypeID()));
            setEventTypeName(getSelectedType().getTypeName());
            setSoftDelete(getSelectedType().getSoftDelete());
            if (getSoftDelete() == true) {
                this.setSoftDeleteString("true");
            } else {
                this.setSoftDeleteString("false");
            }
            setSelectedType(trippyEventTypeSessionLocal.getTypeById(getTypeID()));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to event Type"));
        }
            
    }

    public void loadSelectedPrize() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.setSelectedPrize(prizeSessionLocal.getPrizeById(getPrizeID()));
            setPrizeName(selectedPrize.getPrizeName());
            this.prizeDescription = selectedPrize.getPrizeDescription();
            this.prizeImage = selectedPrize.getPrizeImage();
            this.prizePoint = selectedPrize.getPrizePoint();
            this.prizeQty = selectedPrize.getPrizeQty();
            setSoftDelete(getSelectedPrize().getSoftDelete());
            if (getSoftDelete() == true) {
                this.setSoftDeleteString("true");
            } else {
                this.setSoftDeleteString("false");
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to get Prize"));
        }
        try {
            setSelectedPrize(prizeSessionLocal.getPrizeById(getPrizeID()));
        } catch (Exception e) {

        }
    }

    public void deleteType() {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String tIdStr = params.get("typeID");
        Long tId = Long.parseLong(tIdStr);

        trippyEventTypeSessionLocal.removeTrippyType(tId);
        init();
    }

    public void recoverType() {
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

    public void removeType() {
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

    public String updatePrize() throws ParseException {
        System.out.println("Entering updating prize");
        System.out.println("Prize name: " + getPrizeName());
        Prize toUpdate = new Prize();
        toUpdate.setPrizeID(getPrizeID());
        toUpdate.setPrizeName(getPrizeName());
        toUpdate.setPrizePoint(getPrizePoint());
        toUpdate.setPrizeQty(getPrizeQty());
        toUpdate.setPrizeDescription(getPrizeDescription());
        if (getSoftDeleteString().equals("true")) {
            toUpdate.setSoftDelete(true);
        } else {
            toUpdate.setSoftDelete(false);
        }
        if (prizeFile != null) {
            prizeImageFile = prizeFile.getSubmittedFileName();
            try {

                InputStream bytes = prizeFile.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://houszqzb:leonleon95@houseofcasesg.website/public_html/image-upload/trippyImages/" + prizeFile.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        

//        if(getSoftDeleteString().equals("true"))
//            toUpdate.setSoftDelete((Boolean) true);
//        else
//            toUpdate.setSoftDelete((Boolean) false);
//        //toUpdate.setSoftDelete(getSoftDelete());
        toUpdate.setPrizeImage(prizeImageFile);
        try {
            prizeSessionLocal.updatePrize(toUpdate);
        } catch (Exception e) {

        }
        return "managePrize.xhtml?faces-redirect=true";
    }

    public String createPrize() throws ParseException {

        if (prizeFile != null) {
            prizeImageFile = prizeFile.getSubmittedFileName();
            try {

                InputStream bytes = prizeFile.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://houszqzb:leonleon95@houseofcasesg.website/public_html/image-upload/trippyImages/" + prizeFile.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        
        System.out.println("Entering creating prize");
        Prize toCreate = new Prize();
        toCreate.setPrizeName(getPrizeName());
        toCreate.setPrizePoint(getPrizePoint());
        toCreate.setPrizeQty(getPrizeQty());
        toCreate.setPrizeDescription(getPrizeDescription());
        toCreate.setSoftDelete(false);
        toCreate.setPrizeImage(prizeImageFile);
        prizeSessionLocal.createPrize(toCreate);

        return "managePrize.xhtml?faces-redirect=true";
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

    /**
     * @return the prizeID
     */
    public Long getPrizeID() {
        return prizeID;
    }

    /**
     * @param prizeID the prizeID to set
     */
    public void setPrizeID(Long prizeID) {
        this.prizeID = prizeID;
    }

    /**
     * @return the prizeName
     */
    public String getPrizeName() {
        return prizeName;
    }

    /**
     * @param prizeName the prizeName to set
     */
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    /**
     * @return the prizeDescription
     */
    public String getPrizeDescription() {
        return prizeDescription;
    }

    /**
     * @param prizeDescription the prizeDescription to set
     */
    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    /**
     * @return the prizePoint
     */
    public int getPrizePoint() {
        return prizePoint;
    }

    /**
     * @param prizePoint the prizePoint to set
     */
    public void setPrizePoint(int prizePoint) {
        this.prizePoint = prizePoint;
    }

    /**
     * @return the prizeQty
     */
    public int getPrizeQty() {
        return prizeQty;
    }

    /**
     * @param prizeQty the prizeQty to set
     */
    public void setPrizeQty(int prizeQty) {
        this.prizeQty = prizeQty;
    }

    /**
     * @return the prizeString
     */
    public String getPrizeString() {
        return prizeString;
    }

    /**
     * @param prizeString the prizeString to set
     */
    public void setPrizeString(String prizeString) {
        this.prizeString = prizeString;
    }

    /**
     * @return the prizeImageString
     */
    public String getPrizeImageString() {
        return prizeImageString;
    }

    /**
     * @param prizeImageString the prizeImageString to set
     */
    public void setPrizeImageString(String prizeImageString) {
        this.prizeImageString = prizeImageString;
    }

    /**
     * @return the prizeImage
     */
    public String getPrizeImage() {
        return prizeImage;
    }

    /**
     * @param prizeImage the prizeImage to set
     */
    public void setPrizeImage(String prizeImage) {
        this.prizeImage = prizeImage;
    }

    /**
     * @return the prizeStringArray
     */
    public List<String> getPrizeStringArray() {
        return prizeStringArray;
    }

    /**
     * @param prizeStringArray the prizeStringArray to set
     */
    public void setPrizeStringArray(List<String> prizeStringArray) {
        this.prizeStringArray = prizeStringArray;
    }

    /**
     * @return the prizeImageStringArray
     */
    public List<String> getPrizeImageStringArray() {
        return prizeImageStringArray;
    }

    /**
     * @param prizeImageStringArray the prizeImageStringArray to set
     */
    public void setPrizeImageStringArray(List<String> prizeImageStringArray) {
        this.prizeImageStringArray = prizeImageStringArray;
    }

    /**
     * @return the listOfPrize
     */
    public List<Prize> getListOfPrize() {
        return listOfPrize;
    }

    /**
     * @param listOfPrize the listOfPrize to set
     */
    public void setListOfPrize(List<Prize> listOfPrize) {
        this.listOfPrize = listOfPrize;
    }
    
    /**
     * @return the imgFile
     */
    public String getImgFile() {
        return imgFile;
    }

    /**
     * @param imgFile the imgFile to set
     */
    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    /**
     * @return the file
     */
    public Part getPrizeFile() {
        return prizeFile;
    }

    /**
     * @param file the file to set
     */
    public void setPrizeFile(Part prizeFile) {
        this.prizeFile = prizeFile;
    }

    /**
     * @return the selectedPrize
     */
    public Prize getSelectedPrize() {
        return selectedPrize;
    }

    /**
     * @param selectedPrize the selectedPrize to set
     */
    public void setSelectedPrize(Prize selectedPrize) {
        this.selectedPrize = selectedPrize;
    }
    
    /**
     * @return the prizeImageFile
     */
    public String getPrizeImageFile() {
        return prizeImageFile;
    }

    /**
     * @param prizeImageFile the prizeImageFile to set
     */
    public void setPrizeImageFile(String prizeImageFile) {
        this.prizeImageFile = prizeImageFile;
    }
}
