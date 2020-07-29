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
@Table(name = "tb_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEmpresa.findAll", query = "SELECT t FROM TbEmpresa t"),
    @NamedQuery(name = "TbEmpresa.findByIdEmpresa", query = "SELECT t FROM TbEmpresa t WHERE t.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "TbEmpresa.findByNomEmpresa", query = "SELECT t FROM TbEmpresa t WHERE t.nomEmpresa = :nomEmpresa"),
    @NamedQuery(name = "TbEmpresa.findBySiglaEmpresa", query = "SELECT t FROM TbEmpresa t WHERE t.siglaEmpresa = :siglaEmpresa")})
public class TbEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Basic(optional = false)
    @Column(name = "nom_empresa")
    private String nomEmpresa;
    @Column(name = "sigla_empresa")
    private String siglaEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmpresaIdEmpresa")
    private Collection<TbPessoa> tbPessoaCollection;

    public TbEmpresa() {
    }

    public TbEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public TbEmpresa(Integer idEmpresa, String nomEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nomEmpresa = nomEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getSiglaEmpresa() {
        return siglaEmpresa;
    }

    public void setSiglaEmpresa(String siglaEmpresa) {
        this.siglaEmpresa = siglaEmpresa;
    }

    @XmlTransient
    public Collection<TbPessoa> getTbPessoaCollection() {
        return tbPessoaCollection;
    }

    public void setTbPessoaCollection(Collection<TbPessoa> tbPessoaCollection) {
        this.tbPessoaCollection = tbPessoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbEmpresa)) {
            return false;
        }
        TbEmpresa other = (TbEmpresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbEmpresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
