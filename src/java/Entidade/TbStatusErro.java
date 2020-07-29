/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

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
 * @author orlando
 */
@Entity
@Table(name = "tb_status_erro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbStatusErro.findAll", query = "SELECT t FROM TbStatusErro t"),
    @NamedQuery(name = "TbStatusErro.findByIdErro", query = "SELECT t FROM TbStatusErro t WHERE t.idErro = :idErro"),
    @NamedQuery(name = "TbStatusErro.findByStErroApp", query = "SELECT t FROM TbStatusErro t WHERE t.stErroApp = :stErroApp"),
    @NamedQuery(name = "TbStatusErro.findByDtInicErro", query = "SELECT t FROM TbStatusErro t WHERE t.dtInicErro = :dtInicErro"),
    @NamedQuery(name = "TbStatusErro.findByDtFimErro", query = "SELECT t FROM TbStatusErro t WHERE t.dtFimErro = :dtFimErro"),
    @NamedQuery(name = "TbStatusErro.findByTpErro", query = "SELECT t FROM TbStatusErro t WHERE t.tpErro = :tpErro"),
    @NamedQuery(name = "TbStatusErro.findByCodStart", query = "SELECT t FROM TbStatusErro t WHERE t.codStart = :codStart"),
    @NamedQuery(name = "TbStatusErro.findByFlCodStart", query = "SELECT t FROM TbStatusErro t WHERE t.flCodStart = :flCodStart")})
public class TbStatusErro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_erro")
    private Integer idErro;
    @Basic(optional = false)
    @Column(name = "st_erro_app")
    private boolean stErroApp;
    @Basic(optional = false)
    @Column(name = "dt_inic_erro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInicErro;
    @Basic(optional = false)
    @Column(name = "dt_fim__erro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFimErro;
    @Basic(optional = false)
    @Column(name = "tp_erro")
    private String tpErro;
    @Column(name = "cod_start")
    private Integer codStart;
    @Column(name = "fl_cod_start")
    private Boolean flCodStart;
    @JoinColumn(name = "tb_app_cadastrado_id_app", referencedColumnName = "id_app")
    @ManyToOne(optional = false)
    private TbAppCadastrado tbAppCadastradoIdApp;

    public TbStatusErro() {
    }

    public TbStatusErro(Integer idErro) {
        this.idErro = idErro;
    }

    public TbStatusErro(Integer idErro, boolean stErroApp, Date dtInicErro, Date dtFimErro, String tpErro) {
        this.idErro = idErro;
        this.stErroApp = stErroApp;
        this.dtInicErro = dtInicErro;
        this.dtFimErro = dtFimErro;
        this.tpErro = tpErro;
    }

    public Integer getIdErro() {
        return idErro;
    }

    public void setIdErro(Integer idErro) {
        this.idErro = idErro;
    }

    public boolean getStErroApp() {
        return stErroApp;
    }

    public void setStErroApp(boolean stErroApp) {
        this.stErroApp = stErroApp;
    }

    public Date getDtInicErro() {
        return dtInicErro;
    }

    public void setDtInicErro(Date dtInicErro) {
        this.dtInicErro = dtInicErro;
    }

    public Date getDtFimErro() {
        return dtFimErro;
    }

    public void setDtFimErro(Date dtFimErro) {
        this.dtFimErro = dtFimErro;
    }

    public String getTpErro() {
        return tpErro;
    }

    public void setTpErro(String tpErro) {
        this.tpErro = tpErro;
    }

    public Integer getCodStart() {
        return codStart;
    }

    public void setCodStart(Integer codStart) {
        this.codStart = codStart;
    }

    public Boolean getFlCodStart() {
        return flCodStart;
    }

    public void setFlCodStart(Boolean flCodStart) {
        this.flCodStart = flCodStart;
    }

    public TbAppCadastrado getTbAppCadastradoIdApp() {
        return tbAppCadastradoIdApp;
    }

    public void setTbAppCadastradoIdApp(TbAppCadastrado tbAppCadastradoIdApp) {
        this.tbAppCadastradoIdApp = tbAppCadastradoIdApp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idErro != null ? idErro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbStatusErro)) {
            return false;
        }
        TbStatusErro other = (TbStatusErro) object;
        if ((this.idErro == null && other.idErro != null) || (this.idErro != null && !this.idErro.equals(other.idErro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbStatusErro[ idErro=" + idErro + " ]";
    }
    
}
