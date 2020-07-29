package model;

/**
 *
 * @author orlando
 */
public class InfoServModel {
    private String InfoDisc;// para informação geral de disco do servidor
    private String InfoRede;// para informação de rede 
    private String InfoMen; // informações de memoria

    public InfoServModel() {
    }

    public String getInfoDisc() {
        return InfoDisc;
    }

    public void setInfoDisc(String InfoDisc) {
        this.InfoDisc = InfoDisc;
    }

    public String getInfoRede() {
        return InfoRede;
    }

    public void setInfoRede(String InfoRede) {
        this.InfoRede = InfoRede;
    }

    public String getInfoMen() {
        return InfoMen;
    }

    public void setInfoMen(String InfoMen) {
        this.InfoMen = InfoMen;
    }

    
    
}
