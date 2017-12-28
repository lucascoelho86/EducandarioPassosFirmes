package br.com.educandariopassosfirmes.documentos;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.entidades.Turma;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 
public class RelacaoAlunosTurma{
	
	public JasperPrint gerar(String pTurma, String pChave){
		
		JasperPrint print = null;
		
        try {
        	ArrayList<HashMap<String, Object>> colecaoDados = new ArrayList<HashMap<String, Object>>();
        	HashMap<String, Object> hashDados = new HashMap<String, Object>();
        	String arrayChave[] = pChave.split(":");
    		
    		String[] valores = new String[arrayChave.length];
    		for(int x = 0; x < arrayChave.length; x++){
    			String valor = arrayChave[x];
    			valores = valor.split(";");
    			hashDados = new HashMap<String, Object>();
    			String matricula = valores[0];
    			String nome = valores[1];
    			
    			TurmaDAO turmaDAO = new TurmaDAO();
    			ArrayList<Turma> consultaTurma = turmaDAO.consultar(pTurma, "", "");
    			Turma turma = consultaTurma.get(0);
    			
    			hashDados.put("TITULO", "RELAÇÃO DE ALUNOS DA TURMA (" + turma.getDsTurma().toUpperCase() + ")");
    			hashDados.put("MATRICULA", matricula);
    			hashDados.put("NOME", nome);
    			colecaoDados.add(hashDados);
    		}
        	
            JasperReport report = JasperCompileManager.compileReport("C:\\Users\\lucas\\RelacaoAlunosTurma.jrxml");
            
            print = JasperFillManager.fillReport(report, new HashMap(),
                    new JRBeanCollectionDataSource(colecaoDados));
            
            //exporta o arquivo para a area de trabalho
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\lucas\\Desktop\\RelacaoAlunos.pdf");
            System.out.println("Relatório gerado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO AO GERAR RELATÓRIO!");
        }
		
		return print;
	}
	
}
