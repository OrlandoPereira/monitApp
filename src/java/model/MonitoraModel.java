package model;

import java.util.Comparator;

/**
 *
 * @author orlando
 */
public class MonitoraModel implements Comparator<MonitoraModel> {
    
    private String prioridade;
    private String servidor;
    private String processo;
    private String hdDown;
    private String erroGerado;
    
    // metodo para ordenar os arquivos pelo nome para ser exibidos
     public int compare(MonitoraModel monit, MonitoraModel outramonit) {
        return monit.getPrioridade().
                compareTo(outramonit.getPrioridade());
    }



    public MonitoraModel() {
    }

    public MonitoraModel(String prioridade, String servidor, String processo, String hdDown, String erroGerado) {
        this.prioridade = prioridade;
        this.servidor = servidor;
        this.processo = processo;
        this.hdDown = hdDown;
        this.erroGerado = erroGerado;
    }

    
    
    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
  
    public String getServidor() {
        return servidor;
    }

    public String getProcesso() {
        return processo;
    }

    public String getHdDown() {
        return hdDown;
    }

    public String getErroGerado() {
        return erroGerado;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public void setHdDown(String hdDown) {
        this.hdDown = hdDown;
    }

    public void setErroGerado(String erroGerado) {
        this.erroGerado = erroGerado;
    }
    
    
    
    
    
}
