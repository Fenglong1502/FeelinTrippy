/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.BookedActivity;
import entity.Customer;
import entity.SavedTrip;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import error.NoResultException;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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

    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;
    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;
    @EJB
    SavedTripSessionLocal savedTripSessionLocal;
    @EJB
    CustomerSessionLocal customerSessionLocal;

    @ManagedProperty(value = "#{authenticationManagedBean}")
    private AuthenticationManagedBean authBean;

    public AuthenticationManagedBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthenticationManagedBean authBean) {
        this.authBean = authBean;
    }

    public TrippyManagedBean() {
        
    }

    public String generateRandomEvent() {
        TrippyEventType searchType = trippyEventTypeSessionLocal.searchTrippyEventType("adventure");
        List<TrippyEventItem> listToFilter;

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

//    public void assignSelectedEventItem(ActionEvent event) {
//        
//	selectedEventItem = (TrippyEventItem)event.getComponent().getAttributes().get("event");
//         
//    }
//    
    public String selectEvent(TrippyEventItem tei) {
        selectedEventItem = tei;

        return "activityDetails.xhtml?faces-redirect=true";
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

    public void setSearchTypeStr(String searchTypeStr) {
        this.searchTypeStr = searchTypeStr;
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
