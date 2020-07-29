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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPessoa.findAll", query = "SELECT t FROM TbPessoa t"),
    @NamedQuery(name = "TbPessoa.findByIdPessoa", query = "SELECT t FROM TbPessoa t WHERE t.idPessoa = :idPessoa"),
    @NamedQuery(name = "TbPessoa.findByNomPessoa", query = "SELECT t FROM TbPessoa t WHERE t.nomPessoa = :nomPessoa"),
    @NamedQuery(name = "TbPessoa.findByMsgCelular", query = "SELECT t FROM TbPessoa t WHERE t.msgCelular = :msgCelular"),
    @NamedQuery(name = "TbPessoa.findByMsgEmail", query = "SELECT t FROM TbPessoa t WHERE t.msgEmail = :msgEmail")})
public class TbPessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pessoa")
    private Integer idPessoa;
    @Basic(optional = false)
    @Column(name = "nom_pessoa")
    private String nomPessoa;
    @Column(name = "msg_celular")
    private String msgCelular;
    @Basic(optional = false)
    @Column(name = "msg_email")
    private String msgEmail;
    @ManyToMany(mappedBy = "tbPessoaCollection")
    private Collection<TbGrupoCelular> tbGrupoCelularCollection;
    @ManyToMany(mappedBy = "tbPessoaCollection")
    private Collection<TbGrupoEmail> tbGrupoEmailCollection;
    @JoinColumn(name = "tb_empresa_id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private TbEmpresa tbEmpresaIdEmpresa;

    public TbPessoa() {
    }

    public TbPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TbPessoa(Integer idPessoa, String nomPessoa, String msgEmail) {
        this.idPessoa = idPessoa;
        this.nomPessoa = nomPessoa;
        this.msgEmail = msgEmail;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomPessoa() {
        return nomPessoa;
    }

    public void setNomPessoa(String nomPessoa) {
        this.nomPessoa = nomPessoa;
    }

    public String getMsgCelular() {
        return msgCelular;
    }

    public void setMsgCelular(String msgCelular) {
        this.msgCelular = msgCelular;
    }

    public String getMsgEmail() {
        return msgEmail;
    }

    public void setMsgEmail(String msgEmail) {
        this.msgEmail = msgEmail;
    }

    @XmlTransient
    public Collection<TbGrupoCelular> getTbGrupoCelularCollection() {
        return tbGrupoCelularCollection;
    }

    public void setTbGrupoCelularCollection(Collection<TbGrupoCelular> tbGrupoCelularCollection) {
        this.tbGrupoCelularCollection = tbGrupoCelularCollection;
    }

    @XmlTransient
    public Collection<TbGrupoEmail> getTbGrupoEmailCollection() {
        return tbGrupoEmailCollection;
    }

    public void setTbGrupoEmailCollection(Collection<TbGrupoEmail> tbGrupoEmailCollection) {
        this.tbGrupoEmailCollection = tbGrupoEmailCollection;
    }

    public TbEmpresa getTbEmpresaIdEmpresa() {
        return tbEmpresaIdEmpresa;
    }

    public void setTbEmpresaIdEmpresa(TbEmpresa tbEmpresaIdEmpresa) {
        this.tbEmpresaIdEmpresa = tbEmpresaIdEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPessoa)) {
            return false;
        }
        TbPessoa other = (TbPessoa) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbPessoa[ idPessoa=" + idPessoa + " ]";
    }
    
}
