package Prime;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;
 
@ManagedBean
public class PanelViewDown {
     
    public void onCloseDown(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Painel Fechado", "Clique no menu");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onToggleDown(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, " MonitApp", "Status:" + event.getVisibility().name()); 
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}