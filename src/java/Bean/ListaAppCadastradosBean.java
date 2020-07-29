
package Bean;

import Controller.CmdCadastrados;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.ListarCadastradoModel;



@ManagedBean
@RequestScoped
public class ListaAppCadastradosBean {
    private boolean on_off;
    private String excluirScript;

    
    
    List<ListarCadastradoModel> listarAppsCads = new ArrayList<>();

    public List<ListarCadastradoModel> getListarCad() throws FileNotFoundException, IOException {
        listarAppsCads.clear();

        String caminhoLog = "/usr/local/monitApp/installed";
        String arqNome = "";

        //Fica sempre monitorando a pasta com o log de erro e retorna o nome do log
       
        while (true) {
            File file = new File(caminhoLog);
            File afile[] = file.listFiles();

            int i = 0;
        
                    for (int j = afile.length; i < j; i++) {
                   
                        ListarCadastradoModel mm = new ListarCadastradoModel();
                        File arquivos = afile[i];
                        String[] dado = arquivos.getName().split(" ");

                        mm.setPrioridade(dado[0]);
                        mm.setServer(dado[1]);
                        mm.setNomeServico(dado[2]);
                        mm.setCheck(dado[3]);
                        mm.setInfo(dado[4]);
                        mm.setGpEmail(dado[5]);
                        mm.setGpWhats(dado[6]);
                        arqNome = (arquivos.getName());
                        String arquivoLer = caminhoLog + "/" + arqNome;
                        listarAppsCads.add(mm);
                       
                    } return listarAppsCads;
         }          
    }

    public void setListarCad(List<ListarCadastradoModel> listarErro) {
        this.listarAppsCads = listarAppsCads;
    }

    public List<ListarCadastradoModel> getResult() throws IOException {
        getListarCad();
        return listarAppsCads;

    }
    

    public void setExcluirScript(String app) {
        try {
            CmdCadastrados cmd = new CmdCadastrados();
            cmd.ExcluirScript(app);
        } catch (Exception e) {
        }
    }
    
    public void editarScript(String app) {
        try {
            CmdCadastrados cmd = new CmdCadastrados();
            cmd.editarScript(app);
        } catch (Exception e) {
        }
    }
    
    public void startStopScript(String app) {
        try {
            CmdCadastrados cmd = new CmdCadastrados();
            cmd.StartStopScript(app);
        } catch (Exception e) {
         e.getMessage();
        }
        
    }
      
    
    public boolean isOn_off(String app) throws InterruptedException {   
        CmdCadastrados cmd = new CmdCadastrados();
        try {
        if(!app.isEmpty()){
        return cmd.on_offScript(app);
        }
        } catch (Exception e) {
            System.out.println("Erro do monit: "+e.getMessage());
        }
        return false;
    }

    public void setOn_off(boolean on_off) {
        if(on_off == true)
        this.on_off = false;
        else
        this.on_off = true;
    }

    public List<ListarCadastradoModel> getListarAppsCads() {
        return listarAppsCads;
    }

    public void setListarAppsCads(List<ListarCadastradoModel> listarAppsCads) {
        this.listarAppsCads = listarAppsCads;
    }
    
    
    public void startApp(String prioridade ,String  servidor, String processo, String hdDown) throws InterruptedException{
    CmdCadastrados cmd = new CmdCadastrados();
    cmd.StartarApp(prioridade, servidor, processo, hdDown);
    }
    
    
}
