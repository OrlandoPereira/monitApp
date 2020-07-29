
package dbModel;

import Entidade.TbEmpresa;
import Entidade.TbGrupoCelular;
import Entidade.TbGrupoEmail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ListarGruposdb {
   EntityManagerFactory emf;
    EntityManager em;


    public ListarGruposdb() {
        emf = Persistence.createEntityManagerFactory("MonitAppProjectPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    //Emails
    // uma busca so que é uma lista por ser mais de um resultado
    public List<TbGrupoEmail> getBuscaGpEmails() {
        List<TbGrupoEmail> lista = null;
        try {
            lista = em.createNamedQuery("TbGrupoEmail.findAll", TbGrupoEmail.class).getResultList();
      
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
        //Celulares
        // uma busca so que é uma lista por ser mais de um resultado
        public List<TbGrupoCelular> getBuscaGpCelulars() {
        List<TbGrupoCelular> lista = null;
        try {
            lista = em.createNamedQuery("TbGrupoCelular.findAll", TbGrupoCelular.class).getResultList();
      
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
        
           //Celulares
    // uma busca so que é uma lista por ser mais de um resultado
    public List<TbEmpresa> getBuscaGpEmpresas() {
        List<TbEmpresa> lista = null;
        try {
            lista = em.createNamedQuery("TbEmpresa.findAll", TbEmpresa.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
}
