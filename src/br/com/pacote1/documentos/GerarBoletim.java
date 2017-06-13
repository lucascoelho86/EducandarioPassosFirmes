package br.com.pacote1.documentos;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 
public class GerarBoletim{
	
	public GerarBoletim() {}
	
	public void inserir(String pChave) throws ExcRepositorio{  
		String arrayChave[] = pChave.split(";");
		
	}
	
	public JasperPrint gerar(HashMap<String, Object> pHashMap) throws ExcRepositorio{
		
		ArrayList<HashMap<String, Object>> arCamposRelatorio = new ArrayList<HashMap<String, Object>>();
		JasperPrint print = null;
		
        try {
            
        	arCamposRelatorio.add(pHashMap);
            String nmUsuario = System.getProperty("user.name");
            JasperReport report = JasperCompileManager.compileReport("C:\\Users\\adjackson\\Documents\\WS_Estudo\\projetoNovo\\Boletim.jrxml");

            print = JasperFillManager.fillReport(report, pHashMap,
                    new JRBeanCollectionDataSource(arCamposRelatorio));
            
            //exporta o arquivo para a area de trabalho
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\adjackson\\Desktop\\Boletim.pdf");
            System.out.println("Relatório gerado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO AO GERAR RELATÓRIO!");
        }
		
		return print;
	}
}
