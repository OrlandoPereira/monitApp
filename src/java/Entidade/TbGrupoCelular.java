/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author orlando
 */
@Entity
@Table(name = "tb_grupo_celular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbGrupoCelular.findAll", query = "SELECT t FROM TbGrupoCelular t"),
    @NamedQuery(name = "TbGrupoCelular.findByIdGrupoCel", query = "SELECT t FROM TbGrupoCelular t WHERE t.idGrupoCel = :idGrupoCel"),
    @NamedQuery(name = "TbGrupoCelular.findByNomGrupoCel", query = "SELECT t FROM TbGrupoCelular t WHERE t.nomGrupoCel = :nomGrupoCel")})
public class TbGrupoCelular implements Serializable {
    @ManyToMany(mappedBy = "tbGrupoCelularCollection")
    private Collection<TbAppCadastrado> tbAppCadastradoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo_cel")
    private Integer idGrupoCel;
    @Basic(optional = false)
    @Column(name = "nom_grupo_cel")
    private String nomGrupoCel;
    @JoinTable(name = "tb_pessoa_has_tb_grupo_celular", joinColumns = {
        @JoinColumn(name = "tb_grupo_celular_id_grupo_cel", referencedColumnName = "id_grupo_cel")}, inverseJoinColumns = {
        @JoinColumn(name = "tb_pessoa_id_pessoa", referencedColumnName = "id_pessoa")})
    @ManyToMany
    private Collection<TbPessoa> tbPessoaCollection;

    public TbGrupoCelular() {
    }

    public TbGrupoCelular(Integer idGrupoCel) {
        this.idGrupoCel = idGrupoCel;
    }

    public TbGrupoCelular(Integer idGrupoCel, String nomGrupoCel) {
        this.idGrupoCel = idGrupoCel;
        this.nomGrupoCel = nomGrupoCel;
    }

    public Integer getIdGrupoCel() {
        return idGrupoCel;
    }

    public void setIdGrupoCel(Integer idGrupoCel) {
        this.idGrupoCel = idGrupoCel;
    }

    public String getNomGrupoCel() {
        return nomGrupoCel;
    }

    public void setNomGrupoCel(String nomGrupoCel) {
        this.nomGrupoCel = nomGrupoCel;
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
        hash += (idGrupoCel != null ? idGrupoCel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbGrupoCelular)) {
            return false;
        }
        TbGrupoCelular other = (TbGrupoCelular) object;
        if ((this.idGrupoCel == null && other.idGrupoCel != null) || (this.idGrupoCel != null && !this.idGrupoCel.equals(other.idGrupoCel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbGrupoCelular[ idGrupoCel=" + idGrupoCel + " ]";
    }

    @XmlTransient
    public Collection<TbAppCadastrado> getTbAppCadastradoCollection() {
        return tbAppCadastradoCollection;
    }

    public void setTbAppCadastradoCollection(Collection<TbAppCadastrado> tbAppCadastradoCollection) {
        this.tbAppCadastradoCollection = tbAppCadastradoCollection;
    }
    
}
