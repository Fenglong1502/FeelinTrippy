/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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
    private List<TrippyEventItem> searchEvents = new ArrayList<TrippyEventItem>();

    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;
    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;

    public TrippyManagedBean() {

    }

    public String generateRandomEvent() {
        TrippyEventType searchType = trippyEventTypeSessionLocal.searchTrippyEventType("adventure");
        if (!searchTypeStr.equals("")) {
            searchType = trippyEventTypeSessionLocal.searchTrippyEventType(searchTypeStr);
        }

        List<TrippyEventItem> listToFilter;
        if (searchTypeStr.equals("everything")) {
            listToFilter = trippyEventSessionLocal.searchEventListByPrice((double)searchValue);
        } else {
            listToFilter = trippyEventSessionLocal.searchEventListByConditions(searchType, (double)searchValue);
        }

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
                if(isExist == false){
                    searchEvents.add(tei);
                }
            }
        }

        return "activities.xhtml?faces-redirect=true";
    }

    public void setEverything() {
        searchTypeStr = "everything";
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
