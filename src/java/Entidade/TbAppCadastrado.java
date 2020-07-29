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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_app_cadastrado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAppCadastrado.findAll", query = "SELECT t FROM TbAppCadastrado t"),
    @NamedQuery(name = "TbAppCadastrado.findByIdApp", query = "SELECT t FROM TbAppCadastrado t WHERE t.idApp = :idApp"),
    @NamedQuery(name = "TbAppCadastrado.findByNomApp", query = "SELECT t FROM TbAppCadastrado t WHERE t.nomApp = :nomApp"),
    @NamedQuery(name = "TbAppCadastrado.findByCheckPadrao", query = "SELECT t FROM TbAppCadastrado t WHERE t.checkPadrao = :checkPadrao"),
    @NamedQuery(name = "TbAppCadastrado.findByCheckVerifErro", query = "SELECT t FROM TbAppCadastrado t WHERE t.checkVerifErro = :checkVerifErro")})
public class TbAppCadastrado implements Serializable {
    @JoinTable(name = "tb_app_cadastrado_has_tb_grupo_email", joinColumns = {
        @JoinColumn(name = "tb_app_cadastrado_id_app", referencedColumnName = "id_app")}, inverseJoinColumns = {
        @JoinColumn(name = "tb_grupo_email_id_grupo_email", referencedColumnName = "id_grupo_email")})
    @ManyToMany
    private Collection<TbGrupoEmail> tbGrupoEmailCollection;
    @JoinTable(name = "tb_app_cadastrado_has_tb_grupo_celular", joinColumns = {
        @JoinColumn(name = "tb_app_cadastrado_id_app", referencedColumnName = "id_app")}, inverseJoinColumns = {
        @JoinColumn(name = "tb_grupo_celular_id_grupo_cel", referencedColumnName = "id_grupo_cel")})
    @ManyToMany
    private Collection<TbGrupoCelular> tbGrupoCelularCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_app")
    private Integer idApp;
    @Basic(optional = false)
    @Column(name = "nom_app")
    private String nomApp;
    @Basic(optional = false)
    @Column(name = "check_padrao")
    private int checkPadrao;
    @Basic(optional = false)
    @Column(name = "check_verif_erro")
    private int checkVerifErro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbAppCadastradoIdApp")
    private Collection<TbStatusErro> tbStatusErroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbAppCadastradoIdApp")
    private Collection<TbScriptMonit> tbScriptMonitCollection;
    @JoinColumn(name = "tb_prioridade_id_prioridade", referencedColumnName = "id_prioridade")
    @ManyToOne(optional = false)
    private TbPrioridade tbPrioridadeIdPrioridade;
    @JoinColumn(name = "tb_servidor_id_servidor", referencedColumnName = "id_servidor")
    @ManyToOne(optional = false)
    private TbServidor tbServidorIdServidor;

    public TbAppCadastrado() {
    }

    public TbAppCadastrado(Integer idApp) {
        this.idApp = idApp;
    }

    public TbAppCadastrado(Integer idApp, String nomApp, int checkPadrao, int checkVerifErro) {
        this.idApp = idApp;
        this.nomApp = nomApp;
        this.checkPadrao = checkPadrao;
        this.checkVerifErro = checkVerifErro;
    }

    public Integer getIdApp() {
        return idApp;
    }

    public void setIdApp(Integer idApp) {
        this.idApp = idApp;
    }

    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    public int getCheckPadrao() {
        return checkPadrao;
    }

    public void setCheckPadrao(int checkPadrao) {
        this.checkPadrao = checkPadrao;
    }

    public int getCheckVerifErro() {
        return checkVerifErro;
    }

    public void setCheckVerifErro(int checkVerifErro) {
        this.checkVerifErro = checkVerifErro;
    }

    @XmlTransient
    public Collection<TbStatusErro> getTbStatusErroCollection() {
        return tbStatusErroCollection;
    }

    public void setTbStatusErroCollection(Collection<TbStatusErro> tbStatusErroCollection) {
        this.tbStatusErroCollection = tbStatusErroCollection;
    }

    @XmlTransient
    public Collection<TbScriptMonit> getTbScriptMonitCollection() {
        return tbScriptMonitCollection;
    }

    public void setTbScriptMonitCollection(Collection<TbScriptMonit> tbScriptMonitCollection) {
        this.tbScriptMonitCollection = tbScriptMonitCollection;
    }

    public TbPrioridade getTbPrioridadeIdPrioridade() {
        return tbPrioridadeIdPrioridade;
    }

    public void setTbPrioridadeIdPrioridade(TbPrioridade tbPrioridadeIdPrioridade) {
        this.tbPrioridadeIdPrioridade = tbPrioridadeIdPrioridade;
    }

    public TbServidor getTbServidorIdServidor() {
        return tbServidorIdServidor;
    }

    public void setTbServidorIdServidor(TbServidor tbServidorIdServidor) {
        this.tbServidorIdServidor = tbServidorIdServidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApp != null ? idApp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAppCadastrado)) {
            return false;
        }
        TbAppCadastrado other = (TbAppCadastrado) object;
        if ((this.idApp == null && other.idApp != null) || (this.idApp != null && !this.idApp.equals(other.idApp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbAppCadastrado[ idApp=" + idApp + " ]";
    }

    @XmlTransient
    public Collection<TbGrupoEmail> getTbGrupoEmailCollection() {
        return tbGrupoEmailCollection;
    }

    public void setTbGrupoEmailCollection(Collection<TbGrupoEmail> tbGrupoEmailCollection) {
        this.tbGrupoEmailCollection = tbGrupoEmailCollection;
    }

    @XmlTransient
    public Collection<TbGrupoCelular> getTbGrupoCelularCollection() {
        return tbGrupoCelularCollection;
    }

    public void setTbGrupoCelularCollection(Collection<TbGrupoCelular> tbGrupoCelularCollection) {
        this.tbGrupoCelularCollection = tbGrupoCelularCollection;
    }
    
}
