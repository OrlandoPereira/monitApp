
package Bean;

import Entidade.TbEmpresa;
import Entidade.TbGrupoCelular;
import Entidade.TbGrupoEmail;
import dbModel.ListarGruposdb;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ManagedBean (name = "listGp")
@RequestScoped
public class ListarGrupos {
    
    EntityManagerFactory emf;
    EntityManager em;


    public ListarGrupos() {
        emf = Persistence.createEntityManagerFactory("MonitAppProjectPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
   //Busca para pegar as informações de emails 
    
    //para pegar na combo
    private ListarGruposdb GrupoEmailSelecionada = new ListarGruposdb();
    // teste para tabela
    private ArrayList<ListarGruposdb> GrupoEmailLista = new ArrayList<ListarGruposdb>();
    //para pegar na combo tbm
    private List<SelectItem> grupoEmailSelect;

   
    

//buscar por toda a lista!!!!!
    public ArrayList<ListarGruposdb> getBuscaGpEmail() {
        GrupoEmailLista.clear();
        
        if (GrupoEmailLista == null) {
            ListarGruposdb ce = new ListarGruposdb();
            GrupoEmailLista = new ArrayList<ListarGruposdb>();
            
            ArrayList<TbGrupoEmail> listaEmail = (ArrayList<TbGrupoEmail>) ce.getBuscaGpEmails();
            
            if(listaEmail != null && !listaEmail.isEmpty()){
                SelectItem item;

                for (int i=0; i< listaEmail.size(); i++) {
                    
                    listaEmail.add(new TbGrupoEmail(listaEmail.get(i).getIdGrupoEmail()));
                }
            }
        }
        return GrupoEmailLista;
    }

    public ListarGruposdb getGrupoEmailSelecionada() {
        return GrupoEmailSelecionada;
    }

    public void setGrupoEmailSelecionada(ListarGruposdb GrupoEmailSelecionada) {
        this.GrupoEmailSelecionada = GrupoEmailSelecionada;
    }

    public ArrayList<ListarGruposdb> getGrupoEmailLista() {
        return GrupoEmailLista;
    }

    public void setGrupoEmailLista(ArrayList<ListarGruposdb> GrupoEmailLista) {
        this.GrupoEmailLista = GrupoEmailLista;
    }

    public List<SelectItem> getGrupoEmailSelect() {
        return grupoEmailSelect;
    }

    public void setGrupoEmailSelect(List<SelectItem> grupoEmailSelect) {
        this.grupoEmailSelect = grupoEmailSelect;
    }
        
    
    
    
    
  //-----------------------------------------------  
   //Busca para pegar as informações de numeros de celular 

   //para pegar na combo
    private ListarGruposdb GrupoCelularSelecionada = new ListarGruposdb();
    // teste para tabela
    private ArrayList<ListarGruposdb> GrupoCelularLista = new ArrayList<ListarGruposdb>();
    //para pegar na combo tbm
    private List<SelectItem> grupoCelularSelect;

   
//buscar por toda a lista!!!!!
    public ArrayList<ListarGruposdb> getBuscaGpCelular() {
        GrupoCelularLista.clear();
        
        if (GrupoCelularLista == null) {
            ListarGruposdb ce = new ListarGruposdb();
            GrupoCelularLista = new ArrayList<ListarGruposdb>();
            
            ArrayList<TbGrupoCelular> listaCelular = (ArrayList<TbGrupoCelular>) ce.getBuscaGpCelulars();
            
            if(listaCelular != null && !listaCelular.isEmpty()){
                for (int i=0; i< listaCelular.size(); i++) {
                    
                    listaCelular.add(new TbGrupoCelular(listaCelular.get(i).getIdGrupoCel()));
                }
            }
        }
        return GrupoCelularLista;
    }

    public ListarGruposdb getGrupoCelularSelecionada() {
        return GrupoCelularSelecionada;
    }

    public void setGrupoCelularSelecionada(ListarGruposdb GrupoCelularSelecionada) {
        this.GrupoCelularSelecionada = GrupoCelularSelecionada;
    }

    public ArrayList<ListarGruposdb> getGrupoCelularLista() {
        return GrupoCelularLista;
    }

    public void setGrupoCelularLista(ArrayList<ListarGruposdb> GrupoCelularLista) {
        this.GrupoCelularLista = GrupoCelularLista;
    }

    public List<SelectItem> getGrupoCelularSelect() {
        return grupoCelularSelect;
    }

    public void setGrupoCelularSelect(List<SelectItem> grupoCelularSelect) {
        this.grupoCelularSelect = grupoCelularSelect;
    }

    

    
    
     //-----------------------------------------------  
   //Busca para pegar as informações de empresas 

   //para pegar na combo
    private ListarGruposdb GrupoEmpresaSelecionada = new ListarGruposdb();
    // teste para tabela
    private ArrayList<ListarGruposdb> GrupoEmpresaLista = new ArrayList<ListarGruposdb>();
    //para pegar na combo tbm
    private List<SelectItem> grupoEmpresaSelect;

   
//buscar por toda a lista!!!!!
    public List<SelectItem> getBuscaGpEmp() {
        if(grupoEmpresaSelect == null){
            ListarGruposdb ce = new ListarGruposdb();
            grupoEmpresaSelect = new ArrayList<SelectItem>();
            
            List<TbEmpresa> listaEmpresa = ce.getBuscaGpEmpresas();
            
            if(listaEmpresa != null && !listaEmpresa.isEmpty()){
                for (int i=0; i< listaEmpresa.size(); i++){
                    listaEmpresa.add(new TbEmpresa(listaEmpresa.get(i).getIdEmpresa()));;
                }
            }
        }
        return grupoEmpresaSelect;
    }
    
    

    public ListarGruposdb getGrupoEmpresaSelecionada() {
        return GrupoEmpresaSelecionada;
    }

    public void setGrupoEmpresaSelecionada(ListarGruposdb GrupoEmpresaSelecionada) {
        this.GrupoEmpresaSelecionada = GrupoEmpresaSelecionada;
    }

    public ArrayList<ListarGruposdb> getGrupoEmpresaLista() {
        return GrupoEmpresaLista;
    }

    public void setGrupoEmpresaLista(ArrayList<ListarGruposdb> GrupoEmpresaLista) {
        this.GrupoEmpresaLista = GrupoEmpresaLista;
    }

    public void setGrupoEmpresaSelect(List<SelectItem> grupoEmpresaSelect) {
        this.grupoEmpresaSelect = grupoEmpresaSelect;
    }

    


}
