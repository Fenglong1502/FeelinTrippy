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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dk349
 */
@Entity
public class PrizeOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prizeOrderID;
    
    private Long customerID;
    private Prize prizeRedeemed;
    
    @Temporal(TemporalType.DATE)
    private Date redemptionDate;
    private int quantity;
    private int pointsUsed;

    public Long getPrizeOrderID() {
        return prizeOrderID;
    }

    public void setPrizeOrderID(Long prizeOrderID) {
        this.prizeOrderID = prizeOrderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prizeOrderID != null ? prizeOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrizeOrder)) {
            return false;
        }
        PrizeOrder other = (PrizeOrder) object;
        if ((this.prizeOrderID == null && other.prizeOrderID != null) || (this.prizeOrderID != null && !this.prizeOrderID.equals(other.prizeOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PrizeOrder[ id=" + prizeOrderID + " ]";
    }

    /**
     * @return the customerID
     */
    public Long getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the prizeRedeemed
     */
    public Prize getPrizeRedeemed() {
        return prizeRedeemed;
    }

    /**
     * @param prizeRedeemed the prizeRedeemed to set
     */
    public void setPrizeRedeemed(Prize prizeRedeemed) {
        this.prizeRedeemed = prizeRedeemed;
    }

    /**
     * @return the RedemptionDate
     */
    public Date getRedemptionDate() {
        return redemptionDate;
    }

    /**
     * @param RedemptionDate the RedemptionDate to set
     */
    public void setRedemptionDate(Date redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the pointsUsed
     */
    public int getPointsUsed() {
        return pointsUsed;
    }

    /**
     * @param pointsUsed the pointsUsed to set
     */
    public void setPointsUsed(int pointsUsed) {
        this.pointsUsed = pointsUsed;
    }
    
}
