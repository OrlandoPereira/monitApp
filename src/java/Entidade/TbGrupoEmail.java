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
@Table(name = "tb_grupo_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbGrupoEmail.findAll", query = "SELECT t FROM TbGrupoEmail t"),
    @NamedQuery(name = "TbGrupoEmail.findByIdGrupoEmail", query = "SELECT t FROM TbGrupoEmail t WHERE t.idGrupoEmail = :idGrupoEmail"),
    @NamedQuery(name = "TbGrupoEmail.findByNomGrupoEmail", query = "SELECT t FROM TbGrupoEmail t WHERE t.nomGrupoEmail = :nomGrupoEmail")})
public class TbGrupoEmail implements Serializable {
    @ManyToMany(mappedBy = "tbGrupoEmailCollection")
    private Collection<TbAppCadastrado> tbAppCadastradoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo_email")
    private Integer idGrupoEmail;
    @Basic(optional = false)
    @Column(name = "nom_grupo_email")
    private String nomGrupoEmail;
    @JoinTable(name = "tb_pessoa_has_tb_grupo_email", joinColumns = {
        @JoinColumn(name = "tb_grupo_email_id_grupo_email", referencedColumnName = "id_grupo_email")}, inverseJoinColumns = {
        @JoinColumn(name = "tb_pessoa_id_pessoa", referencedColumnName = "id_pessoa")})
    @ManyToMany
    private Collection<TbPessoa> tbPessoaCollection;

    public TbGrupoEmail() {
    }

    public TbGrupoEmail(Integer idGrupoEmail) {
        this.idGrupoEmail = idGrupoEmail;
    }

    public TbGrupoEmail(Integer idGrupoEmail, String nomGrupoEmail) {
        this.idGrupoEmail = idGrupoEmail;
        this.nomGrupoEmail = nomGrupoEmail;
    }

    public Integer getIdGrupoEmail() {
        return idGrupoEmail;
    }

    public void setIdGrupoEmail(Integer idGrupoEmail) {
        this.idGrupoEmail = idGrupoEmail;
    }

    public String getNomGrupoEmail() {
        return nomGrupoEmail;
    }

    public void setNomGrupoEmail(String nomGrupoEmail) {
        this.nomGrupoEmail = nomGrupoEmail;
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
        hash += (idGrupoEmail != null ? idGrupoEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbGrupoEmail)) {
            return false;
        }
        TbGrupoEmail other = (TbGrupoEmail) object;
        if ((this.idGrupoEmail == null && other.idGrupoEmail != null) || (this.idGrupoEmail != null && !this.idGrupoEmail.equals(other.idGrupoEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbGrupoEmail[ idGrupoEmail=" + idGrupoEmail + " ]";
    }

    @XmlTransient
    public Collection<TbAppCadastrado> getTbAppCadastradoCollection() {
        return tbAppCadastradoCollection;
    }

    public void setTbAppCadastradoCollection(Collection<TbAppCadastrado> tbAppCadastradoCollection) {
        this.tbAppCadastradoCollection = tbAppCadastradoCollection;
    }
    
}
