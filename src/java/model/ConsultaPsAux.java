package model;

/**
 *
 * @author orlando
 */
public class ConsultaPsAux {
    
    private String user, pid, cpu, men, start, comando, parametro;

    public ConsultaPsAux(String user, String pid, String cpu, String men, String start, String comando, String parametro) {
        this.user = user;
        this.pid = pid;
        this.cpu = cpu;
        this.men = men;
        this.start = start;
        this.comando = comando;
        this.parametro = parametro;
    }

    public ConsultaPsAux() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    
  
    
}
