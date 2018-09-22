package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrippyEventType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long typeID;
    
    private String typeName;
    private Boolean softDelete;
    
    public TrippyEventType (String typeName, Boolean softDelete) {
        this.typeName = typeName;
        this.softDelete = softDelete;
    }
    
    
    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeID != null ? typeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrippyEventType)) {
            return false;
        }
        TrippyEventType other = (TrippyEventType) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TrippyEventType[ id=" + typeID + " ]";
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    
}
