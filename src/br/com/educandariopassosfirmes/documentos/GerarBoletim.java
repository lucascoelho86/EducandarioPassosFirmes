package br.com.educandariopassosfirmes.documentos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.com.educandariopassosfirmes.dao.DisciplinaDAO;
import br.com.educandariopassosfirmes.dao.NotaDAO;
import br.com.educandariopassosfirmes.entidades.Disciplina;
import br.com.educandariopassosfirmes.entidades.Nota;
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
	
	public JasperPrint gerar(String pChave) throws ExcRepositorio{
		
		JasperPrint print = null;
		
        try {
            
            JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Lucas\\workspace\\projeto\\src\\br\\com\\educandariopassosfirmes\\documentos\\Boletim.jrxml");

            //print = JasperFillManager.fillReport(report, new HashMap(),
              //      new JRBeanCollectionDataSource(tratarColecaoRelatorio(pChave)));
            
            //exporta o arquivo para a area de trabalho
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\Lucas\\Desktop\\Boletim.pdf");
            System.out.println("Relatório gerado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO AO GERAR RELATÓRIO!");
        }
		
		return print;
	}
	
	/*
	public ArrayList<HashMap<String, Object>> tratarColecaoRelatorio(String pChave){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> camposRelatorio = new ArrayList<HashMap<String, Object>>();
		String arrayChave[] = pChave.split(";");
		
		NotaDAO notaDAO = new NotaDAO();
		
		List<Nota> listNota = notaDAO.consultar(arrayChave[0], null);
		
		Iterator<Nota> itNota = listNota.iterator();
		Integer idDisciplinaAux = 0;
		while(itNota.hasNext()){
			Nota nota = itNota.next();
			
			Integer tpNota = nota.getIdTpNota();
			Integer idDisciplina = nota.getIdDisciplina();
										
			if(idDisciplinaAux.compareTo(0) != 0 && idDisciplinaAux.compareTo(idDisciplina) != 0){
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				
				Disciplina disciplina = disciplinaDAO.consultar(idDisciplinaAux);
				
				hashMap.put("disciplina", disciplina.getDsDisciplina());
				
				Double media = Double.sum((Double)hashMap.get("notaTeste"), (Double)hashMap.get("notaProva"));
				media = Double.sum(media, (Double)hashMap.get("notaTrabalho"));
				media = media / 3;
				
				DecimalFormat df = new DecimalFormat("###,##0.00");
				String valorFormatado = df.format(media);
				
				hashMap.put("notaFinal", valorFormatado);
				
				camposRelatorio.add(hashMap);
				
				hashMap = new HashMap<String, Object>();
				
			}else if(!itNota.hasNext()){
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				
				Disciplina disciplina = disciplinaDAO.consultar(nota.getIdDisciplina());
				
				hashMap.put("disciplina", disciplina.getDsDisciplina());
				
				if(tpNota == 1){
					hashMap.put("notaTeste", nota.getNota());
				}else if(tpNota == 2){
					hashMap.put("notaProva", nota.getNota());
				}else{
					hashMap.put("notaTrabalho", nota.getNota());
				}
				
				Double media = Double.sum((Double)hashMap.get("notaTeste"), (Double)hashMap.get("notaProva"));
				media = Double.sum(media, (Double)hashMap.get("notaTrabalho"));
				media = media / 3;
				
				DecimalFormat df = new DecimalFormat("###,##0.00");
				String valorFormatado = df.format(media);
				
				hashMap.put("notaFinal", valorFormatado);
				
				camposRelatorio.add(hashMap);
			}
			
			hashMap.put("matricula", arrayChave[0]);
			hashMap.put("nome", arrayChave[1]);
			hashMap.put("turma", arrayChave[3]);		
			
			if(tpNota == 1){
				hashMap.put("notaTeste", nota.getNota());
			}else if(tpNota == 2){
				hashMap.put("notaProva", nota.getNota());
			}else{
				hashMap.put("notaTrabalho", nota.getNota());
			}
			
			idDisciplinaAux = idDisciplina;
		}
		
		return camposRelatorio;
	}*/
}
