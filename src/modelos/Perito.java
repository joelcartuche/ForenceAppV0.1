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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "perito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perito.findAll", query = "SELECT p FROM Perito p")
    , @NamedQuery(name = "Perito.findByIdPerito", query = "SELECT p FROM Perito p WHERE p.idPerito = :idPerito")
    , @NamedQuery(name = "Perito.findByNumeroCasoInterno", query = "SELECT p FROM Perito p WHERE p.numeroCasoInterno = :numeroCasoInterno")})
public class Perito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerito")
    private Integer idPerito;
    @Column(name = "numeroCasoInterno")
    private String numeroCasoInterno;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerito")
    private Collection<Proceso> procesoCollection;

    public Perito() {
    }

    public Perito(String numeroCasoInterno, Persona idPersona) {
        this.numeroCasoInterno = numeroCasoInterno;
        this.idPersona = idPersona;
    }
    
    
    public Perito(Integer idPerito) {
        this.idPerito = idPerito;
    }

    public Integer getIdPerito() {
        return idPerito;
    }

    public void setIdPerito(Integer idPerito) {
        this.idPerito = idPerito;
    }

    public String getNumeroCasoInterno() {
        return numeroCasoInterno;
    }

    public void setNumeroCasoInterno(String numeroCasoInterno) {
        this.numeroCasoInterno = numeroCasoInterno;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public Collection<Proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<Proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerito != null ? idPerito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perito)) {
            return false;
        }
        Perito other = (Perito) object;
        if ((this.idPerito == null && other.idPerito != null) || (this.idPerito != null && !this.idPerito.equals(other.idPerito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Perito{" + "idPerito=" + idPerito + ", numeroCasoInterno=" + numeroCasoInterno + ", idPersona=" + idPersona + ", procesoCollection=" + procesoCollection + '}';
    }

   
    
}
