package monit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author orlando
 */
public class ListaProcesso {

    public void listarProcessos(String processo) throws IOException, InterruptedException {
        monit.ConfScriptBean cc = new ConfScriptBean();
        cc.RemoverListaProc();
        
        File arquivo = new File("/usr/local/monitApp/consultaProcesso/script/listaProcesso.sh");
        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write("#########################################\n"
                    + "# Script para pegar os processos rodando#\n"
                    + "# MonitApp solicita e ele gera um txt   # \n"
                    + "#########################################\n"
                    + "\n"
                    + "#!/bin/sh\n"
                    + ". ~/.profile\n"
                    + "\n"
                    + "PROCESSO="+processo+"\n"
                    + "\n"
                    + "	if [ -e /usr/local/monitApp/consultaProcesso/listaProc.txt ]; then\n"
                    + "		# guarda a informação em um arquivo txt para o monitapp poder ler\n"
                    + "		rm /usr/local/monitApp/consultaProcesso/listaProc.txt\n"
                    + "	fi\n"
                    + "\n"
                    + "\n"
                    + "#Com a aplicação rodando ele pega o as informações e armazena, para mais tarde poder testar\n"
                    + "ps aux | grep -i \"$PROCESSO\" | grep -v \"grep\" | awk '{print $1, $2, $3, $4, $9, $11,$12}' >> /usr/local/monitApp/consultaProcesso/listaProc.txt\n"
                    + "\n"
                    + "exit\n"
                    + "###################################\n"
                    + "#  Script para pegar os processos #\n"
                    + "#  rodando                        #\n"
                    + "###################################");
            fw.flush();
            //Chama ExecutarScript para dar permissão ao script e executar ele
            
            cc.PermissaoScript(arquivo);
            cc.ExecutarSh(arquivo);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
