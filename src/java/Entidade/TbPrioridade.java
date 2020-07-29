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
@Table(name = "tb_prioridade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPrioridade.findAll", query = "SELECT t FROM TbPrioridade t"),
    @NamedQuery(name = "TbPrioridade.findByIdPrioridade", query = "SELECT t FROM TbPrioridade t WHERE t.idPrioridade = :idPrioridade"),
    @NamedQuery(name = "TbPrioridade.findByTpPrioridade", query = "SELECT t FROM TbPrioridade t WHERE t.tpPrioridade = :tpPrioridade")})
public class TbPrioridade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prioridade")
    private Integer idPrioridade;
    @Basic(optional = false)
    @Column(name = "tp_prioridade")
    private String tpPrioridade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbPrioridadeIdPrioridade")
    private Collection<TbAppCadastrado> tbAppCadastradoCollection;

    public TbPrioridade() {
    }

    public TbPrioridade(Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    public TbPrioridade(Integer idPrioridade, String tpPrioridade) {
        this.idPrioridade = idPrioridade;
        this.tpPrioridade = tpPrioridade;
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    public String getTpPrioridade() {
        return tpPrioridade;
    }

    public void setTpPrioridade(String tpPrioridade) {
        this.tpPrioridade = tpPrioridade;
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
        hash += (idPrioridade != null ? idPrioridade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPrioridade)) {
            return false;
        }
        TbPrioridade other = (TbPrioridade) object;
        if ((this.idPrioridade == null && other.idPrioridade != null) || (this.idPrioridade != null && !this.idPrioridade.equals(other.idPrioridade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbPrioridade[ idPrioridade=" + idPrioridade + " ]";
    }
    
}
