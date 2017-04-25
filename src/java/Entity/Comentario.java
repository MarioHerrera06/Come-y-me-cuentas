/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Produccion
 */
@Entity
@Table(name = "comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByCodComentario", query = "SELECT c FROM Comentario c WHERE c.codComentario = :codComentario"),
    @NamedQuery(name = "Comentario.findByDComentario", query = "SELECT c FROM Comentario c WHERE c.dComentario = :dComentario"),
    @NamedQuery(name = "Comentario.findByFecha", query = "SELECT c FROM Comentario c WHERE c.fecha = :fecha")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_comentario", nullable = false)
    private Integer codComentario;
    @Size(max = 140)
    @Column(name = "d_comentario", length = 140)
    private String dComentario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "cod_restaurante", referencedColumnName = "cod_restaurante")
    @ManyToOne
    private Restaurante codRestaurante;
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne
    private Usuario codUsuario;

    public Comentario() {
    }

    public Comentario(Integer codComentario) {
        this.codComentario = codComentario;
    }

    public Integer getCodComentario() {
        return codComentario;
    }

    public void setCodComentario(Integer codComentario) {
        this.codComentario = codComentario;
    }

    public String getDComentario() {
        return dComentario;
    }

    public void setDComentario(String dComentario) {
        this.dComentario = dComentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (codComentario != null ? codComentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.codComentario == null && other.codComentario != null) || (this.codComentario != null && !this.codComentario.equals(other.codComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Comentario[ codComentario=" + codComentario + " ]";
    }
    
}
