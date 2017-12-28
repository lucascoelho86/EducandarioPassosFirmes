package br.com.educandariopassosfirmes.documentos;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.entidades.Aluno;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.Turma;
import br.com.educandariopassosfirmes.util.BibliotecaFormatarDados;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 
public class FrequenciaTurma{
	
	public JasperPrint gerar(String pTurma){
		
		JasperPrint print = null;
		
        try {
        	ArrayList<HashMap<String, Object>> colecaoDados = new ArrayList<HashMap<String, Object>>();
        	HashMap<String, Object> hashDados = new HashMap<String, Object>();
        	
        	TurmaDAO turmaDAO = new TurmaDAO();
        	ArrayList<Turma> consultaTurma = turmaDAO.consultar(pTurma, "", "");
        	Turma turma = consultaTurma.get(0);
        	
        	AlunoDAO alunoDAO = new AlunoDAO();
        	ArrayList<Aluno> consultaAlunos = alunoDAO.consultar("", pTurma, "");
        	
        	if(!consultaAlunos.isEmpty()) {
	    		for(int x = 0; x < consultaAlunos.size(); x++){
	    			Aluno aluno = consultaAlunos.get(x);
	    			hashDados = new HashMap<String, Object>();
	    			PessoaDAO pessoaDAO = new PessoaDAO();
	    			ArrayList<Pessoa> consultaPessoa = pessoaDAO.consultar(aluno.getId(), "");
	    			Pessoa pessoa = consultaPessoa.get(0);
	    			
	    			hashDados.put("TITULO", "CHAMADA DA TURMA (" + turma.getDsTurma().toUpperCase() + ")");
	    			hashDados.put("MATRICULA", BibliotecaFormatarDados.formatarMatricula(pessoa.getId()));
	    			hashDados.put("NOME", pessoa.getNome().toUpperCase());
	    			colecaoDados.add(hashDados);
	    		}
        	}else {
        		hashDados.put("TITULO", "CHAMADA DA TURMA (" + turma.getDsTurma().toUpperCase() + ")");
        		hashDados.put("MATRICULA", "-");
    			hashDados.put("NOME", "NÃO EXISTE ALUNOS MATRÍCULADOS NESTA TURMA");
        		colecaoDados.add(hashDados);
        	}
        	
            JasperReport report = JasperCompileManager.compileReport("C:\\Users\\lucas\\FrequenciaTurma.jrxml");
            
            print = JasperFillManager.fillReport(report, new HashMap(),
                    new JRBeanCollectionDataSource(colecaoDados));
            
            //exporta o arquivo para a area de trabalho
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\lucas\\Desktop\\FrequenciaTurma" + pTurma + ".pdf");
            System.out.println("Relatório gerado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO AO GERAR RELATÓRIO!");
        }
		
		return print;
	}
	
}
