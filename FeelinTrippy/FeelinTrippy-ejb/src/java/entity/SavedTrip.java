/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class SavedTrip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long savedTripID;
    
    @Temporal(TemporalType.DATE)
    private Date savedDate;
    private double price;
    @OneToOne
    private TrippyEventItem eventItem;
    @ManyToOne
    private Customer customer;

    public Long getSavedTripID() {
        return savedTripID;
    }

    public void setSavedTripID(Long savedTripID) {
        this.savedTripID = savedTripID;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TrippyEventItem getEventItem() {
        return eventItem;
    }

    public void setEventItem(TrippyEventItem eventItem) {
        this.eventItem = eventItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (savedTripID != null ? savedTripID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SavedTrip)) {
            return false;
        }
        SavedTrip other = (SavedTrip) object;
        if ((this.savedTripID == null && other.savedTripID != null) || (this.savedTripID != null && !this.savedTripID.equals(other.savedTripID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SavedTrip[ savedTripID=" + savedTripID + " ]";
    }
    
}
