package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prize implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prizeID;

    private String prizeName;
    private int prizeQty;
    private String prizeDescription;
    private int prizePoint;
    //Soft delete is used to remove/hide prizes which is not offered on Trippy
    private List<String> prizeImage;
    private Boolean softDelete;
    
    public Prize(){
        
    }
    
    public Prize(String prizeName, int prizePoint, String prizeDescription, List<String> prizeImage, Boolean softDelete) {
        this();
        this.prizeName = prizeName;
        this.prizePoint = prizePoint;
        this.prizeDescription = prizeDescription;
        this.prizeImage = prizeImage;
        this.softDelete = softDelete;
    }
    
    //getter and setters
    public Long getPrizeID() {
        return prizeID;
    }

    public void setPrizeID(Long prizeID) {
        this.prizeID = prizeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prizeID != null ? prizeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prize)) {
            return false;
        }
        Prize other = (Prize) object;
        if ((this.prizeID == null && other.prizeID != null) || (this.prizeID != null && !this.prizeID.equals(other.prizeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prize[ id=" + prizeID + " ]";
    }

    /**
     * @return the prizeName
     */
    public String getPrizeName() {
        return prizeName;
    }

    /**
     * @param prizeName the prizeName to set
     */
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    /**
     * @return the prizeQty
     */
    public int getPrizeQty() {
        return prizeQty;
    }

    /**
     * @param prizeQty the prizeQty to set
     */
    public void setPrizeQty(int prizeQty) {
        this.prizeQty = prizeQty;
    }

    /**
     * @return the prizeDescription
     */
    public String getPrizeDescription() {
        return prizeDescription;
    }

    /**
     * @param prizeDescription the prizeDescription to set
     */
    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    /**
     * @return the prizePoints
     */
    public int getPrizePoint() {
        return prizePoint;
    }

    /**
     * @param prizePoints the prizePoints to set
     */
    public void setPrizePoint(int prizePoint) {
        this.prizePoint = prizePoint;
    }

    /**
     * @return the softDelete
     */
    public Boolean getSoftDelete() {
        return softDelete;
    }

    /**
     * @param softDelete the softDelete to set
     */
    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }

    /**
     * @return the prizeImage
     */
    public List<String> getPrizeImage() {
        return prizeImage;
    }

    /**
     * @param prizeImage the prizeImage to set
     */
    public void setPrizeImage(List<String> prizeImage) {
        this.prizeImage = prizeImage;
    }
    
}
