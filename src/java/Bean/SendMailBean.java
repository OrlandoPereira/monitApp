package Bean;

import Controller.SendEmail;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.NoSuchProviderException;


@ManagedBean
@RequestScoped
public class SendMailBean {

    public SendMailBean() {
    }
    
    private String assunto;
    private String mensagem;
    private String para;
    
    // envia email para a classe 
    public void enviarEmail() throws NoSuchProviderException{
        SendEmail sm = new SendEmail();
        sm.enviaMail(assunto, mensagem, para);
    }
    

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }
    
    
    
}
