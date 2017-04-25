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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Produccion
 */
@Entity
@Table(name = "restaurante", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_restaurante"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurante.findAll", query = "SELECT r FROM Restaurante r"),
    @NamedQuery(name = "Restaurante.findByCodRestaurante", query = "SELECT r FROM Restaurante r WHERE r.codRestaurante = :codRestaurante"),
    @NamedQuery(name = "Restaurante.findByNomRestaurante", query = "SELECT r FROM Restaurante r WHERE r.nomRestaurante = :nomRestaurante"),
    @NamedQuery(name = "Restaurante.findByDescripcion", query = "SELECT r FROM Restaurante r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Restaurante.findByDireccion", query = "SELECT r FROM Restaurante r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Restaurante.findByTelefono", query = "SELECT r FROM Restaurante r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "Restaurante.findByImagen", query = "SELECT r FROM Restaurante r WHERE r.imagen = :imagen")})
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_restaurante", nullable = false)
    private Integer codRestaurante;
    @Size(max = 30)
    @Column(name = "nom_restaurante", length = 30)
    private String nomRestaurante;
    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @Size(max = 30)
    @Column(name = "direccion", length = 30)
    private String direccion;
    @Column(name = "telefono")
    private Integer telefono;
    @Size(max = 100)
    @Column(name = "imagen", length = 100)
    private String imagen;
    @OneToMany(mappedBy = "codRestaurante")
    private Collection<Calificacion> calificacionCollection;
    @JoinColumn(name = "cod_rangoHora", referencedColumnName = "cod_r_hora")
    @ManyToOne
    private Rangohora codrangoHora;
    @JoinColumn(name = "cod_horario", referencedColumnName = "cod_horario")
    @ManyToOne
    private Horario codHorario;
    @JoinColumn(name = "cod_tipo_comida", referencedColumnName = "cod_t_comida")
    @ManyToOne
    private Tipocomida codTipoComida;
    @OneToMany(mappedBy = "codRestaurante")
    private Collection<Comentario> comentarioCollection;

    public Restaurante() {
    }

    public Restaurante(Integer codRestaurante) {
        this.codRestaurante = codRestaurante;
    }

    public Integer getCodRestaurante() {
        return codRestaurante;
    }

    public void setCodRestaurante(Integer codRestaurante) {
        this.codRestaurante = codRestaurante;
    }

    public String getNomRestaurante() {
        return nomRestaurante;
    }

    public void setNomRestaurante(String nomRestaurante) {
        this.nomRestaurante = nomRestaurante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public Collection<Calificacion> getCalificacionCollection() {
        return calificacionCollection;
    }

    public void setCalificacionCollection(Collection<Calificacion> calificacionCollection) {
        this.calificacionCollection = calificacionCollection;
    }

    public Rangohora getCodrangoHora() {
        return codrangoHora;
    }

    public void setCodrangoHora(Rangohora codrangoHora) {
        this.codrangoHora = codrangoHora;
    }

    public Horario getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(Horario codHorario) {
        this.codHorario = codHorario;
    }

    public Tipocomida getCodTipoComida() {
        return codTipoComida;
    }

    public void setCodTipoComida(Tipocomida codTipoComida) {
        this.codTipoComida = codTipoComida;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRestaurante != null ? codRestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurante)) {
            return false;
        }
        Restaurante other = (Restaurante) object;
        if ((this.codRestaurante == null && other.codRestaurante != null) || (this.codRestaurante != null && !this.codRestaurante.equals(other.codRestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Restaurante[ codRestaurante=" + codRestaurante + " ]";
    }
    
}
