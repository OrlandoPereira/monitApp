package Bean;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import model.MonitoraModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author orlando
 */
@ManagedBean
@SessionScoped
public class Monitora implements Serializable{

    private String propCri = "[CRITICAL";
    private String propWar = "[WARNING";
    private String propLow = "[LOW";

    List<MonitoraModel> listarErro = new ArrayList<>();

    public List<MonitoraModel> getListar() throws FileNotFoundException, IOException {
        listarErro.clear();

        String caminhoLog = "/usr/local/monitApp/tmp";
        String arqNome = "";

        //Fica sempre monitorando a pasta com o log de erro e retorna o nome do log 
        try {
      
        while (true) {
            File file = new File(caminhoLog);
            File afile[] = file.listFiles();  
            
            int i = 0;
            int l = 0;
            System.out.println("arq: "+afile.length);
            for (int t = 3; l < t; l++) {
                File nomeArq = afile[l];
                String teste[] = nomeArq.getName().split("]");
                
                System.out.println("count: "+l);
                System.out.println(teste[0]);
                         
                    for (int j = afile.length; i < j; i++) {
                        System.out.println("NO: "+teste[0]);
                    
                        MonitoraModel mm = new MonitoraModel();
                        File arquivos = afile[i];
                        String[] dado = arquivos.getName().split(" ");

                        mm.setPrioridade(dado[0]);
                        mm.setServidor(dado[1]);
                        mm.setProcesso(dado[2]);
                        mm.setHdDown(dado[3]);
                        arqNome = (arquivos.getName());
                        System.out.println("NOME: "+teste[0]);
                        String arquivoLer = caminhoLog + "/" + arqNome;

                        FileReader reader = new FileReader(arquivoLer);
                        BufferedReader leitor = new BufferedReader(reader);

                        String linha = null;
                        while ((linha = leitor.readLine()) != null) {
                            mm.setErroGerado(linha);                          
                        }

                        listarErro.add(mm);
                    }
                    MonitoraModel comparator = new MonitoraModel();
                    Collections.sort(listarErro, comparator);

                } return listarErro;
            

        } 
        
        } catch (Exception e) {
            System.out.println("Não há arquivo erro: " + e.getMessage());
        }
       
        return null;
    }

    public void setListar(List<MonitoraModel> listarErro) {
        this.listarErro = listarErro;
    }

    public List<MonitoraModel> getResult() throws IOException {
        getListar();
        return listarErro;

    }
    
       
    
    
    
    //grafico não arrumado
    
    private LineChartModel areaModel;
 
    @PostConstruct
    public void init() {
        createAreaModel();// grafico
        createPieModels();// pizaa
    }
 
    public LineChartModel getAreaModel() {
        return areaModel;
    }
     
    private void createAreaModel() {
        areaModel = new LineChartModel();
 
        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Falhas");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("OK");
        girls.set("14:00", 52);
        girls.set("14:15", 60);
        girls.set("14:30", 110);
        girls.set("14:45", 90);
        girls.set("15:00", 120);
 
        areaModel.addSeries(boys);
        areaModel.addSeries(girls);
         
        areaModel.setTitle("Gráfico de Status");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
         
        Axis xAxis = new CategoryAxis("Tempo");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total App");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }

    
    // grafico de pizza
    private PieChartModel pieModel2;

    public PieChartModel getPieModel2() {
        return pieModel2;
    }
    
    private void createPieModels() {
        createPieModel2();
    }

     private void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("OK", 325);
        pieModel2.set("Falhas", 140);
         
        pieModel2.setTitle("Total de App");
        pieModel2.setLegendPosition("ne");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
    
}

 
