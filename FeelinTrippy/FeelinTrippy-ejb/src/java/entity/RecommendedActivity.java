/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author MC
 */
@Entity
public class RecommendedActivity implements Serializable {

    /**
     * @return the recImages
     */
    public String getRecImages() {
        return recImages;
    }

    /**
     * @param recImages the recImages to set
     */
    public void setRecImages(String recImages) {
        this.recImages = recImages;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recID;
    
    private String recName;
    private float recPrice;
    private String recDescription;
    private String recStatus;
    private String recImages;
    private Long recByCustomerID; 
    private String eventType;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    

    /**
     * @return the recName
     */
    public String getRecName() {
        return recName;
    }

    /**
     * @param recName the recName to set
     */
    public void setRecName(String recName) {
        this.recName = recName;
    }

    /**
     * @return the recPrice
     */
    public float getRecPrice() {
        return recPrice;
    }

    /**
     * @param recPrice the recPrice to set
     */
    public void setRecPrice(float recPrice) {
        this.recPrice = recPrice;
    }

    /**
     * @return the recDescription
     */
    public String getRecDescription() {
        return recDescription;
    }

    /**
     * @param recDescription the recDescription to set
     */
    public void setRecDescription(String recDescription) {
        this.recDescription = recDescription;
    }

    /**
     * @return the recStatus
     */
    public String getRecStatus() {
        return recStatus;
    }

    /**
     * @param recStatus the recStatus to set
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    /**
     * @return the recByCustomerID
     */
    public Long getRecByCustomerID() {
        return recByCustomerID;
    }

    /**
     * @param recByCustomerID the recByCustomerID to set
     */
    public void setRecByCustomerID(Long recByCustomerID) {
        this.recByCustomerID = recByCustomerID;
    }
    
    public Long getRecID() {
        return recID;
    }

    public void setRecID(Long recID) {
        this.recID = recID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recID != null ? recID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecommendedActivity)) {
            return false;
        }
        RecommendedActivity other = (RecommendedActivity) object;
        if ((this.recID == null && other.recID != null) || (this.recID != null && !this.recID.equals(other.recID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RecommendedActivity[ id=" + recID + " ]";
    }
    
}
