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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produccion
 */
@Entity
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByCodHorario", query = "SELECT h FROM Horario h WHERE h.codHorario = :codHorario")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_horario", nullable = false)
    private Integer codHorario;
    @JoinColumn(name = "cod_r_hora", referencedColumnName = "cod_r_hora")
    @ManyToOne
    private Rangohora codRHora;
    @OneToMany(mappedBy = "codHorario")
    private Collection<Restaurante> restauranteCollection;

    public Horario() {
    }

    public Horario(Integer codHorario) {
        this.codHorario = codHorario;
    }

    public Integer getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(Integer codHorario) {
        this.codHorario = codHorario;
    }

    public Rangohora getCodRHora() {
        return codRHora;
    }

    public void setCodRHora(Rangohora codRHora) {
        this.codRHora = codRHora;
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
        hash += (codHorario != null ? codHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.codHorario == null && other.codHorario != null) || (this.codHorario != null && !this.codHorario.equals(other.codHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Horario[ codHorario=" + codHorario + " ]";
    }
    
}
