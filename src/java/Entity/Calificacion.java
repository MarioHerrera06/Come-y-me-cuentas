/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Produccion
 */
@Entity
@Table(name = "calificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll", query = "SELECT c FROM Calificacion c"),
    @NamedQuery(name = "Calificacion.findByCodValor", query = "SELECT c FROM Calificacion c WHERE c.codValor = :codValor"),
    @NamedQuery(name = "Calificacion.findByNomValor", query = "SELECT c FROM Calificacion c WHERE c.nomValor = :nomValor")})
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_valor", nullable = false)
    private Integer codValor;
    @Size(max = 30)
    @Column(name = "nom_valor", length = 30)
    private String nomValor;
    @JoinColumn(name = "cod_restaurante", referencedColumnName = "cod_restaurante")
    @ManyToOne
    private Restaurante codRestaurante;
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne
    private Usuario codUsuario;

    public Calificacion() {
    }

    public Calificacion(Integer codValor) {
        this.codValor = codValor;
    }

    public Integer getCodValor() {
        return codValor;
    }

    public void setCodValor(Integer codValor) {
        this.codValor = codValor;
    }

    public String getNomValor() {
        return nomValor;
    }

    public void setNomValor(String nomValor) {
        this.nomValor = nomValor;
    }

    public Restaurante getCodRestaurante() {
        return codRestaurante;
    }

    public void setCodRestaurante(Restaurante codRestaurante) {
        this.codRestaurante = codRestaurante;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codValor != null ? codValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificacion)) {
            return false;
        }
        Calificacion other = (Calificacion) object;
        if ((this.codValor == null && other.codValor != null) || (this.codValor != null && !this.codValor.equals(other.codValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Calificacion[ codValor=" + codValor + " ]";
    }
    
}
