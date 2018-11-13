/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.BookedActivity;
import entity.Customer;
import entity.Prize;
import entity.PrizeOrder;
import entity.SavedTrip;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import error.NoResultException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import session.PrizeSessionLocal;
import session.BookedActivitySessionLocal;
import session.CustomerSessionLocal;
import session.SavedTripSessionLocal;
import session.TrippyEventSessionLocal;
import session.TrippyEventTypeSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class TrippyManagedBean implements Serializable {

    /**
     * Creates a new instance of TrippyManagedBean
     */
    private String searchTypeStr = "";
    private int searchValue = 50;
    private TrippyEventItem selectedEventItem;
    private List<TrippyEventItem> searchEvents = new ArrayList<TrippyEventItem>();
    private String phoneNum;
    private String sharingCode;
    
    
    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;
    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;
    @EJB
    SavedTripSessionLocal savedTripSessionLocal;
    @EJB
    CustomerSessionLocal customerSessionLocal;
    @EJB
    BookedActivitySessionLocal bookedActivitySessionLocal;
    @EJB
    PrizeSessionLocal prizeSessionLocal;

    @ManagedProperty(value = "#{authenticationManagedBean}")
    private AuthenticationManagedBean authBean;

    public AuthenticationManagedBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthenticationManagedBean authBean) {
        this.authBean = authBean;
    }

	public void claim(Long POid){
        
        prizeSessionLocal.claimPrize(POid);
        
        
        
    }
    public List<PrizeOrder> getPrizeOrder(Customer c) {
        return prizeSessionLocal.getPrizeRedeemed(c.getUserID());

    }

    public String redeem(Prize p) {
        if (prizeSessionLocal.redeemPrize(authBean.getId(), p.getPrizeID(), 1) == true) {
            System.out.println(p.getPrizeName());
            try {
                System.out.println(p.getPrizeName());

                customerSessionLocal.deductPoints(authBean.getLoggedInCustomer(), p.getPrizePoint());
                authBean.setPoints(customerSessionLocal.getCustomerById(authBean.getLoggedInCustomer().getUserID()).getPoints());
                System.out.println(p.getPrizeName());

            } catch (NoResultException e) {

            }
            return "alert('#')";
        } else {
            return "alert('#')";
        }

    }
	
    public TrippyManagedBean() {

    }

    public List<Prize> getPrizes() {
        return prizeSessionLocal.getAllPrize();
    }

    public String generateRandomEvent() {
        TrippyEventType searchType = trippyEventTypeSessionLocal.searchTrippyEventType("adventure");
        List<TrippyEventItem> listToFilter = null;
        
        if (searchTypeStr.equals("")) {
            listToFilter = trippyEventSessionLocal.searchEventListByPrice((double) searchValue);
        } else {
            searchType = trippyEventTypeSessionLocal.searchTrippyEventType(searchTypeStr);
            listToFilter = trippyEventSessionLocal.searchEventListByConditions(searchType, (double) searchValue);
        }

        searchEvents.clear();
        for (TrippyEventItem tei : listToFilter) {
            if (searchEvents.size() >= 3) {
                break;
            }

            if (searchEvents.size() == 0) {
                searchEvents.add(tei);
            } else {
                boolean isExist = false;
                for (TrippyEventItem tei2 : searchEvents) {
                    if (Objects.equals(tei2.getEventID(), tei.getEventID())) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist == false) {
                    searchEvents.add(tei);
                }
            }
        }

        return "activities.xhtml?faces-redirect=true";
    }

    public String addSavedTrip() throws NoResultException {
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);
        if (customerSessionLocal.isEventExist(selectedEventItem, c.getUserID()) == false) {
            SavedTrip s = new SavedTrip();
            s.setPrice(selectedEventItem.getPrice());
            s.setEventItem(selectedEventItem);

            s.setSavedDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

            savedTripSessionLocal.createdSavedTrip(s, c);
            s = savedTripSessionLocal.getNewlyAddSavedTrip();
            customerSessionLocal.addSavedTrip(c.getUserID(), s);
        }
        c = customerSessionLocal.getCustomerById(c.getUserID());
        authBean.setLoggedInCustomer(c);

        return "mySavedTrips.xhtml?faces-redirect=true";
    }

    public String removeSavedTrip() throws NoResultException {
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);

        List<SavedTrip> savedTrips = c.getSavedTrips();
        SavedTrip tripToDelete = new SavedTrip();
        for (SavedTrip s : savedTrips) {
            if (s.getEventItem().getEventName().equals(selectedEventItem.getEventName())) {
                tripToDelete = s;
            }
        }
        customerSessionLocal.removeSavedTrip(c.getUserID(), tripToDelete);
        savedTripSessionLocal.deleteSavedTrip(tripToDelete.getSavedTripID());

        c = customerSessionLocal.getCustomerById(c.getUserID());
        authBean.setLoggedInCustomer(c);

        return "mySavedTrips.xhtml?faces-redirect=true";
    }

    public String bookSavedTrip() throws NoResultException {
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);

        List<BookedActivity> bookedTrips = c.getBookedActivities();
        BookedActivity b = new BookedActivity();
        b.setBookedBy(c);
        b.setBookedDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        b.setEventItem(selectedEventItem);
        b.setIsDone(false);
        b.setPrice(selectedEventItem.getPrice());
        b.setQty(1);
        b.setStatus(true); // if it is cancelled, etc

        bookedActivitySessionLocal.createBookedActivity(b);
        b = bookedActivitySessionLocal.getNewlyAddSavedTrip();
        customerSessionLocal.addBookedTrip(c.getUserID(), b);
        
        if(selectedEventItem.getPrice() > 0){
            customerSessionLocal.addPoints(c, (int)Math.round(selectedEventItem.getPrice()));
        }

        c = customerSessionLocal.getCustomerById(c.getUserID());
        authBean.setLoggedInCustomer(c);

        return "bookedTrips.xhtml?faces-redirect=true";
    }

    public List<SavedTrip> getSavedTripByCategory(String eventType) {
        TrippyEventType type = trippyEventTypeSessionLocal.searchTrippyEventType(eventType);
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);

        return customerSessionLocal.getSavedTripByType(type, c);
    }

    public List<BookedActivity> getPastByCategory(String eventType) {
        TrippyEventType type = trippyEventTypeSessionLocal.searchTrippyEventType(eventType);
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);

        return customerSessionLocal.getPastTripByType(type, c);
    }

    public List<BookedActivity> getBookedByCategory(String eventType) {
        TrippyEventType type = trippyEventTypeSessionLocal.searchTrippyEventType(eventType);
        FacesContext context = FacesContext.getCurrentInstance();

        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);

        return customerSessionLocal.getBookedTripByType(type, c);
    }

    public void changeTypeToAnimal() {
        searchTypeStr = "Animals and Wildlife";
    }

    public void changeTypeToAdventure() {
        searchTypeStr = "Adventure";
    }

    public void changeTypeToMusic() {
        searchTypeStr = "Music and Night Life";
    }

    public void changeTypeToCulture() {
        searchTypeStr = "Art and Culture";
    }

    public void changeTypeToFoodie() {
        searchTypeStr = "Foodie";
    }

    public boolean checkIfTripExist() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long cId = (Long) context.getApplication().createValueBinding("#{authenticationManagedBean.id}").getValue(context);
        boolean result = customerSessionLocal.isEventExist(selectedEventItem, cId);

        return result;
    }
