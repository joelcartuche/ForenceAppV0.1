/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joelc
 */
@Entity
@Table(name = "proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
    , @NamedQuery(name = "Proceso.findByIdProceso", query = "SELECT p FROM Proceso p WHERE p.idProceso = :idProceso")
    , @NamedQuery(name = "Proceso.findByFechaSolicitud", query = "SELECT p FROM Proceso p WHERE p.fechaSolicitud = :fechaSolicitud")
    , @NamedQuery(name = "Proceso.findByFechaLimiteEntrega", query = "SELECT p FROM Proceso p WHERE p.fechaLimiteEntrega = :fechaLimiteEntrega")
    , @NamedQuery(name = "Proceso.findByValorPericia", query = "SELECT p FROM Proceso p WHERE p.valorPericia = :valorPericia")})
public class Proceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProceso")
    private Integer idProceso;
    @Column(name = "fechaSolicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Column(name = "fechaLimiteEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaLimiteEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorPericia")
    private Double valorPericia;
    @JoinColumn(name = "idPerito", referencedColumnName = "idPerito")
    @ManyToOne(optional = false)
    private Perito idPerito;
    @JoinColumn(name = "idSolicitante", referencedColumnName = "idSolicitante")
    @ManyToOne(optional = false)
    private Solicitante idSolicitante;

    public Proceso(Date fechaSolicitud, Date fechaLimiteEntrega, Double valorPericia, Perito idPerito, Solicitante idSolicitante) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaLimiteEntrega = fechaLimiteEntrega;
        this.valorPericia = valorPericia;
        this.idPerito = idPerito;
        this.idSolicitante = idSolicitante;
    }

    
    public Proceso() {
    }

    public Proceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaLimiteEntrega() {
        return fechaLimiteEntrega;
    }

    public void setFechaLimiteEntrega(Date fechaLimiteEntrega) {
        this.fechaLimiteEntrega = fechaLimiteEntrega;
    }

    public Double getValorPericia() {
        return valorPericia;
    }

    public void setValorPericia(Double valorPericia) {
        this.valorPericia = valorPericia;
    }

    public Perito getIdPerito() {
        return idPerito;
    }

    public void setIdPerito(Perito idPerito) {
        this.idPerito = idPerito;
    }

    public Solicitante getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Solicitante idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proceso{" + "idProceso=" + idProceso + ", fechaSolicitud=" + fechaSolicitud + ", fechaLimiteEntrega=" + fechaLimiteEntrega + ", valorPericia=" + valorPericia + ", idPerito=" + idPerito + ", idSolicitante=" + idSolicitante + '}';
    }

   
}
