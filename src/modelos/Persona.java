/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author joelc
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Persona.findByNombreApellido", query = "SELECT p FROM Persona p WHERE p.nombreApellido = :nombreApellido")
    , @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Persona.findByTelefonoFijo", query = "SELECT p FROM Persona p WHERE p.telefonoFijo = :telefonoFijo")
    , @NamedQuery(name = "Persona.findByTelefonoCelular", query = "SELECT p FROM Persona p WHERE p.telefonoCelular = :telefonoCelular")
    , @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersona")
    private Integer idPersona;
    @Column(name = "nombreApellido")
    private String nombreApellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefonoFijo")
    private String telefonoFijo;
    @Column(name = "telefonoCelular")
    private String telefonoCelular;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "correo")
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private Collection<Perito> peritoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private Collection<Solicitante> solicitanteCollection;

    public Persona() {
    }

    public Persona(String nombreApellido, String direccion, String telefonoFijo, String telefonoCelular, String cedula, String correo) {
        this.nombreApellido = nombreApellido;
        this.direccion = direccion;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
        this.cedula = cedula;
        this.correo = correo;
    }
    
    
    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @XmlTransient
    public Collection<Perito> getPeritoCollection() {
        return peritoCollection;
    }

    public void setPeritoCollection(Collection<Perito> peritoCollection) {
        this.peritoCollection = peritoCollection;
    }

    @XmlTransient
    public Collection<Solicitante> getSolicitanteCollection() {
        return solicitanteCollection;
    }

    public void setSolicitanteCollection(Collection<Solicitante> solicitanteCollection) {
        this.solicitanteCollection = solicitanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombreApellido=" + nombreApellido + ", direccion=" + direccion + ", telefonoFijo=" + telefonoFijo + ", telefonoCelular=" + telefonoCelular + ", cedula=" + cedula + ", correo=" + correo + ", peritoCollection=" + peritoCollection + ", solicitanteCollection=" + solicitanteCollection + '}';
    }
    


}