//    public void assignSelectedEventItem(ActionEvent event) {
//        
//	selectedEventItem = (TrippyEventItem)event.getComponent().getAttributes().get("event");
//         
//    }
//    

    public String shareTrip() {
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);

        if (customerSessionLocal.validPhoneNumber(phoneNum)) {
            String message = "You have received a shared trip from " + c.getFirstName() + " " + c.getLastName() + ". Please insert the code to add it into your saved Trip! \n'" + customerSessionLocal.generateSharingID(selectedEventItem) + "'";
            customerSessionLocal.shareTripsViaPhoneNumber(message, phoneNum);
        } else {
            String message = "You have received a shared trip from " + c.getFirstName() + " " + c.getLastName() + ". Please kindly sign up with us at www.FeelinTrippy.com to check out the shared trip :)";
            customerSessionLocal.shareTripsViaPhoneNumber(message, phoneNum);
        }
        phoneNum = "";

        return "mySavedTrips.xhtml?faces-redirect=true";
    }

    public String addTripFromSharing() throws NoResultException {
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);
       
        TrippyEventItem eventShared = customerSessionLocal.eventShared(sharingCode);
        
        if(customerSessionLocal.isEventExist(eventShared,c.getUserID()) == false){
            SavedTrip s = new SavedTrip();
            s.setPrice(eventShared.getPrice());
            s.setEventItem(eventShared);

            s.setSavedDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

            savedTripSessionLocal.createdSavedTrip(s, c);
            s = savedTripSessionLocal.getNewlyAddSavedTrip();
            customerSessionLocal.addSavedTrip(c.getUserID(), s);
        }
        c = customerSessionLocal.getCustomerById(c.getUserID());
        authBean.setLoggedInCustomer(c);
        
        return "mySavedTrips.xhtml?faces-redirect=true";
    }
    

    public String selectEvent(TrippyEventItem tei) {
        selectedEventItem = tei;

        return "activityDetails.xhtml?faces-redirect=true";
    }

    public String selectBookedEvent(TrippyEventItem tei) {
        selectedEventItem = tei;

        return "bookedActivityDetails.xhtml?faces-redirect=true";
    }
    
      public String selectPastEvent(TrippyEventItem tei) {
        selectedEventItem = tei;

        return "pastActivityDetails.xhtml?faces-redirect=true";
    }


    public void setEverything() {
        searchTypeStr = "everything";
    }

    public TrippyEventItem getSelectedEventItem() {
        return selectedEventItem;
    }

    public void setSelectedEventItem(TrippyEventItem selectedEventItem) {
        this.selectedEventItem = selectedEventItem;
    }

    public String getSearchTypeStr() {
        return searchTypeStr;
    }

    public String getSharingCode() {
        return sharingCode;
    }

    public void setSharingCode(String sharingCode) {
        this.sharingCode = sharingCode;
    }

    public void setSearchTypeStr(String searchTypeStr) {
        this.searchTypeStr = searchTypeStr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(int searchValue) {
        this.searchValue = searchValue;
    }

    public List<TrippyEventItem> getSearchEvents() {
        return searchEvents;
    }

    public void setSearchEvents(List<TrippyEventItem> searchEvents) {
        this.searchEvents = searchEvents;
    }

    public TrippyEventSessionLocal getTrippyEventSessionLocal() {
        return trippyEventSessionLocal;
    }

    public void setTrippyEventSessionLocal(TrippyEventSessionLocal trippyEventSessionLocal) {
        this.trippyEventSessionLocal = trippyEventSessionLocal;
    }

    public TrippyEventTypeSessionLocal getTrippyEventTypeSessionLocal() {
        return trippyEventTypeSessionLocal;
    }

    public void setTrippyEventTypeSessionLocal(TrippyEventTypeSessionLocal trippyEventTypeSessionLocal) {
        this.trippyEventTypeSessionLocal = trippyEventTypeSessionLocal;
    }

    

}
