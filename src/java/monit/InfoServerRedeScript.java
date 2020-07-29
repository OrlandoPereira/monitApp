
package monit;

import Bean.InfoServerBean;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orlando
 */
public class InfoServerRedeScript {
    
        // recupera as informações de rede do servidor.
        private String caminhoArq = "/usr/local/monitApp/consultaProcesso/script/rede.sh";
    
        public void setInfRede() throws InterruptedException {
        ConfScriptBean cc = new ConfScriptBean();

        cc.RemoverInfoDisc();
        File arquivo = new File(caminhoArq);

        if (!arquivo.exists()) {
            try (FileWriter fw = new FileWriter(arquivo)) {
                fw.write("########################################\n" +
"# Script para monitorar processo de Redes#\n" +
"# Processo monitorado é <<Redes>>        # \n" +
"#######################################\n" +
"\n" +
"#!/bin/sh\n" +
". ~/.profile\n" +
"\n" +
"################ VARIAVEIS #############\n" +
"CELULAR='numero'\n" +
"EMAIL='email'\n" +
"ipTeste='ip' # servidor terra 208.84.244.116\n" +
"data=`date`\n" +
"LINK=/usr/local/monitApp/consultaProcesso\n" +
"INFO=/usr/local/monitApp/logs/info\n" +
"ERRO=/usr/local/monitApp/logs\n" +
"STATUSERRO=/usr/local/monitApp/tmp\n" +
"SERVIDOR=  $(ifconfig eth0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p') \n" +
"############# Verificação do servidor rodando local.#################### \n" +
"if [ -e $SERVIDOR ]; then\n" +
"     SERVIDOR=$(ifconfig wlan0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p') \n" +
"fi\n" +
"######################################################################## \n" +
"\n" +
"\n" +
"################### Verificação do status de rede.###################### \n" +
"if ping -c5 \"$ipTeste\" 2>&1 >/dev/null; then\n" +
"	if [ -z $(ping -c5 \"$ipTeste\" 2>&1 > $LINK/mapRede.txt) ]; then\n" +
"		echo \"$ipTeste SERVER ONLINE - $data\" >> $INFO/$(date +%x)__$ipTeste-ONLINE.log\n" +
"		echo \"$ipTeste SERVER ONLINE - $(date +%d/%m-%r)\" > $LINK/stRede.txt\n" +
"		echo $(tail $LINK/mapRede.txt | awk '{print $7}' | sed -n -e '2p' -e '3p' -e '4p' -e '5p') >> $LINK/stRede.txt\n" +
"		echo $(tail $LINK/mapRede.txt | sed -n -e '8p' -e '9p' -e '10p') >> $LINK/stRede.txt\n" +
"		echo $(tail $LINK/mapRede.txt | awk '{print $5, $6, $7}' | sed -n -e '2p' -e '3p' -e '4p') >> $INFO/$(date +%x)__$ipTeste-ONLINE.log\n" +
"	\n" +
"		echo $(tail $LINK/mapRede.txt | sed -n -e '8p' -e '9p') >> $INFO/$(date +%x)__$ipTeste-ONLINE.log\n" +
"	fi\n" +
"else\n" +
"	echo \"[CRITICAL] '$ipTeste' SERVER OFFLINE - $(date +%d/%m-%r)\" >> $ERRO/$(date +%x)__$ipTeste-OFFLINE.log\n" +
"\n" +
"	echo \"[CRITICAL] '$ipTeste' SERVER OFFLINE - $(date +%d/%m-%r)\" > $LINK/stRede.txt\n" +
"\n" +
"\n" +
"	echo \"[CRITICAL] SERVER OFFLINE\"\\\\t$(date +%x\\\\t%X) >> $STATUSERRO/$ipTeste\\ 'OFFLINE'\\ $(date +%T\"_\"%d-%m)\\ .tmp \n" +
"	echo \"\\nPor algum motivo inesperado o servidor $ipTeste está OFFLINE no momento.\"                         | mutt -s \"[ALERTA] Problemas com servidor $ipTeste em $(date +\"%x  %X\")\" $EMAIL -a  $ERRO/*$ipTeste-OFFLINE.log\n" +
"#Comando que envia mensagem via WhatsApp.\n" +
" python yowsup-cli demos  -c config -s $CELULAR \"[CRITICAL] Problemas com servidor $ipTeste em $(date +\"%x  %X\")\"\n" +
" python yowsup-cli demos  -c config -s $CELULAR \"Verificação do Server deve ser imediata!!\"\n" +
"fi\n" +
"\n" +
"\n" +
"\n" +
"");
                fw.flush();
                //Chama ExecutarScript para dar permissão ao script e executar ele
                cc.PermissaoScript(arquivo);
                cc.ExecutarSh(arquivo);

            } catch (IOException ex) {
                Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(InfoServerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cc.ExecutarSh(arquivo);
        }
    }
    
    
}
