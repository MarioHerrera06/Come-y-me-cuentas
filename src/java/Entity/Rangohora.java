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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produccion
 */
@Entity
@Table(name = "rangohora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rangohora.findAll", query = "SELECT r FROM Rangohora r"),
    @NamedQuery(name = "Rangohora.findByCodRHora", query = "SELECT r FROM Rangohora r WHERE r.codRHora = :codRHora"),
    @NamedQuery(name = "Rangohora.findByHoraInicio", query = "SELECT r FROM Rangohora r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "Rangohora.findByHoraFin", query = "SELECT r FROM Rangohora r WHERE r.horaFin = :horaFin")})
public class Rangohora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_r_hora", nullable = false)
    private Integer codRHora;
    @Column(name = "horaInicio")
    private Integer horaInicio;
    @Column(name = "horaFin")
    private Integer horaFin;
    @OneToMany(mappedBy = "codRHora")
    private Collection<Horario> horarioCollection;
    @OneToMany(mappedBy = "codrangoHora")
    private Collection<Restaurante> restauranteCollection;

    public Rangohora() {
    }

    public Rangohora(Integer codRHora) {
        this.codRHora = codRHora;
    }

    public Integer getCodRHora() {
        return codRHora;
    }

    public void setCodRHora(Integer codRHora) {
        this.codRHora = codRHora;
    }

    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    @XmlTransient
    public Collection<Horario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<Horario> horarioCollection) {
        this.horarioCollection = horarioCollection;
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
        hash += (codRHora != null ? codRHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rangohora)) {
            return false;
        }
        Rangohora other = (Rangohora) object;
        if ((this.codRHora == null && other.codRHora != null) || (this.codRHora != null && !this.codRHora.equals(other.codRHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Rangohora[ codRHora=" + codRHora + " ]";
    }
    
}
