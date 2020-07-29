package Controller;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import javax.mail.BodyPart;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author orlando
 */
public class SendEmail {

    Properties props = new Properties();

    /**
     * Parâmetros de conexão com servidor Gmail
     */
    public SendEmail() {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.transport.protocol", "smtp");
    }

    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("email", "senha");
            
        }
    });

    /**
     * Ativa Debug para sessão
     */
    public void enviaMail(String assunto, String mensagem, String para) throws NoSuchProviderException {
        session.setDebug(true);
        Transport transport = session.getTransport(); 

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("suporte email")); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(para);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);//Assunto
            //message.setText("Enviei este email fdsfsdfsdfsdl!");//mensagem textarea
            //desabilitado

               
            // Este email HTML tem 2 partes, BODY e imagem embutida  
            //  
            MimeMultipart multipart = new MimeMultipart("related");

            // 1a parte- html  
            BodyPart messageBodyPart = new MimeBodyPart();
            //Assunto
            String htmlText = mensagem
                    + "<p>\n"    
                    + "<font size=\"2\" face=\"Verdana\">\n"
                    + "Atenciosamente,\n"
                    + "</font>\n"
                    + "<p>\n"
                    + "<font size=\"2\" face=\"Verdana\">\n"
                    + "Support Zenvia\n"
                    + "</font>\n"
                    + "<br><br>\n"
                    + "<font size=\"1\" face=\"Verdana\" color=\"blue\">\n"
                    + "T +55 51 xxxx xxxx | R 123\n"
                    + "</font><br>\n"
                    + "<font size=\"1\" face=\"Verdana\">"
                    + "www.zenvia.com</font><p><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");

            // Adiciona  
            multipart.addBodyPart(messageBodyPart);

            // Segunda parte - a imagem  
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("/usr/local/monitApp/MonitAppProject/web/img/empresa.png");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            //Adiciona  
            multipart.addBodyPart(messageBodyPart);

            // coloca tudo junto  
            message.setContent(multipart);

            transport.connect();
             /**
             * Método para enviar a mensagem criada
             */
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();

            /**
             * Método para enviar a mensagem criada
             */
            //Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
