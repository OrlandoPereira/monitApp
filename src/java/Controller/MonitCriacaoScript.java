package Controller;

//Classe que trata a criação do script que irá monitorar uma aplicação.
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import monit.ConfScriptBean;

public class MonitCriacaoScript {

    //cadastrar aplicação para monitoramento
    public void CriaMonitoramento(String app, int timeApp, int timeAlert, String email, String celular, String prioridade) throws IOException, InterruptedException {
        if (!app.isEmpty()) {

            String datehour = DataHoraAtual();
            //Configuração das aplicações para serem monitoras, esta classe cria um script e executa ele
            //para começar a monitorar, é utilizados metodos de outra classe para permissão e execução 

            
            
            File arquivo = new File("/usr/local/monitApp/scripts/ProcMonitApp" + app + ".sh");
            try (FileWriter fw = new FileWriter(arquivo)) {
                fw.write("########################################\n"
                        + "# Script para monitorar processo      #\n"
                        + "# Processo monitorado é <<" + app + ">> # \n"
                        + "#######################################\n"
                        + "\n"
                        + "#!/bin/sh\n"
                        + ". ~/.profile\n"
                        + "\n"
                        + "################ VARIAVEIS #############\n"
                        + "ORDER_PRIODIDADE='a'\n"
                        + "PRIORIDADE=[" + prioridade + "]\n"
                        + "INTERVALO=" + timeApp + "\n"
                        + "INTERVALOEMAIL=" + timeAlert + "\n"
                        + "PROCESSO=" + app + "\n"
                        + "EMAIL=" + email + "\n"
                        + "CELULAR=55" + celular + "\n"
                        + "INSTALLED=/usr/local/monitApp/installed\n"
                        + "EXECSCRIPT=/usr/local/monitApp/scripts/scriptsEdit/execScript \n"
                        + "SERVIDOR=$(ifconfig eth0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p') \n"
                        + "######################################### Verificação do servidor rodando local.\n"
                        + "if [ -e $SERVIDOR ]; then\n"
                        + "     SERVIDOR=$(ifconfig wlan0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p') \n"
                        + "fi\n"
                        + "################# Atribuir a,b e c para ordenar as prioridades ########################\n"
                        + "if [ $PRIORIDADE = \"[CRITICAL]\" ];then\n"
                        + "	ORDER_PRIODIDADE='a'\n"
                        + "	echo 'A'\n"
                        + "else if [ $PRIORIDADE = \"[WARNING]\" ];then\n"
                        + "	ORDER_PRIODIDADE='b'\n"
                        + "	echo 'B'\n"
                        + "	else if [ $PRIORIDADE = \"[LOW]\" ];then\n"
                        + "		ORDER_PRIODIDADE='c'\n"
                        + "		echo 'C'\n"
                        + "	else\n"
                        + "		echo \"Nenhum dos tipo de prioridades!!\"\n"
                        + "	fi\n"
                        + "fi\n"
                        + "fi\n"
                        + "\n"
                        + "#####################################################################\n"
                        + "################### Cadastro do Monitoramento #########################\n"
                        + " echo \"ProcMonitApp$PROCESSO.sh\" > $INSTALLED/$PRIORIDADE\\ $SERVIDOR\\ $PROCESSO\\ $INTERVALO\\ $INTERVALOEMAIL\\ $EMAIL\\ $CELULAR \n"
                        + "#####################################################################\n"
                        + "################### executavel do script para poder executalo em algum momento\n"
                        + "	echo \"ProcMonitApp$PROCESSO.sh\" > $EXECSCRIPT/$SERVIDOR\\ $PROCESSO\".exec\"\n"
                        + "#######################################################################"
                        + "### Enquanto for verdadeiro executa quando ocorrer o uma falha duas vezes ele encerra, caso contrario continua verificando o status de sucesso, caso ele encerre depois de ser reiniciado manda um email avisando e encerra o script\n"
                        + "while true; do\n"
                        + "\n"
                        + "#Com a aplicação rodando ele pega o pid do processo e armazena, para mais tarde poder testar"
                        + "while true; do\n"
                        + "\n"
                        + "#Com a aplicação rodando ele pega o pid do processo e armazena, para mais tarde poder testar\n"
                        + "iniciar=$(ps aux | grep $PROCESSO | awk '{print $11}' | sed -n '1p')\n"
                        + "\n"
                        + "	# Neste if ele pega o caminho de execução do aplicativo para poder continuar chegando, apos o start dele\n"
                        + "	if [ -e /usr/local/monitApp/executavel/$PROCESSO.init ]; then\n"
                        + "		echo\n"
                        + "	else\n"
                        + "		echo $iniciar >> /usr/local/monitApp/executavel/$PROCESSO.init\n"
                        + "	fi\n"
                        + "\n"
                        + "	#Este if server para limpar o arquivo\n"
                        + "	if [ -e /usr/local/monitApp/pid/$PROCESSO.pid ]; then\n"
                        + "		rm /usr/local/monitApp/pid/$PROCESSO.pid\n"
                        + "	fi\n"
                        + "\n"
                        + "# Pega o pid e armazena em um arquivo	\n"
                        + "PID=$(pgrep $PROCESSO | sed -n '1p' )\n"
                        + ""
                        + "echo $PID >> /usr/local/monitApp/pid/$PROCESSO.pid  \n"
                        + "echo \"PID \"$PID \"\\n Processo \"  $PROCESSO \"\\n Inicializavel \" $iniciar \n"
                        + "\n"
                        + "	# Testa se existe algum pid e realiza a ação\n"
                        + "	#Caso falso ele reinicia a aplicação aguarda um tempo e checa ela novamente\n"
                        + "	if [ -z \"$PID\" ]; then\n"
                        + "		echo \"... not running\"\n"
                        + "		echo \"$PRIORIDADE[ERRO]... not running\"\\\\t$(date +%x\\\\t%X) >> /usr/local/monitApp/logs/$(date +%x)__$PROCESSO-ERRO.log\n"
                        + "		#Comando que executa a aplicação \n"
                        + "		$(cat /usr/local/monitApp/executavel/$PROCESSO.init) > /dev/null & echo $!\n"
                        + "		sleep $INTERVALOEMAIL #aguarda o intervalo para checar novamente\n"
                        + "		#Caso ela tenha finalizado ou não iniciado ele envio um email com alerta/log do erro e finaliza o script para não ficar alertando\n"
                        + "		if [ -z $(pgrep $PROCESSO) ]; then  \n"
                        + "         echo \"[ERRO]... not running\"\\\\t$(date +%x\\\\t%X) >> /usr/local/monitApp/tmp/$ORDER_PRIODIDADE''$PRIORIDADE\\ $SERVIDOR\\ $PROCESSO\\ $(date +%T\"_\"%d-%m)\\ .tmp \n"
                        + "			echo \"\\nPor algum motivo inesperado o processo $PROCESSO não está sendo executado neste momento.\" "
                        + "                        | mutt -s \"$PRIORIDADE Problemas com $PROCESSO em $(date +\"%x  %X\")\" "
                        + "                        $EMAIL -a  /usr/local/monitApp/logs/*$PROCESSO-ERRO.log\n"
                        + "#Comando que envia mensagem via WhatsApp.\n"
                        + " python yowsup-cli demos  -c config -s $CELULAR \"$PRIORIDADE Problemas com $PROCESSO em $(date +\"%x  %X\")\"\n"
                        + " python yowsup-cli demos  -c config -s $CELULAR \"Verificação deve ser realizada!\"\n"
                        + " \n"
                        + "			echo \"Processo parado encerrando script\"\n"
                        + "			exit\n "
                        + "		fi\n"
                        + "	else\n"
                        + "		#caso a aplicação esteja rodando gera u info de ok\n"
                        + "		echo \"... running\"\n"
                        + "		echo \"[INFO]... running\"\\\\t$(date +%x\\\\t%X) >> /usr/local/monitApp/logs/info/$(date +%x)__$PROCESSO-sucesso.log\n"
                        + "	fi\n"
                        + "\n"
                        + "sleep $INTERVALO #aguarda o intervalo e executa novamente o script\n"
                        + "done\n"
                        + "exit\n"
                        + "###################################\n"
                        + "# Script para monitorar processo  #\n"
                        + "# Criado por MonitApp " + datehour + "#\n"
                        + "###################################");
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

    private String DataHoraAtual() {
        String data = "dd/MM/yyyy";
        String hora = "hh:mm";
        String date, hour;

        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        date = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        hour = formata.format(agora);

        System.out.print(date);
        System.out.print(hour);
        return date + " " + hour;
    }

}
