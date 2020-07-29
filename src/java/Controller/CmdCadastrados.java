
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import monit.ConfScriptBean;


public class CmdCadastrados {
    
    // classe para realizar alteração, exclusão e parar/iniciar monitoramento
    // também contem o metodo de iniciar uma aplicação off
    private String startStopsh = "/usr/local/monitApp/scripts/scriptsEdit/onOff.sh";
    private String excluirsh = "/usr/local/monitApp/scripts/scriptsEdit/excluir.sh";
    private String editarsh = "/usr/local/monitApp/scripts/scriptsEdit/editar.sh";
    private String executarsh = "/usr/local/monitApp/scripts/scriptsEdit/executor.sh";
    
      public boolean on_offScript(String shell) throws InterruptedException {
        boolean app;
        if(shell != null)
        try {
            ProcessBuilder pBuilder = new ProcessBuilder("/bin/sh", "-c", "ps aux | "
                    + "grep ProcMonitApp" + shell + ".sh | grep -v grep | awk '{print $12}' "
                    + "| sed -n '1p' | wc -l");
            pBuilder.redirectErrorStream(true);

            String line = null;
            Process p = pBuilder.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //le se for 0 não ha script exec. se 1 existe script de monitoramento
            line = reader.readLine();
            int comparar = Integer.parseInt(line);
            
            //if (true != app && false != app)
            if(comparar == 0){
                app = false;
            }
            else if(comparar == 1){
                app = true;
            }
            else{
                app = true;
            }
               return app;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     return true;
    }
    
      
    // função para iniciar/parar o script de monitoramento
    public void StartStopScript(String app) throws InterruptedException {
        if (!app.isEmpty()) {
            ConfScriptBean cc = new ConfScriptBean();

            cc.RemoverInfoDisc();
            File arquivo = new File(startStopsh);
                try (FileWriter fw = new FileWriter(arquivo)) {
                    fw.write("########################################\n"
                            + "# Script para iniciar/parar o script   #\n"
                            + "# de monitoramento                     # \n"
                            + "#######################################\n"
                            + "#!/bin/sh\n"
                            + "echo \"[PROC SILICITAÇÃO]\"\n"
                            + "################ VARIAVEIS #############\n"
                            + "COMANDOSH='sh '\n"
                            + "MATAR='kill -9 '\n"
                            + "NOMESCRIPT='ProcMonitApp"+app+".sh'\n"
                            + "INICIAR=/usr/local/monitApp/scripts\n"
                            + "PID=$(ps aux | grep $NOMESCRIPT | grep -v grep |"
                            + " awk '{print $2}' | sed -n '1p')\n"
                            + "######################################### \n"
                            + "echo \"O pid do proc: \"$PID\n"
                            + "## Finalizar o monitoramento.\n"
                            + "\n"
                            + "if [ -n \"$PID\" ];then\n"
                            + "	$MATAR $PID\n"
                            + "	echo \"Finalizado o script de monitoramento\"\n"
                            + "exit\n"
                            + "else\n"
                            + "	echo \"Iniciando script de monitoramento\"\n"
                            + "	$COMANDOSH $INICIAR/$NOMESCRIPT\n"
                            + "\n"
                            + "fi\n"
                            + "exit");
                    fw.flush();
                    //Chama ExecutarScript para dar permissão ao script e executar ele
                    cc.PermissaoScript(arquivo);
                    cc.ExecutarSh(arquivo);

                } catch (IOException ex) {

                } catch (InterruptedException ex) {
                    Logger.getLogger(CmdCadastrados.class.getName()).log(Level.SEVERE, null, ex);
                }

           }
    }

    public void ExcluirScript(String app) throws InterruptedException {
        if (!app.isEmpty()) {
            ConfScriptBean cc = new ConfScriptBean();
            File arquivo = new File(excluirsh);
            try (FileWriter fw = new FileWriter(arquivo)) {
                fw.write("########################################\n"
                        + "# Script para Excluir o script         #\n"
                        + "# de monitoramento                     # \n"
                        + "#######################################\n"
                        + "#!/bin/sh\n"
                        + "echo \"[PROC SILICITAÇÃO]\"\n"
                        + "################ VARIAVEIS #############\n"
                        + "COMANDOSH='sh '\n"
                        + "MATAR='kill -9 '\n"
                        + "REMOVE='rm '\n"
                        + "NOMESCRIPT='ProcMonitApp"+app+".sh'\n"
                        + "INICIAR=/usr/local/monitApp/scripts\n"
                        + "INSTALLLED=/usr/local/monitApp/installed\n"
                        + "CADASTRO=*"+app+"*\n"
                        + "PID=$(ps aux | grep $NOMESCRIPT | grep -v grep | "
                        + "awk '{print $2}' | sed -n '1p')\n"
                        + "######################################### \n"
                        + "echo \"O pid do proc: \"$PID\n"
                        + "## Finalizar o monitoramento.\n"
                        + "echo \"TESTE: \"$CADASTRO\n"
                        + "\n"
                        + "if [ -n \"$PID\" ];then\n"
                        + "	$MATAR $PID\n"
                        + "	$REMOVE $INICIAR/$NOMESCRIPT\n"
                        + "	$REMOVE $INSTALLLED/$CADASTRO\n"
                        + "	echo \"Finalizado o script de monitoramento\"\n"
                        + "exit\n"
                        + "else\n"
                        + "	$REMOVE $INICIAR/$NOMESCRIPT\n"
                        + "	$REMOVE $INSTALLLED/$CADASTRO\n"
                        + "	echo \"Iniciando script de monitoramento\"\n"
                        + "\n"
                        + "fi\n"
                        + "exit");
                fw.flush();
                //Chama ExecutarScript para dar permissão ao script e executar ele
                cc.PermissaoScript(arquivo);
                cc.ExecutarSh(arquivo);

                } catch (IOException ex) {

                } catch (InterruptedException ex) {
                    Logger.getLogger(CmdCadastrados.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        
    }
     
    
     public void editarScript(String app) throws InterruptedException{
           if (!app.isEmpty()) {
            ConfScriptBean cc = new ConfScriptBean();

            File arquivo = new File(editarsh);

                   try (FileWriter fw = new FileWriter(arquivo)) {
                       fw.write("########################################\n"
                               + "# Script para Editar o script          #\n"
                               + "# de monitoramento                     # \n"
                               + "#######################################\n"
                               + "#!/bin/sh\n"
                               + "echo \"[PROC SILICITAÇÃO]\"\n"
                               + "################ VARIAVEIS #############\n"
                               + "COMANDOSH='sh '\n"
                               + "MATAR='kill -9 '\n"
                               + "EDIT='gedit '\n"
                               + "NOMESCRIPT='ProcMonitApp"+app+".sh'\n"
                               + "INICIAR=/usr/local/monitApp/scripts\n"
                               + "PID=$(ps aux | grep $NOMESCRIPT | grep -v grep |"
                               + "awk '{print $2}' | sed -n '1p')\n"
                               + "######################################### \n"
                               + "echo \"O pid do proc: \"$PID\n"
                               + "## Finalizar o monitoramento.\n"
                               + "\n"
                               + "if [ -n \"$PID\" ];then\n"
                               + "	$MATAR $PID\n"
                               + "	$EDIT $INICIAR/$NOMESCRIPT\n"
                               + "	echo \"Editado com Sucesso e iniciado Monitoramento\"\n"
                               + "exit\n"
                               + "else\n"
                               + "	$EDIT $INICIAR/$NOMESCRIPT\n"
                               + "	echo \"Editado com Sucesso\"\n"
                               + "\n"
                               + "fi\n"
                               + "exit");
                       fw.flush();
                       //Chama ExecutarScript para dar permissão ao script e executar ele
                       cc.PermissaoScript(arquivo);
                    cc.ExecutarSh(arquivo);

                } catch (IOException ex) {

                } catch (InterruptedException ex) {
                    Logger.getLogger(CmdCadastrados.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
    
    
    // iniciar um processo e seu monitormaneto na dasboard
    public void StartarApp(String prioridade, String servidor, String processo, String horad) throws InterruptedException {
        if (!processo.isEmpty()) {
        //Configuração das aplicações para serem monitoras, esta classe cria um script e executa ela
            //para começar a monitorar, é utilizados metodos de coutra classe para permissão e execução 

            File arquivo = new File(executarsh);
            try (FileWriter fw = new FileWriter(arquivo)) {
                fw.write("##################################\n"
                        + "# Script para Startar processo \n"
                        + "##################################\n"
                        + "\n"
                        + "#!/bin/sh\\n\n"
                        + "\n"
                        + "#Remove o arquivo do status de erro\n"
                        + "if [-e /usr/local/monitApp/tmp/" + prioridade + "\\ " + servidor + "\\ " + processo + "\\ " + horad + "\\ .tmp];then\n"
                        + " echo\n"
                        + "else \n"
                        + "rm /usr/local/monitApp/tmp/" + prioridade + "\\ " + servidor + "\\ " + processo + "\\ " + horad + "\\ .tmp\n"
                        + "fi\n"
                        + "#Reinicia o monitoramento e processo\n"
                        + "sh /usr/local/monitApp/scripts/ProcMonitApp" + processo + ".sh \n"
                        + "exit\n"
                        + " ########### FIM ###################");
                fw.flush();
                //Chama ExecutarScript para dar permissão ao script e executar ele
                monit.ConfScriptBean cc = new ConfScriptBean();
                cc.PermissaoScript(arquivo);
                cc.ExecutarSh(arquivo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
