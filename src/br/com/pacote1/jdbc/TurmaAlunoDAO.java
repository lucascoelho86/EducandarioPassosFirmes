package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.TurmaAluno;

public class TurmaAlunoDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(TurmaAluno pTurmaAluno){
		
		String sql = "INSERT INTO TURMA_ALUNO (id_turma, id_aluno) values (?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, pTurmaAluno.getIdTurma());
			preparador.setString(2, pTurmaAluno.getIdAluno());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public List<TurmaAluno> consultar(Integer pTurma, String pMatricula){
			String sql = "SELECT * FROM TURMA_ALUNO ";
			String where = "WHERE ";
			String sql2 = "ID_TURMA = ?";
			String sql3 = "ID_ALUNO = ?";
			String conector = "";
			String sqlComplementar = "";
			TurmaAluno turmaAluno = null;
			List<TurmaAluno> listTurma = new ArrayList<TurmaAluno>();
			int contador=0;
			try{
				
				if(pTurma != null && pTurma != 0){
					sqlComplementar = sql2;
					conector = "\n AND ";
				}
				
				if(pMatricula != null && !pMatricula.equals("")){
					sqlComplementar = sqlComplementar + conector + sql3;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = con.prepareStatement(sql);
				
				if(pTurma != null && pTurma != 0){
					contador++;
					preparador.setInt(contador, pTurma);
				}
				
				if(pMatricula != null && !pMatricula.equals("")){
					contador++;
					preparador.setString(contador, pMatricula);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				while(resultado.next()){
					turmaAluno = new TurmaAluno();
					
					turmaAluno.setIdTurma(resultado.getInt("id_turma"));
					turmaAluno.setIdAluno(resultado.getString("id_aluno"));
					
					listTurma.add(turmaAluno);
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return listTurma;
			
		}
	
}
