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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import org.jboss.weld.context.RequestContext;
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
    private int searchValue = 100;
    private TrippyEventItem selectedEventItem;
    private List<TrippyEventItem> searchEvents = new ArrayList<TrippyEventItem>();
    private String phoneNum;
    private String sharingCode;

    private String script = null;

    private String tempPic;
    private String tempName;
    private Date tempDate;
    private int tempPoint;

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

    public String claim(Long POid) throws IOException {

        PrizeOrder po = prizeSessionLocal.getPrizeOrderByID(POid);
        tempName = po.getPrizeRedeemed().getPrizeName();
        tempPic = po.getPrizeRedeemed().getPrizeImage();
        tempPoint = po.getPointsUsed();
        tempDate = po.getRedemptionDate();

        prizeSessionLocal.claimPrize(POid);
        return "displayQRCode.xhtml?faces-redirect=true";
    }

    public List<PrizeOrder> getPrizeOrder(Customer c) {
        return prizeSessionLocal.getPrizeRedeemed(c.getUserID());

    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void testingFunction() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('User or password incorrect');");
        out.println("location='activities.xhtml';");
        out.println("</script>");
//        script = "alert('peek-a-boo');";
//        script = null;

    }

    public String redeem(Prize p) throws IOException, NoResultException {
        FacesContext context = FacesContext.getCurrentInstance();
        Customer c = (Customer) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInCustomer}").getValue(context);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        if (p.getPrizePoint() > c.getPoints()) {

            out.println("<script type=\"text/javascript\">");
            out.println("alert('You do not have enough points');");
            out.println("</script>");
        } else {
            boolean result = prizeSessionLocal.redeemPrize(c.getUserID(), p.getPrizeID(), 1);
            if (result == true) {
                customerSessionLocal.deductPoints(c, p.getPrizePoint());
                c = customerSessionLocal.getCustomerById(c.getUserID());
                authBean.setLoggedInCustomer(c);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('The reward is out of stock');");
                out.println("</script>");
            }
        }
        return "rewards.xhtml?faces-redirect=true";
    }

    public TrippyManagedBean() {

    }

    public List<Prize> getPrizes() {
        return prizeSessionLocal.getAllPrize();
    }

    public String generateRandomEvent() {
        TrippyEventType searchType = trippyEventTypeSessionLocal.searchTrippyEventType("adventure");
        List<TrippyEventItem> listToFilter;
        searchEvents = new ArrayList<>();

        if (searchTypeStr.equals("")) {
            listToFilter = trippyEventSessionLocal.searchEventListByPrice((double) searchValue);
        } else {
            searchType = trippyEventTypeSessionLocal.searchTrippyEventType(searchTypeStr);
            listToFilter = trippyEventSessionLocal.searchEventListByConditions(searchType, (double) searchValue);
        }

        for (int i = 0; i < 3; i++) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(listToFilter.size());

            TrippyEventItem tei = listToFilter.get(index);
            if (searchEvents.isEmpty()) {
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

        if (selectedEventItem.getPrice() > 0) {
            customerSessionLocal.addPoints(c, (int) Math.round(selectedEventItem.getPrice()));
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

        if (customerSessionLocal.isEventExist(eventShared, c.getUserID()) == false) {
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

    public String getTempPic() {
        return tempPic;
    }

    public void setTempPic(String tempPic) {
        this.tempPic = tempPic;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public Date getTempDate() {
        return tempDate;
    }

    public void setTempDate(Date tempDate) {
        this.tempDate = tempDate;
    }

    public int getTempPoint() {
        return tempPoint;
    }

    public void setTempPoint(int tempPoint) {
        this.tempPoint = tempPoint;
    }

}
