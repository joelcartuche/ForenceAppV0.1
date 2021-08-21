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
@Table(name = "solicitante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitante.findAll", query = "SELECT s FROM Solicitante s")
    , @NamedQuery(name = "Solicitante.findByIdSolicitante", query = "SELECT s FROM Solicitante s WHERE s.idSolicitante = :idSolicitante")
    , @NamedQuery(name = "Solicitante.findByUrlPericia", query = "SELECT s FROM Solicitante s WHERE s.urlPericia = :urlPericia")
    , @NamedQuery(name = "Solicitante.findByNumeroProceso", query = "SELECT s FROM Solicitante s WHERE s.numeroProceso = :numeroProceso")
    , @NamedQuery(name = "Solicitante.findByUnidadPertenece", query = "SELECT s FROM Solicitante s WHERE s.unidadPertenece = :unidadPertenece")})
public class Solicitante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitante")
    private Integer idSolicitante;
    @Column(name = "urlPericia")
    private String urlPericia;
    @Column(name = "numeroProceso")
    private String numeroProceso;
    @Column(name = "unidadPertenece")
    private String unidadPertenece;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitante")
    private Collection<Proceso> procesoCollection;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public Solicitante() {
    }

    public Solicitante(String urlPericia, String numeroProceso, String unidadPertenece, Persona idPersona) {
        this.urlPericia = urlPericia;
        this.numeroProceso = numeroProceso;
        this.unidadPertenece = unidadPertenece;
        this.idPersona = idPersona;
    }
    
    
    
    public Solicitante(Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getUrlPericia() {
        return urlPericia;
    }

    public void setUrlPericia(String urlPericia) {
        this.urlPericia = urlPericia;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public String getUnidadPertenece() {
        return unidadPertenece;
    }

    public void setUnidadPertenece(String unidadPertenece) {
        this.unidadPertenece = unidadPertenece;
    }

    @XmlTransient
    public Collection<Proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<Proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitante != null ? idSolicitante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitante)) {
            return false;
        }
        Solicitante other = (Solicitante) object;
        if ((this.idSolicitante == null && other.idSolicitante != null) || (this.idSolicitante != null && !this.idSolicitante.equals(other.idSolicitante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solicitante{" + "idSolicitante=" + idSolicitante + ", urlPericia=" + urlPericia + ", numeroProceso=" + numeroProceso + ", unidadPertenece=" + unidadPertenece + ", procesoCollection=" + procesoCollection + ", idPersona=" + idPersona + '}';
    }

    
    
}
