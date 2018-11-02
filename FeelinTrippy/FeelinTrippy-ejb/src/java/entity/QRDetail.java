/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author fengl
 */
@Entity
public class QRDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qrDetailID;
    
    private String qrText;
    @OneToOne(mappedBy = "qrDetail")
    private BookedActivity bookedActivity;

  
    
    public Long getQrDetailID() {
        return qrDetailID;
    }

    public void setQrDetailID(Long qrDetailID) {
        this.qrDetailID = qrDetailID;
    }

    public String getQrText() {
        return qrText;
    }

    public void setQrText(String qrText) {
        this.qrText = qrText;
    }

    public BookedActivity getBookedActivity() {
        return bookedActivity;
    }

    public void setBookedActivity(BookedActivity bookedActivity) {
        this.bookedActivity = bookedActivity;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrDetailID != null ? qrDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QRDetail)) {
            return false;
        }
        QRDetail other = (QRDetail) object;
        if ((this.qrDetailID == null && other.qrDetailID != null) || (this.qrDetailID != null && !this.qrDetailID.equals(other.qrDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.QRDetail[ qrDetailID=" + qrDetailID + " ]";
    }
    
}
