package Bean;

import Controller.InfoServerScript;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.InfoServModel;
import monit.InfoServerRedeScript;

/**
 *
 * @author orlando
 */
@ManagedBean
@SessionScoped
public class InfoServerBean implements Serializable {

    ArrayList<InfoServModel> listarInf = new ArrayList<InfoServModel>();

    InfoServModel ism = new InfoServModel();

    private ArrayList<InfoServModel> getListarInf() {
        return listarInf;
    }

    private void setListarInf(ArrayList<InfoServModel> listarInf) {
        this.listarInf = listarInf;
    }

    public ArrayList<InfoServModel> getMostraInfoDisc() {
        try {
            InfoServerScript iss = new InfoServerScript();
            iss.setInfoDiscServer();
            listarInf.remove(ism);
            listarInf.clear();

            String ResultArq = "/usr/local/monitApp/consultaProcesso/infoServ.txt";

            FileReader reader = new FileReader(ResultArq);
            BufferedReader leitor = new BufferedReader(reader);

            String linha = null;

            while ((linha = leitor.readLine()) != null) {
                String result = linha;
                InfoServModel ism = new InfoServModel();
                ism.setInfoDisc(result);

                System.out.println(result);
                listarInf.add(ism);

            }

            leitor.close();

        } catch (InterruptedException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listarInf;

    }

    public ArrayList<InfoServModel> getMostraInfoMen() {
        try {
            InfoServerScript iss = new InfoServerScript();
            iss.setInfoDiscServer();
            listarInf.remove(ism);
            listarInf.clear();

            String ResultArq = "/usr/local/monitApp/consultaProcesso/memoria.txt";

            FileReader reader = new FileReader(ResultArq);
            BufferedReader leitor = new BufferedReader(reader);

            String linha = null;

            while ((linha = leitor.readLine()) != null) {
                String result = linha;

                InfoServModel ism = new InfoServModel();
                ism.setInfoMen(result);

                listarInf.add(ism);
            }

            leitor.close();

        } catch (InterruptedException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listarInf;

    }

    public ArrayList<InfoServModel> getMostraRede() {
        try {
            //InfoServerRedeScript rede = new InfoServerRedeScript();
            //rede.setInfRede();
            listarInf.remove(ism);
            listarInf.clear();

            String ResultArq = "/usr/local/monitApp/consultaProcesso/stRede.txt";

            FileReader reader = new FileReader(ResultArq);
            BufferedReader leitor = new BufferedReader(reader);

            String linha = null;

            while ((linha = leitor.readLine()) != null) {
                String result = linha;

                InfoServModel ism = new InfoServModel();
                ism.setInfoRede(result);
    
                listarInf.add(ism);
            }

            leitor.close();

        }catch (FileNotFoundException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listarInf;

    }

}
