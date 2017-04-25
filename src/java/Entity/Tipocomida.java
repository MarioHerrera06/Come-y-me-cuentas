/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produccion
 */
@Entity
@Table(name = "tipocomida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocomida.findAll", query = "SELECT t FROM Tipocomida t"),
    @NamedQuery(name = "Tipocomida.findByCodTComida", query = "SELECT t FROM Tipocomida t WHERE t.codTComida = :codTComida"),
    @NamedQuery(name = "Tipocomida.findByTipoComida", query = "SELECT t FROM Tipocomida t WHERE t.tipoComida = :tipoComida")})
public class Tipocomida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_t_comida", nullable = false)
    private Integer codTComida;
    @Size(max = 30)
    @Column(name = "tipoComida", length = 30)
    private String tipoComida;
    @OneToMany(mappedBy = "codTipoComida")
    private Collection<Restaurante> restauranteCollection;

    public Tipocomida() {
    }

    public Tipocomida(Integer codTComida) {
        this.codTComida = codTComida;
    }

    public Integer getCodTComida() {
        return codTComida;
    }

    public void setCodTComida(Integer codTComida) {
        this.codTComida = codTComida;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    @XmlTransient
    public Collection<Restaurante> getRestauranteCollection() {
        return restauranteCollection;
    }

    public void setRestauranteCollection(Collection<Restaurante> restauranteCollection) {
        this.restauranteCollection = restauranteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTComida != null ? codTComida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocomida)) {
            return false;
        }
        Tipocomida other = (Tipocomida) object;
        if ((this.codTComida == null && other.codTComida != null) || (this.codTComida != null && !this.codTComida.equals(other.codTComida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tipocomida[ codTComida=" + codTComida + " ]";
    }
    
}
