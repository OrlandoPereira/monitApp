/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

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
 * @author orlando
 */
@Entity
@Table(name = "tb_servidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbServidor.findAll", query = "SELECT t FROM TbServidor t"),
    @NamedQuery(name = "TbServidor.findByIdServidor", query = "SELECT t FROM TbServidor t WHERE t.idServidor = :idServidor"),
    @NamedQuery(name = "TbServidor.findByNumServidor", query = "SELECT t FROM TbServidor t WHERE t.numServidor = :numServidor"),
    @NamedQuery(name = "TbServidor.findByNomServidor", query = "SELECT t FROM TbServidor t WHERE t.nomServidor = :nomServidor"),
    @NamedQuery(name = "TbServidor.findByStServidor", query = "SELECT t FROM TbServidor t WHERE t.stServidor = :stServidor")})
public class TbServidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servidor")
    private Integer idServidor;
    @Basic(optional = false)
    @Column(name = "num_servidor")
    private String numServidor;
    @Column(name = "nom_servidor")
    private String nomServidor;
    @Basic(optional = false)
    @Column(name = "st_servidor")
    private boolean stServidor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbServidorIdServidor")
    private Collection<TbAppCadastrado> tbAppCadastradoCollection;

    public TbServidor() {
    }

    public TbServidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public TbServidor(Integer idServidor, String numServidor, boolean stServidor) {
        this.idServidor = idServidor;
        this.numServidor = numServidor;
        this.stServidor = stServidor;
    }

    public Integer getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public String getNumServidor() {
        return numServidor;
    }

    public void setNumServidor(String numServidor) {
        this.numServidor = numServidor;
    }

    public String getNomServidor() {
        return nomServidor;
    }

    public void setNomServidor(String nomServidor) {
        this.nomServidor = nomServidor;
    }

    public boolean getStServidor() {
        return stServidor;
    }

    public void setStServidor(boolean stServidor) {
        this.stServidor = stServidor;
    }

    @XmlTransient
    public Collection<TbAppCadastrado> getTbAppCadastradoCollection() {
        return tbAppCadastradoCollection;
    }

    public void setTbAppCadastradoCollection(Collection<TbAppCadastrado> tbAppCadastradoCollection) {
        this.tbAppCadastradoCollection = tbAppCadastradoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServidor != null ? idServidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbServidor)) {
            return false;
        }
        TbServidor other = (TbServidor) object;
        if ((this.idServidor == null && other.idServidor != null) || (this.idServidor != null && !this.idServidor.equals(other.idServidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbServidor[ idServidor=" + idServidor + " ]";
    }
    
}
