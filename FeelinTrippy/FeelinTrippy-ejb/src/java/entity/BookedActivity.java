/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class BookedActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookedID;
    @Temporal(TemporalType.DATE)
    private Date bookedDate;
    
    
    @ManyToOne
    private Customer bookedBy;
    private double price;
    private int qty;
    private boolean status;
    private boolean isDone; //see if trip is done (to become past trip) 
    @OneToOne
    private TrippyEventItem eventItem;
    @OneToOne
    private QRDetail qrDetail;

    public BookedActivity(){
        
    }
    public BookedActivity(Date bookedDate, double price, int qty, boolean status, TrippyEventItem eventItem, QRDetail qrDetail, Customer bookedBy, boolean isDone){
        this();
        this.bookedDate = bookedDate;
        this.bookedBy = bookedBy;
        this.price = price;
        this.qty = qty;
        this.status = status;
        this.eventItem = eventItem;
        this.qrDetail = qrDetail;
        this.isDone = isDone;
    }
    
    public Long getbookedID() {
        return bookedID;
    }

    public void setbookedID(Long bookedID) {
        this.bookedID = bookedID;
    }

    public Long getBookedID() {
        return bookedID;
    }

    public void setBookedID(Long bookedID) {
        this.bookedID = bookedID;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TrippyEventItem getEventItem() {
        return eventItem;
    }

    public void setEventItem(TrippyEventItem eventItem) {
        this.eventItem = eventItem;
    }

    public QRDetail getQrDetail() {
        return qrDetail;
    }

    public void setQrDetail(QRDetail qrDetail) {
        this.qrDetail = qrDetail;
    }

    public Customer getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Customer bookedBy) {
        this.bookedBy = bookedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookedID != null ? bookedID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookedActivity)) {
            return false;
        }
        BookedActivity other = (BookedActivity) object;
        if ((this.bookedID == null && other.bookedID != null) || (this.bookedID != null && !this.bookedID.equals(other.bookedID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BookedActivity[ bookedID =" + bookedID + " ]";
    }

    /**
     * @return the isDone
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * @param isDone the isDone to set
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
}
