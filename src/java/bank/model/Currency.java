/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author prakashRajagopalan
 */
@Entity
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String currencyType;
    private float rates;

    public String currencyType() {
        return currencyType;
    }

    public void currencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    
     public float rates() {
        return rates;
    }

    public void rates(float rates) {
        this.rates = rates;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currencyType!= null ? currencyType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.currencyType == null && other.currencyType != null) || (this.currencyType != null && !this.currencyType.equals(other.currencyType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bank.model.Currency[ id=" + currencyType + " ]";
    }
    
}
