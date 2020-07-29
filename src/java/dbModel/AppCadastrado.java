package dbModel;

import Entidade.TbAppCadastrado;
import Entidade.TbPrioridade;
import Entidade.TbServidor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppCadastrado {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public AppCadastrado() {
        emf = Persistence.createEntityManagerFactory("MonitAppProjectPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    // uma busca so que é uma lista por ser mais de um resultado
    public List<TbAppCadastrado> buscaNomeEmp() {
        List<TbAppCadastrado> lista = null;
        try {
            lista = em.createNamedQuery("TbAppCadastrado.findAll", TbAppCadastrado.class).getResultList();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    //Cadastrar aplicação para monitoramento
    public void insertApp(String app, int timeApp, int timeAlert, String email, String celular, String prioridade){
    if(app != null){
        //busca qual prioridade pertence essa app
        TbPrioridade tbp = (TbPrioridade) em.createNamedQuery("TbPrioridade.findByTpPrioridade", TbPrioridade.class).
         setParameter("tpPrioridade", prioridade).getSingleResult();
        tbp.getIdPrioridade();
        
        //Servidor a ser monitorado
        TbServidor tbs = (TbServidor) em.createNamedQuery("TbServidor.findByNomServidor", TbServidor.class).
         setParameter("ds", null).getSingleResult();
        
        //cadastra a app
        TbAppCadastrado tb = new TbAppCadastrado();
        tb.setNomApp(app);
        tb.setCheckPadrao(timeApp);
        tb.setCheckVerifErro(timeAlert);
        
    }
        
    }

        public void App(String prioridade){
        TbPrioridade tp = em.find(TbPrioridade.class, prioridade);
        System.out.println("Priop: " + tp.getTpPrioridade());
        }
    
        
        
    
}
