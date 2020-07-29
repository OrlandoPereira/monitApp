package Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ConsultaPsAux;
import monit.ListaProcesso;


/**
 *
 * @author orlando
 */
@ManagedBean
@SessionScoped
public class ListarAppsBean implements Serializable {

   
    ArrayList<ConsultaPsAux> listar = new ArrayList<ConsultaPsAux>();

    public ArrayList<ConsultaPsAux> getListar() {
        return listar;
    }

    public void setListar(ArrayList<ConsultaPsAux> listar) {
        this.listar = listar;
    }
    
    ConsultaPsAux cp = new ConsultaPsAux();

    public ArrayList<ConsultaPsAux> getExecuteCommand(String buscar) throws IOException, InterruptedException {
        listar.remove(cp);
        listar.clear();
        
        try {
            if (!buscar.isEmpty())  {
            ListaProcesso lp = new ListaProcesso();
            lp.listarProcessos(buscar);
          
            String caminhoArq = "/usr/local/monitApp/consultaProcesso/listaProc.txt";

            FileReader reader = new FileReader(caminhoArq);
            BufferedReader leitor = new BufferedReader(reader);

            String  linha = null;
      

            while ((linha = leitor.readLine()) != null) {
                
                String[] result = linha.split(" ");
               
                
                ConsultaPsAux cp = new ConsultaPsAux();
                cp.setUser(result[0]);
                cp.setPid(result[1]);
                cp.setCpu(result[2]);
                cp.setMen(result[3]);
                cp.setStart(result[4]);
                cp.setComando(result[5]);
                           
             listar.add(cp); 
             
            }  
                        
            leitor.close();
           
        } else {
                System.out.println("Sem preenchimento dos campos");
        }
            
        } catch (Exception e) {
                System.out.println(e.getMessage());
              }
        return listar;
          
    }

    
}
