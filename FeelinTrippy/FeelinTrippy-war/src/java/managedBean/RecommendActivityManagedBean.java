/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import entity.RecommendedActivity;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import session.RecommendedActivitySessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@RequestScoped
public class RecommendActivityManagedBean {

    /**
     * Creates a new instance of RecommendActivityManagedBean
     */
    @EJB
    RecommendedActivitySessionLocal recommendedActivitySessionLocal;

    private String eventName;
    private String eventDescription;
    private float price;
    private String eventType;
    private String imgFile;
    private String address;
    private Customer user;

    public String save(Customer c) {
        RecommendedActivity ra = new RecommendedActivity();
        ra.setRecName(eventName);
        ra.setRecDescription(eventDescription);
        ra.setRecPrice(price);
        ra.setEventType(eventType);
        ra.setRecImages(eventName);
        ra.setAddress(address);
        ra.setRecStatus(null);
        ra.setRecByCustomerID(c.getUserID());
        
        
        recommendedActivitySessionLocal.createRecommendedActivity(ra);
        return "activitySaved.xhtml";
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public RecommendActivityManagedBean() {
    }

}
