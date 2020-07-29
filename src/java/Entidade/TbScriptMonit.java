/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author orlando
 */
@Entity
@Table(name = "tb_script_monit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbScriptMonit.findAll", query = "SELECT t FROM TbScriptMonit t"),
    @NamedQuery(name = "TbScriptMonit.findByIdScript", query = "SELECT t FROM TbScriptMonit t WHERE t.idScript = :idScript"),
    @NamedQuery(name = "TbScriptMonit.findByNomScript", query = "SELECT t FROM TbScriptMonit t WHERE t.nomScript = :nomScript"),
    @NamedQuery(name = "TbScriptMonit.findByStScript", query = "SELECT t FROM TbScriptMonit t WHERE t.stScript = :stScript")})
public class TbScriptMonit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_script")
    private Integer idScript;
    @Basic(optional = false)
    @Column(name = "nom_script")
    private String nomScript;
    @Basic(optional = false)
    @Column(name = "st_script")
    private boolean stScript;
    @JoinColumn(name = "tb_app_cadastrado_id_app", referencedColumnName = "id_app")
    @ManyToOne(optional = false)
    private TbAppCadastrado tbAppCadastradoIdApp;

    public TbScriptMonit() {
    }

    public TbScriptMonit(Integer idScript) {
        this.idScript = idScript;
    }

    public TbScriptMonit(Integer idScript, String nomScript, boolean stScript) {
        this.idScript = idScript;
        this.nomScript = nomScript;
        this.stScript = stScript;
    }

    public Integer getIdScript() {
        return idScript;
    }

    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }

    public String getNomScript() {
        return nomScript;
    }

    public void setNomScript(String nomScript) {
        this.nomScript = nomScript;
    }

    public boolean getStScript() {
        return stScript;
    }

    public void setStScript(boolean stScript) {
        this.stScript = stScript;
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
        hash += (idScript != null ? idScript.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbScriptMonit)) {
            return false;
        }
        TbScriptMonit other = (TbScriptMonit) object;
        if ((this.idScript == null && other.idScript != null) || (this.idScript != null && !this.idScript.equals(other.idScript))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.TbScriptMonit[ idScript=" + idScript + " ]";
    }
    
}
