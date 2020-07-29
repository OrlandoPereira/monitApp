package Controller;

import Bean.InfoServerBean;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import monit.ConfScriptBean;

public class InfoServerScript {

    // recupera as informações de servidor como disco e memória. 
    private String caminhoArq = "/usr/local/monitApp/consultaProcesso/script/discoInfo.sh";

    public void setInfoDiscServer() throws InterruptedException {
        ConfScriptBean cc = new ConfScriptBean();

        cc.RemoverInfoDisc();
        File arquivo = new File(caminhoArq);

        if (!arquivo.exists()) {
            try (FileWriter fw = new FileWriter(arquivo)) {
                fw.write("#############################################\n" +
"# Script para monitorar uso do disco rigido #\n" +
"# MonitApp solicita a informação e apaga o script# \n" +
"#############################################\n" +
"\n" +
"#!/bin/bash\n" +
"# Script para mandar um aviso por e-mail para RESPONSAVEL\n" +
"#Sobre problemas de uso de disco\n" +
" \n" +
"MAX=90\n" +
"#este =90 se refere a porcentagem maxima de uso que vc quer que seja atingida.\n" +
"RESPONSAVEL='email '\n" +
"#este deve ser o seu e-mail , o qual recebera os avisos de limites atingidos.\n" +
"SERVIDOR=$(ifconfig eth0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p') \n" +
"echo \"vazio: \"$SERVIDOR\n" +
"\n" +
"if [ -z \"$SERVIDOR\" ]; then \n" +
"	SERVIDOR=$(ifconfig wlan0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p')\n" +
"	echo $SERVIDOR \"para wlan0\"\n" +
"	if [ \"$SERVIDOR\" = 'fe80' ];then\n" +
"		SERVIDOR='IP OFFLINE'\n" +
"		echo \"ip fora\"\n" +
"	fi\n" +
"else \n" +
"	SERVIDOR=$(ifconfig eth0  | grep inet | cut -d \":\" -f 2  |awk '{print $1}' | sed -n '1p')\n" +
"echo $SERVIDOR \"para eth0\"\n" +
"fi	\n" +
" \n" +
"# Na linha abaixo , vc devera definir quais as partições a serem monitoradas.\n" +
"for Particao in / /dev/sda* # /dev/sda1 /dev/sda5\n" +
"do\n" +
" \n" +
"	SIZE=`df -h $Particao | sed -e '1d;s/[[:space:]]\\+/ /g' | cut -d' ' -f5 | sed -e 's/^\\([0-9]\\+\\)%/\\1/g'`\n" +
" \n" +
"	if [ \"$SIZE\" -gt \"$MAX\" ] ; then\n" +
"		echo \"Partição \\\"$Particao\\\": $SIZE % de uso\" >> /tmp/df.$$\n" +
"	fi\n" +
"done\n" +
" \n" +
"if [ -e /tmp/df.$$ ] ; then\n" +
"	 echo \"[CRITICAL] Espaço em disco acima de 90%, Uso: \"$SIZE\"%\" >> /usr/local/monitApp/tmp/$SERVIDOR\\ \"Disco\"\\ $(date +%T\"_\"%d-%m)\\ .tmp \n" +
"  \n" +
"	echo \"\\n\" | mutt -s \"[CRITICAL] Espaço em disco acima de 90%\" $RESPONSAVEL < /tmp/df.$$\n" +
"	rm /tmp/df.$$\n" +
"else\n" +
"	echo \"Servidor \"$SERVIDOR\"\\nLast check: \"$(date +%x\" \"%X)\"\\nUso do disco: \"$SIZE\"%\" > /usr/local/monitApp/consultaProcesso/infoServ.txt\n" +
"\n" +
"fi\n" +
"echo \"Servidor $SERVIDOR\" > /usr/local/monitApp/consultaProcesso/memoria.txt \n" +
"free -m -t |awk '{print $1, $2, $3, $4}' | sed -n -e '2p' -e '4p' -e '5p' >> /usr/local/monitApp/consultaProcesso/memoria.txt");
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
