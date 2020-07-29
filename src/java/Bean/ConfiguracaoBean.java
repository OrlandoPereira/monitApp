package Bean;

import Controller.MonitCriacaoScript;
import dbModel.AppCadastrado;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author orlando
 */
@ManagedBean (name = "confApp")
@RequestScoped
public class ConfiguracaoBean implements Serializable {

String nomeApp;
int tempoCheck;
int tempoAlert;
String prioridade;
String server;
int[] email;
int[] celular;

       
public void setAplicacao(String nomeAppx, int tempoCheckx, int tempoAlertx, 
        String prioridadex,String serverx, String[] email,String[] celular){
    
    System.out.println(nomeAppx +"-"+ tempoCheckx +"-"+ tempoAlertx +"-"+ 
            prioridadex+"-"+ serverx+"-"+ Arrays.toString(email)+"-"+ Arrays.toString(celular));

}
    
//cadastrar aplicação para monitoramento
    public void wsetAplicacao(String nomeAppx, int tempoCheckx, int tempoAlertx, String emailx, 
            String celularx, String prioridadex,String serverx, String[] email,String[] celular){
        try {
            if (!nomeAppx.isEmpty() && tempoCheckx!= 0
                    && tempoAlertx!= 0 && !emailx.isEmpty()) {
                MonitCriacaoScript mc = new MonitCriacaoScript();
                mc.CriaMonitoramento(nomeAppx, tempoCheckx, tempoAlertx, emailx, celularx, prioridadex);
                RequestContext.getCurrentInstance().update("growlCad");
                FacesContext context = FacesContext.getCurrentInstance();   
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Aplicação está em Monitoramento!"));
               

                
            } else {
                RequestContext.getCurrentInstance().update("growlCad");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Campos não preenchidos!"));
            }
        } catch (Exception e) {
            RequestContext.getCurrentInstance().update("growlCad");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha", "Problema Aplicação " + e.getMessage()));
        }
    }
    
    public String getBuscaServer()
    {
        try {
            AppCadastrado ac = new AppCadastrado();
            ac.buscaNomeEmp();
            
        } catch (Exception e) {
        }
        return null;
    }

    public String getNomeApp() {
        return nomeApp;
    }

    public void setNomeApp(String nomeApp) {
        this.nomeApp = nomeApp;
    }

    public int getTempoCheck() {
        return tempoCheck;
    }

    public void setTempoCheck(int tempoCheck) {
        this.tempoCheck = tempoCheck;
    }

    public int getTempoAlert() {
        return tempoAlert;
    }

    public void setTempoAlert(int tempoAlert) {
        this.tempoAlert = tempoAlert;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int[] getEmail() {
        return email;
    }

    public void setEmail(int[] email) {
        this.email = email;
    }

    public int[] getCelular() {
        return celular;
    }

    public void setCelular(int[] celular) {
        this.celular = celular;
    }


        

}
