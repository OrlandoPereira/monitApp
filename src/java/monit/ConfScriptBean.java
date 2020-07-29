package monit;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author orlando
 */
@ManagedBean
@RequestScoped
public class ConfScriptBean {

    private String perm = "chmod 777 ";
    private String exec = "sh ";
    private String backg = " ";
    private Runtime run = Runtime.getRuntime();
    private Process proc;
    private String listproc = "rm /usr/local/monitApp/consultaProcesso/script/listaProcesso.sh";
    private String listInfoServDisc = "rm /usr/local/monitApp/consultaProcesso/script/discoInfo.sh";
    private String StartApp = "/usr/local/monitApp/executavel/";

    //Ele esta executando certo o processo do script o problema é que ele fica run, se finalizar o java 
    // o script que esta rodando também é encerrado!!
//Metodo para executar o script sh
    public void ExecutarSh(File shell) throws InterruptedException {

        try {
            proc = run.exec(exec + "" + shell ); //Pega o caminho do arquivo e executa
            proc.waitFor();  // esperar o processo terminar (necessário para manter o
            //  script sempre rodando!!!!)          
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    //Metodo para dar permissão
    public void PermissaoScript(File shell) throws InterruptedException {
        try {
            proc = run.exec(perm + " " + shell); //Pega o caminho do arquivo gera as permissões para executar
            proc.waitFor();  // esperar o processo terminar (necessário para manter o
            //  script sempre rodando!!!!)          
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void RemoverListaProc() throws InterruptedException {
        try {
            proc = run.exec(listproc); //Pega o caminho do arquivo gera as permissões para executar
            proc.waitFor();  // esperar o processo terminar (necessário para manter o
            
            //  script sempre rodando!!!!)          
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
     public void RemoverInfoDisc() throws InterruptedException {
        try {
            proc = run.exec(listInfoServDisc); //Pega o caminho do arquivo gera as permissões para executar
            proc.waitFor();  // esperar o processo terminar (necessário para manter o
            //  script sempre rodando!!!!)          
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
     

    
  

}
